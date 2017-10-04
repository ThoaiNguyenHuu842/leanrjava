package com.ohhay.core.mysql.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.M350;
import com.ohhay.core.mysql.dao.M350Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_M350)
@Scope("prototype")
public class M350DaoImpl extends QbMySqlDaoSupport implements M350Dao{
	@Override
	public int sendMailTabM350Confirm(int pnFO100,String ov102,String pvMV367, String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, ov102));
		listParams.add(new QbSqlParam(Types.CHAR, pvMV367));
		listParams.add(new QbSqlParam(Types.CHAR, pvMV375));
		listParams.add(new QbSqlParam(Types.CHAR, pvMESSA));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.SENDMAILTABM350CONFIRM);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<M350> listOfTabM350Send(String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.SENDMAIL_TABM350PROCCESS);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(M350.class));
		executeQbQuery();
		List<M350> list = (List<M350>) getListReturn();
		return list;
	}

	@Override
	public int sendMailTabM350Check(String email, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, email));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.SENDMAIL_TABM350CHECKED);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

}
