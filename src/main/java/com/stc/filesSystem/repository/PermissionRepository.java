package com.stc.filesSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stc.filesSystem.model.Permission;

import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    List<Permission> findByUserEmailAndPermissionGroupId(String userName, Long permissionGroupId);
}
