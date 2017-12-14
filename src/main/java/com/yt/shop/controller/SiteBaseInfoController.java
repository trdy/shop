package com.yt.shop.controller;

import com.yt.shop.common.Constract;
import com.yt.shop.common.FileUtil;
import com.yt.shop.common.JsonUtil;
import com.yt.shop.dao.OperRecordJpa;
import com.yt.shop.model.OperRecord;
import com.yt.shop.model.ShopInfo;
import com.yt.shop.model.UserInfo;
import com.yt.shop.service.SiteBaseInfoService;
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
     * @return shopInfo对象
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
