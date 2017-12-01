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
 * 会员交易记录表
 * create table user_trade_record
(
   utid                 int not null auto_increment,
   user_id              int,
   utdate               timestamp,
   utdesc               varchar(1024),
   tuiguan_score        decimal(10,2),
   tuiguan_add          decimal(10,2),
   tuiguan_red          decimal(10,2),
   gouwu_score          decimal(10,2),
   gouwu_add            decimal(10,2),
   gouwu_red            decimal(10,2),
   duihuan_score        decimal(10,2),
   duihuan_add          decimal(10,2),
   duihuan_red          decimal(10,2),
   zengzhi_score        decimal(10,2),
   zengzhi_add          decimal(10,2),
   zengzhi_red          decimal(10,2),
   xianjin_score        decimal(10,2),
   xianjin_add          decimal(10,2),
   xianjin_red          decimal(10,2),
   primary key (utid)
);
 * @author Administrator
 *
 */
@Entity
@Table(name="user_trade_record")
public class UserTradeRecord {

	@Id
	@GeneratedValue
	private Long utid;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	private Timestamp utdate;
	private String utdesc;
	
	@Column(name="tuiguan_score")
	private double tuiguanScore; //�ڳ���
	
	@Column(name="tuiguan_add")
	private double tuiguanAdd;
	
	@Column(name="tuiguan_red")
	private double tuiguanRed;
	
	@Column(name="gouwu_score")
	private double gouwuScore;
	
	@Column(name="gouwu_add")
	private double gouwuAdd;
	@Column(name="gouwu_red")
	private double gouwuRed;
	
	@Column(name="duihuan_score")
	private double duihuanScore;
	
	@Column(name="duihuan_add")
	private double duihuanAdd;
	@Column(name="duihuan_red")
	private double duihuanRed;
	
	@Column(name="zengzhi_score")
	private double zengzhiScore;

	@Column(name="zengzhi_add")
	private double zengzhiAdd;
	@Column(name="zengzhi_red")
	private double zengzhiRed;
	
	@Column(name="xianjin_score")
	private double xianjinScore;
	
	@Column(name="xianjin_add")
	private double xianjinAdd;
	@Column(name="xianjin_red")
	private double xianjinRed;

	public Long getUtid() {
		return utid;
	}

	public void setUtid(Long utid) {
		this.utid = utid;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Timestamp getUtdate() {
		return utdate;
	}

	public void setUtdate(Timestamp utdate) {
		this.utdate = utdate;
	}

	public String getUtdesc() {
		return utdesc;
	}

	public void setUtdesc(String utdesc) {
		this.utdesc = utdesc;
	}

	public double getTuiguanScore() {
		return tuiguanScore;
	}

	public void setTuiguanScore(double tuiguanScore) {
		this.tuiguanScore = tuiguanScore;
	}

	public double getGouwuScore() {
		return gouwuScore;
	}

	public void setGouwuScore(double gouwuScore) {
		this.gouwuScore = gouwuScore;
	}

	public double getDuihuanScore() {
		return duihuanScore;
	}

	public void setDuihuanScore(double duihuanScore) {
		this.duihuanScore = duihuanScore;
	}

	public double getZengzhiScore() {
		return zengzhiScore;
	}

	public void setZengzhiScore(double zengzhiScore) {
		this.zengzhiScore = zengzhiScore;
	}

	public double getXianjinScore() {
		return xianjinScore;
	}

	public void setXianjinScore(double xianjinScore) {
		this.xianjinScore = xianjinScore;
	}

	public double getTuiguanAdd() {
		return tuiguanAdd;
	}

	public void setTuiguanAdd(double tuiguanAdd) {
		this.tuiguanAdd = tuiguanAdd;
	}

	public double getTuiguanRed() {
		return tuiguanRed;
	}

	public void setTuiguanRed(double tuiguanRed) {
		this.tuiguanRed = tuiguanRed;
	}

	public double getGouwuAdd() {
		return gouwuAdd;
	}

	public void setGouwuAdd(double gouwuAdd) {
		this.gouwuAdd = gouwuAdd;
	}

	public double getGouwuRed() {
		return gouwuRed;
	}

	public void setGouwuRed(double gouwuRed) {
		this.gouwuRed = gouwuRed;
	}

	public double getDuihuanAdd() {
		return duihuanAdd;
	}

	public void setDuihuanAdd(double duihuanAdd) {
		this.duihuanAdd = duihuanAdd;
	}

	public double getDuihuanRed() {
		return duihuanRed;
	}

	public void setDuihuanRed(double duihuanRed) {
		this.duihuanRed = duihuanRed;
	}

	public double getZengzhiAdd() {
		return zengzhiAdd;
	}

	public void setZengzhiAdd(double zengzhiAdd) {
		this.zengzhiAdd = zengzhiAdd;
	}

	public double getZengzhiRed() {
		return zengzhiRed;
	}

	public void setZengzhiRed(double zengzhiRed) {
		this.zengzhiRed = zengzhiRed;
	}

	public double getXianjinAdd() {
		return xianjinAdd;
	}

	public void setXianjinAdd(double xianjinAdd) {
		this.xianjinAdd = xianjinAdd;
	}

	public double getXianjinRed() {
		return xianjinRed;
	}

	public void setXianjinRed(double xianjinRed) {
		this.xianjinRed = xianjinRed;
	}
}
