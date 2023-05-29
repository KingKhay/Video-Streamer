package com.khaydev.videostream.app.service.email;

import com.khaydev.videostream.app.model.Email;
import jakarta.mail.MessagingException;


import java.io.IOException;

public interface EmailService {

    void sendHtmlMail(Email email, String username) throws MessagingException, IOException;

    void sendPasswordResetMail(Email email, String token) throws MessagingException, IOException;
}
