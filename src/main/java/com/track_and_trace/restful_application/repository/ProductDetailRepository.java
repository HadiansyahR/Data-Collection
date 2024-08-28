package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Product;
import com.track_and_trace.restful_application.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    Optional<ProductDetail> findByProduct(Product product);
}
