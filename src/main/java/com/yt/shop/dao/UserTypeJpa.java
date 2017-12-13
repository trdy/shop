package com.yt.shop.dao;

import com.yt.shop.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeJpa extends JpaRepository<UserType,Long> {
}
