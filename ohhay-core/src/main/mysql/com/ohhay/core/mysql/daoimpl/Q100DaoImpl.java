package com.ohhay.core.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.Q100Piepme;
import com.ohhay.core.mysql.dao.Q100Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_Q100)
@Scope("prototype")
public class Q100DaoImpl extends QbMySqlDaoSupport implements Q100Dao {
	@Override
	public List<Q100> getListQ100(int fq100, String qv101, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fq100));
		listParams.add(new QbSqlParam(Types.CHAR, qv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.QHAY_LISTOFTABQ100);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(Q100.class));
		executeQbQuery();
		List<Q100> listQ100s = (List<Q100>) getListReturn();
		return listQ100s;
	}

	@Override
	public int qhayInsertTabQ100(int pnPQ100, String pvQV101, String pvQV102, Date pdQD103, int pnFO100,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPQ100));
		listParams.add(new QbSqlParam(Types.CHAR, pvQV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvQV102));
		listParams.add(new QbSqlParam(Types.DATE, pdQD103));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.QHAY_INSERTTABQ100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public Q100 qhayCheckTabQ100(String pvQv101, String pvQv102, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvQv102));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pvQv101, MySQLManager.QUERY_TYPE_MO);
		// set sql name
		setQbSqlName(MySqlNames.QHAY_CHECKTABQ100);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(Q100.class));
		executeQbQuery();
		List<Q100> listQ100s = (List<Q100>) getListReturn();
		if (listQ100s != null && listQ100s.size() > 0)
			return listQ100s.get(0);
		return null;
	}

	@Override
	public int qhayToolsUpdateTabQ100pwd(String pvQv101, String pvQv102, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvQv102));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.QHAY_TOOLS_UPDATETABQ100PWD);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int qhayToolsUpdateTabQ100pkg(int pnFO100, Date pdQD104, String pvVV503, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.DATE, pdQD104));
		listParams.add(new QbSqlParam(Types.CHAR, pvVV503));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.QHAY_TOOLS_UPDATETABQ100PKG);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public String qhayToolsUpdatetabq100reset(String pvOV102, String pvLOGIN) {
		Logger log = Logger.getLogger(Q100DaoImpl.class);
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.QHAY_TOOLS_UPDATETABQ100RESET);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();

		return re;
	}

	@Override
	public int qhayToolsUpdatetabq100repwd(String pvOV102, String pvQV102, String pvOV061, String pvCHKEY,
			String pvLOGIN) {
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvQV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV061));
		listParams.add(new QbSqlParam(Types.CHAR, pvCHKEY));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_ADMIN_TEMPLATE, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.QHAY_TOOLS_UPDATETABQ100REPWD);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public Q100 qhayCheckTabQ100Code(String pvQv101, String pvQv102, String pvQv110, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvQv102));
		listParams.add(new QbSqlParam(Types.CHAR, pvQv110));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, pvQv110 + ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN + pvQv101,
				MySQLManager.QUERY_TYPE_MO);
		// set sql name
		setQbSqlName(MySqlNames.QHAY_CHECKTABQ100CODE);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(Q100.class));
		executeQbQuery();
		List<Q100> listQ100s = (List<Q100>) getListReturn();
		if (listQ100s != null && listQ100s.size() > 0)
			return listQ100s.get(0);
		return null;
	}

	@Override
	public Q100 qbonCheckTabQ100(String pvQv101, String pvQv102, String src, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvQv102));
		listParams.add(new QbSqlParam(Types.CHAR, src));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.QBON_CHECKUSERLOGIN);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(Q100.class));
		executeQbQuery();
		List<Q100> listQ100s = (List<Q100>) getListReturn();
		if (listQ100s != null && listQ100s.size() > 0)
			return listQ100s.get(0);
		return null;
	}

	@Override
	public Q100Piepme qhayCheckTabQ100Piepme(String pvQV102, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQV102));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.QHAY_CHECKTABQ100PIEPME);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(Q100Piepme.class));
		executeQbQuery();
		List<Q100Piepme> listQ100s = (List<Q100Piepme>) getListReturn();
		if (listQ100s != null && listQ100s.size() > 0)
			return listQ100s.get(0);
		return null;
	}

	@Override
	public int getValTabQ100Piepme(String qv110) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, qv110));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.QHAY_GETVALTABQ100PIEPME);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int udpateTabQ100Piep(int pnFO100, String pvNV107, String pvNV112, Date pdND117, String pvNV118,
			String pvNV119, String pvNV126, String pvNV128, Date pdND129, String pvVV503, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV107));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV112));
		listParams.add(new QbSqlParam(Types.DATE, pdND117));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV118));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV119));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV128));
		listParams.add(new QbSqlParam(Types.DATE, pdND129));
		listParams.add(new QbSqlParam(Types.CHAR, pvVV503));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.Q_UPDATETABQ100PIEP);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabQ100Busi(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.QHAY_UPDATETABQ100BUSI);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int getTabQ100PiepId(String qv112, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, qv112));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.Q_GETTABQ100PIEPID);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabQ100Regi(int fo100, String piepmeID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, piepmeID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.Q_UPDATETABQ100REGI);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public String getValTabQv112(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.VARCHAR);
		setQbSqlName(MySqlNames.Q_GETVALTABQV112);
		executeQbQuery();
		return getObjectReturnFunction().toString();
	}

	@Override
	public Q100Piepme qhayCheckTabQ100PiepDn(String pvQv110, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQv110));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		setQbFunctionReturnType(Types.VARCHAR);
		setQbSqlName(MySqlNames.QHAY_CHECKTABQ100PIEPDN);
		setQBEntityMapper(new QbEntityMapper(Q100Piepme.class));
		executeQbQuery();
		List<Q100Piepme> listQ100s = (List<Q100Piepme>) getListReturn();
		if (listQ100s != null && listQ100s.size() > 0)
			return listQ100s.get(0);
		return null;
	}

	@Override
	public Q100Piepme qhayCheckTabQ100PiepCa(String pvQv110, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvQv110));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		setQbFunctionReturnType(Types.VARCHAR);
		setQbSqlName(MySqlNames.QHAY_CHECKTABQ100PIEPCA);
		setQBEntityMapper(new QbEntityMapper(Q100Piepme.class));
		executeQbQuery();
		List<Q100Piepme> listQ100s = (List<Q100Piepme>) getListReturn();
		if (listQ100s != null && listQ100s.size() > 0)
			return listQ100s.get(0);
		return null;
	}

}
