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

/**
 * 后台管理首页相关数据加载控制器
 */
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
     * @param request 请求对象
     * @return 该用户配置的菜单集合
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/menu
     *     请求方式：get
     *     请求参数：无
     *</pre>
     * 回应内容：
     *
     * <pre>
     *  正确回应：
     *      {"code":1,
     *      "message":"正确返回结果",
     *      "data":[
                    {"menuId": 0,
                    "menuName": "首页",
                    "menuUrl": "/admin/menu"
                    },

                    {"childMenus": [
                            {"menuId": 130,"menuName": "网站基本信息","menuUrl": "../admin/shopInfoSet"},
                            {"menuId": 140,"menuName": "首页轮播图设置","menuUrl": "../admin/shopBanner"}
                            ],
                     "menuId": 10,
                     "menuName": "网站设置",
                     "menuUrl": ""
                    },
                    {"childMenus": [
                            {"menuId": 180,"menuName": "商品审批","menuUrl": "../admin/goodsManagerApprove"},
                            {"menuId": 340,"menuName": "会员等级设置","menuUrl": "../admin/userTypeUpgrade"}
                            ],
                    "menuId": 399,
                    "menuName": "后台查看商品",
                    "menuUrl": "../admin/backGoodsList"
                    }
            ]}
     * </pre>
     *  <table border="1">
     *      <caption>data对象属性</caption>
     *  <tr><td>属性</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>menuId</td><td>菜单编号</td><td>&nbsp;</td></tr>
     *  <tr><td>menuName</td><td>菜单名称</td><td>&nbsp;</td></tr>
     *  <tr><td>menuUrl</td><td>菜单地址</td><td>&nbsp;</td></tr>
     *  <tr><td>childMenus</td><td>子菜单集合</td><td>二级菜单无子菜单</td></tr>
     *  </table>
     * <pre>
     *   错误回应：
     *     {"code":-1,"message":"用户未登录",data:""}
     * </pre>
     *
     *
     */
    @RequestMapping(value = "/admin/menu",method = RequestMethod.GET)
    public String backIndexMenu(HttpServletRequest request){

        //读取菜单信息
        List<Menu> pm=permissionService.findParnetMenuList();
        //获得用户信息
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        log.info(userInfo.getUserName() + "访问后台管理菜单");

        List<Permission> userPermissions=permissionService.findPermissionByUserTypeId(userInfo.getUserType().getUserTypeId());

        //先获取用配置的权限的menuId
        List<String> mids=new ArrayList<String>();
        for(Permission p:userPermissions){
            mids.add(p.getMenu().getMenuId()+"");
        }

        //定义一个待输出的菜单集合
        List<MenuItem> menuItemList=loadMenuItemList(pm,userInfo,mids);
        log.info("根据userName:"+userInfo.getUserName()+"的userTypeId："+userInfo.getUserType().getUserTypeId()+"查询权限");

        //默认添加一个首页菜单
        MenuItem indexMenuItem=new MenuItem();
        indexMenuItem.setMenuId(0L);
        indexMenuItem.setMenuName("首页");
        indexMenuItem.setMenuUrl("/admin/menu");
        menuItemList.add(0,indexMenuItem);
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"访问后台首页，读取菜单"));
        return JsonUtil.getReturnJson(1,"正确加载用户配置的权限菜单",menuItemList);
    }

    /**
     * 递归加载菜单和子菜单
     * @param menus
     * @return
     */
    private List<MenuItem> loadMenuItemList(Collection<Menu> menus,UserInfo userInfo,List<String> mids){
        List<MenuItem> menuItemList=new ArrayList<MenuItem>();

        for(Menu m:menus){
            if(userInfo.getUserName().equals(Constract.ROOT_USER)||mids.contains(m.getMenuId()+"")) {
                MenuItem menuItem = new MenuItem();
                menuItem.setMenuId(m.getMenuId());
                menuItem.setMenuName(m.getMenuName());
                menuItem.setMenuUrl(m.getMenuUrl());
                List<MenuItem> childMenuItemList = loadMenuItemList(m.getMenus(), userInfo,mids);
                menuItem.setChildMenus(childMenuItemList);
                menuItemList.add(menuItem);
            }
        }
        return menuItemList;
    }



    /**
     * 用户登录后，访问后台首页，加载用户信息
     * @param request 请求对象
     * @return 用户信息json字符串
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/loadUser
     *     请求方式：get
     *     请求参数：无
     *</pre>
     * 回应内容：
     * <pre>
     *     正确回应：
     *     {
            "code":1,
            "message":"正确加载用户信息",
            "data":{
            "code": "420400197402111013",
            "duihuanScore": 0,
            "email": "371866295@qq.com",
            "gouwuScore": 0,
            "headPic": "/upload/userHead/defaultHead.jpg",
            "name": "admin",
            "regTime": 1513217876000,
            "shopCars": [],
            "tradePass": "123",
            "tuiguanScore": 0,
            "userId": 1,
            "userName": "admin",
            "userState": 1,
            "userType": {
            "permissions": [],
            "userTypeId": 10,
            "userTypeName": "管理员"
            },
            "xianjinScore": 0,
            "zengzhiScore": 0
            }
            }
     * </pre>
     * <table border="1">
     *     <caption>data对象属性</caption>
     *  <tr><td>属性</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>userId</td><td>用户编号</td><td>&nbsp;</td></tr>
     *  <tr><td>userName</td><td>用户登录名称</td><td>&nbsp;</td></tr>
     *  <tr><td>name</td><td>用户真实姓名</td><td>&nbsp;</td></tr>
     *  <tr><td>code</td><td>用户身份证号</td><td>&nbsp;</td></tr>
     *  <tr><td>regTime</td><td>注册时间</td><td>&nbsp;</td></tr>
     *  <tr><td>headPic</td><td>用户头像文件路径地址</td><td>&nbsp;</td></tr>
     *  <tr><td>userType.userTypeId</td><td>用户类型编号</td><td>&nbsp;</td></tr>
     *  <tr><td>userType.userTypeName</td><td>用户类型名称</td><td>&nbsp;</td></tr>
     *  <tr><td>tuiguanScore</td><td>推广积分</td><td>&nbsp;</td></tr>
     *  <tr><td>gouwuScore</td><td>购物积分</td><td>&nbsp;</td></tr>
     *  <tr><td>duihuanScore</td><td>兑换积分</td><td>&nbsp;</td></tr>
     *  <tr><td>xianjinScore</td><td>现金积分</td><td>&nbsp;</td></tr>
     *  <tr><td>zengzhiScore</td><td>增值积分</td><td>&nbsp;</td></tr>
     *  <tr><td>userState</td><td>用户状态</td><td>1：正常用户，0:冻结用户</td></tr>
     *  </table>
     * <pre>
     *     错误回应：
     *      {"code":-1,"message":"用户未登录","data":""}
     * </pre>
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

        userInfo.setUserPass(null);//让返回的json没有密码关键信息
        userInfo.setQuestion(null);
        userInfo.setAnswer(null);

        return JsonUtil.getReturnJson(1,"正确加载用户信息",userInfo);
    }

}
