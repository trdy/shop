package com.yt.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 权限表
 */

@Entity
@Table(name="permission")
public class Permission implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Long perid;
	
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name="user_type_id")
	private UserType userType;

	// Constructors

	/** default constructor */
	public Permission() {
	}

	// Property accessors

	public Long getPerid() {
		return this.perid;
	}

	public void setPerid(Long perid) {
		this.perid = perid;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}