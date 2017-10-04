package com.ohhay.web.core.mysql.daoimpl;

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
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.entities.C400;
import com.ohhay.web.core.mysql.dao.C400Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C400)
@Scope("prototype")
public class C400DaoImpl extends QbMySqlDaoSupport implements C400Dao {

	@Override
	public List<C400> chayListOfTabC400(int pnFC400, int pnFO100, int pnFC800,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC400));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC800));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_LISTOFTABC400);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(C400.class));
		executeQbQuery();
		List<C400> list = (List<C400>) getListReturn();
		return list;
	}

	@Override
	public List<C400> chayListOfTabC400View(String qv101, String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, qv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_LISTOFTABC400VIEW);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(C400.class));
		executeQbQuery();
		List<C400> list = (List<C400>) getListReturn();
		return list;
	}

	@Override
	public List<ComtabItem> ahayCombtabc400(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.AHAY_COMTABC400);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

	@Override
	public String chayUpdatetabc400(int fo100, int fc400, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fc400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.CHAY_UPDATETABC400);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

	@Override
	public String insertTabC400(int pnPC400, String pvCV401, String pvCV402, String pvCV403, String pvCV404, 
								 String pvCV405, int pnFO100, int pnFC800, int pnFD000, int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub 
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPC400));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV401));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV402));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV403));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV404));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV405));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC800));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFD000));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFH020));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.CHAY_INSERTTABC400);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

}
