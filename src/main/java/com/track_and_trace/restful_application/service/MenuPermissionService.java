package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.MenuPermission;
import com.track_and_trace.restful_application.model.request.CreateMenuPermissionRequest;
import com.track_and_trace.restful_application.model.response.MenuPermissionResponse;
import com.track_and_trace.restful_application.model.request.UpdateMenuPermissionRequest;

import java.util.List;

public interface MenuPermissionService extends CrudService<MenuPermission, MenuPermissionResponse>{
    MenuPermissionResponse create(CreateMenuPermissionRequest request);
    MenuPermissionResponse find(int idMenu, int idSubRole);
//    Page<MenuPermissionResponse> findAll(int limit, int offset);
    List<MenuPermissionResponse> findAll();
    void delete(int idMenu, int idSubRole);
    MenuPermissionResponse update(UpdateMenuPermissionRequest request);
}
