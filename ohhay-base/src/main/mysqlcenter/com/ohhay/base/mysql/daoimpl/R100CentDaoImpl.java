package com.ohhay.base.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.MySqlNames;
import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.entities.R100CentP;
import com.ohhay.base.entities.R100CentP2017Dash;
import com.ohhay.base.entities.R100CentP2017Eng;
import com.ohhay.base.entities.R100CentP2017Lifo;
import com.ohhay.base.entities.R100CentP2017Piep;
import com.ohhay.base.entities.R100CentP2017Revi;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.base.mysql.dao.R100CentDao;

/**
 * @author ThoaiNH create Feb 15, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R100CENT)
@Scope("prototype")
public class R100CentDaoImpl extends QbMySqlDaoSupport implements R100CentDao {

	@Override
	public int insertTabR100(String pvRV101, String pvRV103, String pvRV105, int pnFP100, int pnFO100V, int pnFO100K,
			String pvLOGIN) {
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvRV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV105));
			listParams.add(new QbSqlParam(Types.INTEGER, pnFP100));
			if(pnFO100V > 0)
				listParams.add(new QbSqlParam(Types.INTEGER, pnFO100V));
			else
				listParams.add(new QbSqlParam(Types.INTEGER, null));
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100K));
			listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.R_INSERTTABR100);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * @author ThoaiVt created 22/02/2017 (non-Javadoc)
	 * 
	 * @see com.ohhay.base.mysql.dao.R100CentDao#r_listOfTabR100Pie(int,
	 * java.lang.String)
	 */

	@Override
	public List<R100CentP> listOfTabR100Pie(int pnFP100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, pnFP100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.R_LISTOFTABR100PIE);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOftabR100Dev(int, java.lang.String)
	 */
	@Override
	public List<R100CentP> listOftabR100Dev(int fo100k,String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100k));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.R_LISTOFTABR100DEV);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOfTabR100Wee(int, java.sql.Date, java.sql.Date, java.lang.String)
	 */
	@Override
	public List<R100CentP> listOfTabR100Wee(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100WEE);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<R100CentP> listOfTabR100Hrs(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100HRS);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<R100CentP> listOfTabR100Vlf(Date pdFRDAT, Date pdTODAT, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.INTEGER, limit));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100VLF);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<R100CentP> listOfTabR100Det(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100DET);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<R100CentP> listOfTabR100Sen(Date pdFRDAT, Date pdTODAT, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.INTEGER, limit));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100SEN);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<R100CentP> listOfTabR100Vie(int fo100, Date pdFRDAT, Date pdTODAT, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.DATE, pdFRDAT));
			listParams.add(new QbSqlParam(Types.DATE, pdTODAT));
			listParams.add(new QbSqlParam(Types.INTEGER, limit));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100VIE);
			setQBEntityMapper(new QbEntityMapper(R100CentP.class));
			executeQbQuery();
			List<R100CentP> listR100CentP = (List<R100CentP>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTabR1002017(String pvRV101, String pvRV103, String pvRV105, String pvRV106, int pnRN107, int pnFP100, int pnFO100V, int pnFO100K, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvRV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV105));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV106));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN107));
			listParams.add(new QbSqlParam(Types.INTEGER, pnFP100));
			if(pnFO100V > 0)
				listParams.add(new QbSqlParam(Types.INTEGER, pnFO100V));
			else
				listParams.add(new QbSqlParam(Types.INTEGER, null));
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100K));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.R2017_INSERTTABR100);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOfTabR100Dash(int, java.lang.String)
	 */
	@Override
	public List<R100CentP2017Dash> listOfTabR100Dash(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100DASH);
			setQBEntityMapper(new QbEntityMapper(R100CentP2017Dash.class));
			executeQbQuery();
			List<R100CentP2017Dash> listR100CentP = (List<R100CentP2017Dash>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOfTabR100Eng(int, int, int, java.lang.String)
	 */
	@Override
	public List<R100CentP2017Eng> listOfTabR100Eng(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.INTEGER, offset));
			listParams.add(new QbSqlParam(Types.INTEGER, limit));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100ENG);
			setQBEntityMapper(new QbEntityMapper(R100CentP2017Eng.class));
			executeQbQuery();
			List<R100CentP2017Eng> listR100CentP = (List<R100CentP2017Eng>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOfTabR100Revi(int, int, int, java.lang.String)
	 */
	@Override
	public List<R100CentP2017Revi> listOfTabR100Revi(int fo100, int offset, int limit, String pvLogin) {
		/// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.INTEGER, offset));
			listParams.add(new QbSqlParam(Types.INTEGER, limit));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100REVI);
			setQBEntityMapper(new QbEntityMapper(R100CentP2017Revi.class));
			executeQbQuery();
			List<R100CentP2017Revi> listR100CentP = (List<R100CentP2017Revi>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOfTabR100Lifo(int, int, int, java.lang.String)
	 */
	@Override
	public List<R100CentP2017Lifo> listOfTabR100Lifo(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.INTEGER, offset));
			listParams.add(new QbSqlParam(Types.INTEGER, limit));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100LIFO);
			setQBEntityMapper(new QbEntityMapper(R100CentP2017Lifo.class));
			executeQbQuery();
			List<R100CentP2017Lifo> listR100CentP = (List<R100CentP2017Lifo>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.base.mysql.dao.R100CentDao#listOfTabR100Piep(int, java.lang.String)
	 */
	@Override
	public List<R100CentP2017Piep> listOfTabR100Piep(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbSqlName(MySqlNames.STAT_LISTOFTABR100PIEP);
			setQBEntityMapper(new QbEntityMapper(R100CentP2017Piep.class));
			executeQbQuery();
			List<R100CentP2017Piep> listR100CentP = (List<R100CentP2017Piep>) getListReturn();
			return listR100CentP;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTabR1002017dis(String pvRV101, String pvRV103, String pvRV105, String pvRV106, int pnRN107, double pnRN109, int pnFP100, int pnFO100V, int pnFO100K, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.CHAR, pvRV101));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV103));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV105));
			listParams.add(new QbSqlParam(Types.CHAR, pvRV106));
			listParams.add(new QbSqlParam(Types.INTEGER, pnRN107));
			listParams.add(new QbSqlParam(Types.DOUBLE, pnRN109));
			listParams.add(new QbSqlParam(Types.INTEGER, pnFP100));
			if(pnFO100V > 0)
				listParams.add(new QbSqlParam(Types.INTEGER, pnFO100V));
			else
				listParams.add(new QbSqlParam(Types.INTEGER, null));
			listParams.add(new QbSqlParam(Types.INTEGER, pnFO100K));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(MySqlNames.R2017_INSERTTABR100DIS);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
