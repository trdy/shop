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
 * 购物车
 */

@Entity
@Table(name="shop_car")
public class ShopCar implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="shop_car_id")
	private Long shopCarId;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo user;
	
	@Column(name="shop_time")
	private Timestamp shopTime;
	
	@Column(name="shop_num")
	private Integer shopNum;
	
	@Column(name="shop_price")
	private Double shopPrice;
	
	@Column(name="goods_param")
	private String goodsParam;

	// Constructors

	/** default constructor */
	public ShopCar() {
	}
	

	public Long getShopCarId() {
		return this.shopCarId;
	}

	public void setShopCarId(Long shopCarId) {
		this.shopCarId = shopCarId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Timestamp getShopTime() {
		return this.shopTime;
	}

	public void setShopTime(Timestamp shopTime) {
		this.shopTime = shopTime;
	}

	public Integer getShopNum() {
		return this.shopNum;
	}

	public void setShopNum(Integer shopNum) {
		this.shopNum = shopNum;
	}

	public Double getShopPrice() {
		return this.shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getGoodsParam() {
		return goodsParam;
	}

	public void setGoodsParam(String goodsParam) {
		this.goodsParam = goodsParam;
	}

}