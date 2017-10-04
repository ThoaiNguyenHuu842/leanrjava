package com.ohhay.core.oracle.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.oracle.QbOracleDaoSupport;
import com.ohhay.core.constant.OracleNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V000OR;
import com.ohhay.core.oracle.dao.V000ORDao;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V000OR)
@Scope("prototype")
public class V000ORDaoImpl extends QbOracleDaoSupport implements V000ORDao{
	Logger log = Logger.getLogger(V000ORDaoImpl.class);
	@Override
	public int insertTabV000O(int fv000, int vn001, int vn002, int vn003, String vv004, String vv005, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv000));
		listParams.add(new QbSqlParam(Types.INTEGER, vn001));
		listParams.add(new QbSqlParam(Types.INTEGER, vn002));
		listParams.add(new QbSqlParam(Types.INTEGER, vn003));
		listParams.add(new QbSqlParam(Types.CHAR, vv004));
		listParams.add(new QbSqlParam(Types.CHAR, vv005));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_INSERTTABV000O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int confirmTabV000(int fv000, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CONFIRMTABV000O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public List<V000OR> listOfTabV000(String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV000);
		setQBEntityMapper(new QbEntityMapper(V000OR.class));
		executeQbQuery();
		log.info("KQ execute: "+getListReturn().toString());
		List<V000OR> list = (List<V000OR>) getListReturn();
		return list;
	}
	
}
