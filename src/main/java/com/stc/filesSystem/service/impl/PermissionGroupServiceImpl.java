package com.stc.filesSystem.service.impl;

import com.stc.filesSystem.exception.ObjectNotFoundException;
import com.stc.filesSystem.model.PermissionGroup;
import com.stc.filesSystem.repository.PermissionGroupRepository;
import com.stc.filesSystem.service.interfaces.PermissionGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PermissionGroupServiceImpl implements PermissionGroupService {

    @Autowired
    PermissionGroupRepository permissionGroupRepository;

    @Override
    public PermissionGroup getPermissionGroupById(Long PermissionGroupId) {
        return permissionGroupRepository.findById(PermissionGroupId)
                .orElseThrow(() -> {
                    throw new ObjectNotFoundException(String.format("No PermissionGroup Exist with id [{}]", PermissionGroupId));
                });
    }

    @Override
    public List<PermissionGroup> getAllPermissionGroups() {
        return permissionGroupRepository.findAll();
    }

    @Override
    public PermissionGroup savePermissionGroup(PermissionGroup PermissionGroup) {
        try {
            permissionGroupRepository.saveAndFlush(PermissionGroup);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return PermissionGroup;
    }

    @Override
    public PermissionGroup updatePermissionGroup(PermissionGroup newPermissionGroup) {
        try {
            PermissionGroup oldPermissionGroup = getPermissionGroupById(newPermissionGroup.getId());
            if (oldPermissionGroup != null) {
                permissionGroupRepository.saveAndFlush(newPermissionGroup);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return newPermissionGroup;
    }

    @Override
    public void deletePermissionGroup(Long PermissionGroupId) {
        permissionGroupRepository.deleteById(PermissionGroupId);
    }
}