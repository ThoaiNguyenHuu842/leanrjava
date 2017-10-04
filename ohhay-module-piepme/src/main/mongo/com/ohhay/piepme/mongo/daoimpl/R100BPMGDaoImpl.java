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
import com.ohhay.piepme.mongo.dao.R100BPMGDao;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;

/**
 * @author TuNt
 * create date Mar 15, 2017
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R100BP)
@Scope("prototype")
public class R100BPMGDaoImpl extends QbMongoDaoSupport implements R100BPMGDao{

	@Override
	public List<R100PSta01> listOfTabR100B01(int fo100, int fp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100B_LISTOFTABR100B_01);
			setParameterNumber(fo100);
			setParameterNumber(fp300);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<R100PSta01> listResult = new ArrayList<R100PSta01>();
				
				R100PSta01 r100pSta01 = new R100PSta01();
				JSONObject data = new JSONObject(kq);
				r100pSta01.setReaches(data.getInt("REACHES"));
				if(data.has("REACHES_5KM"))
					r100pSta01.setReaches05km(data.getInt("REACHES_5KM"));
				if(data.has("REACHES_10KM"))
					r100pSta01.setReaches10km(data.getInt("REACHES_10KM"));
				if(data.has("REACHES_30KM"))
					r100pSta01.setReaches30km(data.getInt("REACHES_30KM"));
				if(data.has("REACHES_100KM"))
					r100pSta01.setReaches100km(data.getInt("REACHES_100KM"));
				
				List<R100PSta01Sum> listPiepsSta = new ArrayList<R100PSta01Sum>();
				JSONArray piepsStaData = data.getJSONArray("PIEPS_STA");
				for(int i = 0; i < piepsStaData.length() ; i++){
					JSONObject sta = piepsStaData.getJSONObject(i);
					R100PSta01Sum r100pSta01Sum = new R100PSta01Sum();
					r100pSta01Sum.setViews(sta.getInt("VIEWS"));
					r100pSta01Sum.setFollows(sta.getInt("FOLLOWS"));
					r100pSta01Sum.setLikes(sta.getInt("LIKES"));
					r100pSta01Sum.setReaches(sta.getInt("REACHES"));
					r100pSta01Sum.setPiep(sta.getInt("PIEP"));
					r100pSta01Sum.setPiepDate(sta.getString("PIEP_DATE"));
					listPiepsSta.add(r100pSta01Sum);
				}
				if(data.has("STA")){
					JSONObject sta = data.getJSONObject("STA");
					R100PSta01Sum r100pSta01Sum = new R100PSta01Sum();
					r100pSta01Sum.setViews(sta.getInt("VIEWS"));
					r100pSta01Sum.setFollows(sta.getInt("FOLLOWS"));
					r100pSta01Sum.setLikes(sta.getInt("LIKES"));
					r100pSta01Sum.setReaches(sta.getInt("REACHES"));
					r100pSta01.setSta(r100pSta01Sum);
				}
				r100pSta01.setPiepsSta(listPiepsSta);
				listResult.add(r100pSta01);
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public int insertTabR100B(int fo100, int fp300, int fo100t, int rn101, String rv102) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100B_INSERTTABR100B);
			setParameterNumber(fo100);
			setParameterNumber(fp300);
			setParameterNumber(fo100t);
			setParameterNumber(rn101);
			setParameterString(rv102);
			int result = (int)Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
