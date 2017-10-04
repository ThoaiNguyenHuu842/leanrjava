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
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.oracle.dao.N000ORDao;

/**
 * @author TuNt create date Mar 7, 2017 ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_N000OR)
@Scope("prototype")
public class N000ORDaoImpl extends QbOracleDaoSupport implements N000ORDao {
	@Override
	public int insertTabN000Pie(String uuid, String nv002, int fo100, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, uuid));
		listParams.add(new QbSqlParam(Types.CHAR, nv002));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_INSERTTABN000PIE);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int checkitTabN000Pie(String uuid, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, uuid));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_CHECKITTABN000PIE);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabN000Pie(String uuid, int fo100, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, uuid));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN000PIE);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N000OR> listOfTabN000Pie(String uuid, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, uuid));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN000PIE);
		setQBEntityMapper(new QbEntityMapper(N000OR.class));
		executeQbQuery();
		List<N000OR> list = (List<N000OR>) getListReturn();
		return list;
	}

	@Override
	public List<N000OR> listOfTabN000PieCA(String uuid, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, uuid));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN000PIECA);
		setQBEntityMapper(new QbEntityMapper(N000OR.class));
		executeQbQuery();
		List<N000OR> list = (List<N000OR>) getListReturn();
		return list;
	}

	@Override
	public List<N000OR> listOfTabN000PieDN(String uuid, String login) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, uuid));
		listParams.add(new QbSqlParam(Types.CHAR, login));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN000PIEDN);
		setQBEntityMapper(new QbEntityMapper(N000OR.class));
		executeQbQuery();
		List<N000OR> list = (List<N000OR>) getListReturn();
		return list;
	}

}
