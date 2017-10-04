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
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.dao.T110PMGDao;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_T110P)
@Scope("prototype")
public class T110PMGDaoImpl extends QbMongoDaoSupport implements T110PMGDao{

	@Override
	public List<T110PMG> getListT110EOM(int fo100, int ft100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T100_LISTOFTABT110);
			setParameterNumber(fo100);
			setParameterNumber(ft100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<T110PMG> listResult = new ArrayList<T110PMG>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					T110PMG t110pmg = new T110PMG();
					t110pmg.setId(obj.getInt("_id"));
					t110pmg.setFt100(obj.getInt("FT100"));
					t110pmg.setFt110(obj.getInt("FT110"));
					t110pmg.setFo100(obj.getInt("FO100"));
					t110pmg.setTv111(obj.getString("TV111"));
					if(obj.has("TV112"))
						t110pmg.setTv112(obj.getString("TV112"));
					t110pmg.setTv119(obj.getString("TV119"));
					if(obj.has("TOTAL_PIEPER"))
						t110pmg.setTotalPieper(obj.getInt("TOTAL_PIEPER"));
					if(obj.has("TV120"))
						t110pmg.setTv120(obj.getString("TV120"));
					listResult.add(t110pmg);
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T110PMG> getNearestCOM1(int fo100, double latitude, double longitude, int ft100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T110_GETT110NEAREST);
			setParameterNumber(fo100);
			setParameterNumber(latitude);
			setParameterNumber(longitude);
			setParameterNumber(ft100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<T110PMG> listResult = new ArrayList<T110PMG>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					T110PMG t110pmg = new T110PMG();
					JSONObject obj =array.getJSONObject(i);
					t110pmg.setId(obj.getInt("_id"));
					t110pmg.setFo100(obj.getInt("FO100"));
					t110pmg.setTv111(obj.getString("TV111"));
					if(obj.has("TV112"))
						t110pmg.setTv112(obj.getString("TV112"));
					if(obj.has("TV119"))
						t110pmg.setTv119(obj.getString("TV119"));
					if(obj.has("ROLE"))
						t110pmg.setRole(obj.getString("ROLE"));
					if(obj.has("LOC")){
						JSONObject loc = obj.getJSONObject("LOC");
						GeoDataPointMG dataPointMG = new GeoDataPointMG(loc.getJSONArray("coordinates").getDouble(0), loc.getJSONArray("coordinates").getDouble(1), loc.getString("address_full_name"));
						t110pmg.setLocation(dataPointMG);
					}
					if(obj.has("TV120"))
						t110pmg.setTv120(obj.getString("TV120"));
					listResult.add(t110pmg);
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<N100SummaryInfo> listOfTabT110Users(int pnFO100, int pnFT110, String pnTV119, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T110_LISTOFTABT110_USERS);
			setParameterNumber(pnFO100);
			setParameterNumber(pnFT110);
			setParameterString(pnTV119);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100).toString();
			if (kq != null) {
				List<N100SummaryInfo> listResult = new ArrayList<N100SummaryInfo>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					N100SummaryInfo n100SummaryInfo = new N100SummaryInfo(obj.getInt("_id"), obj.getInt("FO100"), obj.getString("AVARTA_URL"), obj.getString("NV106"));
					listResult.add(n100SummaryInfo);
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
