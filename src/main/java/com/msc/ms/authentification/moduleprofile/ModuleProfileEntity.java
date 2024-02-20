package com.msc.ms.authentification.moduleprofile;

import com.msc.ms.authentification.common.model.entity.AuditEntity;
import com.msc.ms.authentification.module.ModuleEntity;
import com.msc.ms.authentification.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Table(name = "TMODULE_PROFILE")
@Entity
@Data
public class ModuleProfileEntity extends AuditEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODULE_PROFILE")
    private Integer idModuleProfile;
    @ManyToOne
    @JoinColumn(name = "ID_PROFILE")
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "ID_MODULE")
    private ModuleEntity module;

}
