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
import com.ohhay.other.entities.O160;
import com.ohhay.other.mysql.dao.O160Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_O160)
@Scope("prototype")
public class O160DaoImpl extends QbMySqlDaoSupport implements O160Dao {

	@Override
	public List<O160> listOfTabO160(String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.OHAY_LISTOFTABO160);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ItemTabN750.class));
		executeQbQuery();
		List<O160> list = (List<O160>) getListReturn();
		return list;
	}

	
	

}
