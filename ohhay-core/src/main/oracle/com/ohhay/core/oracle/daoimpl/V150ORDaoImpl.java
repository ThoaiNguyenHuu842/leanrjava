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
import com.ohhay.core.entities.oracle.V150OR;
import com.ohhay.core.oracle.dao.V150ORDao;

/**
 * @author TuNt create date Mar 22, 2017 ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V150OR)
@Scope("prototype")
public class V150ORDaoImpl extends QbOracleDaoSupport implements V150ORDao {

	@Override
	public int createTabV150O(int vn154, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn154));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CREATETABV150O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public int confirmTabV150O(int fv150, int vn152, int vn155, int vn156, int vn157, int vn158, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fv150));
		listParams.add(new QbSqlParam(Types.INTEGER, vn152));
		listParams.add(new QbSqlParam(Types.INTEGER, vn155));
		listParams.add(new QbSqlParam(Types.INTEGER, vn156));
		listParams.add(new QbSqlParam(Types.INTEGER, vn157));
		listParams.add(new QbSqlParam(Types.INTEGER, vn158));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CONFIRMTABV150O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public List<V150OR> listOfTabV150O(int vn154, int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn154));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV150);
		setQBEntityMapper(new QbEntityMapper(V150OR.class));
		executeQbQuery();
		List<V150OR> list = (List<V150OR>) getListReturn();
		return list;
	}

	@Override
	public int checkiTabV150O(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CHECKITABV150O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

}
