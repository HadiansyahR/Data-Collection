package com.track_and_trace.restful_application.repository;

import com.track_and_trace.restful_application.entity.Role;
import com.track_and_trace.restful_application.entity.SubRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubRoleRepository extends JpaRepository<SubRole, Integer> {
    Optional<SubRole> findByRoleAndIdSubRole(Role role, int idSubRole);

    Page<SubRole> findAllByRole(Role role, Pageable pageable);
}
