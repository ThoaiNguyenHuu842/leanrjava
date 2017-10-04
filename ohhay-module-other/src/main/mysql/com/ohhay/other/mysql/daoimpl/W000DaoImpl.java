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
import com.ohhay.other.entities.W000;
import com.ohhay.other.mysql.dao.W000Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_W000)
@Scope("prototype")
public class W000DaoImpl extends QbMySqlDaoSupport implements W000Dao {

	@Override
	public List<W000> listOfTabW000(String pvWV004, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvWV004));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.WHAY_LISTOFTABW000);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(W000.class));
		executeQbQuery();
		List<W000> list = (List<W000>) getListReturn();
		return list;
	}

}
