package com.ohhay.other.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.O100;
import com.ohhay.other.entities.O100Das;
import com.ohhay.other.mysql.dao.O100Dao;


@Repository(value = SpringBeanNames.REPOSITORY_NAME_O100)
@Scope("prototype")
public class O100DaoImpl extends QbMySqlDaoSupport implements O100Dao {

	@Override
	public int ohayInserttabO100(int po100, String ov101, String ov102, String ov103, String qv102, int fk100, int fn750, int fc500, int pnFC800, int fd000, String pvLogin, String uri) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.CHAR, ov101));
		listParams.add(new QbSqlParam(Types.CHAR, ov102));
		listParams.add(new QbSqlParam(Types.CHAR, ov103));
		listParams.add(new QbSqlParam(Types.CHAR, qv102));
		listParams.add(new QbSqlParam(Types.INTEGER, fk100));
		listParams.add(new QbSqlParam(Types.INTEGER, fn750));
		listParams.add(new QbSqlParam(Types.INTEGER, fc500));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC800));
		listParams.add(new QbSqlParam(Types.INTEGER, fd000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setUri(uri);
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.OHAY_INSERTTABO100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<O100> qhayListTabQ100(String ov103, String pvSearch, String pvLogin) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, ov103));
		listParams.add(new QbSqlParam(Types.CHAR, pvSearch));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.OHAY_LISTOFTAB0100SE);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(O100.class));
		executeQbQuery();
		List<O100> list = (List<O100>) getListReturn();
		return list;
	}

	@Override
	public String updateTabO100Confirm(String pvOV102, int pnFO100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams,pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.OHAY_UPDATETABO100CONFIRM);
		executeQbQuery();
		String kq = getObjectReturnFunction().toString();
		return kq;
	}

	@Override
	public int stornoTabO100(int po100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, po100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.OHAY_STORNOTABO100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int stornoTabO100Center(int po100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(com.ohhay.base.constant.MySqlNames.O_STORNOTABO100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int ohayInsertTabO100Evo(int po100, String pvOV101, String pvOV102, String pvOV103,  String pvOV108, String pvQV102, int pnFK100, int pnFN750, int pnFD000, String pvLOGIN, String uri) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV108));
		listParams.add(new QbSqlParam(Types.CHAR, pvQV102));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFK100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFN750));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFD000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setUri(uri);
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.OHAY_INSERTTABO100EVO);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<O100> ohayListTabO100D(int fo100, String ev151, String pvSEARC, int pnOFFSET, int pnLIMIT, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, ev151));
		listParams.add(new QbSqlParam(Types.CHAR, pvSEARC));
		listParams.add(new QbSqlParam(Types.INTEGER, pnOFFSET));
		listParams.add(new QbSqlParam(Types.INTEGER, pnLIMIT));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.O_LISTOFTABO100D);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(O100.class));
		executeQbQuery();
		List<O100> list = (List<O100>) getListReturn();
		return list;
	}

	@Override
	public int updateTabO100D(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams,0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.O_UPDATETABO100D);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int ohayInsertTabO100Bon(int po100, String pvOV101, String pvOV102, String pvOV103, Date pdOD107, String pvOV108, String pvNV109, String pvNV112, Date pdOD114,
			double pnON115, String pvOV116, String pvOV117, String pvOV118, Date pdOD119, String pvOV120,
			String pvOV121, String pvOV122, String pvOV123, String pvOV124, String pvOV125, String pvOV126, String pvOV127,  
			String pvQV102, int pnFK100, int pnFN750, int pnFD000, String pvLOGIN, String uri) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
		listParams.add(new QbSqlParam(Types.DATE, pdOD107));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV108));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV109));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV112));
		listParams.add(new QbSqlParam(Types.CHAR, pdOD114));
		listParams.add(new QbSqlParam(Types.DOUBLE, pnON115));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV116));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV117));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV118));
		listParams.add(new QbSqlParam(Types.DATE, pdOD119));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV120));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV121));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV122));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV123));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV124));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV125));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV126));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV127));
		listParams.add(new QbSqlParam(Types.CHAR, pvQV102));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFK100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFN750));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFD000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setUri(uri);
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.OHAY_INSERTTABO100BON);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<O100> listOfTabO100Del(String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.OHAY_LISTOFTABO100DEL);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(O100.class));
		executeQbQuery();
		List<O100> list = (List<O100>) getListReturn();
		return list;
	}

	@Override
	public List<O100Das> getListAccount(String pvSEARC, int pnPAYME, Date pdFRDAT, Date pdTODAT, int pnOFFSET, int pnLIMIT,
			int pnSORT, int pnDIRECTION, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvSEARC));
		listParams.add(new QbSqlParam(Types.INTEGER, pnPAYME));
		listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
		listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
		listParams.add(new QbSqlParam(Types.INTEGER, pnOFFSET));
		listParams.add(new QbSqlParam(Types.INTEGER, pnLIMIT));
		listParams.add(new QbSqlParam(Types.INTEGER, pnSORT));
		listParams.add(new QbSqlParam(Types.INTEGER, pnDIRECTION));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.O_LISTOFTABO100DAS);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(O100Das.class));
		executeQbQuery();
		List<O100Das> list = (List<O100Das>) getListReturn();
		return list;
	}

	@Override
	public int insertTabO100Piepme(int pnPO100, String pvOV101, String pvOV102, String pvOV103, String pvOV110, String pvNV101, int pnFN750, int pnFK100, String pvLOGIN, String uri) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV110));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV101));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFN750));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFK100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setUri(uri);
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.OHAY_INSERTTABO100PIEP);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabO100Key(String pvOV110, String pvOV116, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvOV110));
		listParams.add(new QbSqlParam(Types.CHAR, pvOV116));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.OBON_UPDATETABO100KEY);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	
}
