package com.msc.ms.authentification.user;

import com.msc.ms.authentification.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName(String username);

    Optional<UserEntity> findByEmail(String email);
}
