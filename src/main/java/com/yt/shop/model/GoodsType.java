package com.yt.shop.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商品分类
 */

@Entity
@Table(name="goods_type")
public class GoodsType implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Long gtid;

	@ManyToOne
	@JoinColumn(name="gpid")
	private GoodsPlate goodsPlate;
	private String gtname;
	private String gtremark;

	// Constructors

	/** default constructor */
	public GoodsType() {
	}

	// Property accessors

	public Long getGtid() {
		return this.gtid;
	}

	public void setGtid(Long gtid) {
		this.gtid = gtid;
	}

	public GoodsPlate getGoodsPlate() {
		return goodsPlate;
	}

	public void setGoodsPlate(GoodsPlate goodsPlate) {
		this.goodsPlate = goodsPlate;
	}

	public String getGtname() {
		return this.gtname;
	}

	public void setGtname(String gtname) {
		this.gtname = gtname;
	}

	public String getGtremark() {
		return this.gtremark;
	}

	public void setGtremark(String gtremark) {
		this.gtremark = gtremark;
	}

}