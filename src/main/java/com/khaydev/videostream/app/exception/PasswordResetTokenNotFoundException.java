package com.khaydev.videostream.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordResetTokenNotFoundException extends RuntimeException{

    public PasswordResetTokenNotFoundException() {
    }

    public PasswordResetTokenNotFoundException(String message) {
        super(message);
    }
}
