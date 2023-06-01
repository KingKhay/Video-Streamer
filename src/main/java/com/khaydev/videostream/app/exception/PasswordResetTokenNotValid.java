package com.khaydev.videostream.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordResetTokenNotValid extends RuntimeException{
    public PasswordResetTokenNotValid() {
    }

    public PasswordResetTokenNotValid(String message) {
        super(message);
    }
}
