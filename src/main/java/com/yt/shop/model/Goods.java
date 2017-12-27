package com.yt.shop.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 商品资料表
 *
 商品状态
 0：新添加未审核
 3：审核未通过
 6：待审核
 10：审核
 20：上架
 30：下架
 90：作废

 */

@Entity
@Table(name="goods")
public class Goods implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	@Column(name="goods_id")
	private Long goodsId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	@ManyToOne
	@JoinColumn(name="gtid")
	private GoodsType goodsType;
	
	@Column(name="goods_name")
	private String goodsName;

	@Column(name="goods_mfrs")
	private String goodsMfrs;

	@Column(name="goods_origin")
	private String goodsOrigin;

	@Column(name="goods_mfrs_tel")
	private String goodsMfrsTel;

	@Column(name="goods_cret")
	private String goodsCret;

	@Column(name="goods_check_report")
	private String goodsCheckReport;

	@Column(name="goods_trademark")
	private String goodsTrademark;

	@Column(name="goods_special_cert")
	private String goodsSpecialCert;

	@Column(name="goods_marketing")
	private String goodsMarketing;

	@Column(name="goods_busi_lice")
	private String goodsBusiLice;

	@Column(name="goods_other_cert")
	private String goodsOtherCert;
	
	@Column(name="goods_num")
	private Integer goodsNum;
	
	private Double price;
	
	@Column(name="cur_price")
	private Double curPrice;
	
	@Column(name="goods_desc")
	private String goodsDesc;
	
	@Column(name="goods_state")
	private Integer goodsState;
	
	@Column(name="input_date")
	private Timestamp inputDate;

	public Timestamp getInputDate() {
		return inputDate;
	}

	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	@OneToMany(targetEntity=GoodsParam.class,fetch=FetchType.EAGER)
	@OrderBy("paramId")
	@JoinColumn(name="goods_id")
	private Set<GoodsParam> goodsParams;
	
	@OneToMany(targetEntity=GoodsPic.class,fetch=FetchType.EAGER)
	@OrderBy("picId")
	@JoinColumn(name="goods_id")
	private Set<GoodsPic> goodsPics;
	
	@OneToMany(targetEntity=GoodsApprover.class,fetch=FetchType.EAGER)
	@OrderBy("gaid desc")
	@JoinColumn(name="goods_id")
	private Set<GoodsApprover> goodsApprovers;
	
	// Constructors

	public Set<GoodsApprover> getGoodsApprovers() {
		return goodsApprovers;
	}

	public void setGoodsApprovers(Set<GoodsApprover> goodsApprovers) {
		this.goodsApprovers = goodsApprovers;
	}

	/** default constructor */
	public Goods() {
	}


	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCurPrice() {
		return this.curPrice;
	}

	public void setCurPrice(Double curPrice) {
		this.curPrice = curPrice;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Integer getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(Integer goodsState) {
		this.goodsState = goodsState;
	}

	public Set<GoodsParam> getGoodsParams() {
		return goodsParams;
	}

	public void setGoodsParams(Set<GoodsParam> goodsParams) {
		this.goodsParams = goodsParams;
	}

	public Set<GoodsPic> getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(Set<GoodsPic> goodsPics){
		this.goodsPics=goodsPics;
	}

	public String getGoodsMfrs() {
		return goodsMfrs;
	}

	public void setGoodsMfrs(String goodsMfrs) {
		this.goodsMfrs = goodsMfrs;
	}

	public String getGoodsOrigin() {
		return goodsOrigin;
	}

	public void setGoodsOrigin(String goodsOrigin) {
		this.goodsOrigin = goodsOrigin;
	}

	public String getGoodsMfrsTel() {
		return goodsMfrsTel;
	}

	public void setGoodsMfrsTel(String goodsMfrsTel) {
		this.goodsMfrsTel = goodsMfrsTel;
	}

	public String getGoodsCret() {
		return goodsCret;
	}

	public void setGoodsCret(String goodsCret) {
		this.goodsCret = goodsCret;
	}

	public String getGoodsCheckReport() {
		return goodsCheckReport;
	}

	public void setGoodsCheckReport(String goodsCheckReport) {
		this.goodsCheckReport = goodsCheckReport;
	}

	public String getGoodsTrademark() {
		return goodsTrademark;
	}

	public void setGoodsTrademark(String goodsTrademark) {
		this.goodsTrademark = goodsTrademark;
	}

	public String getGoodsSpecialCert() {
		return goodsSpecialCert;
	}

	public void setGoodsSpecialCert(String goodsSpecialCert) {
		this.goodsSpecialCert = goodsSpecialCert;
	}

	public String getGoodsMarketing() {
		return goodsMarketing;
	}

	public void setGoodsMarketing(String goodsMarketing) {
		this.goodsMarketing = goodsMarketing;
	}

	public String getGoodsBusiLice() {
		return goodsBusiLice;
	}

	public void setGoodsBusiLice(String goodsBusiLice) {
		this.goodsBusiLice = goodsBusiLice;
	}

	public String getGoodsOtherCert() {
		return goodsOtherCert;
	}

	public void setGoodsOtherCert(String goodsOtherCert) {
		this.goodsOtherCert = goodsOtherCert;
	}
}