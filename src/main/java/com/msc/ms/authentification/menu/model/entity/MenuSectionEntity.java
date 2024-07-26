package com.msc.ms.authentification.menu.model.entity;

import com.msc.ms.authentification.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "TMENU_SECTION")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuSectionEntity {
    @Id
    @Column(name = "ID_MENU_SECTION")
    private Integer idMenuSection;
    @Column(name = "HAS_PATH")
    private Boolean hasPath;
    @Column(name = "PATH")
    private String path;
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "ID_PROFILE")
    @ManyToOne
    private ProfileEntity profile;
    @Column(name = "PARENT")
   private Integer idParentMenu;

}
