package com.yt.shop.model;

/**
 * 非数据库持久化类
 * 用户购物车提交订单
 */
public class ShopCarItem {

	private ShopCar shopCar;
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ShopCar getShopCar() {
		return shopCar;
	}
	public void setShopCar(ShopCar shopCar) {
		this.shopCar = shopCar;
	}
	
}
