package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.model.request.CreateUomRequest;
import com.track_and_trace.restful_application.model.request.UpdateUomRequest;
import com.track_and_trace.restful_application.model.response.UomResponse;
import com.track_and_trace.restful_application.service.implement.UomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UomController {
    
    @Autowired
    private UomServiceImpl uomService;
    
    @PostMapping(
            path = "/api/uoms",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UomResponse> create(Account account,
                                           @RequestBody CreateUomRequest createUomRequest){
        UomResponse uomResponse = uomService.create(createUomRequest);

        return WebResponse.<UomResponse>builder().data(uomResponse).size(1).build();
    }

    @GetMapping(
            path = "/api/uoms",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UomResponse>> findAll(Account account,
                                                   @RequestParam(defaultValue = "10") int limit,
                                                   @RequestParam(defaultValue = "0") int offset){

        Page<UomResponse> uomResponses = uomService.findAll(limit, offset);

        return WebResponse.<List<UomResponse>>builder().data(uomResponses.getContent())
                .size((int) uomResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "/api/uoms/{idUom}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UomResponse> findById(Account account,
                                              @PathVariable("idUom") int id){
        UomResponse uomResponse = uomService.findById(id);

        return WebResponse.<UomResponse>builder().data(uomResponse).size(1).build();
    }

    @DeleteMapping(
            path = "/api/uoms/{idUom}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(Account account,
                                      @PathVariable("idUom") int id){
        uomService.delete(id);

        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/uoms/{idUom}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UomResponse> update(Account account,
                                            @RequestBody UpdateUomRequest updateUomRequest,
                                            @PathVariable("idUom") int id){
        updateUomRequest.setIdUom(id);
        UomResponse uomResponse = uomService.update(updateUomRequest);

        return WebResponse.<UomResponse>builder().data(uomResponse).size(1).build();
    }
}
