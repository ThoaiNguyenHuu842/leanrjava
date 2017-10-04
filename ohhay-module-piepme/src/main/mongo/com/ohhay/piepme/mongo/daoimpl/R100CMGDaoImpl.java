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
import com.ohhay.piepme.mongo.dao.R100CPMGDao;
import com.ohhay.piepme.mongo.dao.R100PMGDao;
import com.ohhay.piepme.mongo.nestedentities.R100CSta1;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;
import com.ohhay.piepme.mongo.userprofile.N100PMG;


/**
 * @author ThoaiNH
 * create Feb 24, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_R100C)
@Scope("prototype")
public class R100CMGDaoImpl extends QbMongoDaoSupport implements R100CPMGDao{

	@Override
	public List<R100CSta1> listOfTabR100C01(int fo100, int fp300) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R100C_LISTOFTABR100C_01);
			setParameterNumber(fo100);
			setParameterNumber(fp300);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<R100CSta1> listResult = new ArrayList<R100CSta1>();
				
				R100CSta1 r100pSta01 = new R100CSta1();
				JSONObject data = new JSONObject(kq);
				r100pSta01.setTotalLikes(data.getInt("TOTAL_LIKES"));
				r100pSta01.setTotalViews(data.getInt("TOTAL_VIEWS"));
				r100pSta01.setTotalReaches(data.getInt("TOTAL_REACHES"));
				/*
				 * status for each piep
				 */
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
				r100pSta01.setPiepsSta(listPiepsSta);
				/*
				 * list user like
				 */
				List<N100PMG> listUsersLike = new ArrayList<N100PMG>();
				JSONArray usersLikeData = data.getJSONArray("USERS_LIKE");
				for(int i = 0; i < usersLikeData.length() ; i++){
					JSONObject jsonObject = usersLikeData.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					JSONObject n100 = jsonObject.getJSONObject("N100");
					n100pmg.setFo100(Integer.parseInt(n100.getString("FO100")));
					n100pmg.setNv101(n100.getString("NV101"));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listUsersLike.add(n100pmg);
				}
				r100pSta01.setListUsersLike(listUsersLike);
				/*
				 * list user View
				 */
				List<N100PMG> listUsersView = new ArrayList<N100PMG>();
				JSONArray usersViewData = data.getJSONArray("USERS_VIEW");
				for(int i = 0; i < usersViewData.length() ; i++){
					JSONObject jsonObject = usersViewData.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					JSONObject n100 = jsonObject.getJSONObject("N100");
					n100pmg.setFo100(Integer.parseInt(n100.getString("FO100")));
					n100pmg.setNv101(n100.getString("NV101"));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listUsersView.add(n100pmg);
				}
				r100pSta01.setListUsersView(listUsersView);
				listResult.add(r100pSta01);
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertTabR100C(int fo100, int fp300, int fo100t, int rn101, String rv102) {
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
