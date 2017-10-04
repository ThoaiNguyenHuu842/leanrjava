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
import com.ohhay.core.entities.oracle.V130OR;
import com.ohhay.core.oracle.dao.V130ORDao;

/**
 * @author TuNt
 * create date Mar 16, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V130OR)
@Scope("prototype")
public class V130ORDaoImpl extends QbOracleDaoSupport implements V130ORDao{

	@Override
	public int createTabV130(int fp100, int fo100, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fp100));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_CREATETABV130O);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<V130OR> listOfTabV130(int vn133, int fp100, int fo100,int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn133));
		listParams.add(new QbSqlParam(Types.INTEGER, fp100));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV130);
		setQBEntityMapper(new QbEntityMapper(V130OR.class));
		executeQbQuery();
		List<V130OR> list = (List<V130OR>) getListReturn();
		return list;
	}

}
