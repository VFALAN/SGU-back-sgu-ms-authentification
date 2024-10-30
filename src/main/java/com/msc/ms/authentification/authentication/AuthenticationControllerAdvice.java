package com.msc.ms.authentification.authentication;

import com.msc.ms.authentification.authentication.error.*;
import com.msc.ms.authentification.common.model.entity.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
@Slf4j
public class AuthenticationControllerAdvice {


    @ExceptionHandler(DisabledUserException.class)
    public ResponseEntity<ErrorResponse> handleDisabledUserException(final DisabledUserException e) {
        log.error("The user is disabled");
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredPasswordException.class)
    public ResponseEntity<ErrorResponse> handleExpiredPasswordException(final ExpiredPasswordException e) {

        log.error("The password has been expired");
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage().concat(" at ").concat(e.getDateExpired())).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoPasswordHistoryException.class)
    public ResponseEntity<ErrorResponse> handleNoPasswordHistoryException(final NoPasswordHistoryException e) {

        log.error("The username: {}  doesn't has a password history", e.getUsername());
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameOrEmailInvalidException.class)
    public ResponseEntity<ErrorResponse> handleUsernameOrEmailInvalidException(final UsernameOrEmailInvalidException e) {

        log.error("The username: {}  doesn't exist", e.getUsernameOrEmail());
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handleENoPasswordHistoryException(final WrongPasswordException e) {

        log.error("The password give doesn't match whit the user");
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }
}
