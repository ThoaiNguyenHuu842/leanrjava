package com.ohhay.web.core.mysql.daoimpl;

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
import com.ohhay.web.core.mysql.dao.T920Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_T920)
@Scope("prototype")
public class T920DaoImpl extends QbMySqlDaoSupport implements T920Dao {
	@Override
	public String getelemTabT920(int ft400, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, ft400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.THAY_GETELEMNTTABT920);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

	@Override
	public String getelemTabT920Child(int ft500, String pvLogin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, ft500));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.THAY_GETELEMNTTABT920CHILD);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}
}
