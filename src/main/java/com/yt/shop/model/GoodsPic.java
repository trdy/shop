package com.yt.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商品图片
 * GoodsPic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="goods_pic")
public class GoodsPic implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	@Column(name="pic_id")
	private Long picId;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@Column(name="pic_path")
	private String picPath;

	// Constructors

	/** default constructor */
	public GoodsPic() {
	}

	// Property accessors

	public Long getPicId() {
		return this.picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}