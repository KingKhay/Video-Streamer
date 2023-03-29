package com.khaydev.videostream.app.service.email;

import com.khaydev.videostream.app.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements  EmailService{

    private final JavaMailSender emailSender;

    @Override
    public void sendHtmlMail(Email email,String username) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom(email.getFrom());
        message.setRecipients(MimeMessage.RecipientType.TO, email.getTo());
        message.setSubject(email.getSubject());

        File htmlFile = new ClassPathResource("data/mail.html").getFile();

        String htmlTemplate = new String(Files.readAllBytes(htmlFile.toPath()));

        htmlTemplate = htmlTemplate.replace("${name}", username);

        message.setContent(htmlTemplate, "text/html; charset=UTF-8");

        emailSender.send(message);
    }
}
