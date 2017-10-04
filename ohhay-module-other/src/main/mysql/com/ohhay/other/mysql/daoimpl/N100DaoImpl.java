package com.ohhay.other.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N100;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.dao.N100Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_N100)
@Scope("prototype")
public class N100DaoImpl extends QbMySqlDaoSupport implements N100Dao {

	@Override
	public int nhayUpdateTabN100(int pnFO100, String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, String pvNV106, String pvNV107, Date pdND108, String pvNV109,int pnNN110, int pnNN111, int pnFD000, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV103));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV104));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV105));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV106));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV107));
		listParams.add(new QbSqlParam(Types.DATE, pdND108));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV109));
		listParams.add(new QbSqlParam(Types.INTEGER, pnNN110));
		listParams.add(new QbSqlParam(Types.INTEGER, pnNN111));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFD000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.NHHAY_UPDATETABN100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	@Override
	public int getValTabN100(int po100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, po100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.NHAY_GETVALTANN100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	@Override
	public int nhayUpdateTabN100Smtp(String pvNV116,String pvNV117,String pvNV118,String pvNV119,String pvNV120, int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV116));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV117));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV118));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV119));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV120));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.NHHAY_UPDATETABN100SMTP);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	@Override
	public List<N100> nhayListOfTabN100Smtp(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.NHHAY_LISTOFTABN100SMTP);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(N100.class));
		executeQbQuery();
		List<N100> list = (List<N100>) getListReturn();
		return list;
	}
}
