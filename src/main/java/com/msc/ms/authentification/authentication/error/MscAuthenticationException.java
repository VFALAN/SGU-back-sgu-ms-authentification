package com.msc.ms.authentification.authentication.error;

import lombok.Getter;

@Getter
public abstract class MscAuthenticationException extends Exception {
    private String code;

    public MscAuthenticationException(final String code, final String message) {
        super(message);
        this.code = code;
    }
}
