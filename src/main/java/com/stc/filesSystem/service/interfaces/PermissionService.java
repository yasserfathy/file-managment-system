package com.stc.filesSystem.service.interfaces;


import com.stc.filesSystem.dto.PermissionDto;
import com.stc.filesSystem.model.Permission;

import java.util.List;

public interface PermissionService {


    Permission getPermissionById(Long PermissionId) throws Exception;

    List<Permission> getAllPermissions();

    Permission savePermission(PermissionDto Permission) throws Exception;

    Permission updatePermission(Permission Permission);

    void deletePermission(Long PermissionId);
    
    List<Permission> getUserPermissionsUnderGroupX(String userEmail ,Long permissionGroupId);


}
