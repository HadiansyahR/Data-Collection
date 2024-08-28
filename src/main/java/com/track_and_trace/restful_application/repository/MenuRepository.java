package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
