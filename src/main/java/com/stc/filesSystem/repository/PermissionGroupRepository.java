package com.stc.filesSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.filesSystem.model.PermissionGroup;


@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup,Long> {
}
