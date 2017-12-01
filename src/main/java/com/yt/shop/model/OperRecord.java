package com.yt.shop.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
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
@Entity
@Table(name="oper_record")
public class OperRecord {

	@Id
	@GeneratedValue
	private Long opreid;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="oper_content")
	private String operContent;
	
	@Column(name="oper_date")
	private Timestamp operDate;
	
	public Long getOpreid() {
		return opreid;
	}
	public void setOpreid(Long opreid) {
		this.opreid = opreid;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getOperContent() {
		return operContent;
	}
	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}
	public Timestamp getOperDate() {
		return operDate;
	}
	public void setOperDate(Timestamp operDate) {
		this.operDate = operDate;
	}
	
	public OperRecord(){}
	public OperRecord(UserInfo userInfo, String ipAddress, String operContent) {
		super();
		this.userInfo = userInfo;
		this.ipAddress = ipAddress;
		this.operContent = operContent;
		this.operDate =new Timestamp(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "OperRecord{" +
				"opreid=" + opreid +
				", userInfo=" + userInfo +
				", ipAddress='" + ipAddress + '\'' +
				", operContent='" + operContent + '\'' +
				", operDate=" + operDate +
				'}';
	}
}
