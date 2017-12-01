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
 * 普通会员申请成为卖家
 * create table accept_sale(asid int primary key auto_increment,
 * user_id int,				//申请的会员id
 * accept_date timestamp,	//申请日期
 * apply_status int,		//申请处理状态0:未审批 1:审批
 * apply_date timestamp);	//审批日期
 * @author Administrator
 *
 */
@Entity
@Table(name="accept_sale")
public class AcceptSale implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long asid;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	@Column(name="accept_date")
	private Timestamp acceptDate;
	
	@Column(name="apply_stats")
	private int applyStatus;
	
	@Column(name="apply_date")
	private Timestamp applyDate;

	public Long getAsid() {
		return asid;
	}

	public void setAsid(Long asid) {
		this.asid = asid;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Timestamp getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Timestamp acceptDate) {
		this.acceptDate = acceptDate;
	}

	public int getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(int applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Timestamp getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}
	
}
