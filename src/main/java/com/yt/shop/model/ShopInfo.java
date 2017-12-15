package com.yt.shop.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shop_info")
public class ShopInfo implements java.io.Serializable{

	
	@Id
	@GeneratedValue
	@Column(name="siid")
	private Long siid;
	
	@Column(name="net_shop_name")
	private String nsname;

	@Column(name="net_shop_logo")
	private String nslogo;
	
	public Long getSiid() {
		return siid;
	}
	public void setSiid(Long siid) {
		this.siid = siid;
	}
	public String getNsname() {
		return nsname;
	}
	public void setNsname(String nsname) {
		this.nsname = nsname;
	}
	public String getNslogo() {
		return nslogo;
	}
	public void setNslogo(String nslogo) {
		this.nslogo = nslogo;
	}
	
}
