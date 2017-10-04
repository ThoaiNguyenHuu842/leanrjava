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
import com.ohhay.web.core.entities.C900;
import com.ohhay.web.core.mysql.dao.C900Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_C900)
@Scope("prototype")
public class C900DaoImpl extends QbMySqlDaoSupport implements C900Dao {

	@Override
	public List<C900> chayListOfTabC900(int pnFC900, int pnFC850, int pnFC920,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		// set list parameter
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC900));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC850));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFC920));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_LISTOFTABC900);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(C900.class));
		executeQbQuery();
		List<C900> list = (List<C900>) getListReturn();
		return list;
	}
}
