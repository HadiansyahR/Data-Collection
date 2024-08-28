package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Role;
import com.track_and_trace.restful_application.entity.SubRole;
import com.track_and_trace.restful_application.model.request.CreateSubRoleRequest;
import com.track_and_trace.restful_application.model.response.SubRoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateSubRoleRequest;
import com.track_and_trace.restful_application.repository.RoleRepository;
import com.track_and_trace.restful_application.repository.SubRoleRepository;
import com.track_and_trace.restful_application.service.SubRoleService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class SubRoleServiceImpl implements SubRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SubRoleRepository subRoleRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public SubRoleResponse responseBuilder(SubRole entity) {
        return SubRoleResponse
                .builder()
                .idRole(entity.getRole().getIdRole())
                .idSubRole(entity.getIdSubRole())
                .subRoleName(entity.getSubRoleName())
                .build();
    }

    @Transactional
    @Override
    public SubRoleResponse create(CreateSubRoleRequest request) {
        validationService.validate(request);

        Role role = roleRepository.findById(request.getIdRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        SubRole subRole = new SubRole();
        subRole.setRole(role);
        subRole.setSubRoleName(request.getSubRoleName());
        subRoleRepository.save(subRole);

        return responseBuilder(subRole);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SubRoleResponse> findAll() {
        List<SubRole> subRoles = subRoleRepository.findAll();

        return subRoles.stream().map(this::responseBuilder).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SubRoleResponse> findAllByRole(int idRole, int limit, int offset) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<SubRole> subRoles = subRoleRepository.findAllByRole(role, pageable);
        List<SubRoleResponse> subRoleResponses = subRoles.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(subRoleResponses, pageable, subRoles.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public SubRoleResponse findById(int idRole, int idSubRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        SubRole subRole = subRoleRepository.findByRoleAndIdSubRole(role, idSubRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        return responseBuilder(subRole);
    }

    @Transactional
    @Override
    public void delete(int idRole, int idSubRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        SubRole subRole = subRoleRepository.findByRoleAndIdSubRole(role, idSubRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        subRoleRepository.delete(subRole);
    }

    @Transactional
    @Override
    public SubRoleResponse update(UpdateSubRoleRequest updateSubRoleRequest) {
        validationService.validate(updateSubRoleRequest);

        Role role = roleRepository.findById(updateSubRoleRequest.getIdRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));

        SubRole subRole = subRoleRepository.findById(updateSubRoleRequest.getIdSubRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        subRole.setRole(role);
        subRole.setSubRoleName(updateSubRoleRequest.getSubRoleName());
        subRoleRepository.save(subRole);

        return responseBuilder(subRole);
    }
}
