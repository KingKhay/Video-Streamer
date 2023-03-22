package com.khaydev.videostream.app.filter;

import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/register")){
            filterChain.doFilter(request, response);
        }
        else{
            String headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(headerAuthorization == null || !headerAuthorization.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }
                String token = headerAuthorization.substring(7);
                String username = this.jwtUtils.extractUsernameFromJwt(token);
                List<String> roles = this.jwtUtils.extractRolesFromJwt(token);

                List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request,response);
        }
        filterChain.doFilter(request,response);
    }
}


