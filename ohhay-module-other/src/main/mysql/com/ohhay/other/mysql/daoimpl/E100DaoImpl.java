package com.ohhay.other.mysql.daoimpl;

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
import com.ohhay.core.entities.E100D;
import com.ohhay.other.mysql.dao.E100Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_E100)
@Scope("prototype")
public class E100DaoImpl extends QbMySqlDaoSupport implements E100Dao{

	@Override
	public int insertTabE100(int pnFO100C, int pnFO100D, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100C));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100D));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.E_INSERTTABE100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<E100D> listOfTabE100D(int pnFO100, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.E_LISTOFTABE100D);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(E100D.class));
		executeQbQuery();
		List<E100D> list = (List<E100D>) getListReturn();
		return list;
	}
	
}
