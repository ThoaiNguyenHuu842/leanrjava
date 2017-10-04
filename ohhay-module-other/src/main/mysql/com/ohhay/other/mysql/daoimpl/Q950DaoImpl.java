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
import com.ohhay.other.mysql.dao.Q950Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_Q950)
@Scope("prototype")
public class Q950DaoImpl extends QbMySqlDaoSupport implements Q950Dao  {

	@Override
	public String org_tools_getiparam(String pvFQ800, String pvFQ850,
			String pvFQ900, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvFQ800));
		listParams.add(new QbSqlParam(Types.CHAR, pvFQ850));
		listParams.add(new QbSqlParam(Types.CHAR, pvFQ900));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.ORG_TOOLS_GETIPARAM);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

}
