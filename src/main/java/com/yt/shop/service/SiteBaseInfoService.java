package com.yt.shop.service;

import com.yt.shop.dao.ShopBannerJpa;
import com.yt.shop.dao.ShopInfoJpa;
import com.yt.shop.model.ShopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 网站基本信息业务类
 */
@Service("siteBaseInfoService")
public class SiteBaseInfoService {

    @Autowired
    private ShopInfoJpa shopInfoJpa;

    @Autowired
    private ShopBannerJpa shopBannerJpa;

    @Transactional
    public ShopInfo findLastShopInfo() {
        return shopInfoJpa.findLastShopInfo();
    }

    @Transactional
    public void insertShopInfo(ShopInfo shopInfo) {
        shopInfoJpa.save(shopInfo);
    }
}
