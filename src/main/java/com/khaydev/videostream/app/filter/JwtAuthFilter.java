package com.khaydev.videostream.app.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/v1/login") || request.getServletPath().equals("/api/v1/customer/register")){
            filterChain.doFilter(request, response);
        }
    }
}

// else{
//            String headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);
//            if(headerAuthorization!=null && headerAuthorization.startsWith("Bearer ")){
//                String token = headerAuthorization.substring(7);
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT theJwt = verifier.verify(token);
//                String username = theJwt.getSubject();
//                List<String> roles = theJwt.getClaim("roles").asList(String.class);
//                List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                filterChain.doFilter(request,response);
//            }
//        }
