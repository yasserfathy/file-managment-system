package com.stc.filesSystem.controller;


import com.stc.filesSystem.dto.PermissionDto;
import com.stc.filesSystem.enums.ItemType;
import com.stc.filesSystem.enums.PermissionLevel;
import com.stc.filesSystem.exception.dto.CustomErrorDTO;
import com.stc.filesSystem.model.Permission;
import com.stc.filesSystem.model.PermissionGroup;
import com.stc.filesSystem.service.interfaces.PermissionGroupService;
import com.stc.filesSystem.service.interfaces.PermissionService;

import static com.stc.filesSystem.enums.ErrorCode.DATA_NOT_VALID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/superAdmin")
public class SuperAdminController {

    @Autowired
    PermissionGroupService groupService;
    
    @Autowired
    PermissionService permissionService;

    @PostMapping("/permissionGroup")
    public ResponseEntity<PermissionGroup> createPermissionGroup(@RequestBody PermissionGroup permissionGroup) throws Exception {
        return new ResponseEntity<>(groupService.savePermissionGroup(permissionGroup), HttpStatus.CREATED);

    }

    @PostMapping("/permission")
    public ResponseEntity<Permission> createPermissions(@RequestBody PermissionDto permissionDto) throws Exception {
       
        return new ResponseEntity<>(permissionService.savePermission(permissionDto), HttpStatus.CREATED);

    }
}
