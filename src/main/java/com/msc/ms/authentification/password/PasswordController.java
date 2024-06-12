package com.msc.ms.authentification.password;

import com.msc.ms.authentification.crypto.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {
    private final PasswordGenerator passwordGenerator;
    private final CryptoService cryptoService;

    @GetMapping
    public ResponseEntity<String> generateNewPassword(@RequestParam("size") int pSize) throws Exception {
        final var password = passwordGenerator.getRandomPassword(25, 25, 25, 25, pSize);
        return ResponseEntity.ok(cryptoService.encrypt(password));
    }
}
