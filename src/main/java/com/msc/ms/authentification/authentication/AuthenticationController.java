package com.msc.ms.authentification.authentication;

import com.msc.ms.authentification.authentication.error.MscAuthenticationException;
import com.msc.ms.authentification.authentication.model.request.LoginRequestDTO;
import com.msc.ms.authentification.authentication.model.request.RegistryRequest;
import com.msc.ms.authentification.authentication.model.response.LoginResponseDTO;
import com.msc.ms.authentification.authentication.model.response.RegistryResponse;
import com.msc.ms.authentification.user.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO pLoginRequest) throws MscAuthenticationException {
        return ResponseEntity.ok(authenticationService.login(pLoginRequest));
    }

    @PostMapping("/registry")
    public ResponseEntity<RegistryResponse> registry(@RequestBody @Valid RegistryRequest pRegistryRequest) {
        return ResponseEntity.ok(new RegistryResponse());
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
