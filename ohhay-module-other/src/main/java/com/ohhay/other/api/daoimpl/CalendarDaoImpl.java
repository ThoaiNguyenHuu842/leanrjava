package com.ohhay.other.api.daoimpl;

import java.sql.Date;
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
import com.ohhay.other.api.dao.CalendarDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_CALENDAR)
@Scope("prototype")
public class CalendarDaoImpl extends QbMySqlDaoSupport implements CalendarDao{

	public String lich_convertsol2Lu(Date pdDATUM, String pvIPADD, String pvLOGIN) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.DATE, pdDATUM));
			listParams.add(new QbSqlParam(Types.CHAR, pvIPADD));
			listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.CHAR);
			setQbSqlName(MySqlNames.LICH_CONVERTSOL2LU);
			executeQbQuery();
			String re = getObjectReturnFunction().toString();
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
