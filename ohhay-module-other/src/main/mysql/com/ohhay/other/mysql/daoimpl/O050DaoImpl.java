package com.ohhay.other.mysql.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mysql.dao.O050Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_O050)
@Scope("prototype")
public class O050DaoImpl extends QbMySqlDaoSupport implements O050Dao{

	@Override
	public String ohayInsertTabO050(String pvOV051, String pvOV061, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvOV051));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV061));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.O_INSERTTABO050);
		executeQbQuery();
		return getObjectReturnFunction().toString();
	}

	@Override
	public int ohayConfirmTabO050(String pvOV051, String pvOV054, String pvOV061, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvOV051));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV054));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV061));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.O_COMFIRMTABO050);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

}
