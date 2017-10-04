package com.ohhay.web.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.web.core.mongo.dao.E150MGDao;
import com.ohhay.web.lego.entities.mongo.web.E150MG;
import com.ohhay.web.lego.entities.mongo.web.E200MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.Status;

/**
 * @author TuNt create date Nov 18, 2016 ohhay-module-web-lego
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_E150MG)
@Scope("prototype")
public class E150MGDaoIpml extends QbMongoDaoSupport implements E150MGDao {
	Logger log = Logger.getLogger(E150MGDaoIpml.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.web.core.mongo.dao.E150MGDao#listOfTabCus(int,
	 * java.lang.String, int, int)
	 */
	@Override
	public List<E150MG> listOfTabCus(int fo100, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		List<E150MG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.E150_LISTOFTAB_CUS);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String result = (String) executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100);
			log.info("Result excute E150_LISTOFTAB_CUS : " + result);
			listResult = new ArrayList<E150MG>();
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				E150MG e150mg = new E150MG();
				e150mg.setId(object.getInt("_id"));
				e150mg.setEv152(object.getString("EV152"));
				e150mg.setTotalSites(object.getInt("TOTAL_SITE"));
				e150mg.setEl159String(object.getString("EL159String"));
				JSONArray arrStatus = object.getJSONArray("STATUS");
				List<Status> listStatus = new ArrayList<Status>();
				for (int j = 0; j < arrStatus.length(); j++) {
					JSONObject jsonStt = arrStatus.getJSONObject(j);
					Status stt = new Status();
					stt.setId(jsonStt.getString("_id"));
					stt.setTotal(jsonStt.getInt("total"));
					listStatus.add(stt);
				}
				e150mg.setStatus(listStatus);
				M900MG m900c = new M900MG();
				JSONObject jM900c = object.getJSONObject("M900C");
				m900c.setId(jM900c.getInt("_id"));
				m900c.setNv100(jM900c.getString("NV100"));
				m900c.setMv903(jM900c.getString("MV903"));
				m900c.setMv905(jM900c.getString("MV905"));
				if (jM900c.has("MV908"))
					m900c.setMv908(jM900c.getString("MV908"));
				if (jM900c.has("REGION"))
					m900c.setRegion(jM900c.getString("REGION"));
				e150mg.setM900c(m900c);
				listResult.add(e150mg);
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		return listResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.web.core.mongo.dao.E150MGDao#listOfTabSitesOfCus(int, int,
	 * java.lang.String, int, int)
	 */
	@Override
	public List<E150MG> listOfTabSitesOfCus(int fo100d, int fo100c, int offset, int limit) {
		// TODO Auto-generated method stub
		List<E150MG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.E150_LISTOFTAB_SITES_OF_CUS);
			setParameterNumber(fo100d);
			setParameterNumber(fo100c);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String result = (String) executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100d);
			log.info("Result excute E150_LISTOFTAB_SITES_OF_CUS : " + result);
			listResult = new ArrayList<E150MG>();
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				E150MG e150mg = new E150MG();
				e150mg.setId(object.getInt("_id"));
				e150mg.setFo100c(object.getInt("FO100C"));
				e150mg.setFo100d(object.getInt("FO100D"));
				e150mg.setFe400(object.getLong("FE400"));
				e150mg.setFe400d(object.getInt("FE400D"));
				e150mg.setEv151(object.getString("EV151"));
				e150mg.setEv152(object.getString("EV152"));
				if (object.has("EL160String"))
					e150mg.setEl160String(object.getString("EL160String"));
				if (object.has("EL159String"))
					e150mg.setEl159String(object.getString("EL159String"));
				E400MG e400c = new E400MG();
				JSONObject jE400c = object.getJSONObject("E400C");
				e400c.setId(jE400c.getInt("_id"));
				e400c.setFo100(jE400c.getInt("FO100"));
				if (jE400c.has("EV403"))
					e400c.setEv403(jE400c.getString("EV403"));
				e400c.setEn404(jE400c.getInt("EN404"));
				e400c.setEv405(jE400c.getString("EV405"));
				e400c.setEl446String(jE400c.getString("EL446String"));
				e400c.setEl448String(jE400c.getString("EL448String"));
				e150mg.setE400c(e400c);
				if (object.has("E200")) {
					E200MG e200mg = new E200MG();
					JSONObject je200 = object.getJSONObject("E200");
					e200mg.setId(je200.getInt("_id"));
					e200mg.setFo100(je200.getInt("FO100"));
					e200mg.setEv201(je200.getString("EV201"));
					e200mg.setEv202(je200.getString("EV202"));
					e200mg.setEl208String(je200.getString("EL208String"));
					e150mg.setE200mg(e200mg);
				}

				listResult.add(e150mg);
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		return listResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.web.core.mongo.dao.E150MGDao#storNoTab(int, int)
	 */
	@Override
	public int storNoTab(int fo100, int pe150) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.E150_STORNOTAB_E150);
			setParameterNumber(fo100);
			setParameterNumber(pe150);
			int result = (int) Float.parseFloat((executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString()));
			return result;
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
			return -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.web.core.mongo.dao.E150MGDao#confirmTabE150(int, int, int)
	 */
	@Override
	public String confirmTabE150(int fo100d, int pe150, int pe400) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.E150_CONFIRMTAB_E150);
			setParameterNumber(fo100d);
			setParameterNumber(pe150);
			setParameterNumber(pe400);
			String result = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100d).toString();
			return result;
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ohhay.web.core.mongo.dao.E150MGDao#listOfTabSitesOfCusDetail(int,
	 * int)
	 */
	@Override
	public E150MG listOfTabSitesOfCusDetail(int fo100, int pe150) {
		// TODO Auto-generated method stub
		E150MG e150mg = new E150MG();
		try {
			setFunctionName(QbMongoFunctionNames.E150_LISTOFTAB_SITES_OF_CUS_DETAIL);
			setParameterNumber(fo100);
			setParameterNumber(pe150);
			String result = (String) executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100);
			log.info("Result excute E150_LISTOFTAB_SITES_OF_CUS_DETAIL : " + result);
			JSONObject object = new JSONObject(result);
			e150mg.setId(object.getInt("_id"));
			e150mg.setFo100c(object.getInt("FO100C"));
			e150mg.setFo100d(object.getInt("FO100D"));
			e150mg.setFe400(object.getLong("FE400"));
			e150mg.setFe400d(object.getInt("FE400D"));
			e150mg.setEv151(object.getString("EV151"));
			e150mg.setEv152(object.getString("EV152"));
			if (object.has("E200")) {
				E200MG e200mg = new E200MG();
				JSONObject je200 = object.getJSONObject("E200");
				e200mg.setId(je200.getInt("_id"));
				e200mg.setFo100(je200.getInt("FO100"));
				e200mg.setEv201(je200.getString("EV201"));
				e200mg.setEv202(je200.getString("EV202"));
				e200mg.setEl208String(je200.getString("EL208String"));
				e150mg.setE200mg(e200mg);
			}
			if (object.has("EL159String"))
				e150mg.setEl159String(object.getString("EL159String"));
			if (object.has("EL160String"))
				e150mg.setEl160String(object.getString("EL160String"));
			E400MG e400c = new E400MG();
			JSONObject jE400c = object.getJSONObject("E400C");
			e400c.setId(jE400c.getInt("_id"));
			e400c.setFo100(jE400c.getInt("FO100"));
			if (jE400c.has("EV403"))
				e400c.setEv403(jE400c.getString("EV403"));
			e400c.setEn404(jE400c.getInt("EN404"));
			e400c.setEv405(jE400c.getString("EV405"));
			e400c.setEl446String(jE400c.getString("EL446String"));
			e400c.setEl448String(jE400c.getString("EL448String"));
			e150mg.setE400c(e400c);
			JSONObject cusInfo = object.getJSONObject("CUS_INFO");
			e150mg.setTotalSites(cusInfo.getInt("TOTAL_SITE"));
			e150mg.setTotalCus(object.getInt("TOTAL_CUS"));
			JSONArray arrStatus = cusInfo.getJSONArray("STATUS");
			List<Status> listStatus = new ArrayList<Status>();
			for (int j = 0; j < arrStatus.length(); j++) {
				JSONObject jsonStt = arrStatus.getJSONObject(j);
				Status stt = new Status();
				stt.setId(jsonStt.getString("_id"));
				stt.setTotal(jsonStt.getInt("total"));
				listStatus.add(stt);
			}
			e150mg.setStatus(listStatus);
			M900MG m900c = new M900MG();
			JSONObject jM900c = cusInfo.getJSONObject("M900C");
			m900c.setId(jM900c.getInt("_id"));
			m900c.setNv100(jM900c.getString("NV100"));
			m900c.setMv903(jM900c.getString("MV903"));
			m900c.setMv905(jM900c.getString("MV905"));
			if (jM900c.has("MV908"))
				m900c.setMv908(jM900c.getString("MV908"));
			if (jM900c.has("REGION"))
				m900c.setRegion(jM900c.getString("REGION"));
			e150mg.setM900c(m900c);
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		return e150mg;
	}

}
