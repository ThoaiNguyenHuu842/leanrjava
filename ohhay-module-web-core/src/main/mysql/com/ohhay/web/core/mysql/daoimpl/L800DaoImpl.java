package com.ohhay.web.core.mysql.daoimpl;

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
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.mysql.dao.L800Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_L800)
@Scope("prototype")
public class L800DaoImpl extends QbMySqlDaoSupport implements L800Dao{

	@Override
	public List<ComtabItem> chayCombtabL800(int fd000, String lv808, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fd000));
		listParams.add(new QbSqlParam(Types.CHAR, lv808));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_COMBTTABL800);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

	@Override
	public List<ComtabItem> chayCombtabL800Video(String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_COMBTTABL800VIDEO);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

}
