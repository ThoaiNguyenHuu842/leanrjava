package com.ohhay.other.mysql.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.F100Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_F100)
@Scope("prototype")
public class F100DaoImpl extends QbMySqlDaoSupport implements F100Dao {

	@Override
	public int fbonInsertTabF100(int pnFQ100, String pvFV101, String pvFV102, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFQ100));
		listParams.add(new QbSqlParam(Types.CHAR, pvFV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvFV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pnFQ100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.FBON_INSERTTABF100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int fbonStornoTabF100(int fo100, int fq100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fq100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.FBON_STORNOTABF100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	
}
