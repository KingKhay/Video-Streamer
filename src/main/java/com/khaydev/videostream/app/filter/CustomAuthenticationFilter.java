package com.khaydev.videostream.app.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager1, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager1;
        this.jwtUtils = jwtUtils;

        setFilterProcessesUrl("/api/login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDTO credentials = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username,password,null);

        return this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User principal = (User) authResult.getPrincipal();
        List<String> roles = principal.getRoles()
                .stream()
                .map(Role::getName)
                .toList();
        String accessToken = this.jwtUtils.createJwt(principal.getUsername(), roles);

        response.setContentType("application/json");

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("username", principal.getUsername());
        responseBody.put("access-token", accessToken);

        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }
}
