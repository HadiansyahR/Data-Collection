package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.Product;
import com.track_and_trace.restful_application.model.request.CreateProductRequest;
import com.track_and_trace.restful_application.model.request.UpdateProductRequest;
import com.track_and_trace.restful_application.model.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService extends CrudService<Product, ProductResponse>{
    ProductResponse create(CreateProductRequest request);
    Page<ProductResponse> findAll(int limit, int offset);
    ProductResponse findById(int id);
    void delete(int id);
    ProductResponse update(UpdateProductRequest request);
}
