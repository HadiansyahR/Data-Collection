package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.model.request.CreateMenuPermissionRequest;
import com.track_and_trace.restful_application.model.response.MenuPermissionResponse;
import com.track_and_trace.restful_application.model.request.UpdateMenuPermissionRequest;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.service.implement.MenuPermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class MenuPermissionController {

    @Autowired
    private MenuPermissionServiceImpl menuPermissionService;

    @GetMapping(
            path = "/api/menus/{idMenu}/subroles/{idSubRole}/menupermission",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MenuPermissionResponse> find(@PathVariable("idMenu") int idMenu,
                                                    @PathVariable("idSubRole") int idSubRole
                                                    ){
        MenuPermissionResponse response = menuPermissionService.find(idMenu, idSubRole);

        return WebResponse.<MenuPermissionResponse>builder().data(response).size(1).build();
    }


//    @GetMapping(
//            path = "/api/menupermissions",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<List<MenuPermissionResponse>> findAll(@RequestParam(defaultValue = "10") int limit,
//                                                             @RequestParam(defaultValue = "0") int offset){
//
//        Page<MenuPermissionResponse> menuPermissionResponses = menuPermissionService.findAll(limit, offset);
//
//        return WebResponse.<List<MenuPermissionResponse>>builder()
//                .data(menuPermissionResponses.getContent())
//                .size((int) menuPermissionResponses.getTotalElements())
//                .build();
//    }

    @GetMapping(
            path = "/api/menupermissions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<MenuPermissionResponse>> findAll(){

        List<MenuPermissionResponse> menuPermissionResponses = menuPermissionService.findAll();

        return WebResponse.<List<MenuPermissionResponse>>builder()
                .data(menuPermissionResponses)
                .size(menuPermissionResponses.size())
                .build();
    }

    @PostMapping(
            path = "/api/menus/{idMenu}/subroles/{idSubRole}/menupermission",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MenuPermissionResponse> create(@RequestBody CreateMenuPermissionRequest createMenuPermissionRequest,
                                                      @PathVariable("idMenu") int idMenu,
                                                      @PathVariable("idSubRole") int idSubRole){
        createMenuPermissionRequest.setIdMenu(idMenu);
        createMenuPermissionRequest.setIdSubRole(idSubRole);

        MenuPermissionResponse response = menuPermissionService.create(createMenuPermissionRequest);

        return WebResponse.<MenuPermissionResponse>builder().data(response).size(1).build();
    }

    @PutMapping(
            path = "/api/menus/{idMenu}/subroles/{idSubRole}/menupermission",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MenuPermissionResponse> update(@PathVariable("idMenu") int idMenu,
                                                      @PathVariable("idSubRole") int idSubRole,
                                                      @RequestBody UpdateMenuPermissionRequest updateMenuPermissionRequest){

        updateMenuPermissionRequest.setIdMenu(idMenu);
        updateMenuPermissionRequest.setIdSubRole(idSubRole);

        log.info(updateMenuPermissionRequest.toString());

        MenuPermissionResponse response = menuPermissionService.update(updateMenuPermissionRequest);

        return WebResponse.<MenuPermissionResponse>builder().data(response).size(1).build();
    }

    @DeleteMapping(
            path = "/api/menus/{idMenu}/subroles/{idSubRole}/menupermission",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idMenu") int idMenu,
                                      @PathVariable("idSubRole") int idSubRole){

        menuPermissionService.delete(idMenu, idSubRole);

        return WebResponse.<String>builder().data("OK").build();
    }
}
