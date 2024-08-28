package com.track_and_trace.restful_application.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuPermissionResponse {

    private int idMenuPermission;

    private int idMenu;

    private String menuName;

    private String labelMenu;

    private int idSubRole;

    private int createPermission;

    private int viewPermission;

    private int updatePermission;

    private int deletePermission;

}
