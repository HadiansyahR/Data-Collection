package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.model.request.CreateSubRoleRequest;
import com.track_and_trace.restful_application.model.response.SubRoleResponse;
import com.track_and_trace.restful_application.model.request.UpdateSubRoleRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.service.implement.SubRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost", "http://localhost:8080", "http://localhost:8000", "http://127.0.0.1:8000"})
public class SubRoleController {

    @Autowired
    private SubRoleServiceImpl subRoleService;

    @PostMapping(
            path = "/api/roles/{idRole}/subroles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<SubRoleResponse> create(@RequestBody CreateSubRoleRequest createSubRoleRequest,
                                               @PathVariable("idRole") int idRole){
        createSubRoleRequest.setIdRole(idRole);
        SubRoleResponse subRoleResponse = subRoleService.create(createSubRoleRequest);

        return WebResponse.<SubRoleResponse>builder().data(subRoleResponse).size(1).build();
    }

    @GetMapping(
            path = "/api/subroles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SubRoleResponse>> findAll(){
        List<SubRoleResponse> subRoleResponses = subRoleService.findAll();

        return WebResponse.<List<SubRoleResponse>>builder()
                .data(subRoleResponses)
                .size(subRoleResponses.size())
                .build();
    }


    @GetMapping(
            path = "/api/roles/{idRole}/subroles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SubRoleResponse>> findAllByRole(@PathVariable("idRole") int idRole,
                                                      @RequestParam(defaultValue = "10") int limit,
                                                      @RequestParam(defaultValue = "0") int offset){
        Page<SubRoleResponse> subRoleResponses = subRoleService.findAllByRole(idRole, limit, offset);

        return WebResponse.<List<SubRoleResponse>>builder().data(subRoleResponses.getContent())
                .size((int) subRoleResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "api/roles/{idRole}/subroles/{idSubRole}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<SubRoleResponse> findById(@PathVariable("idRole") int idRole,
                                                 @PathVariable("idSubRole") int idSubRole){

        SubRoleResponse subRoleResponse = subRoleService.findById(idRole, idSubRole);

        return WebResponse.<SubRoleResponse>builder().data(subRoleResponse).size(1).build();
    }

    @DeleteMapping(
            path = "api/roles/{idRole}/subroles/{idSubRole}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idRole") int idRole,
                                      @PathVariable("idSubRole") int idSubRole){
        subRoleService.delete(idRole, idSubRole);

        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/roles/{idRole}/subroles/{idSubRole}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<SubRoleResponse> update(@PathVariable("idRole") int idRole,
                                               @PathVariable("idSubRole") int idSubRole,
                                               @RequestBody UpdateSubRoleRequest updateSubRoleRequest){
        updateSubRoleRequest.setIdRole(idRole);
        updateSubRoleRequest.setIdSubRole(idSubRole);

        SubRoleResponse subRoleResponse = subRoleService.update(updateSubRoleRequest);

        return WebResponse.<SubRoleResponse>builder().data(subRoleResponse).size(1).build();
    }
}
