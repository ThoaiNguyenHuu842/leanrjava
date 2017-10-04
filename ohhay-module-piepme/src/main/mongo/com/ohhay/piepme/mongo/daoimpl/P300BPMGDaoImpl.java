package com.ohhay.piepme.mongo.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.P300BPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BSummaryInfo;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;


/**
 * @author ThoaiNH
 * create Mar 13, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P300BP)
@Scope("prototype")
public class P300BPMGDaoImpl extends QbMongoDaoSupport implements P300BPMGDao {

	@Override
	public List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LIST_SUGGESTED_OTAG);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<String> list = new ArrayList<String>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					list.add(obj.getString("_id"));
				}
				return list;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public P300BPMG getPieperDetail(int fo100, int pp100) {
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_DETAIL);
			setParameterNumber(fo100);
			setParameterNumber(pp100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				P300BPMG p300pmg = new P300BPMG();
				JSONObject json = new JSONObject(kq);
				p300pmg.setId(json.getInt("_id"));
				p300pmg.setFo100(json.getInt("FO100"));
				p300pmg.setPv301(json.getString("PV301"));
				p300pmg.setPv302(json.getString("PV302"));
				p300pmg.setPn303(json.getInt("PN303"));
				p300pmg.setLiked(json.getInt("LIKED"));
				JSONObject m900 = json.getJSONObject("M900");
				if(json.has("PV304"))
					p300pmg.setPv304(json.getString("PV304"));
				if(json.has("PN306"))
					p300pmg.setPn306(json.getInt("PN306"));
				if(json.has("FOLLOWING_STT"))
					p300pmg.setFollowingStt(json.getInt("FOLLOWING_STT"));
				if(json.has("PV304THUMB"))
					p300pmg.setPv304Thumb(json.getString("PV304THUMB"));
				p300pmg.setPv305(json.getString("PV305"));
				if(json.has("PN310"))
					p300pmg.setPn310(json.getInt("PN310"));
				if(json.has("FT300"))
					p300pmg.setFt300(json.getInt("FT300"));
				if(m900.has("NN109"))
					p300pmg.setNn109(m900.getInt("NN109"));
				if(m900.has("NN110"))
					p300pmg.setNn110(m900.getInt("NN110"));
				try{
					p300pmg.setPn309(Float.parseFloat(json.getString("PN309")));
				}catch(JSONException e){
					p300pmg.setPn309(0);
				}
				if (json.has("OTAG")) {
					List<String> listTemp = new ArrayList<String>();
					JSONArray otags = json.getJSONArray("OTAG");
					for (int j = 0; j < otags.length(); j++) {
						String otagString = otags.getString(j);
						listTemp.add(otagString);
					}
					p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
				}
				if(json.has("PN312"))
					p300pmg.setPn312(json.getInt("PN312"));
				if(json.has("PN313"))
					p300pmg.setPn313(json.getInt("PN313"));
				if(json.has("PV314"))
					p300pmg.setPv314(json.getString("PV314"));
				if(json.has("PRO_STT"))
					p300pmg.setPromotionStt(json.getInt("PRO_STT"));	
				p300pmg.setNv100(m900.getString("NV100"));
				p300pmg.setNv106(m900.getString("NV106"));
				p300pmg.setMv903(m900.getString("MV903"));
				p300pmg.setMv905(m900.getString("MV905"));
				p300pmg.setRegion(m900.getString("REGION"));
				if (m900.has("MV908"))
					p300pmg.setMv908(m900.getString("MV908"));
				if(m900.has("NV108"))
					p300pmg.setNv108(m900.getString("NV108"));
				p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				p300pmg.setPd308(df.parse(json.getString("PD308")));
				if(json.has("STA")){
					JSONObject sta = json.getJSONObject("STA");
					R100PSta01Sum r100pSta01Sum = new R100PSta01Sum();
					r100pSta01Sum.setViews(sta.getInt("VIEWS"));
					r100pSta01Sum.setFollows(sta.getInt("FOLLOWS"));
					r100pSta01Sum.setLikes(sta.getInt("LIKES"));
					r100pSta01Sum.setReaches(sta.getInt("REACHES"));
					p300pmg.setSta(r100pSta01Sum);
				}
				if(json.has("TIME_AGO"))
					p300pmg.setTimeAgo(Long.parseLong(json.getString("TIME_AGO")));
				if(json.has("PD316"))
					p300pmg.setPd316(df.parse(json.getString("PD316")));
				if(json.has("PN307"))
					p300pmg.setPn307(json.getInt("PN307"));
				return p300pmg;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pieper> getListPieperPublic(int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset,
			int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					/*if(pnOffset == 0 && i == 0){
						JSONObject summaryInfo = obj.getJSONObject("SUMMARY_INFO");
						if (summaryInfo.has("TOTAL_HOT_PIEPER"))
							p300pmg.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
						if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
							p300pmg.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
					}*/
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int storNoTabP300P(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_STORNOTAB_P300B);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			int result = (int) Float.parseFloat(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Pieper> getListPieperFollow(int fo100, String pvSearch, int sort, int pnOffset,
			int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_FOLLOW);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					/*if(pnOffset == 0 && i == 0){
						JSONObject summaryInfo = obj.getJSONObject("SUMMARY_INFO");
						if (summaryInfo.has("TOTAL_HOT_PIEPER"))
							p300pmg.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
						if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
							p300pmg.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
					}*/
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Pieper> getListPieperPublicOne(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_ONE);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					// if(pnOffset == 0 && i == 0){
					JSONObject summaryInfo = obj.getJSONObject("SUMMARY_INFO");
					if (summaryInfo.has("TOTAL_HOT_PIEPER"))
						p300pmg.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
					if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
						p300pmg.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
					// }
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pieper> getListPieperPublicByCategory(int fo100, int fo100f, int ft300, String pvSearch,String pvSearchStem, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_CA);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterNumber(ft300);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					/*if(pnOffset == 0 && i == 0){
						JSONObject summaryInfo = obj.getJSONObject("SUMMARY_INFO");
						if (summaryInfo.has("TOTAL_HOT_PIEPER"))
							p300pmg.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
						if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
							p300pmg.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
					}*/
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pieper> getListPieperPublicV2(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300BV2);
			setParameterNumber(pnLa);
			setParameterNumber(pnLong);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					// if(pnOffset == 0 && i == 0){
					JSONObject summaryInfo = obj.getJSONObject("SUMMARY_INFO");
					if (summaryInfo.has("TOTAL_HOT_PIEPER"))
						p300pmg.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
					if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
						p300pmg.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
					// }
					p300pmg.setDistance(obj.getDouble("DISTANCE"));
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int checkRoleOnCreate(int fo100, int ft300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_CHECKROLE_ON_CREATE);
			setParameterNumber(fo100);
			setParameterNumber(ft300);
			int result = (int) Float.parseFloat(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Pieper> getListPieperOwner(int fo100, String pvSearch, String pvSearchStem, int ft300, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_OWNER);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(ft300);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pieper> getListPieperPublicV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300BV3);
			setParameterNumber(pnLa);
			setParameterNumber(pnLong);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					p300pmg.setDistance(obj.getDouble("DISTANCE"));
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public P300BSummaryInfo getSummaryInfoV3(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_SUMMARY_INFO_P300B_V3);
			setParameterNumber(pnLa);
			setParameterNumber(pnLong);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				P300BSummaryInfo p300bSummaryInfo = new P300BSummaryInfo();
				JSONObject summaryInfo = new JSONObject(kq);
				if (summaryInfo.has("TOTAL_HOT_PIEPER"))
					p300bSummaryInfo.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
				if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
					p300bSummaryInfo.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
				return p300bSummaryInfo;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pieper> getListPieperPublicByCategoryV2(double pnLa, double pnLong, int fo100, int fo100f, int ft300, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_CAV2);
			setParameterNumber(pnLa);
			setParameterNumber(pnLong);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterNumber(ft300);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					p300pmg.setDistance(obj.getDouble("DISTANCE"));
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pieper> getListPieperPublicEOM(int fo100, int fo100f, String pvSearch, String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_EOM);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300BPMG p300pmg = new P300BPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"))
						p300pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300pmg.setPn306(obj.getInt("PN306"));
					try {
						p300pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p300pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try {
						p300pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(obj.getString("PD308")));
					p300pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if (obj.has("PRO_STT"))
						p300pmg.setPromotionStt(obj.getInt("PRO_STT"));
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
