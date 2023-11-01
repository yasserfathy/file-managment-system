package com.stc.filesSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Builder
@Entity
@Table(name="permissiongroup")
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="group_name", nullable = false, unique = true)
    private String name;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permissionGroup")
    @JsonIgnore
    private List<Permission> Permissions;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permissionGroup")
    @JsonIgnore
    private List<Item> items;
    


	public PermissionGroup() {
	}

	public PermissionGroup(Long id, String name, List<Permission> permissions, List<Item> items) {
		this.id = id;
		this.name = name;
		Permissions = permissions;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Permission> getPermissions() {
		return Permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		Permissions = permissions;
	}

	@Override
	public String toString() {
		return "PermissionGroup [id=" + id + ", name=" + name + ", items=" + items + ", Permissions=" + Permissions
				+ "]";
	}
    
    

}
