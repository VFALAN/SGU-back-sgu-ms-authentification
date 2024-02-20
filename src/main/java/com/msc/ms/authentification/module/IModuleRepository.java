package com.msc.ms.authentification.module;

import com.msc.ms.authentification.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModuleRepository extends JpaRepository<ModuleEntity,Integer> {
    @Query("select m from ModuleEntity m inner join ModuleProfileEntity mp where mp.profile =:profile and m.active=true ")
    List<ModuleEntity> loadModulesByProfile(@Param("profile")ProfileEntity profile);
}
