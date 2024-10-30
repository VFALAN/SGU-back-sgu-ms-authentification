package com.msc.ms.authentification.authentication.error;

import lombok.Getter;

@Getter
public class DisabledUserException extends MscAuthenticationException {
    public DisabledUserException() {
        super("A001", "User has been disabled");
    }
}
