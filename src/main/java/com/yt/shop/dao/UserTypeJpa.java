package com.yt.shop.dao;

import com.yt.shop.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户类型表
 * create table user_type
 (
 user_type_id         int not null auto_increment,
 user_type_name       varchar(32),
 primary key (user_type_id)
 );
 */
public interface UserTypeJpa extends JpaRepository<UserType,Long> {
}
