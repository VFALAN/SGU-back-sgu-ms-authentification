package com.msc.ms.authentification.menu.repository;

import com.msc.ms.authentification.menu.model.entity.MenuSectionEntity;
import com.msc.ms.authentification.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuSectionRepository extends JpaRepository<MenuSectionEntity, Integer> {
    @Query("SELECT m FROM MenuSectionEntity m where m.profile = :profile AND m.idParentMenu IS NULL")
    List<MenuSectionEntity> findAllTopByProfile(@Param("profile") ProfileEntity profileEntity);

    @Query("SELECT m FROM MenuSectionEntity m where m.idParentMenu = :parent")
    List<MenuSectionEntity> findAllByParent(@Param("parent") Integer pParentId);
}
