package com.ohhay.core.oracle.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.oracle.QbOracleDaoSupport;
import com.ohhay.core.constant.OracleNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.dao.V330ORDao;

/**
 * @author TuNt
 * create date Jun 30, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V330OR)
@Scope("prototype")
public class V330ORDaoImpl extends QbOracleDaoSupport implements V330ORDao{
	Logger log = Logger.getLogger(this.getClass()); 
	/* (non-Javadoc)
	 * @see com.ohhay.core.oracle.dao.V330ORDao#insertTabV330(int, java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public String insertTabV330(int pv330, String vv334, int fo100, int fv300, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pv330));
		listParams.add(new QbSqlParam(Types.CHAR, vv334));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fv300));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		log.info("param insertTabV330 : "+pv330+" "+vv334+" "+fo100+" "+fv300+" "+pvLogin);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_INSERTTABV330);
		executeQbQuery();
		String result = getObjectReturnFunction().toString();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.oracle.dao.V330ORDao#usedItTabV330(java.lang.String, int, java.lang.String)
	 */
	@Override
	public int usedItTabV330(String vv334, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, vv334));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_USEDITTABV330);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

}
