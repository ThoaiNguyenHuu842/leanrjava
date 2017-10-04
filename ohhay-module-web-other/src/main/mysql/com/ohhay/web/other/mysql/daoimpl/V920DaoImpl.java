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
import com.ohhay.web.other.mysql.dao.V920Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_V920)
@Scope("prototype")
public class V920DaoImpl extends QbMySqlDaoSupport implements V920Dao {

	@Override
	public String vhayGetelemTabV920(String ov101, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, ov101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.VHAY_GETELEMTABV920);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}
}
