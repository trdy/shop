package com.yt.shop.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 用户类型表
 */

@Entity
@Table(name="user_type")
public class UserType implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="user_type_id")
	private Long userTypeId;
	
	@Column(name="user_type_name")
	private String userTypeName;
	
	@OneToMany(targetEntity=Permission.class,mappedBy="userType",fetch=FetchType.LAZY)
	@JoinColumn(name="user_type_id")
	private Set<Permission> permissions;

	// Constructors


	public UserType(Long userTypeId) {
		this.userTypeId=userTypeId;
	}
	public UserType(){}


	public Long getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return this.userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}