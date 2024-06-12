package com.msc.ms.authentification.authentication.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginRequestDTO implements Serializable {
    private String username;
    private String password;
}
