package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
