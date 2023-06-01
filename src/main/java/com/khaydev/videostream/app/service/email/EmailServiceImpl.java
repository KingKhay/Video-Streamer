package com.khaydev.videostream.app.service.email;

import com.khaydev.videostream.app.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements  EmailService{

    private final JavaMailSender emailSender;

    @Override
    public void sendHtmlMail(Email email,String username) throws MessagingException, IOException {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("${name}", username);
        sendHTMLMail(email, "data/user_registration.html", placeholders);
    }

    @Override
    public void sendPasswordResetMail(Email email, String token) throws MessagingException, IOException {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("${token}", token);
        sendHTMLMail(email, "data/password_reset.html", placeholders);
    }

    public void sendHTMLMail(Email email, String templateFile, Map<String, String> placeholders) throws MessagingException, IOException {
        MimeMessage message;
        message = emailSender.createMimeMessage();

        message.setFrom(email.getFrom());
        message.setRecipients(MimeMessage.RecipientType.TO, email.getTo());
        message.setSubject(email.getSubject());

        File htmlFile = new ClassPathResource("data/password_reset.html").getFile();
        String htmlTemplate = new String(Files.readAllBytes(htmlFile.toPath()));

        for(Map.Entry<String, String> placeholder : placeholders.entrySet()){
            String placeholderKey = placeholder.getKey();
            String placeholderValue = placeholder.getValue();
            htmlTemplate = htmlTemplate.replace(placeholderKey, placeholderValue);
        }

        message.setContent(htmlTemplate, "text/html; charset=UTF-8");

        emailSender.send(message);
    }
}
