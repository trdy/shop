package com.yt.shop.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 */

@Entity
@Table(name="user_info")
public class UserInfo implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne
	@JoinColumn(name="user_type_id")
	private UserType userType;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_pass")
	private String userPass;
	
	private String name;
	private String code;
	
	@Column(name="trade_pass")
	private String tradePass;

	//@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name="reg_time")
	private Timestamp regTime;
	
	@Column(name="head_pic")
	private String headPic;
	private String email;
	private String question;
	private String answer;
	
	@Column(name="refuser_id")
	private Integer refuserId;
	
	@Column(name="tuiguan_score")
	private Double tuiguanScore;
	@Column(name="gouwu_score")
	private Double gouwuScore;
	@Column(name="duihuan_score")
	private Double duihuanScore;
	@Column(name="zengzhi_score")
	private Double zengzhiScore;
	@Column(name="xianjin_score")
	private Double xianjinScore;
	
	@Column(name="user_state")
	private Integer userState;
	
	@OneToMany(targetEntity=ShopCar.class,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Set<ShopCar> shopCars;

	// Constructors


	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(Long userId){
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTradePass() {
		return this.tradePass;
	}

	public void setTradePass(String tradePass) {
		this.tradePass = tradePass;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public String getHeadPic() {
		return this.headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getRefuserId() {
		return this.refuserId;
	}

	public void setRefuserId(Integer refuserId) {
		this.refuserId = refuserId;
	}

	public Double getTuiguanScore() {
		return this.tuiguanScore;
	}

	public void setTuiguanScore(Double tuiguanScore) {
		this.tuiguanScore = tuiguanScore;
	}

	public Double getGouwuScore() {
		return this.gouwuScore;
	}

	public void setGouwuScore(Double gouwuScore) {
		this.gouwuScore = gouwuScore;
	}

	public Double getDuihuanScore() {
		return this.duihuanScore;
	}

	public void setDuihuanScore(Double duihuanScore) {
		this.duihuanScore = duihuanScore;
	}

	public Double getZengzhiScore() {
		return this.zengzhiScore;
	}

	public void setZengzhiScore(Double zengzhiScore) {
		this.zengzhiScore = zengzhiScore;
	}

	public Double getXianjinScore() {
		return this.xianjinScore;
	}

	public void setXianjinScore(Double xianjinScore) {
		this.xianjinScore = xianjinScore;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Set<ShopCar> getShopCars() {
		return shopCars;
	}

	public void setShopCars(Set<ShopCar> shopCars) {
		this.shopCars = shopCars;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"userId=" + userId +
				", userType=" + userType +
				", userName='" + userName + '\'' +
				", userPass='" + userPass + '\'' +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", tradePass='" + tradePass + '\'' +
				", regTime=" + regTime +
				", headPic='" + headPic + '\'' +
				", email='" + email + '\'' +
				", question='" + question + '\'' +
				", answer='" + answer + '\'' +
				", refuserId=" + refuserId +
				", tuiguanScore=" + tuiguanScore +
				", gouwuScore=" + gouwuScore +
				", duihuanScore=" + duihuanScore +
				", zengzhiScore=" + zengzhiScore +
				", xianjinScore=" + xianjinScore +
				", userState=" + userState +
				", shopCars=" + shopCars +
				'}';
	}
}