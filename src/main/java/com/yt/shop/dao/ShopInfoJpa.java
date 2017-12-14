package com.yt.shop.dao;

import com.yt.shop.model.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 商城基本信息dao
 * create table shop_info
 (
 siid                 int not null auto_increment,
 net_shop_name        varchar(64),
 net_shop_logo        varchar(256),
 primary key (siid)
 );
 */
public interface ShopInfoJpa extends JpaRepository<ShopInfo,Long> {

    @Query(value = "select * from shop_info s order by s.siid desc limit 1",nativeQuery = true)
    ShopInfo findLastShopInfo();
}
