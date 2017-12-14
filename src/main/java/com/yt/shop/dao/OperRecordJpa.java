package com.yt.shop.dao;

import com.yt.shop.model.OperRecord;
import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 后台操作记录dao
 * 操作记录表
 create table oper_record
 (
 opreid               int not null auto_increment,
 user_id              int,
 ip_address           varchar(128),
 oper_content         varchar(2048),
 oper_date            timestamp,
 primary key (opreid)
 );
 * @author Administrator
 *
 */
public interface OperRecordJpa extends JpaSpecificationExecutor<OperRecord>,PagingAndSortingRepository<OperRecord,Long> {


}
