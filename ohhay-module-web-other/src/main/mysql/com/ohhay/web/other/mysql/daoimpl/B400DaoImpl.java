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
import com.ohhay.web.other.mysql.dao.B400Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_B400)
@Scope("prototype")
public class B400DaoImpl extends QbMySqlDaoSupport implements B400Dao {

	@Override
	public int inserttabb400(int pnPB400, String pvBV401, String ov1pvBV402, String pvBV403, String fpvBV404, String pvBV405, int pnFO100, int pnFC800, int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPB400));
		listParams.add(new QbSqlParam(Types.CHAR, pvBV401));
		listParams.add(new QbSqlParam(Types.CHAR, ov1pvBV402));
		listParams.add(new QbSqlParam(Types.CHAR, pvBV403));
		listParams.add(new QbSqlParam(Types.CHAR, fpvBV404));
		listParams.add(new QbSqlParam(Types.CHAR, pvBV405));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC800));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFH020));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.BHAY_INSERTTABB400);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}


}
