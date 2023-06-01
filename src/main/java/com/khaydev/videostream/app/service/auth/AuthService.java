package com.khaydev.videostream.app.service.auth;

import com.khaydev.videostream.app.dto.*;
import com.khaydev.videostream.app.model.User;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface AuthService {

    RegisterResponse register(User user) throws MessagingException, IOException;


    LoginResponse login(LoginDTO loginDTO);

    String resetPassword(PasswordResetEmailRequest passwordResetEmail) throws MessagingException, IOException;

    String saveResetPassword(PasswordDto passwordDto);
}
