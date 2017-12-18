package com.yt.shop.controller;

import com.yt.shop.common.Constract;
import com.yt.shop.common.FileUtil;
import com.yt.shop.common.JsonUtil;
import com.yt.shop.dao.OperRecordJpa;
import com.yt.shop.model.*;
import com.yt.shop.service.SiteBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 网站基本信息控制器
 */

@RestController
public class SiteBaseInfoController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperRecordJpa operRecordJpa;

    @Autowired
    private SiteBaseInfoService siteBaseInfoService;

    /////-------------------------------------网站设置--&gt;网站基本信息设置---------------------------------------------
    /**
     * 网站设置--&gt;网站基本信息设置，转到设置网站名称和logo页面，获得页面需要JSON数据
     * @param request 请求对象
     * @return shopInfo对象json字符串
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopInfoSet
     *     请求方式：get
     *     请求参数：无
     *</pre>
     * 回应内容：
     * <pre>
     *    正确回应：
     *      {"code":
     *          {"nslogo":"/upload/nslogo/a80b1b7e-6352-43cf-91b7-08c1faf0b0ff.gif",
     *          "nsname":"创福网",
     *          "siid":2}
     *      }
     * </pre>
     * <table border="1">
     *     <caption>json对象属性</caption>
     *  <tr><td>属性</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>nslogo</td><td>商城lOGO文件路径地址</td><td>&nbsp;</td></tr>
     *  <tr><td>nsname</td><td>商城名称</td><td>&nbsp;</td></tr>
     *  <tr><td>siid</td><td>记录序号</td><td>&nbsp;</td></tr>
     *  </table>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/shopInfoSet",method = RequestMethod.GET)
    public String shopBaseInfoSet(HttpServletRequest request){
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);

        log.info("访问网站基本信息设置，返回最后一个设置的网站信息");
        ShopInfo shopInfo=siteBaseInfoService.findLastShopInfo();

        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"访问网站基本信息菜单准备设置的网站信息"));
        return JsonUtil.getReturnJson(shopInfo);
    }

    /**
     * 网站设置--&gt;网站基本信息设置，修改或保存网站名称和上传LOGO
     * @param file 上传文件
     * @param request 请求对象
     * @return 1：成功 0：失败
     *
     * *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopBaseInfoSave
     *     请求方式：post enctype="multipart/form-data"
     *     请求参数：nslogo(上传文件),nsname,nsid
     *</pre>
     * <table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>nslogo</td><td>上传商品图片文件</td><td>&nbsp;</td></tr>
     *  <tr><td>nsname</td><td>商城名称</td><td>&nbsp;</td></tr>
     *  <tr><td>nsid</td><td>商城id记录号</td><td>可以不写，如果有该参数，则修改变成修改数据</td></tr>
     *  </table>
     * 回应内容：
     * <pre>
     *    正确回应：
     *      {"code":1}  //成功
     *      {"code":0}  //失败
     * </pre>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/shopBaseInfoSave",method = RequestMethod.POST)
    public String shopBaseInfoSave(@RequestParam("nslogo") MultipartFile file, HttpServletRequest request) {
        log.info("访问网站基本信息设置，设置网站名称，上传logo");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);

        try {
            ShopInfo shopInfo = new ShopInfo();
            String nsid = request.getParameter("nsid");
            if(null!=nsid&&!"".equals(nsid)){
                shopInfo.setSiid(Long.parseLong(nsid));
            }
            String nsname = request.getParameter("nsname");
            if(null!=nsname&&!"".equals(nsname)){
                shopInfo.setNsname(nsname);
            }

            if (!file.isEmpty()) {
                String filePath = System.getProperty("user.dir") + "/upload/nslogo/";
                int n = file.getOriginalFilename().indexOf(".");
                String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(n);
                log.info("上传文件目录：" + filePath);

                try {
                    FileUtil.uploadFile(file.getBytes(), filePath, fileName);
                    shopInfo.setNslogo("/upload/nslogo/" + fileName);
                    log.info("上传成功");

                } catch (Exception e) {
                    log.error("执行文件上传：" + e.toString());

                }
            } else {
                shopInfo.setNslogo("/upload/nslogo/chuangfulogo.png");
            }

            siteBaseInfoService.insertShopInfo(shopInfo);
            log.debug("文件上传成功,数据正确保存：" + shopInfo);
            operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"重新设置网站基本信息"));

            return JsonUtil.getReturnJson(1);
        } catch (Exception e) {
            return JsonUtil.getReturnJson(0);
        }
    }

    /////--------------------------------------网站设置--&gt;轮播图设置--------------------------------------------

    /**
     * 网站设置--&gt;轮播图设置，后台管理员访问首页轮播图列表
     * @param request 请求对象
     * @return 轮播图信息集合，json字符串
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopBanner
     *     请求方式：get
     *     请求参数：无
     *</pre>
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *      {"code":[
     *          {"banid":1,
     *          "bannerDesc":"宣传1",
     *          "bannerPath":"/upload/banner/0f707c1e-5f59-46c1-963f-e336a44157fe.gif",
     *          "bannerUrl":"#"},
     *          {"banid":2,
     *          "bannerDesc":"宣传2",
     *          "bannerPath":"/upload/banner/71bddb6b-5707-4054-b3ed-141df9f16278.gif",
     *          "bannerUrl":"#"}]
     *       }
     * </pre>
     * <table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>banid</td><td>轮播图编号</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerDesc</td><td>轮播图简单描述</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerPath</td><td>轮播图文件链接地址</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerUrl</td><td>轮播图宣传链接地址</td><td>&nbsp;</td></tr>
     *  </table>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/shopBanner",method = RequestMethod.GET)
    public String backShopBanner(HttpServletRequest request){
        log.info("后台管理员访问轮播图设置列表");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);

        //查询轮播图列表
        List<ShopBanner> shopBannerList=siteBaseInfoService.findShopBannerList();

        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"读取轮播图列表信息"));
        return JsonUtil.getReturnJson(shopBannerList);
    }

    /**
     * 网站设置--&gt;轮播图设置，后台管理员删除轮播图
     * @param shopBannerId 轮播图编号
     * @param request 请求对象
     * @return json字符串
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopBanner/{轮播图id}
     *     请求方式：delete
     *     请求参数：无
     *</pre>
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *      {"code",1} //删除成功
     *      {"code",0} //删除失败
     * </pre>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/shopBanner/{shopBannerId}",method = RequestMethod.DELETE)
    public String backShopBannerDel(@PathVariable Long shopBannerId,HttpServletRequest request){
        log.info("后台管理员删除轮播图");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员删除轮播图"));

        try {
            siteBaseInfoService.deleteShopBanner(shopBannerId);
            return JsonUtil.getReturnJson("1");
        }catch (Exception e){
            return JsonUtil.getReturnJson("0");
        }
    }

    /**
     * 网站设置--&gt;轮播图设置，后台管理员访问轮播图编辑
     * @param shopBannerId 轮播图编号
     * @param request 请求对象
     * @return json字符串
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopBanner/{轮播图id}，如果是新增轮播图操作，id设置0
     *     请求方式：get
     *     请求参数：无
     *</pre>
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *      新增前：{"code","add"}
     *
     *      修改前：{"code":{
     *          "banid":1,
     *          "bannerDesc":"宣传1",
     *          "bannerPath":"/upload/banner/0f707c1e-5f59-46c1-963f-e336a44157fe.gif",
     *          "bannerUrl":"#"}
     *      } //表示待修改轮播图
     * </pre>
     * <table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>banid</td><td>轮播图编号</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerDesc</td><td>轮播图简单描述</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerPath</td><td>轮播图文件链接地址</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerUrl</td><td>轮播图宣传链接地址</td><td>&nbsp;</td></tr>
     *  </table>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/shopBanner/{shopBannerId}",method = RequestMethod.GET)
    public String backShopBannerEdit(@PathVariable Long shopBannerId,HttpServletRequest request){
        log.info("后台管理员访问轮播图编辑");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员访问轮播图编辑"));

        if(0==shopBannerId){
            return JsonUtil.getReturnJson("add");
        }else{
            ShopBanner shopBanner=siteBaseInfoService.findShopBannerById(shopBannerId);
            return JsonUtil.getReturnJson(shopBanner);
        }
    }

    /**
     * 网站设置--&gt;轮播图设置，后台管理员保存轮播图信息
     * @param file 轮播图图片文件
     * @param request 请求对象
     * @return json字符串
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopBanner
     *     请求方式：post  enctype="multipart/form-data"
     *     请求参数：bannerUrl,bannerDesc,banid,bannerFile(上传文件)
     *</pre>
     * <table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>bannerUrl</td><td>轮播图关联的宣传页链接地址</td><td>&nbsp;</td></tr>
     *  <tr><td>bannerDesc</td><td>轮播图描述</td><td>&nbsp;</td></tr>
     *  <tr><td>banid</td><td>轮播图id编号</td><td>如果有该参数，则修改变成修改数据</td></tr>
     *  <tr><td>bannerFile</td><td>轮播图文件</td><td>&nbsp;</td></tr>
     *  </table>
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *      {"code",1} //保存成功
     *      {"code",{0}} //保存失败
     * </pre>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/shopBanner",method = RequestMethod.POST)
    public String backShopBannerSave(@RequestParam("bannerFile") MultipartFile file, HttpServletRequest request){
        log.info("后台管理员保存轮播图信息");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);

        try {
            ShopBanner shopBanner = new ShopBanner();
            String banid = request.getParameter("banid");
            String bannerUrl = request.getParameter("bannerUrl");
            String bannerDesc = request.getParameter("bannerDesc");

            if(null!=banid&&!"".equals(banid)){
                shopBanner.setBanid(Long.parseLong(banid));
            }
            if(null!=bannerUrl&&!"".equals(bannerUrl)){
                shopBanner.setBannerUrl(bannerUrl);
            }
            if(null!=bannerDesc&&!"".equals(bannerDesc)){
                shopBanner.setBannerDesc(bannerDesc);
            }

            if (!file.isEmpty()) {
                String filePath = System.getProperty("user.dir") + "/upload/banner/";
                int n = file.getOriginalFilename().indexOf(".");
                String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(n);
                log.info("上传文件目录：" + filePath);

                try {
                    FileUtil.uploadFile(file.getBytes(), filePath, fileName);
                    shopBanner.setBannerPath("/upload/banner/" + fileName);
                    log.info("上传成功");

                } catch (Exception e) {
                    log.error("执行文件上传：" + e.toString());

                }
            }

            siteBaseInfoService.insertShopBanner(shopBanner);
            log.debug("文件上传成功,数据正确保存：" + shopBanner);
            operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员保存轮播图信息"));

            return JsonUtil.getReturnJson(1);
        } catch (Exception e) {
            return JsonUtil.getReturnJson(0);
        }
    }

    /////-----------------------------------网站设置--&gt;会员类型设置-------------------------------------------------

    /**
     * 网站设置--&gt;会员类型设置，查询所有会员类型列表
     * @param request 请求对象
     * @return 会员类型列表 json字符串
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/userType
     *     请求方式：get
     *     请求参数：无
     *</pre>
     *
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *      {
            "code": [
                {
                "permissions": [],
                "userTypeId": 10,
                "userTypeName": "管理员"
                },
                {
                "permissions": [],
                "userTypeId": 30,
                "userTypeName": "网站操作员"
                }]
            }
     * </pre>
     * <table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>userTypeId</td><td>会员类型编号</td><td>&nbsp;</td></tr>
     *  <tr><td>userTypeName</td><td>会员类型名称</td><td>&nbsp;</td></tr>
     *  <tr><td>permissions</td><td>配置的权限集合</td><td>&nbsp;</td></tr>
     *  </table>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/userType",method = RequestMethod.GET)
    public String backUserTypeList(HttpServletRequest request){
        log.info("查询所有会员类型列表");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);

        List<UserType> userTypeList=siteBaseInfoService.findUserTypeList();
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员查询所有会员类型列表"));
        return JsonUtil.getReturnJson(userTypeList);
    }

    /**
     * 网站设置--&gt;会员类型设置，会员类型编辑
     * @param userTypeId 会员编号
     * @param request 请求对象
     * @return json字符串

     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/userType/{userTypeId}
     *     请求方式：get
     *     请求参数：无
     *</pre>
     *
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *      转到新增: {"code":"add"}
     *      转到修改：{"code":{"permissions":[],"userTypeId":10,"userTypeName":"管理员"}}
     * </pre>
     * <table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>userTypeId</td><td>会员类型编号</td><td>&nbsp;</td></tr>
     *  <tr><td>userTypeName</td><td>会员类型名称</td><td>&nbsp;</td></tr>
     *  <tr><td>permissions</td><td>配置的权限集合</td><td>&nbsp;</td></tr>
     *  </table>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/userType/{userTypeId}",method = RequestMethod.GET)
    public String backUserTypeEdit(@PathVariable Long userTypeId,HttpServletRequest request){
        log.info("会员类型编辑");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员执行会员类型编辑"));

        if(0==userTypeId){
            return JsonUtil.getReturnJson("add");
        }else{
            UserType userType=siteBaseInfoService.findUserTypeById(userTypeId);
            return JsonUtil.getReturnJson(userType);
        }
    }

    /**
     * 网站设置--&gt;会员类型设置，会员类型保存
     * @param userType 会员类型json对象
     * @param request 请求对象
     * @return json字符串
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/userType
     *     请求方式：post
     *     请求参数：会员类型json对象
     *     格式：{"userTypeId":101,"userTypeName":"普通会员1"}
     *</pre>
     *<table border="1">
     *      <caption>json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>userTypeId</td><td>会员类型编号</td><td>为空时表示新增会员类型,否则会修改对应编号会员类型</td></tr>
     *  <tr><td>userTypeName</td><td>会员类型名称</td><td>&nbsp;</td></tr>
     *  </table>
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *       {"code":1} 保存成功
     *       {"code":0} 保存失败
     * </pre>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/userType",method = RequestMethod.POST)
    public String backUserTypeSave(@RequestBody UserType userType, HttpServletRequest request){
        log.info("会员类型保存");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员执行会员类型保存"));

        try {
            siteBaseInfoService.insertUserType(userType);
            return JsonUtil.getReturnJson("1");
        }catch (Exception e){
            return JsonUtil.getReturnJson("0");
        }
    }

    /**
     * 网站设置--&gt;会员类型设置，会员类型删除
     * @param userTypeId 会员编号
     * @param request 请求对象
     * @return json字符串
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/userType/{userTypeId}
     *     请求方式：delete
     *     请求参数：无
     *</pre>
     *
     * 回应内容：
     * <pre>
     *    正确回应：
     *       {"code":1} 删除成功
     *       {"code":0} 删除失败
     * </pre>
     * <pre>
     *    错误回应：
     *       {"code":-1} //用户未登录
     * </pre>
     */
    @RequestMapping(value = "/admin/userType/{userTypeId}",method = RequestMethod.DELETE)
    public String backUserTypeDel(@PathVariable Long userTypeId,HttpServletRequest request){
        log.info("会员类型保存");
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userInfo.getUserName()+"后台管理员执行会员类型删除"));

        try{
            siteBaseInfoService.deleteUserType(userTypeId);
            return JsonUtil.getReturnJson("1");
        }catch (Exception e){
            return JsonUtil.getReturnJson("0");
        }
    }

}