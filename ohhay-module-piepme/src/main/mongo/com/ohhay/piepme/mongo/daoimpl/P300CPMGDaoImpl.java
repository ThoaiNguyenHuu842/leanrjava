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
import com.ohhay.core.entities.mongo.other.LIKES;
import com.ohhay.piepme.mongo.dao.P300CPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.G100SummaryInfo;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author TuNt
 * create date Nov 5, 2016
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P300CP)
@Scope("prototype")
public class P300CPMGDaoImpl extends QbMongoDaoSupport implements P300CPMGDao {

	@Override
	public List<Pieper> getListCirclePieper(int fo100, int fo100t, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300C_LISTOFTABP300C);
			setParameterNumber(fo100);
			setParameterNumber(fo100t);
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
					P300CPMG p300mg = new P300CPMG();
					p300mg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300mg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300mg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300mg.setPv301(obj.getString("PV301"));
					p300mg.setPv302(obj.getString("PV302"));
					p300mg.setViews(obj.getInt("VIEWS"));
					if(obj.has("PV304THUMB"));
						p300mg.setPv304Thumb(obj.getString("PV304THUMB"));
					if(obj.has("PV304"))
						p300mg.setPv304(obj.getString("PV304"));
					if(obj.has("PN306"))
						p300mg.setPn306(obj.getInt("PN306"));
					if(obj.has("PV304THUMB"))
						p300mg.setPv304Thumb(obj.getString("PV304THUMB"));
					if (obj.has("OTAG")) {
						List<String> listTemp = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAG");
						for (int j = 0; j < otags.length(); j++) {
							String otagString = otags.getString(j);
							listTemp.add(otagString);
						}
						p300mg.setOtags(listTemp.toArray(new String[listTemp.size()]));
					}
					JSONObject m900 = obj.getJSONObject("M900");
					p300mg.setNv100(m900.getString("NV100"));
					p300mg.setNv106(m900.getString("NV106"));
					p300mg.setMv903(m900.getString("MV903"));
					p300mg.setMv905(m900.getString("MV905"));
					if(m900.has("NV108"))
						p300mg.setNv108(m900.getString("NV108"));
					p300mg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300mg.setMv908(m900.getString("MV908"));
					p300mg.setUrlAvarta(p300mg.getUrlAvarta());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300mg.setPd308(df.parse(obj.getString("PD308")));
					p300mg.setPd316(df.parse(obj.getString("PD316")));
					if(obj.has("PN312"))
						p300mg.setPn312((int)Double.parseDouble(obj.getString("PN312")));
					if(obj.has("PN313"))
						p300mg.setPn313((int)Double.parseDouble(obj.getString("PN313")));
					if(obj.has("PV314"))
						p300mg.setPv314(obj.getString("PV314"));
					if(obj.has("TIME_AGO"))
						p300mg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					if(obj.has("TOTAL_RECIPIENTS"))
						p300mg.setTotalRecipients(obj.getInt("TOTAL_RECIPIENTS"));
					listPieper.add(p300mg);
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
	public P300CPMG getPieperDetail(int fo100, int pp200) {

		try {
			setFunctionName(QbMongoFunctionNames.P300C_LISTOFTABP300C_DETAIL);
			setParameterNumber(fo100);
			setParameterNumber(pp200);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				P300CPMG p300pmg = new P300CPMG();
				JSONObject json = new JSONObject(kq);
				p300pmg.setId(json.getInt("_id"));
				p300pmg.setFo100(json.getInt("FO100"));
				p300pmg.setPv301(json.getString("PV301"));
				p300pmg.setPv302(json.getString("PV302"));
				p300pmg.setPn303(json.getInt("PN303"));
				p300pmg.setLiked(json.getInt("LIKED"));
				p300pmg.setPv304(json.getString("PV304"));
				if(json.has("PN306"))
					p300pmg.setPn306(json.getInt("PN306"));
				p300pmg.setViews(json.getInt("VIEWS"));
				p300pmg.setPv305(json.getString("PV305"));
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
				if(json.has("FG100S")){
					JSONArray fg100s = json.getJSONArray("FG100S");
					List<Integer> listFG100S = new ArrayList<Integer>();
					for(int i = 0; i < fg100s.length(); i++)
						listFG100S.add(fg100s.getInt(i));
					p300pmg.setListFG100S(listFG100S);
				}
				if(json.has("FO100S")){
					JSONArray fo100s = json.getJSONArray("FO100S");
					List<Integer> listFO100S = new ArrayList<Integer>();
					for(int i = 0; i < fo100s.length(); i++)
						listFO100S.add(fo100s.getInt(i));
					p300pmg.setListFO100S(listFO100S);
				}
				if(json.has("GROUPS")){
					List<G100SummaryInfo> list = new ArrayList<G100SummaryInfo>();
					JSONArray groups = json.getJSONArray("GROUPS");
					for(int i = 0; i < groups.length(); i++){
						JSONObject jsonObject = groups.getJSONObject(i);
						G100SummaryInfo g100SummaryInfo = new G100SummaryInfo(jsonObject.getInt("_id"), jsonObject.getString("GV101"), jsonObject.getString("GV102"));
						list.add(g100SummaryInfo);
					}
					p300pmg.setGroups(list);
				}
				if(json.has("RECIPIENTS")){
					List<N100SummaryInfo> list = new ArrayList<N100SummaryInfo>();
					JSONArray recipients = json.getJSONArray("RECIPIENTS");
					for(int i = 0; i < recipients.length(); i++){
						JSONObject jsonObject = recipients.getJSONObject(i);
						N100SummaryInfo n100SummaryInfo = new N100SummaryInfo(jsonObject.getInt("_id"), jsonObject.getInt("FO100"), jsonObject.getString("AVARTA_URL"), jsonObject.getString("NV106"));
						list.add(n100SummaryInfo);
					}
					p300pmg.setRecipients(list);
				}
				/*
				 * get 3 newest users like pieper
				 */
				/*if(json.has("LIKES")){
					List<LIKES> listUserLikes = new ArrayList<LIKES>();
					JSONArray likes = json.getJSONArray("LIKES");
					for(int i = 0; i < likes.length(); i++){
						JSONObject like = likes.getJSONObject(i);
						if(like.has("M900")){
							JSONObject m900Like = like.getJSONObject("M900");
							LIKES likesEntity = new LIKES();
							likesEntity.setFo100(m900.getInt("_id"));
							likesEntity.setMv903(m900Like.getString("MV903"));
							likesEntity.setMv905(m900Like.getString("MV905"));
							likesEntity.setMv908(m900Like.getString("MV908"));
							likesEntity.setRegion(m900Like.getString("REGION"));
							likesEntity.setUrlAvarta(likesEntity.getUrlAvarta());
							listUserLikes.add(likesEntity);
						}
					}
					p200pmg.setListUserLikes(listUserLikes);
				}*/
				if(json.has("TOTAL_RECIPIENTS"))
					p300pmg.setTotalRecipients(json.getInt("TOTAL_RECIPIENTS"));
				if(json.has("TIME_AGO"))
					p300pmg.setTimeAgo(Long.parseLong(json.getString("TIME_AGO")));
				if(json.has("PD316"))
					p300pmg.setPd316(df.parse(json.getString("PD316")));
				return p300pmg;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> listSuggestedOtag(int fo100, String pvSearch, String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300C_LIST_SUGGESTED_OTAG);
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
	public int storNoTabP300C(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300C_STORNOTAB_P300C);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
