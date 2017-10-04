package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ChartItemInfo;

public interface R100Dao {
	int rhayInsertTabR100(int fo100, int colno, String login);
	int rhayUpdateTabR100Vote(int fo100v, int fo100,String rv121 ,String pvLogin);
	List<ChartItemInfo> rhayReportTabR1001(int fo100, int rn120, String pvLogin);
	int rhayUpdateTabR100Call(int fo100, int day, int month, int year,int total,String pvLogin);
	int updateTabR100Sha(int fo100s, String hoten, int fo100, int day, int month, int year,String link, String pvLogin);
}
