package com.ohhay.core.oracle.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.oracle.QbOracleDaoSupport;
import com.ohhay.core.constant.OracleNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V080OR;
import com.ohhay.core.oracle.dao.V080ORDao;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V080OR)
@Scope("prototype")
public class V080ORDaoImpl extends QbOracleDaoSupport implements V080ORDao{

	@Override
	public int createTabV080O(int vn081, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn081));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CREATETABV080O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int confirmTabV080O(int vn081, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn081));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CONFIRMTABV080O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public List<V080OR> listOfTabV080(int vn081, int fo100,int offset, int limit,  String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn081));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV080);
		setQBEntityMapper(new QbEntityMapper(V080OR.class));
		executeQbQuery();
		List<V080OR> list = (List<V080OR>) getListReturn();
		return list;
	}
	
}
