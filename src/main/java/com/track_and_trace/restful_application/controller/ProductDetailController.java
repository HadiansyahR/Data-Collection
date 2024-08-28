package com.track_and_trace.restful_application.controller;

import com.track_and_trace.restful_application.model.WebResponse;
import com.track_and_trace.restful_application.model.request.CreateProductDetailRequest;
import com.track_and_trace.restful_application.model.request.UpdateProductDetailRequest;
import com.track_and_trace.restful_application.model.response.ProductDetailResponse;
import com.track_and_trace.restful_application.service.implement.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductDetailController {

    @Autowired
    private ProductDetailServiceImpl productDetailService;

    @GetMapping(
            path = "/api/productdetails",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductDetailResponse>> find(@RequestParam(defaultValue = "10") int limit,
                                               @RequestParam(defaultValue = "0") int offset){
        Page<ProductDetailResponse> productDetailResponses = productDetailService.findAll(limit, offset);

        return WebResponse.<List<ProductDetailResponse>>builder().data(productDetailResponses.getContent())
                .size((int) productDetailResponses.getTotalElements())
                .build();
    }

    @GetMapping(
            path = "/api/products/{idProduct}/productdetails",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductDetailResponse> findByProduct(@PathVariable("idProduct") int idProduct){
        ProductDetailResponse response = productDetailService.findByProduct(idProduct);

        return WebResponse.<ProductDetailResponse>builder().data(response).size(1).build();
    }

    @GetMapping(
            path = "/api/productdetails/{idProductDetail}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductDetailResponse> findById(@PathVariable("idProductDetail") int id){
        ProductDetailResponse response = productDetailService.findById(id);

        return WebResponse.<ProductDetailResponse>builder().data(response).size(1).build();
    }

    @PostMapping(
            path = "/api/products/{idProduct}/productdetails",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductDetailResponse> create(@PathVariable("idProduct") int idProduct,
                                                     @RequestBody CreateProductDetailRequest request){
        request.setIdProduct(idProduct);
        ProductDetailResponse productDetailResponse = productDetailService.create(request);

        return WebResponse.<ProductDetailResponse>builder().data(productDetailResponse).size(1).build();
    }

//    @PatchMapping(
//            path = "/api/products/{idProduct}/productdetails/{idProductDetail}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<ProductDetailResponse> update(@RequestBody UpdateProductDetailRequest request,
//                                                     @PathVariable("idProduct") int idProduct,
//                                                     @PathVariable("idProductDetail") int idProductDetail){
//        request.setIdProduct(idProduct);
//        request.setIdProductDetail(idProductDetail);
//
//        ProductDetailResponse response = productDetailService.update(request);
//
//        return WebResponse.<ProductDetailResponse>builder().data(response).size(1).build();
//    }

    @PatchMapping(
            path = "/api/products/{idProduct}/productdetails",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductDetailResponse> update(@RequestBody UpdateProductDetailRequest request,
                                                     @PathVariable("idProduct") int idProduct){
        request.setIdProduct(idProduct);

        ProductDetailResponse response = productDetailService.update(request);

        return WebResponse.<ProductDetailResponse>builder().data(response).size(1).build();
    }

//    @DeleteMapping(
//            path = "/api/products/{idProduct}/productdetails/{idProductDetail}",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<String> delete(@PathVariable("idProduct") int idProduct,
//                                      @PathVariable("idProductDetail") int idProductDetail){
//
//        productDetailService.delete(idProduct, idProductDetail);
//
//        return WebResponse.<String>builder().data("OK").build();
//    }
    @DeleteMapping(
            path = "/api/products/{idProduct}/productdetails",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idProduct") int idProduct){

        productDetailService.delete(idProduct);

        return WebResponse.<String>builder().data("OK").build();
    }
}
