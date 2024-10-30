package com.msc.ms.authentification.authentication.error;

import lombok.Getter;

@Getter
public class WrongPasswordException extends MscAuthenticationException {
    public WrongPasswordException() {
        super("A002", "Wrong Password");
    }
}
