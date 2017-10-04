package com.ohhay.core.oracle.daoimpl;

import java.sql.Date;
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
import com.ohhay.core.entities.oracle.N100AddOR;
import com.ohhay.core.entities.oracle.N100OCAF;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.oracle.dao.N100ORDao;

@Service(value = SpringBeanNames.REPOSITORY_NAME_N100OR)
@Scope("prototype")
public class N100ORDaoImpl extends QbOracleDaoSupport implements N100ORDao {
	Logger log = Logger.getLogger(N100ORDaoImpl.class);

	@Override
	public int insertTabN100(int pnPN100, String pvNV101, String pvNV102, String pvNV103, String pvNV104,
			String pvNV105, String pvNV106, String pvNV107, Date pdND108, String pvNV109, String pvNV119, int pnFO100,
			int pnFK100, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPN100));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV103));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV104));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV105));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV106));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV107));
		listParams.add(new QbSqlParam(Types.DATE, pdND108));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV109));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV119));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFK100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_INSERTTABN100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N100OR> listOfTabN100OH(int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100OH);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public int insertTabN100EVO(String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105,
			String pvNV106, String pvNV107, Date pdND108, String pvNV109, String pvNV112, Date pdND114, double pnNN115,
			String pvNV119, String pvNV126, String pvNV127, String pvNV128, Date pvND129, String pvNV130,
			String pvNV131, Date pdND132, String pvNV133, String pvNV134, String pvNV135, String pvNV136,
			String pvNV137, String pvNV138, String pvNV139, int fo100, int pnFK100, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV103));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV104));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV105));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV106));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV107));
		listParams.add(new QbSqlParam(Types.DATE, pdND108));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV109));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV112));
		listParams.add(new QbSqlParam(Types.DATE, pdND114));
		listParams.add(new QbSqlParam(Types.DOUBLE, pnNN115));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV119));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV127));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV128));
		listParams.add(new QbSqlParam(Types.DATE, pvND129));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV130));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV131));
		listParams.add(new QbSqlParam(Types.DATE, pdND132));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV133));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV134));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV135));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV136));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV137));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV138));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV139));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFK100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_INSERTTABN100EVO);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int stornoTabN100(int pn100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pn100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_STORNOTABN100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public String insertTabN100PIE(String pvNV101, String pvNV102, String pvNV106, int pnFO100, int pnFK100,
			String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV106));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFK100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_INSERTTABN100PIE);
		executeQbQuery();
		return getObjectReturnFunction().toString();
	}

	@Override
	public int updateTabN100BUS(int pnFO100, String pvNV107, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV107));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100BUS);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N100OR> listOfTabN100AFF(String pvNV126, int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100AFF);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public List<N100OR> listOfTabN100CUS(String pvNV126, int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100CUS);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public List<N100OR> listOfTabN100OPIE(int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100OPIE);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public int updateTabN100AFF(int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100AFF);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N100OR> listOfTabN100IWA(String pvNV126, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100IWA);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public int updateTabN100IWA(int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100IWA);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabN100ADD(String nv101, String nv102, Date pdND108, String pvNV109, String pvNV127,
			String pvNV130, String pvNV131, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, nv101));
		listParams.add(new QbSqlParam(Types.CHAR, nv102));
		listParams.add(new QbSqlParam(Types.DATE, pdND108));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV109));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV127));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV130));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV131));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100ADD);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N100AddOR> listOfTabN100OADD(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_ListOfTabN100OADD);
		setQBEntityMapper(new QbEntityMapper(N100AddOR.class));
		executeQbQuery();
		List<N100AddOR> list = (List<N100AddOR>) getListReturn();
		return list;
	}

	@Override
	public int updateTabN100REG(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100REG);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabN100IMM(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100IMM);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N100OR> listOfTabN100RIM(String nv126, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, nv126));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100RIM);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();

		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public List<N100OR> listOfTabN100IMM(String nv126, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, nv126));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100IMM);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OR> list = (List<N100OR>) getListReturn();
		return list;
	}

	@Override
	public int confirmTabN100IMM(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_CONFIRMTABN100IMM);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N100OCAF> listOfTabN100OCAF(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABN100OCAF);
		setQBEntityMapper(new QbEntityMapper(N100OR.class));
		executeQbQuery();
		List<N100OCAF> list = (List<N100OCAF>) getListReturn();
		return list;
	}

	@Override
	public int updateTabN100CODE(int fo100, String pvNV107, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV107));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABN100CODE);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

}
