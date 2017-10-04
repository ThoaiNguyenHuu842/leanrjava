package com.ohhay.other.mysql.daoimpl;

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
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.other.mysql.dao.R900Dao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R900)
@Scope("prototype")
public class R900DaoImpl extends QbMySqlDaoSupport implements  R900Dao{

	@Override
	public int insertTabR900(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958, int pnREFID, int pnFO100T, String pvLogin){
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV902));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV904));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV908));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV958));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100T));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.RHAY_INSERTTABR900);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
	
	@Override
	public int insertTabR900V1(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958,String pvRV959,String pvRV960, int pnREFID, int pnFO100T, String pvLogin){
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV902));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV904));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV908));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV958));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV959));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV960));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.INTEGER, pnFO100T));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.RHAY_INSERTTABR900V1);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9001);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9002);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9003);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9004);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9005);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9006);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9007);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9008);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9009);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	
	@Override
	public List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9010);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9011);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9012);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9013);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9014);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.RHAY_REPORTTABR9015);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9001);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9002);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9003);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9004);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9005);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9006);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9007);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9008);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9009);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	
	@Override
	public List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9010);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9011);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9012);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9013);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9014);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}
	
	@Override
	public List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV957));
		listParams.add(new QbSqlParam(Types.INTEGER, pnINTER));
		listParams.add(new QbSqlParam(Types.INTEGER, pnREFID));
		listParams.add(new QbSqlParam(Types.CHAR, pvLogin));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set sql name
		setQbSqlName(MySqlNames.REVO_REPORTTABR9015);
		// set row mapper
		setQBEntityMapper(new QbEntityMapper(ChartItemInfo2.class));
		executeQbQuery();
		List<ChartItemInfo2> list = (List<ChartItemInfo2>) getListReturn();
		return list;
	}

	@Override
	public int insertTabR900a(int fo100, String pvRV901, String pvRV902, String pvRV904, 
							 int pnRN905, String pvRV907, String pvRV908, String pvRV913, String pvRV914, String pvRV915, String pvLOGIN) {
		// TODO Auto-generated method stub
		List<QbSqlParam> listParams = new ArrayList<QbSqlParam>();
		listParams.add(new QbSqlParam(Types.INTEGER, fo100));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV901));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV902));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV904));
		listParams.add(new QbSqlParam(Types.INTEGER, pnRN905));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV907));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV908));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV913));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV914));
		listParams.add(new QbSqlParam(Types.CHAR, pvRV915));
		listParams.add(new QbSqlParam(Types.CHAR, pvLOGIN));
		setQbListSqlParams(listParams, fo100, MySQLManager.QUERY_TYPE_FO100);
		// set fuction
		setQbFunction(true);
		setQbFunctionReturnType(Types.INTEGER);
		setQbSqlName(MySqlNames.RHAY_INSERTTABR900A);
		executeQbQuery();
		int re = Integer.parseInt(getObjectReturnFunction().toString());
		return re;
	}
}
