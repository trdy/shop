package com.yt.shop.model;

import java.util.List;

/**
 * 非数据库持久化类
 * 配置角色菜单的数据模型
 */
public class RoleMenu implements java.io.Serializable{

	private Menu menu; //角色关联的菜单
	private int flag; //是否拥有权限 0:没有，1有
	
	private List<RoleMenu> childMenus;
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List<RoleMenu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<RoleMenu> childMenus) {
		this.childMenus = childMenus;
	}
	
}
