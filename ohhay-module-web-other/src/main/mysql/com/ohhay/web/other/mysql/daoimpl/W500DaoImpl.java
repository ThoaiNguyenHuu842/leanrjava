package com.ohhay.web.other.mysql.daoimpl;

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
import com.ohhay.web.other.mysql.dao.W500Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_W500)
@Scope("prototype")
public class W500DaoImpl extends QbMySqlDaoSupport implements W500Dao {

	@Override
	public List<ComtabItem> listOfTabW500(int fo100, int fw400, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, fw400));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.WHAY_LISTOFTABW500);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

}
