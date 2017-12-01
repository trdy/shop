package com.yt.shop.model;

/**
 * 不是数据库持久化bean
 * 用于统计会员充值提现记录数据模型
 */
public class DailySaveCash {

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
	public double getTuiguan() {
		return tuiguan;
	}
	public void setTuiguan(double tuiguan) {
		this.tuiguan = tuiguan;
	}
	public double getGouwu() {
		return gouwu;
	}
	public void setGouwu(double gouwu) {
		this.gouwu = gouwu;
	}
	public double getDuihuan() {
		return duihuan;
	}
	public void setDuihuan(double duihuan) {
		this.duihuan = duihuan;
	}
	public double getZengzhi() {
		return zengzhi;
	}
	public void setZengzhi(double zengzhi) {
		this.zengzhi = zengzhi;
	}
	public double getXianjin() {
		return xianjin;
	}
	public void setXianjin(double xianjin) {
		this.xianjin = xianjin;
	}
	private int userId;
	private String userName;
	private double tuiguan;
	private double gouwu;
	private double duihuan;
	private double zengzhi;
	private double xianjin;
}
