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
import com.ohhay.web.core.mysql.dao.L400Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_L400)
@Scope("prototype")
public class L400DaoImpl extends QbMySqlDaoSupport implements L400Dao {

	@Override
	public int generateTabL400(int fo100, int fc800, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fc800));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.LHAY_GENERATETABL400);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<ComtabItem> combTabL400(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.LHAY_COMTABL400);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

	@Override
	public int inserttabL400(int pnPL400, String pvLV401, String pvLV402, String pvLV403, String pvLV404, String pvLV405, int pnFO100, int pnFC800, int pnFD000, int fh020,String pvLogin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPL400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLV401));
		listParams.add(new QbSqlParam(Types.CHAR, pvLV402));
		listParams.add(new QbSqlParam(Types.CHAR, pvLV403));
		listParams.add(new QbSqlParam(Types.CHAR, pvLV404));
		listParams.add(new QbSqlParam(Types.CHAR, pvLV405));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC800));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFD000));
		listParams.add(new QbSqlParam(Types.INTEGER, fh020));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.LHAY_INSERTTABL400);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
}
