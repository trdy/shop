package com.yt.shop.dao;

import com.yt.shop.model.ShopNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopNewsJpa extends JpaRepository<ShopNews,Long> {

    @Query(value = "from ShopNews s order by s.newsDate desc")
    List<ShopNews> findShopNewsList();
}
