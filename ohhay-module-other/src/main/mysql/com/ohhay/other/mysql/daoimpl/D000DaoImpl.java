package com.ohhay.other.mysql.daoimpl;

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
import com.ohhay.other.mysql.dao.D000Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_D000)
@Scope("prototype")
public class D000DaoImpl extends QbMySqlDaoSupport implements D000Dao {

	@Override
	public List<ComtabItem> combTabD000(String pvDv002, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvDv002));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.D_COMTABD000);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

	@Override
	public List<ComtabItem> combTabD000Regis(String pvDv002, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvDv002));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.D_COMTABD000REGIS);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}



}
