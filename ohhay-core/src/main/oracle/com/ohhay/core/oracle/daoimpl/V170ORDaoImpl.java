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
import com.ohhay.core.entities.oracle.V170OR;
import com.ohhay.core.oracle.dao.V170ORDao;

/**
 * @author TuNt
 * create date Mar 16, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V170OR)
@Scope("prototype")
public class V170ORDaoImpl extends QbOracleDaoSupport implements V170ORDao{
	Logger log = Logger.getLogger(V170ORDaoImpl.class);
	@Override
	public String checkedTabV170(int fp100, int fo100, int fo100d, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fp100));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100d));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CHECKEDTABV170O);
		executeQbQuery();
		return getObjectReturnFunction().toString();
	}

	@Override
	public int usedItTabV170O(int fv170, String vv172, int vn175, int fo100u, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv170));
		listParams.add(new QbSqlParam(Types.CHAR, vv172));
		listParams.add(new QbSqlParam(Types.INTEGER, vn175));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100u));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_USEDITTABV170O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public List<V170OR> listOfTabV170(int fo100, int fp100, int fo100d, int offset, int limit,String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fp100));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100d));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV170);
		setQBEntityMapper(new QbEntityMapper(V170OR.class));
		executeQbQuery();
		List<V170OR> list = (List<V170OR>) getListReturn();
		return list;
	}
}
