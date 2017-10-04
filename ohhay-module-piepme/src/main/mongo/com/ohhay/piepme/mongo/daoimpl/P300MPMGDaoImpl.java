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
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.P300MPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300MMG;
import com.ohhay.piepme.mongo.nestedentities.P300ConInfo;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;

/**
 * @author ThoaiNH create Sep 21, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P300MP)
@Scope("prototype")
public class P300MPMGDaoImpl extends QbMongoDaoSupport implements P300MPMGDao {

	@Override
	public P300MMG getPieperDetail(int fo100, int pp300, int pnSeen) {
		try {
			setFunctionName(QbMongoFunctionNames.P300M_LISTOFTABP300M_DETAIL);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			setParameterNumber(pnSeen);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				P300MMG p300pmg = new P300MMG();
				JSONObject json = new JSONObject(kq);
				p300pmg.setId(json.getInt("_id"));
				p300pmg.setFo100(json.getInt("FO100"));
				p300pmg.setPv301(json.getString("PV301"));
				p300pmg.setPv302(json.getString("PV302"));
				p300pmg.setPn303(json.getInt("PN303"));
				if(json.has("PV304"))
					p300pmg.setPv304(json.getString("PV304"));
				if(json.has("PV304THUMB"))
					p300pmg.setPv304Thumb(json.getString("PV304THUMB"));
				p300pmg.setPv305(json.getString("PV305"));
				if(json.has("PN310"))
					p300pmg.setPn310(json.getInt("PN310"));
				if(json.has("NN109"))
					p300pmg.setNn109(json.getInt("NN109"));
				if(json.has("NN110"))
					p300pmg.setNn110(json.getInt("NN110"));
				if(json.has("VD303"))
					p300pmg.setVd303(json.getString("VD303"));
				if(json.has("VD304"))
					p300pmg.setVd304(json.getString("VD304"));
				try{
					p300pmg.setPn309(Float.parseFloat(json.getString("PN309")));
					JSONObject m900 = json.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setNv108(m900.getString("NV108"));
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p300pmg.setPd308(df.parse(json.getString("PD308")));
					if(json.has("PIEPER_IMGS"))
					{
						JSONArray jsonArray = json.getJSONArray("PIEPER_IMGS");
						List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
						for(int i = 0; i < jsonArray.length(); i++){
							JSONObject img = jsonArray.getJSONObject(i);
							PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
							pieperImgs.add(pieperImg);
						}
						p300pmg.setPieperImgs(pieperImgs);
					}
					if(json.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(json.getString("TIME_AGO")));
					if(json.has("VOUCHER_CODE"))
						p300pmg.setVoucherCode(json.getString("VOUCHER_CODE"));
					if(json.has("VV308"))
						p300pmg.setVv308(json.getString("VV308"));
				}catch(JSONException e){
					p300pmg.setPn309(0);
					e.printStackTrace();
				}
				return p300pmg;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	
	}

	@Override
	public List<P300ConInfo> listOfTabP300MCon(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_LISTOFTABP300M_CON);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<P300ConInfo> listPieper = new ArrayList<P300ConInfo>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300ConInfo p300ConInfo = new P300ConInfo();
					p300ConInfo.setPv301(obj.getString("PV301"));
					p300ConInfo.setPv302(obj.getString("PV302"));
					p300ConInfo.setPd308(obj.getString("PD308"));
					p300ConInfo.setFo100f(obj.getInt("FO100F"));
					p300ConInfo.setFo100(obj.getInt("FO100"));
					p300ConInfo.setPn303(obj.getInt("PN303"));
					p300ConInfo.setFp300(obj.getInt("FP300"));
					p300ConInfo.setFo100Owner(obj.getInt("FO100OWNER"));
					if(obj.has("IS_NEW"))
						p300ConInfo.setIsNew(1);
					/*
					 * M900 info
					 */
					if( obj.has("M900")){
						JSONObject m900Json = obj.getJSONObject("M900");
						int fo100F = m900Json.getInt("_id");
						String mv908 = null;
						if(m900Json.has("MV908"))
							mv908 = m900Json.getString("MV908");
						String mv905 = m900Json.getString("MV905");
						String region = m900Json.getString("REGION");
						p300ConInfo.setNv100(m900Json.getString("NV100"));
						p300ConInfo.setUrlAvatar(ApplicationHelper.getUrlAvarta(fo100F, mv908, mv905, region));
						listPieper.add(p300ConInfo);
					}
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
	public List<P300MMG> listOfTabP300M(int fo100, int fo100f, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_LISTOFTABP300M);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<P300MMG> listPieper = new ArrayList<P300MMG>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300MMG p300pmg = new P300MMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PD309"))
						p300pmg.setPd309(obj.getString("PD309"));
					if(obj.has("PD308"))
						p300pmg.setPd308String(obj.getString("PD308"));
					if(obj.has("DELIVERY_STT"))
						p300pmg.setDeliveryStt(obj.getInt("DELIVERY_STT"));
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try{
						p300pmg.setNv108(m900.getString("NV108"));
					}catch(JSONException e){
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					/*SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p100pmg.setPd308(df.parse(obj.getString("PD308")));*/
					if(obj.has("PN312"))
						p300pmg.setPn312((int)Double.parseDouble(obj.getString("PN312")));
					if(obj.has("PN313"))
						p300pmg.setPn313((int)Double.parseDouble(obj.getString("PN313")));
					if(obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if(obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if(obj.has("PIEPER_IMGS"))
					{
						JSONArray jsonArray = obj.getJSONArray("PIEPER_IMGS");
						List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
						for(int j = 0; j < jsonArray.length(); j++){
							JSONObject img = jsonArray.getJSONObject(j);
							PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
							pieperImgs.add(pieperImg);
						}
						p300pmg.setPieperImgs(pieperImgs);
					}
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					if(obj.has("VOUCHER_CODE"))
						p300pmg.setVoucherCode(obj.getString("VOUCHER_CODE"));
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
	public List<P300MMG> listOfTabP300MArchived(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_LISTOFTABP300M_ARCHIVED);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String result = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (result != null) {
				List<P300MMG> listPieper = new ArrayList<P300MMG>();
				JSONArray json = new JSONArray(result);
				for (int i = 0; i < json.length(); i++) {
					JSONObject obj = json.getJSONObject(i);
					P300MMG p300pmg = new P300MMG();
					p300pmg.setId(obj.getInt("FP300"));
					p300pmg.setPd308String(obj.getString("PD308"));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					p300pmg.setFo100(obj.getInt("FO100"));
					p300pmg.setFo100f(obj.getInt("FO100F"));
					M900MG m900 = new M900MG();
					JSONObject jsonM900 = obj.getJSONObject("M900");
					m900.setId(jsonM900.getInt("_id"));
					m900.setNv100(jsonM900.getString("NV100"));
					m900.setMv903(jsonM900.getString("MV903"));
					m900.setMv905(jsonM900.getString("MV905"));
					m900.setMv908(jsonM900.getString("MV908"));
					p300pmg.setM900mg(m900);
					if(obj.has("PIEPER_IMGS"))
					{
						JSONArray jsonArray = obj.getJSONArray("PIEPER_IMGS");
						List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
						for(int j = 0; j < jsonArray.length(); j++){
							JSONObject img = jsonArray.getJSONObject(j);
							PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
							pieperImgs.add(pieperImg);
						}
						p300pmg.setPieperImgs(pieperImgs);
					}
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					listPieper.add(p300pmg);
				}
				return listPieper;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public int archiveTabP300(int fo100, int fo100f) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_ARCHIVETAB_P300M);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int storNoTabP300MCon(int fo100, int fo100f) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_STORNOTAB_CON);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateTabPD310(int fo100, int fo100dn, String voucherCode) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_UPDATETAB_PD310);
			setParameterNumber(fo100);
			setParameterNumber(fo100dn);
			setParameterString(voucherCode);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<P300ConInfo> listOfTabP300MConV2(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_LISTOFTABP300M_CONV2);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<P300ConInfo> listPieper = new ArrayList<P300ConInfo>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300ConInfo p300ConInfo = new P300ConInfo();
					p300ConInfo.setPv301(obj.getString("PV301"));
					p300ConInfo.setPv302(obj.getString("PV302"));
					p300ConInfo.setPd308(obj.getString("PD308"));
					p300ConInfo.setFo100f(obj.getInt("FO100F"));
					p300ConInfo.setFo100(obj.getInt("FO100"));
					p300ConInfo.setPn303(obj.getInt("PN303"));
					p300ConInfo.setFp300(obj.getInt("FP300"));
					p300ConInfo.setFo100Owner(obj.getInt("FO100OWNER"));
					if(obj.has("IS_NEW"))
						p300ConInfo.setIsNew(1);
					/*
					 * M900 info
					 */
					if( obj.has("M900")){
						JSONObject m900Json = obj.getJSONObject("M900");
						int fo100F = m900Json.getInt("_id");
						String mv908 = null;
						if(m900Json.has("MV908"))
							mv908 = m900Json.getString("MV908");
						String mv905 = m900Json.getString("MV905");
						String region = m900Json.getString("REGION");
						p300ConInfo.setNv100(m900Json.getString("NV100"));
						p300ConInfo.setUrlAvatar(ApplicationHelper.getUrlAvarta(fo100F, mv908, mv905, region));
						listPieper.add(p300ConInfo);
					}
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
	public List<P300MMG> listOfTabP300MV2(int fo100, int fo100f, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_LISTOFTABP300MV2);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null) {
				List<P300MMG> listPieper = new ArrayList<P300MMG>();
				JSONArray array = new JSONArray(kq);
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					P300MMG p300pmg = new P300MMG();
					p300pmg.setId((int) Double.parseDouble(obj.getString("_id")));
					p300pmg.setFo100((int) Double.parseDouble(obj.getString("FO100")));
					p300pmg.setPn303((int) Double.parseDouble(obj.getString("PN303")));
					p300pmg.setPv301(obj.getString("PV301"));
					p300pmg.setPv302(obj.getString("PV302"));
					if(obj.has("PV304"))
						p300pmg.setPv304(obj.getString("PV304"));
					if(obj.has("PD309"))
						p300pmg.setPd309(obj.getString("PD309"));
					if(obj.has("PD308"))
						p300pmg.setPd308String(obj.getString("PD308"));
					if(obj.has("DELIVERY_STT"))
						p300pmg.setDeliveryStt(obj.getInt("DELIVERY_STT"));
					JSONObject m900 = obj.getJSONObject("M900");
					p300pmg.setNv100(m900.getString("NV100"));
					p300pmg.setNv106(m900.getString("NV106"));
					p300pmg.setMv903(m900.getString("MV903"));
					p300pmg.setMv905(m900.getString("MV905"));
					p300pmg.setNn109(m900.getInt("NN109"));
					p300pmg.setNn110(m900.getInt("NN110"));
					try{
						p300pmg.setNv108(m900.getString("NV108"));
					}catch(JSONException e){
						p300pmg.setNv108("");
					}
					p300pmg.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						p300pmg.setMv908(m900.getString("MV908"));
					p300pmg.setUrlAvarta(p300pmg.getUrlAvarta());
					/*SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					p100pmg.setPd308(df.parse(obj.getString("PD308")));*/
					if(obj.has("PN312"))
						p300pmg.setPn312((int)Double.parseDouble(obj.getString("PN312")));
					if(obj.has("PN313"))
						p300pmg.setPn313((int)Double.parseDouble(obj.getString("PN313")));
					if(obj.has("PV314"))
						p300pmg.setPv314(obj.getString("PV314"));
					if(obj.has("PN309"))
						p300pmg.setPn309(Float.parseFloat(obj.getString("PN309")));
					if(obj.has("PIEPER_IMGS"))
					{
						JSONArray jsonArray = obj.getJSONArray("PIEPER_IMGS");
						List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
						for(int j = 0; j < jsonArray.length(); j++){
							JSONObject img = jsonArray.getJSONObject(j);
							PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
							pieperImgs.add(pieperImg);
						}
						p300pmg.setPieperImgs(pieperImgs);
					}
					if(obj.has("TIME_AGO"))
						p300pmg.setTimeAgo(Long.parseLong(obj.getString("TIME_AGO")));
					if(obj.has("VOUCHER_CODE"))
						p300pmg.setVoucherCode(obj.getString("VOUCHER_CODE"));
					if(obj.has("PINNED"))
						p300pmg.setPinned(obj.getInt("PINNED"));
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
	public int pinTabP300M(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_PINTAB_P300M);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int unpinTabP300M(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300M_UNPINTAB_P300M);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	
}
