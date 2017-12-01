package com.yt.shop.model;

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
 * 商品参数
 * CREATE TABLE GOODS_PARAM
(
   PARAM_ID             INT NOT NULL AUTO_INCREMENT,
   GOODS_ID             INT,
   PARAM_NAME           VARCHAR(128),
   PRIMARY KEY (PARAM_ID)
);
 * @author Administrator
 *
 */
@Entity
@Table(name="goods_param")
public class GoodsParam implements java.io.Serializable{

	@Id
	@GeneratedValue
	@Column(name="param_id")
	private Long paramId;
	@ManyToOne
	@JoinColumn(name="goods_Id")
	private Goods goods;
	@Column(name="param_name")
	private String paramName;
	
	@OneToMany(targetEntity=GoodsParamValue.class,fetch=FetchType.EAGER,mappedBy="goodsParam")
	@OrderBy("pvid")
	private Set<GoodsParamValue> goodsParamValues;
	
	public Long getParamId() {
		return paramId;
	}
	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Set<GoodsParamValue> getGoodsParamValues() {
		return goodsParamValues;
	}
	public void setGoodsParamValues(Set<GoodsParamValue> goodsParamValues) {
		this.goodsParamValues = goodsParamValues;
	}
}
