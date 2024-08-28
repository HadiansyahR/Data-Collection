package com.track_and_trace.restful_application.entity;

import com.track_and_trace.restful_application.enums.PgSQLEnumType;
import com.track_and_trace.restful_application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master_account")
@TypeDef(
        name = "account_status",
        typeClass = PgSQLEnumType.class
)
public class Account {

    @Id
    private String username;

    private String password;

    @Column(name = "nameaccount")
    private String nameAccount;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_sub_role", referencedColumnName = "id_sub_role")
    private SubRole subRole;

    @Enumerated(EnumType.STRING)
    @Type(type = "account_status")
    @Column(name = "status")
    private Status status;

}
