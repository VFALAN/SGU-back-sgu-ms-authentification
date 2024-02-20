package com.msc.ms.authentification.log.authentication;

import com.msc.ms.authentification.log.authentication.model.LoginRequestDTO;
import com.msc.ms.authentification.jwt.JwtService;
import com.msc.ms.authentification.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public String login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        UserDetails user = userService.buildUserDetails(loginRequestDTO.getUsername());
        final var hash = new HashMap<String,String>();
        hash.put("DashBoard","General");
        return jwtService.getToken(hash, user);
    }


}
