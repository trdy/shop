package com.yt.shop.dao;

import com.yt.shop.common.BaseDao;
import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("testDBDao")
public interface TestDBDao extends BaseDao<UserInfo> {

    @Query(value = "from UserInfo u where u.userName=:userName and u.userPass=:userPass")
    UserInfo findUserInfoByNameAndPass(@Param("userName") String userName, @Param("userPass") String userPass);
}
