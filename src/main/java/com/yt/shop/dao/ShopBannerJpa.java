package com.yt.shop.dao;

import com.yt.shop.model.ShopBanner;
import com.yt.shop.model.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 首页轮播图
 * create table shop_banner
 (
 banid                int not null auto_increment,
 banner_desc          varchar(256),
 banner_path          varchar(256),
 banner_url           varchar(256),
 primary key (banid)
 );

 */
public interface ShopBannerJpa extends JpaRepository<ShopBanner,Long> {
}
