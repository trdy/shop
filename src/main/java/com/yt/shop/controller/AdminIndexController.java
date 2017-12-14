package com.yt.shop.controller;

import com.yt.shop.common.Constract;
import com.yt.shop.common.JsonUtil;
import com.yt.shop.dao.OperRecordJpa;
import com.yt.shop.model.Menu;
import com.yt.shop.model.OperRecord;
import com.yt.shop.model.Permission;
import com.yt.shop.model.UserInfo;
import com.yt.shop.model.format.MenuItem;
import com.yt.shop.service.PermissionService;
import com.yt.shop.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class AdminIndexController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OperRecordJpa operRecordJpa;

    /**
     * 用户登录成功后，访问后台首页加载系统配置的用户菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/menu",method = RequestMethod.GET)
    public String backIndexMenu(HttpServletRequest request){


        //读取菜单信息
        List<Menu> pm=permissionService.findParnetMenuList();
        //获得用户信息
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        log.info(userInfo.getUserName() + "访问后台管理菜单");

        //定义一个待输出的菜单集合
        List<MenuItem> menuItemList=new ArrayList<>();
        //检查用户身份，如果是系统默认超级管理员，直接读取所有菜单，否则根据用户配置的菜单项检查，设置菜单集合
        if(userInfo.getUserName().equals(Constract.ROOT_USER)){
            log.info("如果是系统默认超级管理员，返回最高权限");
            //一级菜单
            for(Menu m:pm){
                MenuItem menuItem=new MenuItem();
                menuItem.setMenuId(m.getMenuId());
                menuItem.setMenuName(m.getMenuName());
                menuItem.setMenuUrl(m.getMenuUrl());
                List<MenuItem> childMenuItemList=new ArrayList<>();
                Set<Menu> cms=m.getMenus();
                //二级菜单
                for(Menu cm:cms){
                    MenuItem childMenuItem=new MenuItem();
                    childMenuItem.setMenuId(cm.getMenuId());
                    childMenuItem.setMenuName(cm.getMenuName());
                    childMenuItem.setMenuUrl(cm.getMenuUrl());
                    childMenuItemList.add(childMenuItem);
                }
                menuItem.setChildMenus(childMenuItemList);
                menuItemList.add(menuItem);
            }
        }else{
            Long userTypeId=userInfo.getUserType().getUserTypeId();
            log.info("不是系统默认的超级用户，根据userName:"+userInfo.getUserName()+"的userTypeId："+userTypeId+"查询权限");
            List<Permission> userPermissions=permissionService.findPermissionByUserTypeId(userTypeId);

            //先获取用配置的权限的menuId
            List<String> mids=new ArrayList<String>();
            for(Permission p:userPermissions){
                mids.add(p.getMenu().getMenuId()+"");
            }
            //再检查一级菜单
            for(Menu m:pm){
                if(mids.contains(m.getMenuId()+"")){
                    MenuItem menuItem=new MenuItem();
                    menuItem.setMenuId(m.getMenuId());
                    menuItem.setMenuName(m.getMenuName());
                    menuItem.setMenuUrl(m.getMenuUrl());

                    List<MenuItem> childMenuItemList=new ArrayList<>();
                    //获得二级子菜单，再检查是否配置有权限
                    Set<Menu> cms=m.getMenus();
                    for(Menu cm:cms){
                        //如果配置有二级子菜单权限，加入集合
                        if(mids.contains(cm.getMenuId()+"")){
                            MenuItem childMenuItem=new MenuItem();
                            childMenuItem.setMenuId(cm.getMenuId());
                            childMenuItem.setMenuName(cm.getMenuName());
                            childMenuItem.setMenuUrl(cm.getMenuUrl());
                            childMenuItemList.add(childMenuItem);
                        }
                    }
                    menuItem.setChildMenus(childMenuItemList);
                    menuItemList.add(menuItem);
                }
            }
        }
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"访问后台首页，读取菜单"));
        return JsonUtil.getReturnJson(menuItemList);
    }



    /**
     * 用户登录后，访问后台首页，加载用户信息
     * @return
     */
    @RequestMapping(value = "/admin/loadUser",method = RequestMethod.GET)
    public String backLoadUserInfo(HttpServletRequest request){

        //获得用户信息
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);

        //重写查询数据库中最新数据，因为积分可能有变化
        userInfo = userInfoService.findUserInfoById(userInfo.getUserId());
        request.getSession().setAttribute(Constract.ADMIN_LOGIN_FLAG,userInfo);

        log.info("用户登录后，访问后台首页，加载用户信息");
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"用户登录后，访问后台首页，加载用户信息"));

        return JsonUtil.getReturnJson(userInfo);
    }

}
