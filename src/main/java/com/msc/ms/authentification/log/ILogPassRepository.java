package com.msc.ms.authentification.log;

import com.msc.ms.authentification.log.model.LogPassEntity;
import com.msc.ms.authentification.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILogPassRepository extends JpaRepository<LogPassEntity, Integer> {
    @Query("select l from LogPassEntity l where l.idUser =:user order by l.dateCreate desc limit 1")
    LogPassEntity finaAllByUser(@Param("user") UserEntity pUserEntity);
}
