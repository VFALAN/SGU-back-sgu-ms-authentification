package com.msc.ms.authentification.user;

import com.msc.ms.authentification.log.ILogPassRepository;
import com.msc.ms.authentification.log.model.LogPassEntity;
import com.msc.ms.authentification.user.model.UserDetailDTO;
import com.msc.ms.authentification.user.model.UserEntity;
import com.msc.ms.authentification.user.model.UserRequest;
import com.msc.ms.authentification.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository iUserRepository;
    private final ILogPassRepository iLogPassRepository;


    public UserResponse registryNewUser(UserRequest pUSerRequest) {
        return null;
    }

    public UserEntity findUser(String pEmailOrUsername) {
        var optionalUser = this.iUserRepository.findByUserName(pEmailOrUsername);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            optionalUser = this.iUserRepository.findByEmail(pEmailOrUsername);
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            } else {
                throw new IllegalArgumentException("No User found for username or email: " + pEmailOrUsername);
            }
        }
    }

    public UserDetailDTO buildUserDetails(String username) {
        log.info("trying to log in for user {}", username);
        final var user = this.findUser(username);
        final var logPassword = getPassword(user);
        // cargar los modulos del perfil

        return new UserDetailDTO(user.getUserName(), logPassword.getPassword(), user.getActive(), logPassword.getExpired(), List.of("test"));
    }

    public Boolean validIsUsernameACurrentUser(String username) {
        final var mOptionalUserEntity = iUserRepository.findByUserName(username);
        return mOptionalUserEntity.isPresent();
    }

    public Boolean validIsEmailACurrentUser(String pEmail) {
        final var mOptionUserEntity = iUserRepository.findByEmail(pEmail);
        return mOptionUserEntity.isPresent();
    }

    protected LogPassEntity getPassword(UserEntity userEntity) {
        return this.iLogPassRepository.finaAllByUser(userEntity).getFirst();
    }

}