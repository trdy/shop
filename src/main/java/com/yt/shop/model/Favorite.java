package com.yt.shop.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 会员收藏商品
 * create table favorite
(
   favid                int not null auto_increment,
   goods_id             int,
   user_id              int,
   fav_date             timestamp,
   primary key (favid)
);
 * @author Administrator
 *
 */
@Entity
@Table(name="favorite")
public class Favorite implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long favid;
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	@Column(name="fav_date")
	private Timestamp favDate;

	public Long getFavid() {
		return favid;
	}

	public void setFavid(Long favid) {
		this.favid = favid;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Timestamp getFavDate() {
		return favDate;
	}

	public void setFavDate(Timestamp favDate) {
		this.favDate = favDate;
	}
	
}
