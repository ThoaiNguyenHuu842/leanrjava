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
import com.ohhay.base.mysql.dao.R000CentDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_R000CENT)
@Scope("prototype")
public class R000CentDaoImpl extends QbMySqlDaoSupport implements R000CentDao{
	public int getValTabR000(String ip) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, ip));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.CHAR);
			setQbSqlName(MySqlNames.R_GETVALTABR000);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
}
