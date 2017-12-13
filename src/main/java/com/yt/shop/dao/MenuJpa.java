package com.yt.shop.dao;

import com.yt.shop.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuJpa extends JpaRepository<Menu,Long>{

    @Query(value = "from Menu m where m.parentMenu=null order by menuId")
    List<Menu> findParnetMenuList();
}
