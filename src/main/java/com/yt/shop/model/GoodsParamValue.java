package com.yt.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商品参数值
 * CREATE TABLE GOODS_PARAM_VALUE
(
   PVID                 INT NOT NULL AUTO_INCREMENT,
   GOODS_ID             INT,
   PARAM_ID             INT,
   PVNAME               VARCHAR(128),
    PVDIFF               DECIMAL(10,2),
   PRIMARY KEY (PVID)
);
 * @author Administrator
 *
 */
@Entity
@Table(name="goods_param_value")
public class GoodsParamValue implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long pvid;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@ManyToOne
	@JoinColumn(name="param_id")
	private GoodsParam goodsParam;
	
	private String pvname;
	
	private Double pvdiff;

	public Long getPvid() {
		return pvid;
	}

	public void setPvid(Long pvid) {
		this.pvid = pvid;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsParam getGoodsParam() {
		return goodsParam;
	}

	public void setGoodsParam(GoodsParam goodsParam) {
		this.goodsParam = goodsParam;
	}

	public String getPvname() {
		return pvname;
	}

	public void setPvname(String pvname) {
		this.pvname = pvname;
	}

	public Double getPvdiff() {
		return pvdiff;
	}

	public void setPvdiff(Double pvdiff) {
		this.pvdiff = pvdiff;
	}
}
