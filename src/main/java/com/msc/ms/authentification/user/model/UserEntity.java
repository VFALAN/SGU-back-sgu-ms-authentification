package com.msc.ms.authentification.user.model;

import com.msc.ms.authentification.common.model.entity.AuditEntity;
import com.msc.ms.authentification.log.model.LogPassEntity;
import com.msc.ms.authentification.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Table(name = "TUSER")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AuditEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    @Basic(optional = false)
    private Integer idUser;
    @Column(name = "NAME")
    @Basic(optional = false)
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USER_NAME")
    private String userName;

    @JoinColumn(name = "PROFILE")
    @ManyToOne
    private ProfileEntity profile;
    @OneToMany(mappedBy = "idUser")
    private List<LogPassEntity> logPassList;



}
