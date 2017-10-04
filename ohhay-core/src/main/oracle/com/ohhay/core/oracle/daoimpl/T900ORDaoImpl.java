package com.ohhay.core.oracle.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.oracle.QbOracleDaoSupport;
import com.ohhay.core.constant.OracleNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.entities.oracle.T900OR;
import com.ohhay.core.oracle.dao.T900ORDao;

/**
 * @author ThoaiVt
 * Mar 29, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_T900OR)
@Scope("prototype")
public class T900ORDaoImpl extends QbOracleDaoSupport implements T900ORDao {

	@Override
	public List<T900OR> listOfTabT900OPEN(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABT900OPEN);
		setQBEntityMapper(new QbEntityMapper(T900OR.class));
		executeQbQuery();
		List<T900OR> list = (List<T900OR>) getListReturn();
		return list;
	}

	@Override
	public List<T900OR> listOfTabT900CONF(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABT900CONF);
		setQBEntityMapper(new QbEntityMapper(T900OR.class));
		executeQbQuery();
		List<T900OR> list = (List<T900OR>) getListReturn();
		return list;
	}

	@Override
	public List<T900OR> listOfTabT900APP(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABT900APP);
		setQBEntityMapper(new QbEntityMapper(T900OR.class));
		executeQbQuery();
		List<T900OR> list = (List<T900OR>) getListReturn();
		return list;
	}

	@Override
	public List<T900OR> listOfTabT900AFF(int fo100,int offset, int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABT900AFF);
		setQBEntityMapper(new QbEntityMapper(T900OR.class));
		executeQbQuery();
		List<T900OR> list = (List<T900OR>) getListReturn();
		return list;
	}

}
