package com.yt.shop.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 商品板块
 * GoodsPlate entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="goods_plate")
public class GoodsPlate implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="gpid")
	private Long gpid;
	private String gpname;
	private String gpremark;

	@JSONField(serialize = false)
	@OneToMany(targetEntity=GoodsType.class,fetch=FetchType.EAGER)
	@OrderBy("gtid")
	@JoinColumn(name="gpid")
	private Set<GoodsType> goodsTypes;

	/** 页面展示商品需要添加的数据模型，不参加持久化 */
	@Transient
	private List<Goods> goodsList;

	// Constructors

	/** default constructor */
	public GoodsPlate() {
	}

	public Long getGpid() {
		return this.gpid;
	}

	public void setGpid(Long gpid) {
		this.gpid = gpid;
	}

	public String getGpname() {
		return this.gpname;
	}

	public void setGpname(String gpname) {
		this.gpname = gpname;
	}

	public String getGpremark() {
		return this.gpremark;
	}

	public void setGpremark(String gpremark) {
		this.gpremark = gpremark;
	}

	public Set<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(Set<GoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}