package com.ohhay.base.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.MySqlNames;
import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.base.mysql.dao.O100CentDao;
import com.ohhay.base.util.AESCenterUtil;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_O100CENT)
@Scope("prototype")
public class O100CentDaoImpl extends QbMySqlDaoSupport implements O100CentDao{
	private static Logger log = Logger.getLogger(O100CentDaoImpl.class);
	@Override
	public int insertTabO100(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV104));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.O_INSERTTAB_O100);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getValTabO100Web(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_GETWEBTABO100_WEB);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getValTabO100Topic(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_GETWEBTABO100_TOPIC);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getValTabO100MySql(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_GETWEBTABO100_MYSQL);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getMoTabO100MySql(String ov101, String ov061, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, ov101));
			listParams.add(new QbSqlParam(Types.CHAR, ov061));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_GETMOBTABO100_MYSQL);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertTabO100EVO(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV105, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV104));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV105));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.O_INSERTTAB_O100EVO);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	@Override
	public String ohayInsertTabO100Bon(int po100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV105,  Date pdOD114,
			double pnON115, String pvOV116, String pvOV117, String pvOV118, Date pdOD119, String pvOV130, String pvOV131, Date pdOD132, String pvLOGIN) {
		try {
			// TODO Auto-generated method stub
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, po100));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV104));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV105));
			listParams.add(new QbSqlParam(Types.CHAR, pdOD114));
			listParams.add(new QbSqlParam(Types.DOUBLE, pnON115));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV116));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV117));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV118));
			listParams.add(new QbSqlParam(Types.DATE, pdOD119));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV130));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV131));
			listParams.add(new QbSqlParam(Types.DATE, pdOD132));
			listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
			setQbListSqlParams(listParams,0, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_INSERTTAB_O100BON);
			executeQbQuery();
			return AESCenterUtil.decrypt(getObjectReturnFunction().toString(), 72);
		} catch (Exception ex) {
			log.info("--error decrypt result");
			return null;
		}
	}

	@Override
	public String getValTabO100Shop(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_GETWEBTABO100_SHOP);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getValTabO100Piepme(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.BLOB);
			setQbSqlName(MySqlNames.O_GETVALTABO100PIEPME);
			executeQbQuery();
			String kq = getObjectReturnFunction().toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertTabO100Piep(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV110, String pvLOGIN) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV102));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV104));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV110));
			listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.O_INSERTTABO100PIEP);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateTabO100Piepme(int pnFO100, String pvOV126, String pvLOGIN) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.CHAR, pvOV126));
			listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.O_UPDATETABO100PIEPME);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	

}
