package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.model.request.CreateUomPackageRequest;
import com.track_and_trace.restful_application.model.request.UpdateUomPackageRequest;
import com.track_and_trace.restful_application.model.response.UomPackageResponse;
import com.track_and_trace.restful_application.service.implement.UomPackageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UomPackageController {
    
    @Autowired
    private UomPackageServiceImpl uomPackageService;

    @PostMapping(
            path = "/api/uompackages",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UomPackageResponse> create(Account account,
                                            @RequestBody CreateUomPackageRequest createUomPackageRequest){
        UomPackageResponse uomPackageResponse = uomPackageService.create(createUomPackageRequest);

        return WebResponse.<UomPackageResponse>builder().data(uomPackageResponse).size(1).build();
    }

    @GetMapping(
            path = "/api/uompackages",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UomPackageResponse>> findAll(@RequestParam(defaultValue = "10") int limit,
                                                         @RequestParam(defaultValue = "0") int offset){

        Page<UomPackageResponse> uomPackageResponses = uomPackageService.findAll(limit, offset);

        return WebResponse.<List<UomPackageResponse>>builder().data(uomPackageResponses.getContent())
                .size((int) uomPackageResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "/api/uompackages/{idUomPackage}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UomPackageResponse> findById(Account account,
                                              @PathVariable("idUomPackage") int id){
        UomPackageResponse uomPackageResponse = uomPackageService.findById(id);

        return WebResponse.<UomPackageResponse>builder().data(uomPackageResponse).size(1).build();
    }

    @DeleteMapping(
            path = "/api/uompackages/{idUomPackage}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(Account account,
                                      @PathVariable("idUomPackage") int id){
        uomPackageService.delete(id);

        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/uompackages/{idUomPackage}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UomPackageResponse> update(Account account,
                                            @RequestBody UpdateUomPackageRequest updateUomPackageRequest,
                                            @PathVariable("idUomPackage") int id){
        updateUomPackageRequest.setIdUomPackage(id);
        UomPackageResponse uomPackageResponse = uomPackageService.update(updateUomPackageRequest);

        return WebResponse.<UomPackageResponse>builder().data(uomPackageResponse).size(1).build();
    }
}
