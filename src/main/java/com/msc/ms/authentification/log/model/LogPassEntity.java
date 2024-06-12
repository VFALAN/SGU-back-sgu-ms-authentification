package com.msc.ms.authentification.log.model;

import com.msc.ms.authentification.common.model.entity.AuditEntity;
import com.msc.ms.authentification.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "TLOG_PASS")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogPassEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOG_PASS")
    private Integer idLogPass;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EXPIRED")
    private Boolean expired;

    @JoinColumn(name = "ID_USER")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity idUser;


}
