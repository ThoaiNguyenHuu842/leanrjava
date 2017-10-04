package com.ohhay.other.mysql.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ChartItemInfo;
import com.ohhay.other.mysql.dao.R100Dao;
import com.ohhay.other.mysql.service.R100Service;
@Service(value = SpringBeanNames.SERVICE_NAME_R100)
@Scope("prototype")
public class R100ServiceImpl implements R100Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R100)
	private R100Dao r100Dao;
	@Override
	public int rhayInsertTabR100(int fo100,int colno,
			String login) {
		// TODO Auto-generated method stub
		return r100Dao.rhayInsertTabR100(fo100, colno, login);
	}
	@Override
	public int rhayUpdateTabR100Vote(int fo100v, int fo100,String rv121 ,String pvLogin){
		// TODO Auto-generated method stub
		return r100Dao.rhayUpdateTabR100Vote(fo100v, fo100, rv121, pvLogin);
	}
	@Override
	public List<ChartItemInfo> rhayReportTabR1001(int fo100, int rn120, String pvLogin) {
		// TODO Auto-generated method stub
		return r100Dao.rhayReportTabR1001(fo100, rn120, pvLogin);
	}
	@Override
	public int rhayUpdateTabR100Call(int fo100, int day, int month, int year, int total, String pvLogin) {
		// TODO Auto-generated method stub
		return r100Dao.rhayUpdateTabR100Call(fo100, day, month, year, total, pvLogin);
	}
	@Override
	public int updateTabR100Sha(int fo100s, String hoten, int fo100, String link, String pvLogin) {
		// TODO Auto-generated method stub
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String ss = dateFormat.format(date);
		String s[]= ss.split("/");
		int day = Integer.parseInt(s[0]);
		int month = Integer.parseInt(s[1]);
		int year = Integer.parseInt(s[2]);
		return r100Dao.updateTabR100Sha(fo100s, hoten, fo100, day, month, year, link, pvLogin);
	}
	

}
