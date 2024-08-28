package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Menu;
import com.track_and_trace.restful_application.entity.MenuPermission;
import com.track_and_trace.restful_application.entity.SubRole;
import com.track_and_trace.restful_application.model.response.MenuPermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuPermissionRepository extends JpaRepository<MenuPermission, Integer> {
    Optional<MenuPermission> findByMenuAndSubRole(Menu menu, SubRole subRole);

    @Query("SELECT new com.track_and_trace.restful_application.model.response.MenuPermissionResponse (COALESCE(mp.idMenuPermission, 0) as idMenuPermission, " +
            "m.idMenu, m.menuName, m.labelMenu, COALESCE(mp.subRole.idSubRole, 0)  as idSubRole, COALESCE(mp.createPermission, 0) as createPermission, COALESCE(mp.viewPermission, 0) as viewPermission, " +
            "COALESCE(mp.updatePermission, 0) as updatePermission, COALESCE(mp.deletePermission, 0) as deletePermission) " +
            "FROM Menu m LEFT JOIN MenuPermission mp ON m.idMenu = mp.menu.idMenu")
    List<MenuPermissionResponse> getAllMenuPermissions();
}
