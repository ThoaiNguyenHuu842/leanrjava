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
import com.ohhay.core.entities.oracle.V050OR;
import com.ohhay.core.oracle.dao.V050ORDao;

/**
 * @author TuNt create date Mar 22, 2017 ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V050OR)
@Scope("prototype")
public class V050ORDaoImpl extends QbOracleDaoSupport implements V050ORDao {

	@Override
	public int insertTabV050O(int fv050, int vn051, int vn052, int vn053, String vv054, String vv055, int fv000,
			String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv000));
		listParams.add(new QbSqlParam(Types.INTEGER, vn051));
		listParams.add(new QbSqlParam(Types.INTEGER, vn052));
		listParams.add(new QbSqlParam(Types.INTEGER, vn053));
		listParams.add(new QbSqlParam(Types.CHAR, vv054));
		listParams.add(new QbSqlParam(Types.CHAR, vv055));
		listParams.add(new QbSqlParam(Types.INTEGER, fv000));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_INSERTTABV050O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int confirmTabV050(int fv050, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv050));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CONFIRMTABV050O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public List<V050OR> listOfTabV050(int fv050, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv050));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV050);
		setQBEntityMapper(new QbEntityMapper(V050OR.class));
		executeQbQuery();
		List<V050OR> list = (List<V050OR>) getListReturn();
		return list;
	}

}
