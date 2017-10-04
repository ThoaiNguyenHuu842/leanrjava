package com.ohhay.core.mysql.daoimpl;

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
import com.ohhay.core.entities.Q170;
import com.ohhay.core.mysql.dao.Q170Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_Q170)
@Scope("prototype")
public class Q170DaoImpl extends QbMySqlDaoSupport implements Q170Dao{

	@Override
	public List<Q170> listOfTabQ170(int fo100, int fq300, int fq400, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fq300));
		listParams.add(new QbSqlParam(Types.INTEGER, fq400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.QHAY_LISTOFTABQ170);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(Q170.class));
		executeQbQuery();
		List<Q170> list = (List<Q170>) getListReturn();
		return list;
	}
	
}
