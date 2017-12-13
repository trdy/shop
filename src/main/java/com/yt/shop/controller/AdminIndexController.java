package com.yt.shop.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yt.shop.common.Constract;
import com.yt.shop.dao.OperRecordJpa;
import com.yt.shop.model.*;
import com.yt.shop.service.PermissionService;
import com.yt.shop.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AdminIndexController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OperRecordJpa operRecordJpa;

    /**
     * 后台管理首页
     * @return
     */
    @RequestMapping("/admin/menu")
    @ResponseBody
    public String adminMenu(HttpServletRequest request){

        UserInfo userInfo=(UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        log.info(userInfo.getUserName()+"进入后台管理首页");

        //定义返回的结果集
        List<RoleMenu> roleMenus=new ArrayList<RoleMenu>();
        List<Menu> menus=permissionService.findParnetMenuList();
        log.info("加载菜单列表："+menus);
        //获得用户菜单

        if(userInfo.getUserName().equals("admin")){
            log.info("如果是admin,获得admin菜单");
            for(Menu m:menus){
                RoleMenu rm=new RoleMenu();
                rm.setMenu(m);
                Set<Menu> childMenus=m.getMenus();
                List<RoleMenu> childRoleMenus=new ArrayList<RoleMenu>();
                for(Iterator<Menu> it = childMenus.iterator(); it.hasNext();){
                    Menu childMenu=it.next();
                    RoleMenu crm=new RoleMenu();
                    crm.setMenu(childMenu);
                    childRoleMenus.add(crm);
                }
                rm.setChildMenus(childRoleMenus);
                roleMenus.add(rm);
            }
            log.info("获得admin菜单完成"+roleMenus);
        }else{
            UserType userType=userInfo.getUserType();
            log.info("如果不是admin，找不用户类型"+userType);
            List<Permission> userPermissions=permissionService.findPermissionByUserTypeId(userType.getUserTypeId());
            log.info("根据用户的类型加载权限配置"+userPermissions);
            List<String> mids=new ArrayList<String>();
            for(Permission p:userPermissions){
                mids.add(p.getMenu().getMenuId()+"");
            }
            //先一级菜单
            for(Menu m:menus){
                if(mids.contains(m.getMenuId()+"")){
                    RoleMenu rm=new RoleMenu();
                    rm.setMenu(m);

                    //在检查二级菜单
                    if(m.getMenus().size()>0){
                        List<RoleMenu> chileMenus=new ArrayList<RoleMenu>();
                        for(Iterator<Menu> menuIt=m.getMenus().iterator();menuIt.hasNext();){
                            Menu childMenu=menuIt.next();
                            if(mids.contains(childMenu.getMenuId()+"")){
                                RoleMenu crm=new RoleMenu();
                                crm.setMenu(childMenu);
                                chileMenus.add(crm);
                            }
                        }
                        rm.setChildMenus(chileMenus);
                    }
                    roleMenus.add(rm);
                }
            }
            log.info("根据用户类型加载菜单完成"+roleMenus);
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userMenus",roleMenus);
        String json=JSON.toJSONString(map);
        System.out.println(json);

        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"读取用户操作菜单，显示后台首页"));
        return json;
    }
}
