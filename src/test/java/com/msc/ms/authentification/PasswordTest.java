package com.msc.ms.authentification;

import com.msc.ms.authentification.log.authentication.AuthenticationService;
import com.msc.ms.authentification.log.authentication.model.LoginRequestDTO;
import com.msc.ms.authentification.password.PasswordGenerator;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Slf4j


@RunWith(SpringRunner.class)
class PasswordTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordGenerator passwordGenerator;

    @Test
    void validAuthenticationEmail() {

        final var request = LoginRequestDTO.builder().username("alanvillafan95@gmail.com").password("Hola123$").build();
        final var token = this.authenticationService.login(request);
        log.info(token);
        Assert.hasText(token);
    }
    @Test
    void validAuthenticationUsername() {

        final var request = LoginRequestDTO.builder().username("vifaAdmin").password("Hola123$").build();
        final var token = this.authenticationService.login(request);
        log.info(token);
        Assert.hasText(token);
    }
    @Test
    void validAuthenticationFail() {

       try {
           final var request = LoginRequestDTO.builder().username("vifaAdmin").password("wrongPAss").build();
           this.authenticationService.login(request);
       }catch (BadCredentialsException e ){
           Assert.isTrue(true);
       }

    }

    @Test
    void testPassword() {
        final var passwordUnEncrypted = "Hola123$";
        final var passwordEncrypted = this.passwordEncoder.encode(passwordUnEncrypted);
        log.info("{}:{}", passwordUnEncrypted, passwordEncrypted);
        Assert.isTrue(passwordEncoder.matches(passwordUnEncrypted, passwordEncrypted));
    }

    @Test
    void generationPassword(){
        final var passwordLength = 100;
        final var password = this.passwordGenerator.getRandomPassword(10,25,25,25,passwordLength);
        log.info("password generated: {}",password);
        Assert.isTrue(password.length()==passwordLength);
    }
}
