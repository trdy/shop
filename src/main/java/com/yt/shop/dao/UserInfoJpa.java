package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户表操作dao
 *
 * create table user_info
 (
 user_id              int not null auto_increment,
 user_type_id         int,
 user_name            varchar(32),
 user_pass            varchar(32),
 name                 varchar(32),
 code                 varchar(32),
 trade_pass           varchar(32),
 reg_time             timestamp,
 head_pic             varchar(128),
 email                varchar(64),
 question             varchar(512),
 answer               varchar(512),
 refuser_id           int,
 tuiguan_score        decimal(10,2),
 gouwu_score          decimal(10,2),
 duihuan_score        decimal(10,2),
 zengzhi_score        decimal(10,2),
 xianjin_score        decimal(10,2),
 user_state           int comment '0:未开通
 1.开通',
 primary key (user_id)
 );
 */
public interface UserInfoJpa  extends JpaRepository<UserInfo,Long> {

    /**
     * 后台管理员登录根据用户名和密码查询后台用户
     * @param userName 用户名
     * @param userPass 密码
     * @return 用户对象
     */
    @Query(value = "select * from user_info u where u.user_type_id<100 and u.user_state=1 and u.user_name=?1 and u.user_pass=?2",nativeQuery = true)
    UserInfo findBackUserByNameAndPass(String userName, String userPass);
}
