package com.yt.shop.model;

/**
 * 非数据库持久化类
 * 用于描述首页热卖商品排行的数据模型
 */
public class HotTop {

	private int goodsId;
	private String goodsName;
	private int saleNum;
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}
	
}
