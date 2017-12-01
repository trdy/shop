package com.yt.shop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 新闻
create table shop_news(
snid int primary key auto_increment,
title varchar(128),
context varchar(4096),
news_date TIMESTAMP);
 *
 */

@Entity
@Table(name="shop_news")
public class ShopNews implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long snid;
	
	private String title;
	
	private String context;
	
	@Column(name="news_date")
	private Date newsDate;

	public Long getSnid() {
		return snid;
	}

	public void setSnid(Long snid) {
		this.snid = snid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	
}
