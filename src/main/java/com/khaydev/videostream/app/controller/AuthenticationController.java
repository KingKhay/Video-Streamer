package com.khaydev.videostream.app.controller;

import com.khaydev.videostream.app.dto.*;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.service.auth.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthenticationController {

    private final AuthService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody User user) throws MessagingException, IOException {
        return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(service.login(loginDTO));
    }

    @PostMapping("/user/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetEmailRequest passwordResetEmailRequest) throws MessagingException, IOException {
        return ResponseEntity.ok(service.resetPassword(passwordResetEmailRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/user/savePassword")
    public ResponseEntity<String> saveResetPassword(@Valid @RequestBody PasswordDto passwordDto){
        return ResponseEntity.ok(service.saveResetPassword(passwordDto));
    }
}
