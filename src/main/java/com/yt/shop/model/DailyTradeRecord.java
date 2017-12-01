package com.yt.shop.model;

/**
 * 不是数据库持久化bean
 * 用于统计会员交易数据模型
 */
public class DailyTradeRecord {

	private int userId;
	private String userName;
	private double t1; ////新订单和发货中
	private double t2; //用户确认
	private double t3; //申请退货
	private double t4; //退货 完成
	private double t5; //交易完成
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getT1() {
		return t1;
	}
	public void setT1(double t1) {
		this.t1 = t1;
	}
	public double getT2() {
		return t2;
	}
	public void setT2(double t2) {
		this.t2 = t2;
	}
	public double getT3() {
		return t3;
	}
	public void setT3(double t3) {
		this.t3 = t3;
	}
	public double getT4() {
		return t4;
	}
	public void setT4(double t4) {
		this.t4 = t4;
	}
	public double getT5() {
		return t5;
	}
	public void setT5(double t5) {
		this.t5 = t5;
	}
}
