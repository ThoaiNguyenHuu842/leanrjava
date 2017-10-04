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
import com.ohhay.web.core.mysql.dao.L920Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_L920)
@Scope("prototype")
public class L920DaoImpl extends QbMySqlDaoSupport implements L920Dao {

	@Override
	public String getElemTabL920(int  fl400, String pvLogin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fl400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.LHAY_GETELEMETABL920);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

}
