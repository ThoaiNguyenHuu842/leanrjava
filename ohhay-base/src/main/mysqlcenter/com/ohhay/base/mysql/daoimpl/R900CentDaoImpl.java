package com.ohhay.base.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.constant.MySqlNames;
import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.base.mysql.dao.R900CentDao;
import com.ohhay.base.util.AESCenterUtil;

/**
 * @author ThoaiVt date 11/08/2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R900CENT)
@Scope("prototype")
public class R900CentDaoImpl extends QbMySqlDaoSupport implements R900CentDao {
	
	@Override
	public int insertTabR900(int pnRN901, Object pvBEZEI, Object pnVALUE, String pvREART, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN901));
			listParams.add(new QbSqlParam(Types.CHAR, pvBEZEI));
			listParams.add(new QbSqlParam(Types.CHAR, pnVALUE));
			listParams.add(new QbSqlParam(Types.CHAR, pvREART));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.R_INSERTTABR900);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
