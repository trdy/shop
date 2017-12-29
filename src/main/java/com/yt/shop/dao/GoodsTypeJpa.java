package com.yt.shop.dao;

import com.yt.shop.model.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsTypeJpa extends JpaRepository<GoodsType,Long> {

    @Query(value = "from GoodsType gt where gt.goodsPlate.gpid=?1")
    List<GoodsType> findGoodsTypeByGoodsPlateId(Long goodsPlateId);
}
