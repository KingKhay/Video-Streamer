package com.khaydev.videostream.app.event;

import com.khaydev.videostream.app.model.Email;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RegistrationSuccessListener {

    @Value("${mail.from}")
    private String emailFrom;

    private final EmailService emailService;
    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMMIT)
    @Async
    public void onRegistrationSuccess(RegistrationSuccessEvent registrationSuccessEvent) throws MessagingException, IOException {

        final var user = (User) registrationSuccessEvent.getSource();

        Email email = Email.builder()
                .from(emailFrom)
                .to(user.getEmail())
                .subject("Welcome to VideoStreamer")
                .build();

        emailService.sendHtmlMail(email, user.getUsername());
    }
}
