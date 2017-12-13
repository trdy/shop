package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoJpa  extends JpaRepository<UserInfo,Long> {

    @Query(value = "select * from user_info u where u.user_type<100 and u.user_state=1 u.user_name=?1 and u.user_pass=?2",nativeQuery = true)
    UserInfo findBackUserByNameAndPass(String userName, String userPass);
}
