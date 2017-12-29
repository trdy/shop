package com.yt.shop.dao;

import com.yt.shop.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsJpa extends JpaRepository<Goods,Long> {

    @Query(value = "from Goods g where g.goodsType.gtid=?1")
    List<Goods> findGoodsListByGoodsTypeId(Long goodsTypeId);
}
