package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.entity.Account;
import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.model.request.CreateProductRequest;
import com.track_and_trace.restful_application.model.request.UpdateProductRequest;
import com.track_and_trace.restful_application.model.response.ProductResponse;
import com.track_and_trace.restful_application.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    
    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(
            path = "/api/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(Account account,
                                            @RequestBody CreateProductRequest createProductRequest){
        ProductResponse productResponse = productService.create(createProductRequest);

        return WebResponse.<ProductResponse>builder().data(productResponse).size(1).build();
    }

    @GetMapping(
            path = "/api/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> findAll(Account account,
                                                   @RequestParam(defaultValue = "10") int limit,
                                                   @RequestParam(defaultValue = "0") int offset){

        Page<ProductResponse> productResponses = productService.findAll(limit, offset);

        return WebResponse.<List<ProductResponse>>builder().data(productResponses.getContent())
                .size((int) productResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "/api/products/{idProduct}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> findById(Account account,
                                              @PathVariable("idProduct") int id){
        ProductResponse productResponse = productService.findById(id);

        return WebResponse.<ProductResponse>builder().data(productResponse).size(1).build();
    }

    @DeleteMapping(
            path = "/api/products/{idProduct}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(Account account,
                                      @PathVariable("idProduct") int id){
        productService.delete(id);

        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/products/{idProduct}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> update(Account account,
                                            @RequestBody UpdateProductRequest updateProductRequest,
                                            @PathVariable("idProduct") int id){
        updateProductRequest.setIdProduct(id);
        ProductResponse productResponse = productService.update(updateProductRequest);

        return WebResponse.<ProductResponse>builder().data(productResponse).size(1).build();
    }
}
