package com.ohhay.web.core.mongo.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.ohhay.core.criteria.ChartPieAnalyst;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.web.core.mongo.dao.E400MGDao;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_E400MG)
@Scope("prototype")
public class E400MGDaoImpl extends QbMongoDaoSupport implements E400MGDao, Serializable {
	Logger logger = Logger.getLogger(E400MGDaoImpl.class);

	@Override
	public int updateSiteName(int fo100, int pe400, String ev405) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.EVO_UDDATE_SITENAME);
			setParameterNumber(fo100);
			setParameterNumber(pe400);
			setParameterString(ev405);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * ThoaiVt load templateCMS create date 20/02/2016 (non-Javadoc)
	 * 
	 * @see com.ohhay.web.core.mongo.dao.E400MGDao#getListTemplateCMS(int, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E400MG> getListTemplateCMS(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<E400MG> listE400mg = null;
		try {
			setFunctionName(QbMongoFunctionNames.E400_LIST_OF_TABE_400);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				listE400mg = new ArrayList<E400MG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					logger.info("-- template id : -  " + " : " + jsonObject.getString("EN402") + " : "
							+ jsonObject.getString("_id"));
					E400MG e400mg = new E400MG();
					e400mg.setEl446(new Date());
					e400mg.setEl448(new Date());
					e400mg.setId(Integer.parseInt(jsonObject.getString("_id")));
					e400mg.setEn402((int) (Double.parseDouble(jsonObject.getString("EN402"))));
					e400mg.setTotalUser((int) Double.parseDouble(jsonObject.getString("TOTAL_USE")));
					e400mg.setNv100(jsonObject.getString("NV100"));
					try {
						e400mg.setEv403(jsonObject.getString("EV403"));
						e400mg.setEv401(jsonObject.getString("EV401"));
						e400mg.setEv405(jsonObject.getString("EV405"));
					} catch (Exception e) {
						logger.info(e.toString());
						// TODO: handle exception
						logger.info("ERROR DATA ELEMENT : " + i);
					}
					if (jsonObject.has("FA950S")) {
						List<Integer> intList = new ArrayList<Integer>();
						JSONArray stringList = jsonObject.getJSONArray("FA950S");
						for (int j = 0; j < stringList.length(); j++) {
							intList.add(stringList.getInt(j));
						}
						e400mg.setFa950s(intList);
					}
					e400mg.setFo100(jsonObject.getInt("FO100"));
					e400mg.setEl446String(jsonObject.getString("EL446"));
					e400mg.setEl448String(jsonObject.getString("EL448"));
					e400mg.setRoleAdmin((fo100 == e400mg.getFo100() ? 1 : 0));
					/*
					 * if (i == 0 && offset==0) e400mg.setSizeRowss((int)
					 * Double.parseDouble(jsonObject.getString("ROWSS")));
					 */
					listE400mg.add(e400mg);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listE400mg;
	}

	/**
	 * @param fo100
	 * @return fixed by Tunt 18/11/2016
	 */
	@Override
	public List<E400MG> getListMysite(int fo100, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		// try {
		// Query query2 = new Query();
		// query2.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
		// List<Integer> listOfEN402 = new ArrayList<Integer>();
		// listOfEN402.add(0);
		// listOfEN402.add(1);
		// query2.addCriteria(Criteria.where(QbMongoFiledsName.EN402).in(listOfEN402));
		// query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
		// query2.with(new Sort(Sort.Direction.ASC, QbMongoFiledsName.ID));
		// List<E400MG> list =
		// getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,
		// fo100).find(query2, E400MG.class);
		// return list;
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		List<E400MG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.E400_LISTOFTAB_MYSITE);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String result = (String) executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100);
			logger.info("Result excute mongo function E400_LISTOFTAB_MYSITE :" + result);
			if (result != null) {
				listResult = new ArrayList<E400MG>();
				JSONArray jsa = new JSONArray(result);
				for (int i = 0; i < jsa.length(); i++) {
					JSONObject obj = jsa.getJSONObject(i);
					E400MG e400mg = new E400MG();
					e400mg.setId(obj.getInt("_id"));
					e400mg.setFo100(obj.getInt("FO100"));
					e400mg.setEl446String(obj.getString("EL446String"));
					e400mg.setEl448String(obj.getString("EL448String"));
					e400mg.setEn404(obj.getInt("EN404"));
					e400mg.setEv405(obj.getString("EV405"));
					e400mg.setTotalDesignerHistory(obj.getInt("TOTAL_DESIGN_HISTORY"));
					if (obj.has("M900D")) {
						JSONObject jsonM900D = obj.getJSONObject("M900D");
						M900MG m900d = new M900MG();
						m900d.setId(jsonM900D.getInt("_id"));
						m900d.setNv100(jsonM900D.getString("NV100"));
						m900d.setMv903(jsonM900D.getString("MV903"));
						m900d.setMv905(jsonM900D.getString("MV905"));
						m900d.setMv908(jsonM900D.getString("MV908"));
						if (jsonM900D.has("REGION"))
							m900d.setRegion(jsonM900D.getString("REGION"));
						if (jsonM900D.has("SECOND_LAST_EDIT"))
							m900d.setTimeLastEdit(
									DateHelper.convertDateToTimeLine(jsonM900D.getLong("SECOND_LAST_EDIT")));
						e400mg.setM900d(m900d);
					}
					if (obj.has("M900L")) {
						JSONObject jsonM900l = obj.getJSONObject("M900L");
						M900MG m900l = new M900MG();
						m900l.setId(jsonM900l.getInt("_id"));
						m900l.setNv100(jsonM900l.getString("NV100"));
						m900l.setMv903(jsonM900l.getString("MV903"));
						m900l.setMv905(jsonM900l.getString("MV905"));
						m900l.setMv908(jsonM900l.getString("MV908"));
						if (jsonM900l.has("REGION"))
							m900l.setRegion(jsonM900l.getString("REGION"));
						e400mg.setM900l(m900l);
					}
					if (obj.has("EV152"))
						e400mg.setEv152(obj.getString("EV152"));
					if (obj.has("EV152L"))
						e400mg.setEv152l(obj.getString("EV152L"));
					if (obj.has("EV403"))
						e400mg.setEv403(obj.getString("EV403"));
					if (obj.has("FE150"))
						e400mg.setFe150(obj.getInt("FE150"));
					if (obj.has("FE150L"))
						e400mg.setFe150l(obj.getInt("FE150L"));
					if (obj.has("FE400D"))
						e400mg.setFe400d(obj.getInt("FE400D"));
					if (obj.has("FE400L"))
						e400mg.setFe400l(obj.getInt("FE400L"));
					if (obj.has("ROWSS") && i == 0)
						e400mg.setSizeRowss(obj.getInt("ROWSS"));

					listResult.add(e400mg);
				}
			}
		} catch (Exception e) {
			logger.info(e.toString());
			e.printStackTrace();
		}
		return listResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.web.core.mongo.dao.E400MGDao#getListAllsite(int, int, int)
	 */
	@Override
	public List<E400MG> getListAllsite(int fo100, int offset, int limit, String pvSearch) {
		setFunctionName(QbMongoFunctionNames.E400_LISTOFTAB_ALL);
		setParameterNumber(fo100);
		setParameterNumber(offset);
		setParameterNumber(limit);
		setParameterString(pvSearch);
		List<E400MG> listAccount = null;
		String resultData = "";
		try {
			resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				listAccount = new ArrayList<E400MG>();
				JSONArray jsonArray = new JSONArray(resultData);
				logger.info("DATA : " + resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					E400MG e400mg = new E400MG();
					if (i == 0 && offset == 0)
						e400mg.setSizeRowss((int) Double.parseDouble(jsonObject.getString("ROWSS")));
					e400mg.setId(Long.parseLong(jsonObject.getString("_id")));
					e400mg.setEl448String(jsonObject.getString("EL448"));
					e400mg.setEl446String(jsonObject.getString("EL446"));
					e400mg.setEv405(jsonObject.getString("EV405"));
					try {
						e400mg.setEv403(jsonObject.getString("EV403"));
					} catch (Exception e) {
						// TODO: handle exception
						logger.info("Not image !");
					}
					e400mg.setNv100(jsonObject.getString("NV100"));
					listAccount.add(e400mg);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Data ERROR : " + e.toString());
		}
		return listAccount;
	}

	/*
	 * (non-Javadoc) ThoaiVt 18/03/2016 get hight chart
	 */
	@Override
	public List<ChartPieAnalyst> getDataHightChart(int fo100) {
		setFunctionName(QbMongoFunctionNames.E900_REPORTTAB02);
		List<ChartPieAnalyst> listPie = null;
		String resultData = "";
		try {
			resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				listPie = new ArrayList<ChartPieAnalyst>();
				JSONArray jsonArray = new JSONArray(resultData);
				int sumCount = 0, k = 0;
				;
				for (int i = 0; i < jsonArray.length(); i++) {
					if (i == 0) {
						while (k < jsonArray.length()) {
							JSONObject jsonObject = jsonArray.getJSONObject(k);
							int rate = (int) Double.parseDouble(jsonObject.getString("count").toString());
							sumCount += rate;
							k++;
						}
					}
					logger.info("Sum data : " + sumCount);
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					ChartPieAnalyst pieAnalyst = new ChartPieAnalyst();
					int rate = (int) Double.parseDouble(jsonObject.getString("count").toString());
					pieAnalyst.setNameComponent(jsonObject.getString("_id"));
					pieAnalyst.setRateReal(rate);
					pieAnalyst.setRate(((float) rate / sumCount) * 100);
					listPie.add(pieAnalyst);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Data ERROR : " + e.toString());
		}
		return listPie;
	}

	@Override
	public List<ChartPieAnalyst> getCharOfWebid(int fo100, int webid) {
		setFunctionName(QbMongoFunctionNames.E900_REPORTTAB01);
		setParameterNumber(webid);
		List<ChartPieAnalyst> listPie = null;
		String resultData = "";
		try {
			resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				listPie = new ArrayList<ChartPieAnalyst>();
				JSONArray jsonArray = new JSONArray(resultData);
				int sumCount = 0, k = 0;
				for (int i = 0; i < jsonArray.length(); i++) {
					if (i == 0) {
						while (k < jsonArray.length()) {
							JSONObject jsonObject = jsonArray.getJSONObject(k);
							// counter all rate
							int rate = (int) Double.parseDouble(jsonObject.getString("count").toString());
							sumCount += rate;
							k++;
						}
					}
					logger.info("Sum data : " + sumCount);
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					ChartPieAnalyst pieAnalyst = new ChartPieAnalyst();
					int rate = (int) Double.parseDouble(jsonObject.getString("count").toString());
					pieAnalyst.setNameComponent(jsonObject.getString("_id"));
					pieAnalyst.setRateReal(rate);
					pieAnalyst.setRate(((float) rate / sumCount) * 100);
					listPie.add(pieAnalyst);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Data ERROR : " + e.toString());
		}
		return listPie;
	}

	@Override
	public E400MG getStatisticWeb(int fo100) {
		setFunctionName(QbMongoFunctionNames.WEBTOOLS_REPORTTAB01);
		E400MG dataStatisticWeb = null;
		String resultData = "";
		try {
			resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				logger.info("Data : " + resultData);
				dataStatisticWeb = new E400MG();
				JSONObject jsonObject = new JSONObject(resultData);
				logger.info("DAF : " + jsonObject.getString("totalSite") + " : " + jsonObject.getString("totalDomain")
						+ " : " + jsonObject.getString("totalSection"));
				dataStatisticWeb.setEl448(new Date());
				dataStatisticWeb.setEl446(new Date());
				// get array only one element
				dataStatisticWeb.setTotalSize((int) Double.parseDouble(jsonObject.getString("totalSite").toString()));
				dataStatisticWeb
						.setTotalDomain((int) Double.parseDouble(jsonObject.getString("totalDomain").toString()));
				dataStatisticWeb
						.setTotalSection((int) Double.parseDouble(jsonObject.getString("totalSection").toString()));
			}
		} catch (Exception s) {
			logger.info(s);
		}
		return dataStatisticWeb;
	}

	@Override
	public int getTotalHiddenComponent(int fo100, int idWeb) {
		// TODO Auto-generated method stub
		setFunctionName(QbMongoFunctionNames.E400_GETTOTALHIDEN_COMP);
		setParameterNumber(idWeb);
		int nubHiddenComp = 0;
		try {
			nubHiddenComp = (int) Float
					.parseFloat(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nubHiddenComp;
	}

	@Override
	public String listOfTabE400Graph(int fo100) {
		// TODO Auto-generated method stub
		List<E400MG> listE400mg = null;
		try {
			setFunctionName(QbMongoFunctionNames.E400_LISTOFTABE400_GRAPH);
			setParameterNumber(fo100);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			return resultData;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
