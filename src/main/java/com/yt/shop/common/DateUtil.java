package com.yt.shop.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate2FormatString(Date date,String mask){
		SimpleDateFormat df=new SimpleDateFormat(mask);
		return df.format(date);
	}
	
	public static String getCurrDate(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	
	public static int getMonthLastDay(String yearMonth){
		int day=0;
		String[] str=yearMonth.split("-");
		int year=Integer.parseInt(str[0]);
		int month=Integer.parseInt(str[1]);
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day=31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day=30;
			break;
		case 2:
			if(year%4==0&&year%100!=0||year%400==0){
				day=29;
			}else{
				day=28;
			}
			break;
		}
		return day;
	}
}
