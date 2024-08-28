package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.*;
import com.track_and_trace.restful_application.model.request.CreateMenuRequest;
import com.track_and_trace.restful_application.model.request.UpdateMenuRequest;
import com.track_and_trace.restful_application.model.response.MenuResponse;
import com.track_and_trace.restful_application.service.implement.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost", "http://localhost:8080", "http://127.0.0.1:8000"})
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @PostMapping(
            path = "/api/menus",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MenuResponse> create(Account account,
                                            @RequestBody CreateMenuRequest createMenuRequest){
        MenuResponse menuResponse = menuService.create(createMenuRequest);

        return WebResponse.<MenuResponse>builder().data(menuResponse).size(1).build();
    }

    @GetMapping(
            path = "/api/menus",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<MenuResponse>> findAll(Account account,
                                                   @RequestParam(defaultValue = "10") int limit,
                                                   @RequestParam(defaultValue = "0") int offset){

        Page<MenuResponse> menuResponses = menuService.findAll(limit, offset);

        return WebResponse.<List<MenuResponse>>builder().data(menuResponses.getContent())
                .size((int) menuResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "/api/menus/{idMenu}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MenuResponse> findById(Account account,
                                              @PathVariable("idMenu") int id){
        MenuResponse menuResponse = menuService.findById(id);

        return WebResponse.<MenuResponse>builder().data(menuResponse).size(1).build();
    }

    @DeleteMapping(
            path = "/api/menus/{idMenu}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(Account account,
                                      @PathVariable("idMenu") int id){
        menuService.delete(id);

        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/menus/{idMenu}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MenuResponse> update(Account account,
                                            @RequestBody UpdateMenuRequest updateMenuRequest,
                                            @PathVariable("idMenu") int id){
        updateMenuRequest.setIdMenu(id);
        MenuResponse menuResponse = menuService.update(updateMenuRequest);

        return WebResponse.<MenuResponse>builder().data(menuResponse).size(1).build();
    }
}
