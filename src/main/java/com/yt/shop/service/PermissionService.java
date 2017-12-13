package com.yt.shop.service;

import com.yt.shop.dao.MenuJpa;
import com.yt.shop.dao.PermissionJpa;
import com.yt.shop.dao.UserTypeJpa;
import com.yt.shop.model.Menu;
import com.yt.shop.model.Permission;
import com.yt.shop.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionService {
    @Autowired
    private MenuJpa menuJpa;

    @Autowired
    private PermissionJpa permissionJpa;

    @Autowired
    private UserTypeJpa userTypeJpa;

    /**
     * 查询出系统中所有定义的菜单项
     * @return
     */
    @Transactional
    public List<Menu> findParnetMenuList() {
        return menuJpa.findParnetMenuList();
    }

    /**
     * 查询指定menuId的菜单项
     * @param menuId
     * @return
     */
    @Transactional
    public Menu findMenuById(Long menuId) {
        return menuJpa.findOne(menuId);
    }

    /**
     * 修改菜单
     * @param menu
     */
    @Transactional
    public void updateMenu(Menu menu) {
        menuJpa.save(menu);
    }

    /**
     * 新增菜单
     * @param menu
     */
    @Transactional
    public void insertMenu(Menu menu) {
        menuJpa.save(menu);
    }

    /**
     * 删除菜单
     * @param menuId
     */
    @Transactional
    public void deleteMenuById(Long menuId) {
        menuJpa.delete(menuId);
    }

    @Transactional
    public List<UserType> findUserTypeList() {
        return userTypeJpa.findAll();
    }

    @Transactional
    public UserType findUserTypeById(Long userTypeId) {
        return userTypeJpa.findOne(userTypeId);
    }

    @Transactional
    public void updateRolePermission(String userTypeId, String[] pmIds) {
        //1.先查询出用户类型已经配置的菜单
        List<Permission> pmlist=permissionJpa.findPermissionByUserTypeId(Long.parseLong(userTypeId));
        List<String> mids=new ArrayList<String>();//已配置的菜单id集合
        for(Permission p:pmlist){
            mids.add(p.getMenu().getMenuId()+"");
        }

        //2.计算需要新增的
        List<String> pmIdList=new ArrayList<String>();//用户保存传递过来的菜单编号集合
        List<String> addm=new ArrayList<String>();//用来保存新增的菜单编号
        if(null!=pmIds){
            for(String id:pmIds){
                pmIdList.add(id);
                if(!mids.contains(id)){
                    addm.add(id);
                }
            }
        }
        //新增到数据库
        for(String id:addm){
            permissionJpa.insertPermission(userTypeId,id);
        }


        //3.计算需要删除的
        List<String> delm=new ArrayList<String>();//用来保存删除的菜单编号
        for(String id:mids){
            if(!pmIdList.contains(id)){
                delm.add(id);
            }
        }
        //删除数据库配置
        for(String id:delm){
            permissionJpa.deletePermission(userTypeId,id);
        }
    }

    public List<Permission> findPermissionByUserTypeId(Long userTypeId) {
        return permissionJpa.findPermissionByUserTypeId(userTypeId);
    }

}
