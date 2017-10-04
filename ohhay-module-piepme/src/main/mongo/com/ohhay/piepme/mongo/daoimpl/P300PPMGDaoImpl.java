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
import com.ohhay.piepme.mongo.dao.P300PPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;

/**
 * @author ThoaiNH create Sep 23, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P300PP)
@Scope("prototype")
public class P300PPMGDaoImpl extends QbMongoDaoSupport implements P300PPMGDao {

	@Override
	public List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300P_LIST_SUGGESTED_OTAG);
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
	public P300PPMG getPieperDetail(int fo100, int pp100) {
		try {
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300P_DETAIL);
			setParameterNumber(fo100);
			setParameterNumber(pp100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				P300PPMG p300pmg = new P300PPMG();
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
				if(json.has("FOLLOWING_STT"))
					p300pmg.setFollowingStt(json.getInt("FOLLOWING_STT"));
				if(json.has("PV304THUMB"))
					p300pmg.setPv304Thumb(json.getString("PV304THUMB"));
				p300pmg.setPv305(json.getString("PV305"));
				if(json.has("PN306"))
					p300pmg.setPn306(json.getInt("PN306"));
				if(json.has("PN310"))
					p300pmg.setPn310(json.getInt("PN310"));
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
				if(json.has("TOTAL_REPORTS"))
					p300pmg.setTotalReports(json.getInt("TOTAL_REPORTS"));
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
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300P);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300PPMG p300pmg = new P300PPMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304THUMB"));
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
					
					
					p300pmg.setPn306(obj.getInt("PN306"));
					
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
					if(obj.has("TOTAL_REPORTS"))
						p300pmg.setTotalReports(obj.getInt("TOTAL_REPORTS"));
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
	public int storNoTabP300P(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300P_STORNOTAB_P300P);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			int result = (int) Float.parseFloat(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			System.out.println("kq-----------storNoTabP300P------:"+result);
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
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300P_FOLLOW);
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
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300PPMG p300pmg = new P300PPMG();
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
					if (obj.has("PN312"))
						p300pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p300pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if(obj.has("TOTAL_REPORTS"))
						p300pmg.setTotalReports(obj.getInt("TOTAL_REPORTS"));
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
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300P_ONE);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300PPMG p300pmg = new P300PPMG();
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
					if(obj.has("TOTAL_REPORTS"))
						p300pmg.setTotalReports(obj.getInt("TOTAL_REPORTS"));
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
	public P300PPMG getPieperDetailV2(int fo100, int pp100) {
		try {
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300P_DETAILV2);
			setParameterNumber(fo100);
			setParameterNumber(pp100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				P300PPMG p300pmg = new P300PPMG();
				JSONObject json = new JSONObject(kq);
				p300pmg.setId(json.getInt("_id"));
				p300pmg.setFo100(json.getInt("FO100"));
				p300pmg.setPv301(json.getString("PV301"));
				if(json.has("STT"))
					p300pmg.setStt(json.getString("STT"));
				p300pmg.setPv302(json.getString("PV302"));
				p300pmg.setPn303(json.getInt("PN303"));
				p300pmg.setLiked(json.getInt("LIKED"));
				if(json.has("PV304"))
					p300pmg.setPv304(json.getString("PV304"));
				if(json.has("FOLLOWING_STT"))
					p300pmg.setFollowingStt(json.getInt("FOLLOWING_STT"));
				if(json.has("PV304THUMB"))
					p300pmg.setPv304Thumb(json.getString("PV304THUMB"));
				p300pmg.setPv305(json.getString("PV305"));
				if(json.has("PN306"))
					p300pmg.setPn306(json.getInt("PN306"));
				if(json.has("PN310"))
					p300pmg.setPn310(json.getInt("PN310"));
				if(json.has("NN109"))
					p300pmg.setNn109(json.getInt("NN109"));
				if(json.has("NN110"))
					p300pmg.setNn110(json.getInt("NN110"));
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
				if(json.has("TOTAL_REPORTS"))
					p300pmg.setTotalReports(json.getInt("TOTAL_REPORTS"));
				JSONObject m900 = json.getJSONObject("M900");
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
	public List<Pieper> getListPieperPublicV2(int fo100, int fo100f, String pvSearch, String pvSearchStem, int sort, int pnOffset,
			int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300PV2);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(sort);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<Pieper> listPieper = new ArrayList<Pieper>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300PPMG p100pmg = new P300PPMG();
					p100pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p100pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p100pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p100pmg.setPv301(obj.getString("PV301"));
					if(obj.has("STT"))
						p100pmg.setStt(obj.getString("STT"));
					if(obj.has("USER_STT"))
						p100pmg.setUserStt(obj.getString("USER_STT"));
					p100pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304"))
						p100pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PV304THUMB"))
						p100pmg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PN306"))
						p100pmg.setPn306(obj.getInt("PN306"));
					try {
						p100pmg.setPn307(obj.getInt("PN307"));
					} catch (JSONException e) {
						p100pmg.setPn307(0);
					}
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p100pmg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					
					
					p100pmg.setPn306(obj.getInt("PN306"));
					
					JSONObject m900 = obj.getJSONObject("M900");
					p100pmg.setNv100(m900.getString("NV100"));
					p100pmg.setNv106(m900.getString("NV106"));
					p100pmg.setMv903(m900.getString("MV903"));
					p100pmg.setMv905(m900.getString("MV905"));
					p100pmg.setNn109(m900.getInt("NN109"));
					p100pmg.setNn110(m900.getInt("NN110"));
					try {
						p100pmg.setNv108(m900.getString("NV108"));
					} catch (JSONException e) {
						p100pmg.setNv108("");
					}
					p100pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p100pmg.setMv908(m900.getString("MV908"));
					p100pmg.setUrlAvarta(p100pmg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p100pmg.setPd308(df.parse(obj.getString("PD308")));
					p100pmg.setPd316(df.parse(obj.getString("PD316")));
					if (obj.has("PN312"))
						p100pmg.setPn312((int) Double.parseDouble(obj.getString("PN312")));
					if (obj.has("PN313"))
						p100pmg.setPn313((int) Double.parseDouble(obj.getString("PN313")));
					if (obj.has("PV314"))
						p100pmg.setPv314(obj.getString("PV314"));
					if(obj.has("TOTAL_REPORTS"))
						p100pmg.setTotalReports(obj.getInt("TOTAL_REPORTS"));
					if (obj.has("PN309"))
						p100pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					/*if(pnOffset == 0 && i == 0){
						JSONObject summaryInfo = obj.getJSONObject("SUMMARY_INFO");
						if (summaryInfo.has("TOTAL_HOT_PIEPER"))
							p100pmg.setTotalHotPieper(summaryInfo.getInt("TOTAL_HOT_PIEPER"));
						if (summaryInfo.has("TOTAL_NEAREST_PIEPER"))
							p100pmg.setTotalNearestPieper(summaryInfo.getInt("TOTAL_NEAREST_PIEPER"));
					}*/
					if(obj.has("TIME_AGO"))
						p100pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p100pmg);
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
	public int confirmTabPA317(int fo100, int pp300, String stt) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.P300P_CONFIRMTAB_PA317);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			setParameterString(stt);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateTabPA317(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.P300P_UPDATETAB_PA317);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateTabPA317V2(int pp300, List<Double> pa317) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.P300P_UPDATETAB_PA317V2);
			setParameterNumber(pp300);
			setParameterArrayNumber(pa317);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int checkRoleOnCreate(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300P_CHECKROLE_ON_CREATE);
			setParameterNumber(fo100);
			int result = (int) Float.parseFloat(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Pieper> getListPieperOwner(int fo100, String pvSearch, String pvSearchStem, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300P_LISTOFTABP300P_OWNER);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
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
					P300PPMG p300pmg = new P300PPMG();
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
					p300pmg.setPn306(obj.getInt("PN306"));
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
					if(obj.has("TOTAL_REPORTS"))
						p300pmg.setTotalReports(obj.getInt("TOTAL_REPORTS"));
					if (obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
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
