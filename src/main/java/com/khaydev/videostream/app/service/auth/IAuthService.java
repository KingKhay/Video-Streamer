package com.khaydev.videostream.app.service.auth;


import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.dto.LoginResponse;
import com.khaydev.videostream.app.dto.PasswordResetEmailRequest;
import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.event.RegistrationSuccessEvent;
import com.khaydev.videostream.app.exception.user.UserAlreadyExistException;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.Email;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.RoleRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.service.CustomUserDetails;
import com.khaydev.videostream.app.service.email.EmailService;
import com.khaydev.videostream.app.service.passwordreset.PasswordResetService;
import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class IAuthService implements AuthService{

    @Value("${mail.from}")
    private String emailFrom;

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final ApplicationEventPublisher publisher;

    private final PasswordResetService passwordResetService;

    private final EmailService emailService;


    @Override
    public RegisterResponse register(User user) {
        Optional<User> existingUser = this.repository.findUserByUsername(user.getUsername());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistException("username already exists");
        }
        String roleUser = "ROLE_USER";
        Role userRole = roleRepository.getRoleByName(roleUser);
        user.getRoles().add(userRole);
        user.setPassword(encoder.encode(user.getPassword()));

        repository.save(user);

        publisher.publishEvent(new RegistrationSuccessEvent(user));

        return RegisterResponse.builder()
                .username(user.getUsername())
                .message("user saved successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        try{
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    ));
            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            String token = jwtUtils.createJwt(userDetails.getUsername(), roles, new Date(System.currentTimeMillis() * 10 * 60 * 1000));

            return new LoginResponse(userDetails.getUsername(),token, "login successful");
        }catch(Exception ex){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public String resetPassword(PasswordResetEmailRequest passwordResetEmail) throws MessagingException, IOException {
        User user = repository.findUserByEmail(passwordResetEmail.email())
                .orElseThrow(UserNotFoundException::new);

        String token = UUID.randomUUID().toString();

        //Creates a token and adds it to the database alongside a user
        passwordResetService.createResetPasswordToken(user, token);

        //Logic to send the token to the email provided
        Email email = Email.builder()
                .from(emailFrom)
                .to(passwordResetEmail.email())
                .subject("Reset Password")
                .build();
        emailService.sendPasswordResetMail(email, token);

        return "password reset mail sent successfully";
    }
}
