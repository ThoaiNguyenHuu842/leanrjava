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
import com.ohhay.core.entities.oracle.V100OR;
import com.ohhay.core.oracle.dao.V100ORDao;

/**
 * @author TuNt
 * create date Mar 22, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_V100OR)
@Scope("prototype")
public class V100ORDaoImpl extends QbOracleDaoSupport implements V100ORDao{

	@Override
	public int createTabV100O(int vn103, int vn104, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn103));
		listParams.add(new QbSqlParam(Types.INTEGER, vn104));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.CHAR);
		setQbSqlName(OracleNames.OHAY_CREATETABV100O);
		executeQbQuery();
		int result = Integer.parseInt(getObjectReturnFunction().toString());
		return result;
	}

	@Override
	public List<V100OR> listOfTabV100(int vn103, int vn104, int fo100,int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, vn103));
		listParams.add(new QbSqlParam(Types.INTEGER, vn104));
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.INTEGER, offset));
		listParams.add(new QbSqlParam(Types.INTEGER, limit));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.OHAY_LISTOFTABV100);
		setQBEntityMapper(new QbEntityMapper(V100OR.class));
		executeQbQuery();
		List<V100OR> list = (List<V100OR>) getListReturn();
		return list;
	}
	
}
