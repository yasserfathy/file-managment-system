package com.stc.filesSystem.service.impl;

import com.stc.filesSystem.dto.PermissionDto;
import com.stc.filesSystem.enums.ItemType;
import com.stc.filesSystem.enums.PermissionLevel;
import com.stc.filesSystem.exception.ObjectNotFoundException;
import com.stc.filesSystem.exception.dto.CustomErrorDTO;
import com.stc.filesSystem.model.Permission;
import com.stc.filesSystem.model.PermissionGroup;
import com.stc.filesSystem.repository.PermissionRepository;
import com.stc.filesSystem.service.interfaces.PermissionGroupService;
import com.stc.filesSystem.service.interfaces.PermissionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.stc.filesSystem.enums.ErrorCode.DATA_NOT_VALID;


@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;


    @Autowired
    PermissionGroupService permissionGroupService;

    @Override
    public Permission getPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> {
                    throw new ObjectNotFoundException(String.format("No Permission Exist with id [{}]", permissionId));
                });
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission savePermission(PermissionDto permissionDto) throws Exception {
        try {
            PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupById(permissionDto.getPermissionGroupId());
            Permission newPermission = new Permission(null, permissionDto.getUserEmail(), null, permissionGroup);

            if (permissionDto.getPermissionLevel().equals(PermissionLevel.EDIT.name())) {
                newPermission.setPermissionLevel(PermissionLevel.EDIT);

            } else if (permissionDto.getPermissionLevel().equals(PermissionLevel.VIEW.name())) {
                newPermission.setPermissionLevel(PermissionLevel.VIEW);

            } else {
                CustomErrorDTO customErrorDTO = new CustomErrorDTO(String.format("item type can't be empty and must be one of these values [{}]", ItemType.values()), DATA_NOT_VALID.getValue());
                throw customErrorDTO;
            }

            return permissionRepository.saveAndFlush(newPermission);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Permission updatePermission(Permission newPermission) {
        try {
            Permission oldPermission = getPermissionById(newPermission.getId());
            if (oldPermission != null) {
                permissionRepository.saveAndFlush(newPermission);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return newPermission;
    }

    @Override
    public void deletePermission(Long PermissionId) {
        permissionRepository.deleteById(PermissionId);
    }

    @Override
    public List<Permission> getUserPermissionsUnderGroupX(String userEmail, Long permissionGroupId) {
        return permissionRepository.findByUserEmailAndPermissionGroupId(userEmail,permissionGroupId);
    }
}