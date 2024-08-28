package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Uom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UomRepository extends JpaRepository<Uom, Integer> {
}
