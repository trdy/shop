package com.yt.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 订单明细
CREATE TABLE ORDER_DETAIL
(
   ODID                 INT NOT NULL AUTO_INCREMENT,
   GOODS_ID             INT,
   ORDER_ID             INT,
   ODNUM                INT,
   ODPRICE              DECIMAL(10,2),
   ODPARAM              VARCHAR(512),
   PRIMARY KEY (ODID)
);
 */

@Entity
@Table(name="order_detail")
public class OrderDetail implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="odid")
	private Long odid;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Torder torder;
	private Integer odnum;
	private Double odprice;
	private String odparam;


	public Long getOdid() {
		return this.odid;
	}

	public void setOdid(Long odid) {
		this.odid = odid;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Torder getTorder() {
		return torder;
	}

	public void setTorder(Torder torder) {
		this.torder = torder;
	}

	public Integer getOdnum() {
		return this.odnum;
	}

	public void setOdnum(Integer odnum) {
		this.odnum = odnum;
	}

	public Double getOdprice() {
		return this.odprice;
	}

	public void setOdprice(Double odprice) {
		this.odprice = odprice;
	}

	public String getOdparam() {
		return odparam;
	}

	public void setOdparam(String odparam) {
		this.odparam = odparam;
	}

}