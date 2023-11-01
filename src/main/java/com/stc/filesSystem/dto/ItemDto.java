package com.stc.filesSystem.dto;

import com.stc.filesSystem.enums.ItemType;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder

public class ItemDto {

    private ItemType type;

    private String name;
    
    private String userName;

    private Long parentId;

    private Long permissionGroupId;

    private MultipartFile file;
    
    private Long fileId;

	public ItemDto() {
	}

	public ItemDto(ItemType type, String name, String userName, Long parentId, Long permissionGroupId, MultipartFile file, Long fileId) {
		this.type = type;
		this.name = name;
		this.userName = userName;
		this.parentId = parentId;
		this.permissionGroupId = permissionGroupId;
		this.file = file;
		this.fileId =fileId;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getPermissionGroupId() {
		return permissionGroupId;
	}

	public void setPermissionGroupId(Long permissionGroupId) {
		this.permissionGroupId = permissionGroupId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	
}
