package com.ohhay.piepme.mongo.daoimpl;

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
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.P300VPMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300VMG;
import com.ohhay.piepme.mongo.nestedentities.P300VConInfo;
import com.ohhay.piepme.mongo.nestedentities.P300VDetailInfo;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.nestedentities.V300PMG;
import com.ohhay.piepme.mongo.nestedentities.VoucherInfo;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_P300VP)
@Scope("prototype")
public class P300VPMGDaoImpl extends QbMongoDaoSupport implements P300VPMGDao{

	@Override
	public List<P300VConInfo> getListPieperOwner(int fo100, String pvSearch, int sort, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300V_LISTOFTABP300V_OWNER);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterNumber(sort);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			if (kq != null && ApplicationHelper.isJSON(kq)) {
				List<P300VConInfo> listPieper = new ArrayList<P300VConInfo>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					P300VConInfo p300v = new P300VConInfo();
					JSONObject obj = array.getJSONObject(i);
					p300v.setId(obj.getInt("_id"));
					p300v.setFo100(obj.getInt("FO100"));
					p300v.setPv301(obj.getString("PV301"));
					p300v.setPv302(obj.getString("PV302"));
					p300v.setPv305(obj.getString("PV305"));
					if(obj.has("PV304"))
						p300v.setPv304(obj.getString("PV304"));
					if(obj.has("PV304THUMB"))
						p300v.setPv304(obj.getString("PV304THUMB"));
					if(obj.has("PV314"))
						p300v.setPv314(obj.getString("PV314"));
					p300v.setTotalUsed(obj.getInt("TOTAL_USED"));
					JSONObject v300Object = obj.getJSONObject("V300");
					p300v.setVd303(v300Object.getString("VD303"));
					p300v.setVd304(v300Object.getString("VD304"));
					p300v.setVv307(v300Object.getString("VV307"));
					p300v.setVv308(v300Object.getString("VV308"));
					if(v300Object.has("VV310"))
						p300v.setVv310(v300Object.getString("VV310"));
					listPieper.add(p300v);
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
	public P300VDetailInfo getPieperDetail(int fo100, int pp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300V_LISTOFTABP300V_DETAIL);
			setParameterNumber(fo100);
			setParameterNumber(pp300);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			P300VDetailInfo pieper = new P300VDetailInfo();
			if (kq != null && ApplicationHelper.isJSON(kq)) {
				JSONObject obj = new JSONObject(kq);
				
				pieper.setFo100(obj.getInt("FO100"));
				pieper.setPv301(obj.getString("PV301"));
				pieper.setPv302(obj.getString("PV302"));
				pieper.setPn303(obj.getInt("PN303"));
				if(obj.has("PV304"))
					pieper.setPv304(obj.getString("PV304"));
				if(obj.has("PV304THUMB"))
					pieper.setPv304Thumb(obj.getString("PV304THUMB"));
				pieper.setPv305(obj.getString("PV305"));
				if(obj.has("PN309"))
					pieper.setPn309(obj.getInt("PN309"));
				pieper.setPn310(obj.getInt("PN310"));
				pieper.setPn312(obj.getInt("PN312"));
				pieper.setPn313(obj.getInt("PN313"));
				if(obj.has("PV314"))
					pieper.setPv314(obj.getString("PV314"));
				JSONObject v300Object = obj.getJSONObject("V300");
				
				pieper.setVd303(v300Object.getString("VD303"));
				pieper.setVv307(v300Object.getString("VV307"));
				pieper.setVd304(v300Object.getString("VD304"));
				pieper.setVn309(v300Object.getInt("VN309"));
				if(v300Object.has("FV300OR"))
					pieper.setFv300OR(v300Object.getInt("FV300OR"));
				pieper.setVv308(v300Object.getString("VV308"));	
				if(v300Object.has("VV310"))
					pieper.setVv310(v300Object.getString("VV310"));
				if(obj.has("PIEPER_IMGS"))
				{
					JSONArray jsonArray = obj.getJSONArray("PIEPER_IMGS");
					List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
					for(int i = 0; i < jsonArray.length(); i++){
						JSONObject img = jsonArray.getJSONObject(i);
						PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
						pieperImgs.add(pieperImg);
					}
					pieper.setPieperImgs(pieperImgs);
				}
			}
			return pieper;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VoucherInfo> listAllVoucher(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300V_LISTOFTAB_VOUCHER);
			setParameterNumber(fo100);
			setParameterNumber(pv300);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null && ApplicationHelper.isJSON(kq)) {
				List<VoucherInfo> list = new ArrayList<VoucherInfo>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					VoucherInfo voucherInfo = new VoucherInfo();
					JSONObject obj = array.getJSONObject(i);
					voucherInfo.setFo100(obj.getInt("FO100"));
					voucherInfo.setFp300V(obj.getInt("FP300V"));
					voucherInfo.setNv106(obj.getString("NV106"));
					voucherInfo.setUrlAvarta(obj.getString("AVARTA_URL"));
					voucherInfo.setVoucherCode(obj.getString("VOUCHER_CODE"));
					voucherInfo.setDateCreate(obj.getString("DATE_CREATE"));
					list.add(voucherInfo);
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
	public int getTotalVoucher(int fo100, int pv300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P300V_GETTOTALVOUCHER);
			setParameterNumber(fo100);
			setParameterNumber(pv300);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
