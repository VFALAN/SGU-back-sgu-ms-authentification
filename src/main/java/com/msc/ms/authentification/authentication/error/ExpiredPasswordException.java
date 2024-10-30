package com.msc.ms.authentification.authentication.error;

import lombok.Getter;

@Getter
public class ExpiredPasswordException extends MscAuthenticationException {
    private String dateExpired;

    public ExpiredPasswordException(String dateExpired) {
        super("A004","Expired password");
        this.dateExpired = dateExpired;
    }
}
