package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.request.CreateRoleRequest;
import com.track_and_trace.restful_application.model.response.RoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateRoleRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.service.implement.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost", "http://localhost:8080", "http://127.0.0.1:8000"})
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping(
            path = "/api/roles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> create(Account account,
                                            @RequestBody CreateRoleRequest createRoleRequest){
        RoleResponse roleResponse = roleService.create(createRoleRequest);

        return WebResponse.<RoleResponse>builder().data(roleResponse).size(1).build();
    }

    @GetMapping(
            path = "/api/roles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<RoleResponse>> findAll(Account account,
                                                   @RequestParam(defaultValue = "10") int limit,
                                                   @RequestParam(defaultValue = "0") int offset){
        Page<RoleResponse> roleResponses = roleService.findAll(limit, offset);

        return WebResponse.<List<RoleResponse>>builder().data(roleResponses.getContent())
                .size((int) roleResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "/api/roles/{idRole}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> findById(Account account,
                                              @PathVariable("idRole") int id){
        RoleResponse roleResponse = roleService.findById(id);

        return WebResponse.<RoleResponse>builder().data(roleResponse).size(1).build();
    }

    @DeleteMapping(
            path = "/api/roles/{idRole}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(Account account,
                                      @PathVariable("idRole") int id){
        roleService.delete(id);

        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/roles/{idRole}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> update(Account account,
                                            @RequestBody UpdateRoleRequest updateRoleRequest,
                                            @PathVariable("idRole") int id){
        updateRoleRequest.setIdRole(id);
        RoleResponse roleResponse = roleService.update(updateRoleRequest);

        return WebResponse.<RoleResponse>builder().data(roleResponse).size(1).build();
    }
}
