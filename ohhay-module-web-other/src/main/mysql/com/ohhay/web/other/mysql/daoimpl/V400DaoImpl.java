package com.ohhay.web.other.mysql.daoimpl;

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
import com.ohhay.web.other.mysql.dao.V400Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_V400)
@Scope("prototype")
public class V400DaoImpl extends QbMySqlDaoSupport implements V400Dao{

	@Override
	public int insertTabV400(int pnPW400, String pvWV401, String ov1pvWV402, String pvWV403, String pvWV404, String pvWV405, int pnFO100, int pnFC800, int pnFD000, int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPW400));
		listParams.add(new QbSqlParam(Types.CHAR, pvWV401));
		listParams.add(new QbSqlParam(Types.CHAR, ov1pvWV402));
		listParams.add(new QbSqlParam(Types.CHAR, pvWV403));
		listParams.add(new QbSqlParam(Types.CHAR, pvWV404));
		listParams.add(new QbSqlParam(Types.CHAR, pvWV405));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC800));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFD000));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFH020));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.VHAY_INSERTTABV400);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	
}
