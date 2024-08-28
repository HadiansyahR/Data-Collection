package com.track_and_trace.restful_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_permission")
public class MenuPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu_permission")
    private int idMenuPermission;

    @ManyToOne
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "id_sub_role", referencedColumnName = "id_sub_role")
    private SubRole subRole;

    @Column(name = "create_permission")
    private int createPermission;

    @Column(name = "update_permission")
    private int updatePermission;

    @Column(name = "delete_permission")
    private int deletePermission;

    @Column(name = "view_permission")
    private int viewPermission;

    public MenuPermission(Menu menu, SubRole subRole, int createPermission, int updatePermission, int deletePermission, int viewPermission) {
        this.menu = menu;
        this.subRole = subRole;
        this.createPermission = createPermission;
        this.updatePermission = updatePermission;
        this.deletePermission = deletePermission;
        this.viewPermission = viewPermission;
    }
}
