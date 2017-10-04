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
import com.ohhay.core.entities.M300;
import com.ohhay.core.entities.N100;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.mysql.dao.M300Dao;
import com.ohhay.other.mysql.dao.N100Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M300)
@Scope("prototype")
public class M300DaoImpl extends QbMySqlDaoSupport implements M300Dao {

	@Override
	public List<M300> autoFillProfile(String qv101 ,String email) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, email));
		listParams.add(new QbSqlParam(Types.CHAR, qv101));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.MHAY_LISTOFTABM300SMTP);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(M300.class));
		executeQbQuery();
		List<M300> list = (List<M300>) getListReturn();
		return list;
	}

	
}
