package com.ohhay.other.mysql.daoimpl;

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
import com.ohhay.other.entities.R150;
import com.ohhay.other.mysql.dao.R150Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_R150)
@Scope("prototype")
public class R150DaoImpl extends QbMySqlDaoSupport implements R150Dao {
	@Override
	public List<R150> rhayReportTabR150(int pnFO100, Date pdRD154, int pnOFFSET, int pnLIMIT, String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.DATE, pdRD154));
		listParams.add(new QbSqlParam(Types.INTEGER, pnOFFSET));
		listParams.add(new QbSqlParam(Types.INTEGER, pnLIMIT));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPOSTTABR150);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(R150.class));
		executeQbQuery();
		List<R150> list = (List<R150>) getListReturn();
		return list;
	}

	@Override
	public List<R150> rhayReportTabR150hist(int pnFO100, int pnOFFSET, int pnLIMIT, String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.INTEGER, pnOFFSET));
		listParams.add(new QbSqlParam(Types.INTEGER, pnLIMIT));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPOSTTABR150HIST);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(R150.class));
		executeQbQuery();
		List<R150> list = (List<R150>) getListReturn();
		return list;
	}

	@Override
	public int updateTabR150(int pr150, String rv153, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pr150));
		listParams.add(new QbSqlParam(Types.CHAR, rv153));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.RHAY_UPDATETABR150);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int getRowTabR150(int pnFO100, Date pdRD154, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.DATE, pdRD154));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, pnFO100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.RHAY_GETROWTABR150);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
}
