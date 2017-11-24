package com.yt.shop.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * anthor:liyun
 * create:2017-11-22 13:51
 *
 * create table tab_user(user_id int primary key auto_increment,user_name varchar(20),user_pass varchar(20),birth TIMESTAMP)
 */
@Entity
@Table(name = "tab_user")
public class UserInfo implements java.io.Serializable{
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @Column(name="user_name")
    private String userName;

    @Column(name = "user_pass")
    private String userPass;

    private Timestamp birth;

    public UserInfo() {
    }

    public UserInfo(int userId, String userName, String userPass,Timestamp birth) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.birth = birth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", birth=" + birth +
                '}';
    }
}
