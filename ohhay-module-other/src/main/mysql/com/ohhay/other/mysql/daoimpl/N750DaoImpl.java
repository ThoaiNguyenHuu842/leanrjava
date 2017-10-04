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
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.dao.N750Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_N750)
@Scope("prototype")
public class N750DaoImpl extends QbMySqlDaoSupport implements N750Dao {

	@Override
	public List<ItemTabN750> nhayCombTabN750(String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.N_COMTABN750);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ItemTabN750.class));
		executeQbQuery();
		List<ItemTabN750> list = (List<ItemTabN750>) getListReturn();
		return list;
	}

	@Override
	public List<ItemTabN750> nhayCombTabN750Set(String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.N_COMTABN750_SET);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ItemTabN750.class));
		executeQbQuery();
		List<ItemTabN750> list = (List<ItemTabN750>) getListReturn();
		return list;
	}
	@Override
	public List<ItemTabN750> listOfTabN750(String inLocation) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, inLocation));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.N_LISTOFTABN750);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ItemTabN750.class));
		executeQbQuery();
		List<ItemTabN750> list = (List<ItemTabN750>) getListReturn();
		return list;
	}	
}
