package com.yt.shop.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * create table person(pid int primary key auto_increment,pname varchar(50),birth timestamp,pimg varchar(100));
 */

@Entity
public class Person implements java.io.Serializable{

    @Id
    @GeneratedValue
    private Long pid;
    private String pname;
    private Timestamp birth;
    private String pimg;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", birth=" + birth +
                ", pimg='" + pimg + '\'' +
                '}';
    }
}
