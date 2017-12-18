package com.yt.shop.service;

import com.yt.shop.dao.ShopBannerJpa;
import com.yt.shop.dao.ShopInfoJpa;
import com.yt.shop.dao.UserTypeJpa;
import com.yt.shop.model.ShopBanner;
import com.yt.shop.model.ShopInfo;
import com.yt.shop.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 网站基本信息业务类
 */
@Service("siteBaseInfoService")
public class SiteBaseInfoService {

    @Autowired
    private ShopInfoJpa shopInfoJpa;

    @Autowired
    private ShopBannerJpa shopBannerJpa;

    @Autowired
    private UserTypeJpa userTypeJpa;

    @Transactional
    public ShopInfo findLastShopInfo() {
        return shopInfoJpa.findLastShopInfo();
    }

    @Transactional
    public void insertShopInfo(ShopInfo shopInfo) {
        shopInfoJpa.save(shopInfo);
    }

    @Transactional
    public List<ShopBanner> findShopBannerList() {
        return shopBannerJpa.findAll();
    }

    @Transactional
    public ShopBanner findShopBannerById(Long shopBannerId) {
        return shopBannerJpa.findOne(shopBannerId);
    }

    @Transactional
    public void insertShopBanner(ShopBanner shopBanner) {
        shopBannerJpa.save(shopBanner);
    }

    @Transactional
    public void deleteShopBanner(Long shopBannerId) {
        shopBannerJpa.delete(shopBannerId);
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
    public void insertUserType(UserType userType) {
        userTypeJpa.save(userType);
    }

    @Transactional
    public void deleteUserType(Long userTypeId) {
        userTypeJpa.delete(userTypeId);
    }
}
