package com.yt.shop.dao;

import com.yt.shop.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * create table menu
 (
 menu_id              int not null auto_increment,
 menu_name            varchar(128),
 menu_url             varchar(512),
 menu_desc            varchar(1024),
 parent_menu_id       int,
 primary key (menu_id)
 );
 */
public interface MenuJpa extends JpaRepository<Menu,Long>{

    @Query(value = "from Menu m where m.parentMenu=null order by menuId")
    List<Menu> findParnetMenuList();
}
