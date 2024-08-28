package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.ProductDetail;
import com.track_and_trace.restful_application.model.request.CreateProductDetailRequest;
import com.track_and_trace.restful_application.model.request.UpdateProductDetailRequest;
import com.track_and_trace.restful_application.model.response.ProductDetailResponse;
import org.springframework.data.domain.Page;

public interface ProductDetailService extends CrudService<ProductDetail, ProductDetailResponse>{
    ProductDetailResponse create(CreateProductDetailRequest request);
    Page<ProductDetailResponse> findAll(int limit, int offset);
    ProductDetailResponse findById(int id);
    ProductDetailResponse findByProduct(int idProduct);
//    void delete(int id);
    void delete(int idProduct);
    ProductDetailResponse update(UpdateProductDetailRequest request);
}
