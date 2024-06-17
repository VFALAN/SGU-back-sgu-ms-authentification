package com.msc.ms.authentification;

import com.msc.ms.authentification.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest



@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private  UserService userService;
public UserServiceTest(){

}



    @Test
    public void testIsEmailAlreadyUser() {
        final var mEmailStrigExisting = "alanvillafan95@gmail.com";
        final var mEmailStrigNoExisting = "noexistingemail@gmail.com";

        final var existingResult = userService.validIsEmailACurrentUser(mEmailStrigExisting);
        final var noExistingResult = userService.validIsEmailACurrentUser(mEmailStrigNoExisting);
        Assert.assertTrue(existingResult);
        Assert.assertFalse(noExistingResult);
    }

    @Test
    public void testIsUsernameAlreadyUser() {
        final var mUsernameExistingString = "vifaAdmin";
        final var mUsernameNoExistingString = "no-existing-username";
        final var existingResult = userService.validIsUsernameACurrentUser(mUsernameExistingString);
        final var noExistingResult = userService.validIsUsernameACurrentUser(mUsernameNoExistingString);
        Assert.assertFalse(noExistingResult);
        Assert.assertTrue(existingResult);
    }
}
