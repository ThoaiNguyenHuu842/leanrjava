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
import com.ohhay.piepme.mongo.dao.R100VMGDao;
import com.ohhay.piepme.mongo.entities.other.R100VAMG;
import com.ohhay.piepme.mongo.entities.other.R100VMG;

/**
 * @author ThoaiVt
 * @date 07-07-2017
 * @tag ohhay-core
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R100V)
@Scope("prototype")
public class R100VMGDaoImpl extends QbMongoDaoSupport implements R100VMGDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.core.mongo.dao.R100VMGDao#listOfTabR100V01(int, int) get
	 * statistic Voucher
	 */
	@Override
	public R100VAMG listOfTabR100V01(int fo100, int pv300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100V_LISTOFTABR100V_01);
			setParameterNumber(fo100);
			setParameterNumber(pv300);
			R100VAMG r100vamg = new R100VAMG();
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				JSONObject data = new JSONObject(resultData);
				/*
				 * SENT
				 */
				if(data.has("SENT")){
					JSONObject dataContent = data.getJSONObject("SENT");
					List<R100VMG> r100vmgs = new ArrayList<R100VMG>();
					r100vamg.setTotalSent(dataContent.getInt("TOTAL"));
					JSONArray dataArSent = new JSONArray(dataContent.getString("USERS"));
					for (int i = 0; i < dataArSent.length(); i++) {
						R100VMG r100vmg = new R100VMG();
						JSONObject obj = dataArSent.getJSONObject(i);
						JSONArray jsonArray = new JSONArray(obj.getString("FO100R"));
						int fo100Vr = Integer.parseInt(jsonArray.get(0).toString());
						r100vmg.setFo100(fo100Vr);
						r100vmg.setUrlAvarta(obj.getString("AVARTA_URL"));
						r100vmgs.add(r100vmg);
					}
					r100vamg.setR100vmgsSent(r100vmgs);
				}
				/*
				 * VIEWS
				 */
				if(data.has("VIEWS")){
					List<R100VMG> r100vmgs = new ArrayList<R100VMG>();
					JSONObject dataContent = data.getJSONObject("VIEWS");
					r100vamg.setTotalView(dataContent.getInt("TOTAL"));
					JSONArray dataArSent = new JSONArray(dataContent.getString("USERS"));
					for (int i = 0; i < dataArSent.length(); i++) {
						R100VMG r100vmg = new R100VMG();
						JSONObject obj = dataArSent.getJSONObject(i);
						JSONArray jsonArray = new JSONArray(obj.getString("FO100R"));
						int fo100Vr = Integer.parseInt(jsonArray.get(0).toString());
						r100vmg.setFo100(fo100Vr);
						r100vmg.setUrlAvarta(obj.getString("AVARTA_URL"));
						r100vmgs.add(r100vmg);
					}
					r100vamg.setR100vmgsView(r100vmgs);
				}

				/*
				 * USED
				 */
				if(data.has("USED")){
					List<R100VMG> r100vmgs = new ArrayList<R100VMG>();
					JSONObject dataContent = data.getJSONObject("USED");
					r100vamg.setTotalUsed(dataContent.getInt("TOTAL"));
					JSONArray dataArSent = new JSONArray(dataContent.getString("USERS"));
					for (int i = 0; i < dataArSent.length(); i++) {
						R100VMG r100vmg = new R100VMG();
						JSONObject obj = dataArSent.getJSONObject(i);
						JSONArray jsonArray = new JSONArray(obj.getString("FO100R"));
						int fo100Vr = Integer.parseInt(jsonArray.get(0).toString());
						r100vmg.setFo100(fo100Vr);
						r100vmg.setUrlAvarta(obj.getString("AVARTA_URL"));
						r100vmgs.add(r100vmg);
					}
					r100vamg.setR100vmgsUsed(r100vmgs);
				}
				return r100vamg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.core.mongo.dao.R100VMGDao#listOfTabR100V02(int, int, int,
	 * int) list received Voucher
	 */
	@Override
	public List<R100VMG> listOfTabR100V02(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100V_LISTOFTABR100V_02);
			setParameterNumber(fo100);
			setParameterNumber(pv300);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				List<R100VMG> r100vmgs = new ArrayList<R100VMG>();
				JSONArray data = new JSONArray(resultData);
				for (int i = 0; i < data.length(); i++) {
					R100VMG r100vmg = new R100VMG();
					JSONObject obj = data.getJSONObject(i);
					JSONArray jsonArray = new JSONArray(obj.getString("FO100R"));
					int fo100Vr = Integer.parseInt(jsonArray.get(0).toString());
					r100vmg.setFo100(fo100Vr);
					r100vmg.setUrlAvarta(obj.getString("AVARTA_URL"));
					r100vmg.setNv106(obj.getString("NV106"));
					r100vmgs.add(r100vmg);
				}
				return r100vmgs;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.core.mongo.dao.R100VMGDao#listOfTabR100V03(int, int, int,
	 * int) list viewer Voucher
	 */
	@Override
	public List<R100VMG> listOfTabR100V03(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100V_LISTOFTABR100V_03);
			setParameterNumber(fo100);
			setParameterNumber(pv300);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				List<R100VMG> r100vmgs = new ArrayList<R100VMG>();
				JSONArray data = new JSONArray(resultData);
				for (int i = 0; i < data.length(); i++) {
					R100VMG r100vmg = new R100VMG();
					JSONObject obj = data.getJSONObject(i);
					JSONArray jsonArray = new JSONArray(obj.getString("FO100R"));
					int fo100Vr = Integer.parseInt(jsonArray.get(0).toString());
					r100vmg.setFo100(fo100Vr);
					r100vmg.setUrlAvarta(obj.getString("AVARTA_URL"));
					r100vmg.setNv106(obj.getString("NV106"));
					r100vmgs.add(r100vmg);
				}
				return r100vmgs;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ohhay.core.mongo.dao.R100VMGDao#listOfTabR100V04(int, int, int,
	 * int) list used Voucher
	 */
	@Override
	public List<R100VMG> listOfTabR100V04(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100V_LISTOFTABR100V_04);
			setParameterNumber(fo100);
			setParameterNumber(pv300);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				List<R100VMG> r100vmgs = new ArrayList<R100VMG>();
				JSONArray data = new JSONArray(resultData);
				for (int i = 0; i < data.length(); i++) {
					R100VMG r100vmg = new R100VMG();
					JSONObject obj = data.getJSONObject(i);
					JSONArray jsonArray = new JSONArray(obj.getString("FO100R"));
					int fo100Vr = Integer.parseInt(jsonArray.get(0).toString());
					r100vmg.setFo100(fo100Vr);
					r100vmg.setUrlAvarta(obj.getString("AVARTA_URL"));
					r100vmg.setNv106(obj.getString("NV106"));
					r100vmg.setTotalUsed(obj.getInt("PN315"));
					r100vmgs.add(r100vmg);
				}
				return r100vmgs;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
