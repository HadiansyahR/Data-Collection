package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Role;
import com.track_and_trace.restful_application.model.request.CreateRoleRequest;
import com.track_and_trace.restful_application.model.response.RoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateRoleRequest;
import com.track_and_trace.restful_application.repository.RoleRepository;
import com.track_and_trace.restful_application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public RoleResponse create(CreateRoleRequest request) {
        validationService.validate(request);

        Role role = new Role();
        role.setRoleName(request.getRoleName());
        role.setRoleStatus(request.getRoleStatus());
        roleRepository.save(role);

        return responseBuilder(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<RoleResponse> findAll(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<Role> roles= roleRepository.findAll(pageable);
        List<RoleResponse> roleResponses = roles.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(roleResponses, pageable, roles.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public RoleResponse findById(int id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        return responseBuilder(role);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        roleRepository.delete(role);
    }

    @Override
    public RoleResponse responseBuilder(Role entity) {
        return RoleResponse
                .builder()
                .idRole(entity.getIdRole())
                .roleName(entity.getRoleName())
                .roleStatus(entity.getRoleStatus())
                .build();
    }

    @Transactional
    @Override
    public RoleResponse update(UpdateRoleRequest updateRoleRequest) {
        validationService.validate(updateRoleRequest);

        Role role = roleRepository.findById(updateRoleRequest.getIdRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        role.setRoleName(updateRoleRequest.getRoleName());
        role.setRoleStatus(updateRoleRequest.getRoleStatus());
        roleRepository.save(role);

        return responseBuilder(role);
    }
}
