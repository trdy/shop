package com.yt.shop.dao;

import com.yt.shop.model.OperRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * create table oper_record
 (
 opreid               int not null auto_increment,
 user_id              int,
 ip_address           varchar(128),
 oper_content         varchar(2048),
 oper_date            timestamp,
 primary key (opreid)
 );
 */
@Repository("operRecordDao")
public class OperRecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insertOperRecord(OperRecord operRecord) {

        String sql="insert into oper_record(user_id,ip_address,oper_content,oper_date)";
        sql+=" values("+operRecord.getUserInfo().getUserId()+",'"+operRecord.getIpAddress()+"','"+operRecord.getOperContent()+"','"+operRecord.getOperDate()+"')";
        jdbcTemplate.execute(sql);
    }
}
