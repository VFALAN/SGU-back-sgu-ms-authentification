package com.msc.ms.authentification.profile;

import com.msc.ms.authentification.common.model.entity.AuditEntity;
import com.msc.ms.authentification.moduleprofile.ModuleProfileEntity;
import com.msc.ms.authentification.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "TPROFILE")
@Entity
@Data
public class ProfileEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROFILE")
    private Integer idProfile;
    @Column(name = "NAME")
    private String name;
    @Column(name = "KEY_PROFILE")
    private String key;
    @OneToMany(mappedBy = "profile")
    private List<UserEntity> userEntityList;
    @OneToMany(mappedBy = "profile")
    private List<ModuleProfileEntity> moduleProfileList;
}
