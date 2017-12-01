package com.yt.shop.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 评论表
 * 会员购买商品发布评论
create table appraise
(
   apprid               int not null auto_increment,
   goods_id             int,
   appr_score           int,
   appr_date            timestamp,
   appr_content         varchar(1024),
   buy_id               int,
   primary key (apprid)
);
 * @author Administrator
 *
 */
@Entity
@Table(name="appraise")
public class Appraise implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long apprid;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@ManyToOne
	@JoinColumn(name="buy_id")
	private UserInfo buy;
	
	@Column(name="appr_score")
	private int apprScore;
	
	@Column(name="appr_date")
	private Timestamp apprDate;
	
	@Column(name="appr_content")
	private String apprContent;
	
	@OneToMany(targetEntity=AppraiseReply.class,fetch=FetchType.EAGER)
	@JoinColumn(name="apprid")
	private Set<AppraiseReply> appraiseReplys;

	public Appraise(){}

	public Long getApprid() {
		return apprid;
	}

	public void setApprid(Long apprid) {
		this.apprid = apprid;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public UserInfo getBuy() {
		return buy;
	}

	public void setBuy(UserInfo buy) {
		this.buy = buy;
	}

	public int getApprScore() {
		return apprScore;
	}

	public void setApprScore(int apprScore) {
		this.apprScore = apprScore;
	}

	public Timestamp getApprDate() {
		return apprDate;
	}

	public void setApprDate(Timestamp apprDate) {
		this.apprDate = apprDate;
	}

	public String getApprContent() {
		return apprContent;
	}

	public void setApprContent(String apprContent) {
		this.apprContent = apprContent;
	}

	public Set<AppraiseReply> getAppraiseReplys() {
		return appraiseReplys;
	}

	public void setAppraiseReplys(Set<AppraiseReply> appraiseReplys) {
		this.appraiseReplys = appraiseReplys;
	}
	
}
