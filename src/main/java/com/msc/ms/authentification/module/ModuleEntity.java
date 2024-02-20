package com.msc.ms.authentification.module;

import com.msc.ms.authentification.common.model.entity.AuditEntity;
import com.msc.ms.authentification.moduleprofile.ModuleProfileEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "CMODULE")
@Entity
@Data
public class ModuleEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODULE")
    private Integer idModule;
    @Column(name = "PATH")
    private String path;
    @Column(name = "NAME")
    private String key;


    @OneToMany(mappedBy = "module")
    private List<ModuleProfileEntity> moduleProfileList;
}
