package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoJpa  extends JpaRepository<UserInfo,Long> {

    /**
     * 后台管理员登录根据用户名和密码查询后台用户
     * @param userName
     * @param userPass
     * @return
     */
    @Query(value = "select * from user_info u where u.user_type_id<100 and u.user_state=1 and u.user_name=?1 and u.user_pass=?2",nativeQuery = true)
    UserInfo findBackUserByNameAndPass(String userName, String userPass);
}
