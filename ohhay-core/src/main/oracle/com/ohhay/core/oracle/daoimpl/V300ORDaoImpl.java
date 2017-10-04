package com.ohhay.core.oracle.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.oracle.QbOracleDaoSupport;
import com.ohhay.core.constant.OracleNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.oracle.dao.V300ORDao;

/**
 * @author TuNt
 * create date Jun 30, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V300OR)
@Scope("prototype")
public class V300ORDaoImpl extends QbOracleDaoSupport implements V300ORDao{

	@Override
	public int insertTabV300(int pv300, String vv301, String vv302, Date vd303, Date vd304, String vv305, String vv306, String vv308, int vn309, int fo100, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pv300));
		listParams.add(new QbSqlParam(Types.CHAR, vv301));
		listParams.add(new QbSqlParam(Types.CHAR, vv302));
		listParams.add(new QbSqlParam(Types.DATE, vd303));
		listParams.add(new QbSqlParam(Types.DATE, vd304));
		listParams.add(new QbSqlParam(Types.CHAR, vv305));
		listParams.add(new QbSqlParam(Types.CHAR, vv306));
		listParams.add(new QbSqlParam(Types.CHAR, vv308));
		listParams.add(new QbSqlParam(Types.INTEGER, vn309));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_INSERTTABV300);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int checkedTabV300(int fo100, String vv308, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, vv308));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CHECKEDTABV300);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int stornoTabV300O(int fv300, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv300));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_STORNOTABV300);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int deActTabV300O(int fv300, String vv307, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv300));
		listParams.add(new QbSqlParam(Types.CHAR, vv307));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_DEACTTABV300);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

}
