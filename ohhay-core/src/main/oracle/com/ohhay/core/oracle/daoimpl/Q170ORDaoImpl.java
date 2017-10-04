package com.ohhay.core.oracle.daoimpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.oracle.QbOracleDaoSupport;
import com.ohhay.core.constant.OracleNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.Q170OR;
import com.ohhay.core.oracle.dao.Q170ORDao;

/**
 * @author ThoaiNH
 * create Aug 30, 2017
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_Q170OR)
@Scope("prototype")
public class Q170ORDaoImpl extends QbOracleDaoSupport implements Q170ORDao {

	@Override
	public int insertTabQ170(int fq400, int fo100u, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
			listParams.add(new QbSqlParam(Types.INTEGER, fq400));
			listParams.add(new QbSqlParam(Types.INTEGER, fo100u));
			listParams.add(new QbSqlParam(Types.INTEGER, fo100));
			listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
			setQbListSqlParams(listParams);
			// set fuction
			setQbFunction(true);
			setQbFunctionReturnType(Types.INTEGER);
			setQbSqlName(OracleNames.OHAY_INSERTTABQ170);
			executeQbQuery();
			int re = Integer.parseInt(getObjectReturnFunction().toString());
			return re;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int stornoTabQ170(int fq400, int fo100u, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fq400));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100u));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_STORNOTABQ170);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<Q170OR> listOfTabQ170(int fo100u, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100u));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABQ170);
		setQBEntityMapper(new QbEntityMapper(Q170OR.class));
		executeQbQuery();
		List<Q170OR> list = (List<Q170OR>) getListReturn();
		return list;
	}
	
}
