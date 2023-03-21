package com.khaydev.videostream.app.controller;

import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
    }
}
