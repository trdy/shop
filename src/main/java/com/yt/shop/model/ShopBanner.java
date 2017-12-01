package com.yt.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 首页轮播图
 * create table shop_banner(
banid int primary key auto_increment,
banner_desc varchar(128),
banner_path varchar(128),
banner_url varchar(128));
*/

@Entity
@Table(name="shop_banner")
public class ShopBanner implements java.io.Serializable{

	@Id
	@GeneratedValue
	@Column(name="banid")
	private Long banid;
	@Column(name="banner_desc")
	private String bannerDesc;
	@Column(name="banner_path")
	private String bannerPath;
	@Column(name="banner_url")
	private String bannerUrl;
	public Long getBanid() {
		return banid;
	}
	public void setBanid(Long banid) {
		this.banid = banid;
	}
	public String getBannerDesc() {
		return bannerDesc;
	}
	public void setBannerDesc(String bannerDesc) {
		this.bannerDesc = bannerDesc;
	}
	public String getBannerPath() {
		return bannerPath;
	}
	public void setBannerPath(String bannerPath) {
		this.bannerPath = bannerPath;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	
}
