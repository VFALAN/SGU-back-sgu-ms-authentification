package com.msc.ms.authentification.authentication;

import com.msc.ms.authentification.authentication.model.LoginRequestDTO;
import com.msc.ms.authentification.authentication.model.LoginResponseDTO;
import com.msc.ms.authentification.user.UserService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO pLoginRequest) {
        return ResponseEntity.ok(authenticationService.login(pLoginRequest));
    }

    @GetMapping("validEmailIsUser")
    public ResponseEntity<Boolean> validEmailIsUser(@RequestParam("email") String pEmail) {
        return ResponseEntity.ok(userService.validIsEmailACurrentUser(pEmail));
    }

    @GetMapping("validUsernameIsUser")
    public ResponseEntity<Boolean> validUsernameIsUser(@RequestParam("username") String pUsername) {
        return ResponseEntity.ok(userService.validIsEmailACurrentUser(pUsername));
    }
}
