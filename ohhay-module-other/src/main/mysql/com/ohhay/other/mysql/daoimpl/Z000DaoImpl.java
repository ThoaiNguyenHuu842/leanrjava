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
import com.ohhay.other.mysql.dao.Z000Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_Z000)
@Scope("prototype")
public class Z000DaoImpl extends QbMySqlDaoSupport implements Z000Dao {
	@Override
	public int zhayInsertTabZ000(int pz000, String zv001, String zv002, String zv003, String zv004, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pz000));
		listParams.add(new QbSqlParam(Types.CHAR, zv001));
		listParams.add(new QbSqlParam(Types.CHAR, zv002));
		listParams.add(new QbSqlParam(Types.CHAR, zv003));
		listParams.add(new QbSqlParam(Types.CHAR, zv004));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.ZHAY_INSERTTABZ000);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

}
