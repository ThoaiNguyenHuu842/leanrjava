package com.ohhay.core.oracle.daoimpl;

import java.sql.Date;
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
import com.ohhay.core.entities.oracle.C100OR;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.oracle.dao.C100ORDao;
import com.ohhay.core.oracle.dao.N100ORDao;

@Service(value = SpringBeanNames.REPOSITORY_NAME_C100OR)
@Scope("prototype")
public class C100ORDaoImpl extends QbOracleDaoSupport implements C100ORDao {

	@Override
	public int insertTabC100(int pnPC100, String pvCV101, String pvCV102, String pvCV103, int pnFO100, int pnFO100C, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, pnPC100));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV101));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV102));
		listParams.add(new QbSqlParam(Types.CHAR, pvCV103));
		listParams.add(new QbSqlParam(Types.CHAR, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pnFO100C));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_INSERTABC100);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<C100OR> listOfTabC100(String pvCV102, int pnFO100C, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvCV102));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100C));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABC100);
		setQBEntityMapper(new QbEntityMapper(C100OR.class));
		executeQbQuery();
		List<C100OR> list = (List<C100OR>) getListReturn();
		return list;
	}
	
}
