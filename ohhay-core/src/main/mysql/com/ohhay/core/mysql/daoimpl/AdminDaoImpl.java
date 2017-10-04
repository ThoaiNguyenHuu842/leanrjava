package com.ohhay.core.mysql.daoimpl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.base.mysql.QbMySqlDaoSupport;
import com.ohhay.core.constant.MySqlNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.BonEvoAccount;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.core.mysql.dao.AdminDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_ADMIN)
@Scope("prototype")
public class AdminDaoImpl extends QbMySqlDaoSupport implements AdminDao {

	@Override
	public int adminSetNewTemplate(int pnFID00, String pvHERKU, int pnFC400, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFID00));
		listParams.add(new QbSqlParam(Types.CHAR, pvHERKU));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.ADMIN_SETNEWTEMPLATE);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public String getIpOfLocation(String ip) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, ip));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.IP2LOCATION);
		executeQbQuery();
		return getObjectReturnFunction().toString();
	}

	@Override
	public List<ChartItemInfo2> reportWebDaily(Date pdDATEF, Date pdDATET, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.DATE, pdDATEF));
		listParams.add(new QbSqlParam(Types.DATE, pdDATET));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTWEBDAILY);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public int adminUpdateTabO100(int po100, int fc800, int fd000, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, po100));
		listParams.add(new QbSqlParam(Types.INTEGER, fc800));
		listParams.add(new QbSqlParam(Types.INTEGER, fd000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.CTEMP_UPDATETABO100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	@Override
	public List<BonEvoAccount> listOfTabAccounts(int fo100, String qv101, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, qv101));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.ADMIN_LISTOFACCOUNTS);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(BonEvoAccount.class));
		executeQbQuery();
		List<BonEvoAccount> list = (List<BonEvoAccount>) getListReturn();
		return list;
	}

}
