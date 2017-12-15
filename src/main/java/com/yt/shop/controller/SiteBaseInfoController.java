package com.yt.shop.controller;

import com.yt.shop.common.Constract;
import com.yt.shop.common.FileUtil;
import com.yt.shop.common.JsonUtil;
import com.yt.shop.dao.OperRecordJpa;
import com.yt.shop.model.OperRecord;
import com.yt.shop.model.ShopInfo;
import com.yt.shop.model.UserInfo;
import com.yt.shop.service.SiteBaseInfoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 网站基本信息设置，转到设置网站名称和logo页面，获得页面需要JSON数据
     * @param request
     * @return shopInfo对象json字符串
     *
     *<p/>
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
     *  <tr><td>属性</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>nsLogo</td><td>商城lOGO文件路径地址</td><td>&nbsp;</td></tr>
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
     * 网站基本信息设置，修改或保存网站名称和上传LOGO
     * @param file
     * @param request
     * @return 1：成功 0：失败
     *
     * *<p/>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/shopBaseInfoSave
     *     请求方式：post enctype="multipart/form-data"
     *     请求参数：
     *</pre>
     * <table border="1">
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

            return JsonUtil.getReturnJson(1);
        } catch (Exception e) {
            return JsonUtil.getReturnJson(0);
        }
    }


}
