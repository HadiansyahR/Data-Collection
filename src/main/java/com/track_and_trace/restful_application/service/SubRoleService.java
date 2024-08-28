package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.SubRole;
import com.track_and_trace.restful_application.model.request.CreateSubRoleRequest;
import com.track_and_trace.restful_application.model.response.SubRoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateSubRoleRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubRoleService extends CrudService<SubRole, SubRoleResponse>{
    SubRoleResponse create(CreateSubRoleRequest request);
    Page<SubRoleResponse> findAllByRole(int id, int limit, int offset);
    List<SubRoleResponse> findAll();
    SubRoleResponse findById(int idRole, int idSubRole);
    void delete(int idRole, int idSubRole);
    SubRoleResponse update(UpdateSubRoleRequest updateSubRoleRequest);
}
