package com.yt.shop.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 菜单
 */

@Entity
@Table(name="menu")
public class Menu implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="menu_id")
	private Long menuId;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="menu_url")
	private String menuUrl;
	
	@Column(name="menu_desc")
	private String menuDesc;
	
	@ManyToOne
	@JoinColumn(name="parent_menu_id")
	private Menu parentMenu;
	
	@OneToMany(targetEntity=Menu.class,fetch=FetchType.EAGER)
	@JoinColumn(name="parent_menu_id")
	@OrderBy("menuId")
	private Set<Menu> menus;

	// Constructors

	public Menu(){}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
}