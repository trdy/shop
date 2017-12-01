package com.yt.shop.model;;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商品审批
drop table if exists goods_approver;

create table goods_approver
(
   gaid                 int not null auto_increment,
   goods_id             int,
   approver_date        timestamp,
   approver_state       int comment '0:未处理 1:已处理',
   approver_reason      varchar(512),
   primary key (gaid)
);


 * @author Administrator
 *
 */
@Entity
@Table(name="goods_approver")
public class GoodsApprover {

	@Id
	@GeneratedValue
	private Long gaid;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@Column(name="approver_date")
	private Timestamp approverDate;
	
	@Column(name="approver_state")
	private int approverState;
	
	@Column(name="approver_reason")
	private String approverReason;
	
	public String getApproverReason() {
		return approverReason;
	}
	public void setApproverReason(String approverReason) {
		this.approverReason = approverReason;
	}
	public Long getGaid() {
		return gaid;
	}
	public void setGaid(Long gaid) {
		this.gaid = gaid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Timestamp getApproverDate() {
		return approverDate;
	}
	public void setApproverDate(Timestamp approverDate) {
		this.approverDate = approverDate;
	}
	public int getApproverState() {
		return approverState;
	}
	public void setApproverState(int approverState) {
		this.approverState = approverState;
	}
	
}
