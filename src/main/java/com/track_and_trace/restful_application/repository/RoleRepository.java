package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
