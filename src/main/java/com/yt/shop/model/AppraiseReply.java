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
 *
 * 评论回复表
create table appraise_reply
(
   arid                 int not null auto_increment,
   apprid               int,
   appr_reply_date      timestamp,
   appr_reply_text      varchar(1024),
   sale_id              int,
   primary key (arid)
);
 * @author Administrator
 *
 */
@Entity
@Table(name="appraise_reply")
public class AppraiseReply implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long arid;
	
	@ManyToOne
	@JoinColumn(name="apprid")
	private Appraise appraise;
	
	@Column(name="appr_reply_date")
	private Timestamp apprReplyDate;
	
	@Column(name="appr_reply_text")
	private String apprReplyText;
	
	@ManyToOne
	@JoinColumn(name="sale_id")
	private UserInfo sale;

	public Long getArid() {
		return arid;
	}

	public void setArid(Long arid) {
		this.arid = arid;
	}

	public Appraise getAppraise() {
		return appraise;
	}

	public void setAppraise(Appraise appraise) {
		this.appraise = appraise;
	}

	public Timestamp getApprReplyDate() {
		return apprReplyDate;
	}

	public void setApprReplyDate(Timestamp apprReplyDate) {
		this.apprReplyDate = apprReplyDate;
	}

	public String getApprReplyText() {
		return apprReplyText;
	}

	public void setApprReplyText(String apprReplyText) {
		this.apprReplyText = apprReplyText;
	}

	public UserInfo getSale() {
		return sale;
	}

	public void setSale(UserInfo sale) {
		this.sale = sale;
	}
	
}
