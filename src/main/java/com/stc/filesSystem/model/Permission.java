package com.stc.filesSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stc.filesSystem.enums.PermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Builder
@Entity(name = "permissions")
@Table(name="permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_email",  nullable = false)
    private String userEmail;

    @Column(name="permission_level")
    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id")
    @JsonIgnore
    private PermissionGroup permissionGroup;

	public Permission() {
	}

	public Permission(Long id, String userEmail, PermissionLevel permissionLevel, PermissionGroup permissionGroup) {
		this.id = id;
		this.userEmail = userEmail;
		this.permissionLevel = permissionLevel;
		this.permissionGroup = permissionGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public PermissionLevel getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(PermissionLevel permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}
}
