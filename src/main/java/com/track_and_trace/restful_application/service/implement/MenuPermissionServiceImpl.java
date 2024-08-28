package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Menu;
import com.track_and_trace.restful_application.entity.MenuPermission;
import com.track_and_trace.restful_application.entity.SubRole;
import com.track_and_trace.restful_application.model.request.CreateMenuPermissionRequest;
import com.track_and_trace.restful_application.model.response.MenuPermissionResponse;
import com.track_and_trace.restful_application.model.request.UpdateMenuPermissionRequest;
import com.track_and_trace.restful_application.repository.MenuPermissionRepository;
import com.track_and_trace.restful_application.repository.MenuRepository;
import com.track_and_trace.restful_application.repository.SubRoleRepository;
import com.track_and_trace.restful_application.service.MenuPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class MenuPermissionServiceImpl implements MenuPermissionService {

    @Autowired
    private MenuPermissionRepository menuPermissionRepository;

    @Autowired
    private SubRoleRepository subRoleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ValidationService validationService;

//    @Override
//    public MenuPermissionResponse responseBuilder(MenuPermission entity) {
//        return MenuPermissionResponse
//                .builder()
//                .idMenuPermission(entity.getIdMenuPermission())
//                .idMenu(entity.getMenu().getIdMenu())
//                .idSubRole(entity.getSubRole().getIdSubRole())
//                .createPermission(entity.getCreatePermission())
//                .viewPermission(entity.getViewPermission())
//                .updatePermission(entity.getUpdatePermission())
//                .deletePermission(entity.getDeletePermission())
//                .build();
//    }
    @Override
    public MenuPermissionResponse responseBuilder(MenuPermission entity) {
        return MenuPermissionResponse
                .builder()
                .idMenuPermission(entity.getIdMenuPermission())
                .idMenu(entity.getMenu().getIdMenu())
                .menuName(entity.getMenu().getMenuName())
                .labelMenu(entity.getMenu().getLabelMenu())
                .idSubRole(entity.getSubRole().getIdSubRole())
                .createPermission(entity.getCreatePermission())
                .viewPermission(entity.getViewPermission())
                .updatePermission(entity.getUpdatePermission())
                .deletePermission(entity.getDeletePermission())
                .build();
    }

    @Transactional
    @Override
    public MenuPermissionResponse create(CreateMenuPermissionRequest request) {
        validationService.validate(request);

        Menu menu = menuRepository.findById(request.getIdMenu())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        SubRole subRole = subRoleRepository.findById(request.getIdSubRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        MenuPermission menuPermission = new MenuPermission();
        menuPermission.setMenu(menu);
        menuPermission.setSubRole(subRole);
        menuPermission.setCreatePermission(request.getCreatePermission());
        menuPermission.setViewPermission(request.getViewPermission());
        menuPermission.setUpdatePermission(request.getUpdatePermission());
        menuPermission.setDeletePermission(request.getDeletePermission());
        menuPermissionRepository.save(menuPermission);

        return responseBuilder(menuPermission);
    }

    @Transactional(readOnly = true)
    @Override
    public MenuPermissionResponse find(int idMenu, int idSubRole) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        SubRole subRole = subRoleRepository.findById(idSubRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        MenuPermission menuPermission = menuPermissionRepository.findByMenuAndSubRole(menu, subRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu permission is not found"));

        return responseBuilder(menuPermission);
    }

//    @Transactional(readOnly = true)
//    @Override
//    public Page<MenuPermissionResponse> findAll(int limit, int offset) {
//        Pageable pageable = PageRequest.of(offset/limit, limit);
//        Page<MenuPermission> menuPermissions = menuPermissionRepository.findAll(pageable);
//        List<MenuPermissionResponse> menuPermissionResponses = menuPermissions.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());
//
//        return new PageImpl<>(menuPermissionResponses, pageable, menuPermissions.getTotalElements());
//    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuPermissionResponse> findAll(){
//        List<MenuPermissionResponse> menuPermissionResponses = menuPermissionRepository.getAllMenuPermissions();

        return menuPermissionRepository.getAllMenuPermissions();
    }

    @Transactional
    @Override
    public void delete(int idMenu, int idSubRole) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        SubRole subRole = subRoleRepository.findById(idSubRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

        MenuPermission menuPermission = menuPermissionRepository.findByMenuAndSubRole(menu, subRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu permission is not found"));

        menuPermissionRepository.delete(menuPermission);
    }

    @Transactional
    @Override
    public MenuPermissionResponse update(UpdateMenuPermissionRequest request) {
        validationService.validate(request);

        Menu menu = menuRepository.findById(request.getIdMenu())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        SubRole subRole = subRoleRepository.findById(request.getIdSubRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Role is not found"));

//        MenuPermission menuPermission = menuPermissionRepository.findByMenuAndSubRole(menu, subRole)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu permission is not found"));

        MenuPermission menuPermission = menuPermissionRepository.findByMenuAndSubRole(menu, subRole)
                .orElseGet(() -> new MenuPermission(menu, subRole, 0, 0, 0, 0));

        log.info(String.valueOf(menuPermission.getIdMenuPermission()));

//        MenuPermission menuPermission = menuPermissionRepository.findById(request.getIdMenuPermission())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu permission is not found"));

        if (request.getCreatePermission() != null)
            menuPermission.setCreatePermission(request.getCreatePermission());
        if (request.getViewPermission() != null)
            menuPermission.setViewPermission(request.getViewPermission());
        if (request.getUpdatePermission() != null)
            menuPermission.setUpdatePermission(request.getUpdatePermission());
        if (request.getDeletePermission() != null)
            menuPermission.setDeletePermission(request.getDeletePermission());

        menuPermissionRepository.save(menuPermission);

        return responseBuilder(menuPermission);
    }
}
