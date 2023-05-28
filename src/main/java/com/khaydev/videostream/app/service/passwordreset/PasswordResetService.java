package com.khaydev.videostream.app.service.passwordreset;

import com.khaydev.videostream.app.model.User;

public interface PasswordResetService {

    void createResetPasswordToken(User user, String token);
}
