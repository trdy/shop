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
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 订单表
 * 
 * CREATE TABLE TORDER
(
   ORDER_ID             INT NOT NULL AUTO_INCREMENT,
   ORDER_TIME           TIMESTAMP,
   ORDER_TOTAL          DECIMAL(10,2),
   PAY_TOTAL            DECIMAL(10,2),
   PAY_MENT             INT,
   PAY_STATE            INT COMMENT 0:新订单， 10.发货中， 20.用户确认， 30.申请退货， 40.完成， 50.退货完成,
   BUY_ID               INT,
   SALE_ID              INT,
   DEST_ADDRESS         VARCHAR(1024),
   PRIMARY KEY (ORDER_ID)
);

 */

@Entity
@Table(name="torder")
public class Torder implements java.io.Serializable {

	// Fields

	@Id
	@Column(name="order_id")
	private String orderId;
	
	@ManyToOne
	@JoinColumn(name="buy_id")
	private UserInfo buy;
	
	@ManyToOne
	@JoinColumn(name="sale_id")
	private UserInfo sale;
	
	@Column(name="order_time")
	private Timestamp orderTime;
	
	@Column(name="order_total")
	private Double orderTotal;
	
	@Column(name="pay_total")
	private Double payTotal;
	
	@Column(name="pay_ment")
	private Integer payMent;
	
	@Column(name="pay_state")
	private Integer payState;
	
	@Column(name="dest_address")
	private String destAddress;
	
	@Column(name="delive_com")
	private String deliveCom;
	
	@Column(name="delive_no")
	private String deliveNo;
	
	@Column(name="send_date")
	private Timestamp sendDate;
	
	@Column(name="rece_date")
	private Timestamp receDate;
	
	@OneToMany(targetEntity=OrderDetail.class,fetch=FetchType.EAGER)
	@OrderBy("odid")
	@JoinColumn(name="order_id")
	private Set<OrderDetail> orderDetails;

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public UserInfo getBuy() {
		return buy;
	}

	public void setBuy(UserInfo buy) {
		this.buy = buy;
	}

	public UserInfo getSale() {
		return sale;
	}

	public void setSale(UserInfo sale) {
		this.sale = sale;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Double getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(Double payTotal) {
		this.payTotal = payTotal;
	}

	public Integer getPayMent() {
		return payMent;
	}

	public void setPayMent(Integer payMent) {
		this.payMent = payMent;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getDeliveCom() {
		return deliveCom;
	}

	public void setDeliveCom(String deliveCom) {
		this.deliveCom = deliveCom;
	}

	public String getDeliveNo() {
		return deliveNo;
	}

	public void setDeliveNo(String deliveNo) {
		this.deliveNo = deliveNo;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public Timestamp getReceDate() {
		return receDate;
	}

	public void setReceDate(Timestamp receDate) {
		this.receDate = receDate;
	}
}