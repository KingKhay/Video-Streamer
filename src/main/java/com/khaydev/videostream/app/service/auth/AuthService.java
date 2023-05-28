package com.khaydev.videostream.app.service.auth;

import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.dto.LoginResponse;
import com.khaydev.videostream.app.dto.PasswordResetEmailRequest;
import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.model.User;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface AuthService {

    RegisterResponse register(User user) throws MessagingException, IOException;


    LoginResponse login(LoginDTO loginDTO);

    String resetPassword(PasswordResetEmailRequest passwordResetEmail);
}
