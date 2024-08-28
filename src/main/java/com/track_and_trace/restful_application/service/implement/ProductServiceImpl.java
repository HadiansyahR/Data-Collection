package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Product;
import com.track_and_trace.restful_application.model.request.CreateProductRequest;
import com.track_and_trace.restful_application.model.request.UpdateProductRequest;
import com.track_and_trace.restful_application.model.response.ProductResponse;
import com.track_and_trace.restful_application.repository.ProductRepository;
import com.track_and_trace.restful_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public ProductResponse create(CreateProductRequest request) {
        validationService.validate(request);

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setStatus(request.getStatus());
        productRepository.save(product);

        return responseBuilder(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductResponse> findAll(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = products.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse findById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        return responseBuilder(product);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        productRepository.delete(product);
    }

    @Transactional
    @Override
    public ProductResponse update(UpdateProductRequest request) {
        validationService.validate(request);

        Product product = productRepository.findById(request.getIdProduct())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        product.setProductName(request.getProductName());
        product.setStatus(request.getStatus());
        productRepository.save(product);

        return responseBuilder(product);
    }

    @Override
    public ProductResponse responseBuilder(Product entity) {
        return ProductResponse
                .builder()
                .idProduct(entity.getIdProduct())
                .productName(entity.getProductName())
                .status(entity.getStatus())
                .build();
    }
}
