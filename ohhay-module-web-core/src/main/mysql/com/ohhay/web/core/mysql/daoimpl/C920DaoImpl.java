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
import com.ohhay.web.core.entities.C920;
import com.ohhay.web.core.mysql.dao.C920Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C920)
@Scope("prototype")
public class C920DaoImpl extends QbMySqlDaoSupport implements C920Dao {

	@Override
	public List<C920> chayListOfTabC920(String pvHv101, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvHv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_LISTOFTABC920);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(C920.class));
		executeQbQuery();
		List<C920> list = (List<C920>) getListReturn();
		return list;
	}

	@Override
	public List<C920> chayListOfTabC920View(String pvHv101, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvHv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_LISTOFTABC920VIEW);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(C920.class));
		executeQbQuery();
		List<C920> list = (List<C920>) getListReturn();
		return list;
	}

	@Override
	public String chayGetelemTabC920(String ov101, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, ov101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.CTEMP_GETELEMTABC920);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

	@Override
	public String chayGetelemTabC920Lp(String ov101, int fc400, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, ov101));
		listParams.add(new QbSqlParam(Types.INTEGER, fc400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.CHAY_GETELEMTABC920LP);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

	@Override
	public String chayGetelemTabC920Child(int fc500, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fc500));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.CTEMP_GETELEMTABC920CHILD);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}
}
