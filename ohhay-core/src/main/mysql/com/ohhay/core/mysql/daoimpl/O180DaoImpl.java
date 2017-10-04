package com.ohhay.core.mysql.daoimpl;

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
import com.ohhay.core.entities.O180;
import com.ohhay.core.mysql.dao.O180Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_O180)
@Scope("prototype")
public class O180DaoImpl extends QbMySqlDaoSupport implements O180Dao {

	@Override
	public int insertTabO180(int po180, String ov182, String ov183, String ov184, int fo100c, int fo100p, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, po180));
			listParams.add(new QbSqlParam(Types.CHAR, ov182));
			listParams.add(new QbSqlParam(Types.CHAR, ov183));
			listParams.add(new QbSqlParam(Types.CHAR, ov184));
			listParams.add(new QbSqlParam(Types.INTEGER, fo100c));
			listParams.add(new QbSqlParam(Types.INTEGER, fo100p));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.OHAY_INSERTTABO180);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornotabo180(int fo100p, int fo100c, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100p));
			listParams.add(new QbSqlParam(Types.INTEGER, fo100c));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.OHAY_STORNOTABO180);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<O180> listOfTabO180C(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.OHAY_LISTOFTABO180C);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(O180.class));
		executeQbQuery();
		List<O180> list = (List<O180>) getListReturn();
		return list;
	}

}
