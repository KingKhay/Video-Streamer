package com.khaydev.videostream.app.service.auth;


import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.dto.LoginResponse;
import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.model.Email;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.RoleRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.service.CustomUserDetails;
import com.khaydev.videostream.app.service.email.EmailService;
import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class IAuthService implements AuthService{

    @Value("${mail.from}")
    private String emailFrom;

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final String roleUser = "ROLE_USER";

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    @Override
    public RegisterResponse register(User user) throws MessagingException, IOException {
        Role userRole = roleRepository.getRoleByName(roleUser);
        user.getRoles().add(userRole);
        user.setPassword(encoder.encode(user.getPassword()));

        repository.save(user);

        Email email = Email.builder()
                .from(emailFrom)
                .to(user.getEmail())
                .subject("Welcome to VideoStreamer")
                .build();

        emailService.sendHtmlMail(email);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .message("user saved successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
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
        String token = jwtUtils.createJwt(userDetails.getUsername(), roles);

        return new LoginResponse(userDetails.getUsername(),token);
    }
}
