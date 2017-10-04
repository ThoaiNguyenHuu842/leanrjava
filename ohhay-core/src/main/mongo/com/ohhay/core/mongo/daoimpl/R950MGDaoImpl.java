package com.ohhay.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R950MG;
import com.ohhay.core.mongo.dao.R950MGDao;
import com.ohhay.core.utils.DateHelper;

/**
 * @author TuNt
 * create date Mar 8, 2017
 * ohhay-core
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R950MG)
@Scope("prototype")
public class R950MGDaoImpl extends QbMongoDaoSupport implements R950MGDao {

	@Override
	public int insertTabR950(int fo100, int fo100t, int webId, String rv951, String rv952, String rv953, String rv954,
			String rv955, String rv957, String rv958, String rv959, String rv961, String rv962, String rv963) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R950_INSERTTABR950);
			setParameterNumber(fo100);
			setParameterNumber(fo100t);
			setParameterNumber(webId);
			setParameterString(rv951);
			setParameterString(rv952);
			setParameterString(rv953);
			setParameterString(rv954);
			setParameterString(rv955);
			setParameterString(rv957);
			setParameterString(rv958);
			setParameterString(rv959);
			setParameterString(rv961);
			setParameterString(rv962);
			setParameterString(rv963);
			if(fo100 == 0)
				fo100 = ApplicationConstant.FO100_SUPER_ADMIN;
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<R950MG> reportTab001(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		List<R950MG> listR950mg = new ArrayList<R950MG>();
		try {
			setFunctionName(QbMongoFunctionNames.R950_REPORTTAB001);
			setParameterNumber(fo100);
			setParameterNumber(webId);
			setParameterNumber(dateCod);
			/*
			 * convert String date to long
			 * */
			long dateFromLong = 0;
			long dateToLong = 0;
			if(dateCod==0 && !dateFromString.equals(""))
				dateFromLong = DateHelper.convertStringToDate(dateFromString+" 00:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			if(dateCod==0 && !dateToString.equals(""))
				dateToLong = DateHelper.convertStringToDate(dateToString+" 24:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			setParameterNumber(dateFromLong);
			setParameterNumber(dateToLong);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray array = new JSONArray(kq);
			for (int i = 0; i < array.length(); i++) {
				JSONObject json = array.getJSONObject(i);
				R950MG r950 = new R950MG();
				r950.setId(json.getString("_id"));
				r950.setTotal(json.getInt("TOTAL"));
				listR950mg.add(r950);
			}
			return listR950mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<R950MG> reportTab002(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		List<R950MG> listR950mg = new ArrayList<R950MG>();
		try {
			setFunctionName(QbMongoFunctionNames.R950_REPORTTAB002);
			setParameterNumber(fo100);
			setParameterNumber(webId);
			setParameterNumber(dateCod);
			/*
			 * convert String date to long
			 * */
			long dateFromLong = 0;
			long dateToLong = 0;
			if(dateCod==0 && !dateFromString.equals(""))
				dateFromLong = DateHelper.convertStringToDate(dateFromString+" 00:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			if(dateCod==0 && !dateToString.equals(""))
				dateToLong = DateHelper.convertStringToDate(dateToString+" 24:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			setParameterNumber(dateFromLong);
			setParameterNumber(dateToLong);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray array = new JSONArray(kq);
			for (int i = 0; i < array.length(); i++) {
				JSONObject json = array.getJSONObject(i);
				R950MG r950 = new R950MG();
				r950.setId(json.getString("_id"));
				r950.setTotal(json.getInt("TOTAL"));
				listR950mg.add(r950);
			}
			return listR950mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<R950MG> reportTab003(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		List<R950MG> listR950mg = new ArrayList<R950MG>();
		try {
			setFunctionName(QbMongoFunctionNames.R950_REPORTTAB003);
			setParameterNumber(fo100);
			setParameterNumber(webId);
			setParameterNumber(dateCod);
			/*
			 * convert String date to long
			 * */
			long dateFromLong = 0;
			long dateToLong = 0;
			if(dateCod==0 && !dateFromString.equals(""))
				dateFromLong = DateHelper.convertStringToDate(dateFromString+" 00:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			if(dateCod==0 && !dateToString.equals(""))
				dateToLong = DateHelper.convertStringToDate(dateToString+" 24:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			setParameterNumber(dateFromLong);
			setParameterNumber(dateToLong);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray array = new JSONArray(kq);
			for (int i = 0; i < array.length(); i++) {
				JSONObject json = array.getJSONObject(i);
				R950MG r950 = new R950MG();
				r950.setId(json.getString("_id"));
				r950.setTotal(json.getInt("TOTAL"));
				listR950mg.add(r950);
			}
			return listR950mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<R950MG> reportTab004(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		List<R950MG> listR950mg = new ArrayList<R950MG>();
		try {
			setFunctionName(QbMongoFunctionNames.R950_REPORTTAB004);
			setParameterNumber(fo100);
			setParameterNumber(webId);
			setParameterNumber(dateCod);
			/*
			 * convert String date to long
			 * */
			long dateFromLong = 0;
			long dateToLong = 0;
			if(dateCod==0 && !dateFromString.equals(""))
				dateFromLong = DateHelper.convertStringToDate(dateFromString+" 00:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			if(dateCod==0 && !dateToString.equals(""))
				dateToLong = DateHelper.convertStringToDate(dateToString+" 24:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			setParameterNumber(dateFromLong);
			setParameterNumber(dateToLong);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray array = new JSONArray(kq);
			for (int i = 0; i < array.length(); i++) {
				JSONObject json = array.getJSONObject(i);
				R950MG r950 = new R950MG();
				r950.setId(json.getString("_id"));
				r950.setTotal(json.getInt("TOTAL"));
				listR950mg.add(r950);
			}
			return listR950mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<R950MG> reportTab005(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		List<R950MG> listR950mg = new ArrayList<R950MG>();
		try {
			setFunctionName(QbMongoFunctionNames.R950_REPORTTAB005);
			setParameterNumber(fo100);
			setParameterNumber(webId);
			setParameterNumber(dateCod);
			/*
			 * convert String date to long
			 * */
			long dateFromLong = 0;
			long dateToLong = 0;
			if(dateCod==0 && !dateFromString.equals(""))
				dateFromLong = DateHelper.convertStringToDate(dateFromString+" 00:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			if(dateCod==0 && !dateToString.equals(""))
				dateToLong = DateHelper.convertStringToDate(dateToString+" 24:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			setParameterNumber(dateFromLong);
			setParameterNumber(dateToLong);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray array = new JSONArray(kq);
			for (int i = 0; i < array.length(); i++) {
				JSONObject json = array.getJSONObject(i);
				R950MG r950 = new R950MG();
				r950.setId(json.getString("_id"));
				r950.setTotal(json.getInt("TOTAL"));
				listR950mg.add(r950);
			}
			return listR950mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<R950MG> reportTab006(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		List<R950MG> listR950mg = new ArrayList<R950MG>();
		try {
			setFunctionName(QbMongoFunctionNames.R950_REPORTTAB006);
			setParameterNumber(fo100);
			setParameterNumber(webId);
			setParameterNumber(dateCod);
			/*
			 * convert String date to long
			 * */
			long dateFromLong = 0;
			long dateToLong = 0;
			if(dateCod==0 && !dateFromString.equals(""))
				dateFromLong = DateHelper.convertStringToDate(dateFromString+" 00:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			if(dateCod==0 && !dateToString.equals(""))
				dateToLong = DateHelper.convertStringToDate(dateToString+" 24:00:00","dd/MM/yyyy HH:mm:ss").getTime();
			setParameterNumber(dateFromLong);
			setParameterNumber(dateToLong);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray array = new JSONArray(kq);
			for (int i = 0; i < array.length(); i++) {
				JSONObject json = array.getJSONObject(i);
				R950MG r950 = new R950MG();
				r950.setId(json.getString("_id"));
				r950.setTotal(json.getInt("TOTAL"));
				listR950mg.add(r950);
			}
			return listR950mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
