package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.Role;
import com.track_and_trace.restful_application.model.request.CreateRoleRequest;
import com.track_and_trace.restful_application.model.response.RoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateRoleRequest;
import org.springframework.data.domain.Page;

public interface RoleService extends CrudService<Role, RoleResponse>{
    RoleResponse create(CreateRoleRequest request);
    Page<RoleResponse> findAll(int limit, int offset);
    RoleResponse findById(int id);
    void delete(int id);
    RoleResponse update(UpdateRoleRequest updateRoleRequest);
}
