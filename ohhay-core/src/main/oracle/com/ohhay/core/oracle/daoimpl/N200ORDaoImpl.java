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
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.entities.oracle.N200OR;
import com.ohhay.core.oracle.dao.N200ORDao;

/**
 * @author ThoaiVt 
 * @date 28/03/2017
 */
@Service(value = SpringBeanNames.REPOSITORY_NAME_N200OR)
@Scope("prototype")
public class N200ORDaoImpl extends QbOracleDaoSupport implements N200ORDao {

	@Override
	public int insertTabN200VND(String pvNV201, String pvNV204, String pvNV207, String pvNV208, String pvNV209, String pnNV212,
			int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.CHAR, pvNV201));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV204));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV207));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV208));
		listParams.add(new QbSqlParam(Types.CHAR, pvNV209));
		listParams.add(new QbSqlParam(Types.CHAR, pnNV212));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(OracleNames.OHAY_INSERTTABN200VND);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<N200OR> listOfTabN200O(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams);
		setQbSqlName(OracleNames.NCURPKS_OHAY_ListOfTabN200O);
		setQBEntityMapper(new QbEntityMapper(N200OR.class));
		executeQbQuery();
		List<N200OR> list = (List<N200OR>) getListReturn();
		return list;
	}

}
