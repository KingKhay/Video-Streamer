package com.khaydev.videostream.app.service.auth;


import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.dto.LoginResponse;
import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.RoleRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.service.CustomUserDetails;
import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IAuthService implements AuthService{

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final String roleUser = "ROLE_USER";

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(User user) {
        Role userRole = roleRepository.getRoleByName(roleUser);
        user.getRoles().add(userRole);
        user.setPassword(encoder.encode(user.getPassword()));

        repository.save(user);

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
