package com.khaydev.videostream.app.service.passwordreset;

import com.khaydev.videostream.app.model.PasswordResetToken;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.PasswordTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetImpl implements PasswordResetService{

    private final PasswordTokenRepository passwordTokenRepository;
    @Override
    public void createResetPasswordToken(User user, String token) {

        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(passwordResetToken);
    }
}
