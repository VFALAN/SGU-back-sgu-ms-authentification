package com.msc.ms.authentification.log.authentication;

import com.msc.ms.authentification.log.authentication.model.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO pLoginRequest) {
        return ResponseEntity.ok(authenticationService.login(pLoginRequest));
    }
}
