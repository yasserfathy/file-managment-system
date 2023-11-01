package com.stc.filesSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stc.filesSystem.enums.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;


@Builder
@Entity
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private ItemType type;

    @Column(name="name")
    private String name;
    
    
    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    @JsonIgnore
    private PermissionGroup permissionGroup;

    @ManyToOne( cascade = { CascadeType.ALL } )
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Item parent;

    @OneToMany(mappedBy = "parent")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private List<Item> subItems;

	public Item() {
	}

	public Item(Long id, ItemType type, String name, PermissionGroup permissionGroup, Item parent, List<Item> subItems) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.permissionGroup = permissionGroup;
		this.parent = parent;
		this.subItems = subItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public Item getParent() {
		return parent;
	}

	public void setParent(Item parent) {
		this.parent = parent;
	}

	public List<Item> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<Item> subItems) {
		this.subItems = subItems;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", type=" + type + ", name=" + name + ", permissionGroup=" + permissionGroup
				+ ", parent=" + parent + ", subItems=" + subItems + "]";
	}
    
    
    
    

}
