package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;



public interface TestDBJPA extends JpaSpecificationExecutor<UserInfo>,PagingAndSortingRepository<UserInfo,Long> {

    @Query(value = "from UserInfo u where u.userName=:userName and u.userPass=:userPass")
    UserInfo findUserInfoByNameAndPass(@Param("userName") String userName, @Param("userPass") String userPass);


}
