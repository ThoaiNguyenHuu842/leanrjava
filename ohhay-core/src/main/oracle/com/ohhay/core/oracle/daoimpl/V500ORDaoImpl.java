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
import com.ohhay.core.entities.oracle.V500OR;
import com.ohhay.core.oracle.dao.V500ORDao;

@Service(value = SpringBeanNames.REPOSITORY_NAME_V500OR)
@Scope("prototype")
public class V500ORDaoImpl extends QbOracleDaoSupport implements V500ORDao {
	@Override
	public List<V500OR> listOfTabV500(String pvVV503, String pvNV752, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvVV503));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV752));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		// set sql name
		setQbSqlName(OracleNames.OHAY_LISTOFTABV500);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(V500OR.class));
		executeQbQuery();
		List<V500OR> list = (List<V500OR>) getListReturn();
		return list;
	}

	@Override
	public int updateTabV500EVO(String pvNV106, Date pdND114, double pnNN115, String pvNV126, String pvNV127,
			String pvNV128, Date pvND129, String pvNV130, String pvPROID) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV106));
		listParams.add(new QbSqlParam(Types.DATE, pdND114));
		listParams.add(new QbSqlParam(Types.DOUBLE, pnNN115));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV127));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV128));
		listParams.add(new QbSqlParam(Types.DATE, pvND129));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV130));
		listParams.add(new QbSqlParam(Types.CHAR, pvPROID));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABV500EVO);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public int updateTabV500Vnd(double pnNN115, String pvNV126, String pvPROID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.DOUBLE, pnNN115));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV126));
		listParams.add(new QbSqlParam(Types.CHAR, pvPROID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_UPDATETABV500VND);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

}
