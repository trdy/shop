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
充值提现表
create table save_cash_record
(
   sacaid               int not null auto_increment,
   buy_id               int,
   amount               decimal(10,2),
   scdate               timestamp,
   opuser_id            int,
   score_type           int comment '0：推广 1：购物 2：兑换 3：增值 4：现金',
   arrow                int comment '0：充值 1：提现',
   primary key (sacaid)
);

 */
@Entity
@Table(name="save_cash_record")
public class SaveCashRecord implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long sacaid;
	
	@ManyToOne
	@JoinColumn(name="buy_id")
	private UserInfo buy;
	
	private double amount;
	
	private Timestamp scdate;
	
	@ManyToOne
	@JoinColumn(name="opuser_id")
	private UserInfo oper;
	
	@Column(name="score_type")
	private int scoreType;
	private int arrow;
	public Long getSacaid() {
		return sacaid;
	}
	public void setSacaid(Long sacaid) {
		this.sacaid = sacaid;
	}
	public UserInfo getBuy() {
		return buy;
	}
	public void setBuy(UserInfo buy) {
		this.buy = buy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getScdate() {
		return scdate;
	}
	public void setScdate(Timestamp scdate) {
		this.scdate = scdate;
	}
	public UserInfo getOper() {
		return oper;
	}
	public void setOper(UserInfo oper) {
		this.oper = oper;
	}
	public int getScoreType() {
		return scoreType;
	}
	public void setScoreType(int scoreType) {
		this.scoreType = scoreType;
	}
	public int getArrow() {
		return arrow;
	}
	public void setArrow(int arrow) {
		this.arrow = arrow;
	}
	
}
