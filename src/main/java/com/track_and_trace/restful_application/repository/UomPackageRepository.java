package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.UomPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UomPackageRepository extends JpaRepository<UomPackage, Integer> {
}
