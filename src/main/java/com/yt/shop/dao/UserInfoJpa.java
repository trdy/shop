package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserInfoJpa  extends JpaSpecificationExecutor<UserInfo>,PagingAndSortingRepository<UserInfo,Long> {

    @Query(value = "select * from user_info u where u.user_name=?1 and u.user_pass=?2",nativeQuery = true)
    UserInfo findBackUserByNameAndPass(String userName, String userPass);
}
