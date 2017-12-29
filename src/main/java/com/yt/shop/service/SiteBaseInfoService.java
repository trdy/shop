package com.yt.shop.service;

import com.yt.shop.dao.*;
import com.yt.shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 网站基本信息业务类
 */
@Service("siteBaseInfoService")
public class SiteBaseInfoService {

    //----------------------------------------------------
    @Autowired
    private ShopInfoJpa shopInfoJpa;

    @Transactional
    public ShopInfo findLastShopInfo() {
        return shopInfoJpa.findLastShopInfo();
    }

    @Transactional
    public void insertShopInfo(ShopInfo shopInfo) {
        shopInfoJpa.save(shopInfo);
    }

    //----------------------------------------------------
    @Autowired
    private ShopBannerJpa shopBannerJpa;

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

    //----------------------------------------------------
    @Autowired
    private UserTypeJpa userTypeJpa;

    @Transactional
    public List<UserType> findUserTypeList() {
        return userTypeJpa.findAll();
    }

    @Transactional
    public UserType findUserTypeById(Long userTypeId) {
        return userTypeJpa.findOne(userTypeId);
    }

    @Transactional
    public void saveUserType(UserType userType) {
        userTypeJpa.save(userType);
    }

    @Transactional
    public void deleteUserType(Long userTypeId) {
        userTypeJpa.delete(userTypeId);
    }

    //----------------------------------------------------
    @Autowired
    private GoodsPlateJpa goodsPlateJpa;

    @Transactional
    public List<GoodsPlate> findGoodsPlateList() {
        return goodsPlateJpa.findAll();
    }

    @Transactional
    public GoodsPlate findGoodsPlateById(Long goodsPlateId) {
        return goodsPlateJpa.findOne(goodsPlateId);
    }

    @Transactional
    public void saveGoodsPlate(GoodsPlate goodsPlate) {
        goodsPlateJpa.save(goodsPlate);
    }

    @Transactional
    public void deleteGoodsPlateById(Long goodsPlateId) {
        goodsPlateJpa.delete(goodsPlateId);
    }

    //----------------------------------------------------
    @Autowired
    private GoodsTypeJpa goodsTypeJpa;

    @Transactional
    public List<GoodsType> findGoodsTypeList() {
        return goodsTypeJpa.findAll();
    }

    @Transactional
    public GoodsType findGoodsTypeById(Long goodsTypeId) {
        return goodsTypeJpa.findOne(goodsTypeId);
    }

    @Transactional
    public void saveGoodsType(GoodsType goodsType) {
        goodsTypeJpa.save(goodsType);
    }

    @Transactional
    public void deleteGoodsTypeById(Long goodsTypeId) {
        goodsTypeJpa.delete(goodsTypeId);
    }

    @Transactional
    public List<GoodsType> findGoodsTypeByGoodsPlateId(Long goodsPlateId) {
        return goodsTypeJpa.findGoodsTypeByGoodsPlateId(goodsPlateId);
    }

    //--------------------------------------------------------
    @Autowired
    private ShopNewsJpa shopNewsJpa;

    @Transactional
    public List<ShopNews> findSHopNewsList() {
        return shopNewsJpa.findShopNewsList();
    }

    @Transactional
    public void insertShopNew(ShopNews shopNews) {
        shopNewsJpa.save(shopNews);
    }


    //---------------------------------------------------------
    @Autowired
    private GoodsJpa goodsJpa;

    @Transactional
    public List<Goods> findGoodsListByGoodsTypeId(Long goodsTypeId) {
        return goodsJpa.findGoodsListByGoodsTypeId(goodsTypeId);
    }
}
