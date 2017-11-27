package com.yt.shop.dao;


import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



/**
 * anthor:liyun
 * create:2017-11-24 14:29
 */
@Repository("testDBDao")
public interface TestDBDao extends JpaSpecificationExecutor<UserInfo>,PagingAndSortingRepository<UserInfo, Long> {

    @Query(value = "from UserInfo u where u.userName=:userName and u.userPass=:userPass")
    UserInfo findUserInfoByNameAndPass(@Param("userName") String userName, @Param("userPass") String userPass);
}
