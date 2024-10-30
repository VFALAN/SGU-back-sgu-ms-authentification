package com.msc.ms.authentification.authentication.error;

import lombok.Getter;

@Getter
public class NoPasswordHistoryException extends MscAuthenticationException {
    private String username;

    public NoPasswordHistoryException(String username) {
        super("A005", "No password history for user " + username);
        this.username = username;
    }
}
