package com.ohhay.other.mysql.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.sql.Date;
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
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.other.mysql.dao.R900DaoNew;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R900_NEW)
@Scope("prototype")
public class R900DaoNewImpl extends QbMySqlDaoSupport implements  R900DaoNew{

	@Override
	public List<ChartItemInfo2> reportTabR950V(int pnFo100, int pnRN905, int pnRN906, int pnINTER, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFo100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950V);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChartItemInfo2> reportTabR950B(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV951));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950B);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChartItemInfo2> reportTabR950D(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV951));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950D);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChartItemInfo2> reportTabR950L(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV951));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950L);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChartItemInfo2> reportTabR950S(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV951));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950S);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChartItemInfo2> reportTabR950J(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV951));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950J);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChartItemInfo2> reportTabR950U(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN906));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV951));
			listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.STAT_REPORTTABR950U);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
			executeQbQuery();
			List<ChartItemInfo2> list = getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int statGetCountTabR950(String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.STAT_GETCOUNTTABR950);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> List<T> cronjReportTabR900B(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.CRONJ_REPORTTABR900B);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(mapping));
			executeQbQuery();
			List<T> list = (List<T>) getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> cronjReportTabR900D(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.CRONJ_REPORTTABR900D);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(mapping));
			executeQbQuery();
			List<T> list = (List<T>) getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> cronjReportTabR900L(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.CRONJ_REPORTTABR900L);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(mapping));
			executeQbQuery();
			List<T> list = (List<T>) getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> cronjReportTabR900S(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.CRONJ_REPORTTABR900S);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(mapping));
			executeQbQuery();
			List<T> list = (List<T>) getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> cronjReportTabR900U(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.CRONJ_REPORTTABR900U);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(mapping));
			executeQbQuery();
			List<T> list = (List<T>) getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> cronjReportTabR900J(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, ApplicationConstant.FO100_SUPER_ADMIN, MySQLManager.QUERY_TYPE_FO100);
			// set sql name
			setQbSqlName(MySqlNames.CRONJ_REPORTTABR900J);
			// set row mapper
			setQBEntityMapper(new QbEntityMapper(mapping));
			executeQbQuery();
			List<T> list = (List<T>) getListReturn();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
