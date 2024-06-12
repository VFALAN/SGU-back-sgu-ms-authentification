package com.msc.ms.authentification.crypto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CryptoTestService {

    @Autowired
    private CryptoService cryptoService;

    public CryptoTestService() {
    }

    @Test
    public void testEncryptAndDeCrypt() throws Exception {
        final var testString = "msc-users";
        final var textEncrypted = this.cryptoService.encrypt(testString);
        log.info("the string: {} has been encrypted as: {}", testString, textEncrypted);
        final var textDecrypted = this.cryptoService.decrypt(textEncrypted);
        log.info("the encrypted text: {}  has been decrypted as: {}", textEncrypted, textDecrypted);
        List.of("ms-authentication","ms-address","ms-users","ms-file","ms-notification","ms-processor").forEach(s-> {
            try {
                log.info("service: {} key: {}",s,this.cryptoService.encrypt(s));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Assert.assertEquals(testString, textDecrypted);

    }
}
