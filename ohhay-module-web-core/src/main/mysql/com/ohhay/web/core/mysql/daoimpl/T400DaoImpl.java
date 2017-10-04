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
import com.ohhay.web.core.mysql.dao.T400Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_T400)
@Scope("prototype")
public class T400DaoImpl extends QbMySqlDaoSupport implements T400Dao {

	@Override
	public int insertTabT400(int pt400, String tv401, String tv402, String tv403, String tv404, String tv405, int fo100, int fc800, int fd000, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pt400));
		listParams.add(new QbSqlParam(Types.CHAR, tv401));
		listParams.add(new QbSqlParam(Types.CHAR, tv402));
		listParams.add(new QbSqlParam(Types.CHAR, tv403));
		listParams.add(new QbSqlParam(Types.CHAR, tv404));
		listParams.add(new QbSqlParam(Types.CHAR, tv405));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fc800));
		listParams.add(new QbSqlParam(Types.INTEGER, fd000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.THAY_INSERTTABT400);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	@Override
	public int applyNewTemplate(int fo100, int pt400, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, pt400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.THAY_APPLYNEWTEMPLATE);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	

}
