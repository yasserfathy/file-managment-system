package com.stc.filesSystem.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PermissionDto {

	private String userEmail;

	@Enumerated(EnumType.STRING)
	private String permissionLevel;

	private Long permissionGroupId;

	public PermissionDto() {
	}

	public PermissionDto(String userEmail, String permissionLevel, Long permissionGroupId) {
		this.userEmail = userEmail;
		this.permissionLevel = permissionLevel;
		this.permissionGroupId = permissionGroupId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String user_email) {
		this.userEmail = user_email;
	}

	public String getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(String permission_level) {
		this.permissionLevel = permission_level;
	}

	public Long getPermissionGroupId() {
		return permissionGroupId;
	}

	public void setPermissionGroupId(Long permissionGroupId) {
		this.permissionGroupId = permissionGroupId;
	}
	
	

}
