package com.khaydev.videostream.app.service.auth;


import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.dto.LoginResponse;
import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.event.RegistrationSuccessEvent;
import com.khaydev.videostream.app.exception.user.UserAlreadyExistException;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.RoleRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.service.CustomUserDetails;
import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class IAuthService implements AuthService{
    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final ApplicationEventPublisher publisher;


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
}
