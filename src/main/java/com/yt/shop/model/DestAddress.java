package com.yt.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 送货地址表
 *
 */

@Entity
@Table(name="dest_address")
public class DestAddress implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="address_Id")
	private Long addressId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	@Column(name="address_text")
	private String addressText;
	
	private String person;
	private String phone;
	private Integer level;

	// Constructors

	/** default constructor */
	public DestAddress() {
	}

	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getAddressText() {
		return this.addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}