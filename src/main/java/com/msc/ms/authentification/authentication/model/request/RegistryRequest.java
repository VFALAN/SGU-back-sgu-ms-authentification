package com.msc.ms.authentification.authentication.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegistryRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Middle name is required")
    private String middleName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "birth date is required")
    private Date birthDate;
    @NotBlank(message = "gender is required")
    private String gender;
    @NotBlank(message = "Age is required")
    private int age;
}
