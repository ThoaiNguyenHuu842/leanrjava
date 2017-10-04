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
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.piepme.mongo.dao.P300BEmPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300BEmPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;


/**
 * @author ThoaiNH
 * create Mar 13, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P300BEMP)
@Scope("prototype")
public class P300BEmPMGDaoImpl extends QbMongoDaoSupport implements P300BEmPMGDao {
	@Override
	public List<Pieper> getListPieper(double pnLa, double pnLong, int fo100, int fo100f, String pvSearch, String pvSearchStem, String pvSearchAd, String pvSearchStemAd, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300BEM);
			setParameterNumber(pnLa);
			setParameterNumber(pnLong);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterString(pvSearchAd);
			setParameterString(pvSearchStemAd);
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
	public P300BEmPMG getPieperDetailEm(int fo100, int pp100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300B_LISTOFTABP300B_DETAIL_EM);
			setParameterNumber(fo100);
			setParameterNumber(pp100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				P300BEmPMG p300pmg = new P300BEmPMG();
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
				if (json.has("OTAG_AD")) {
					List<String> listTemp = new ArrayList<String>();
					JSONArray otags = json.getJSONArray("OTAG_AD");
					for (int j = 0; j < otags.length(); j++) {
						String otagString = otags.getString(j);
						listTemp.add(otagString);
					}
					p300pmg.setOtagAd(listTemp.toArray(new String[listTemp.size()]));
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
				JSONObject loc = json.getJSONObject("LOC");
				GeoDataPointMG dataPointMG = new GeoDataPointMG(loc.getJSONArray("coordinates").getDouble(0), loc.getJSONArray("coordinates").getDouble(1), loc.getString("address_full_name"));
				p300pmg.setLocation(dataPointMG);
				return p300pmg;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
