package com.msc.ms.authentification.authentication.error;

import lombok.Getter;

@Getter
public class UsernameOrEmailInvalidException extends MscAuthenticationException {
    private   String usernameOrEmail;


    public UsernameOrEmailInvalidException(String pUsernameOrEmail) {
        super("A003","The username or email is invalid: ");
        this.usernameOrEmail = pUsernameOrEmail;
    }

}
