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
import com.ohhay.web.core.mysql.dao.C800Dao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_C800)
@Scope("prototype")
public class C800DaoImpl extends QbMySqlDaoSupport implements C800Dao {
	@Override
	public List<ComtabItem> chayCombtabc800(int fd000, int fk100, String src, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fd000));
		listParams.add(new QbSqlParam(Types.INTEGER, fk100));
		listParams.add(new QbSqlParam(Types.CHAR, src));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CTEMP_COMTABC800);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

	@Override
	public String chayGetElemTabC800(int fc800, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fc800));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(MySqlNames.CTEMP_GETELEMTABC800);
		executeQbQuery();
		String re = getObjectReturnFunction().toString();
		return re;
	}

	@Override
	public List<ComtabItem> chayCombtabc800LgPages(int fd000, int fk100, String src, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, fd000));
		listParams.add(new QbSqlParam(Types.INTEGER, fk100));
		listParams.add(new QbSqlParam(Types.CHAR, src));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, 0, MySQLManager.QUERY_TYPE_IGNORE);
		// set sql name
		setQbSqlName(MySqlNames.CHAY_COMBTABC800LDPAGES);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ComtabItem.class));
		executeQbQuery();
		List<ComtabItem> list = (List<ComtabItem>) getListReturn();
		return list;
	}

}
