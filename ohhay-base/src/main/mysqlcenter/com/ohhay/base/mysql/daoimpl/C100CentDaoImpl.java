package com.ohhay.base.mysql.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.MySqlNames;
import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.base.mysql.dao.C100CentDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C100CENT)
@Scope("prototype")
public class C100CentDaoImpl extends QbMySqlDaoSupport implements C100CentDao {

	@Override
	public String getValTabC100Con(String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.C_GETVALTABC100CON);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getValTabC100MySQL(String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.C_GETVALTABC100MYSQL);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getValTabC100Web(String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.C_GETVALTABC100WEB);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getValTabC100Piepme(String cv101, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, cv101));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.C_GETVALTABC100PIEPME);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
