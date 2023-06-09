package com.khaydev.videostream.app.exception;

import com.khaydev.videostream.app.exception.user.UserAlreadyExistException;
import com.khaydev.videostream.app.exception.user.UserError;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.exception.video.VideoError;
import com.khaydev.videostream.app.exception.video.VideoNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({VideoNotFoundException.class})
    protected ResponseEntity<Object> handleVideoNotFoundException(VideoNotFoundException ex){
        VideoError error = new VideoError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
        UserError error = new UserError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        UserError error = new UserError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.CONFLICT.toString());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    protected ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex){
        UserError error = new UserError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.CONFLICT.toString());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        UserError error = new UserError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(PasswordResetTokenNotFoundException.class)
    protected ResponseEntity<Object> handlePasswordResetTokenNotFoundException(PasswordResetTokenNotFoundException ex) {
        UserError error = new UserError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordResetTokenNotValid.class)
    protected ResponseEntity<Object> handlePasswordResetTokenNotValidException(PasswordResetTokenNotValid ex) {
        UserError error = new UserError();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.toString());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
