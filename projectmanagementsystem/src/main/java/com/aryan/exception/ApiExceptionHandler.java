package com.aryan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<> (errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {

        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;

        ApiException apiException = new ApiException(e.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );
        return new ResponseEntity<>(apiException, unauthorized);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(e.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<Object> handleMailSendException(MailSendException e) {

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiException apiException = new ApiException(e.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );
        return new ResponseEntity<>(apiException, internalServerError);
    }

}
