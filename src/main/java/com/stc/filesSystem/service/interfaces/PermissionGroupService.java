package com.stc.filesSystem.service.interfaces;


import com.stc.filesSystem.model.PermissionGroup;

import java.util.List;

public interface PermissionGroupService {


    PermissionGroup getPermissionGroupById(Long PermissionGroupId) throws Exception;

    List<PermissionGroup> getAllPermissionGroups();

    PermissionGroup savePermissionGroup(PermissionGroup PermissionGroup);

    PermissionGroup updatePermissionGroup(PermissionGroup PermissionGroup);

    void deletePermissionGroup(Long PermissionGroupId);


}
