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
import com.ohhay.piepme.mongo.dao.N100BusPMGDao;
import com.ohhay.piepme.mongo.nestedentities.K100SummaryInfo;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Jun 15, 2017
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_N100BUSP)
@Scope("prototype")
public class N100BusPMGDaoImpl extends QbMongoDaoSupport implements N100BusPMGDao{

	@Override
	public List<N100PMG> listOfTabN100Sug(String search, int offset, int limit) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100BUS_SUGGESTED);
			setParameterString(search);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString();
			if(resultData != null){
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					n100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					n100pmg.setNv101(jsonObject.getString("NV101"));
					n100pmg.setNv102(jsonObject.getString("NV102"));
					n100pmg.setNv106(jsonObject.getString("NV106"));
					n100pmg.setNv107(jsonObject.getString("NV107"));
					n100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					if (jsonObject.has("FRIEND_STT")){						
						n100pmg.setFriendStt(Integer.parseInt(jsonObject.getString("FRIEND_STT")));
					}
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(n100pmg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<K100SummaryInfo> listSuggestedEnterprise(int fo100, String pvSearch, String pvSearchStem, int offset, int limit) {
		// TODO Auto-generated method stub
		List<K100SummaryInfo> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.N100_LIST_SUGGESTED_ENTERPRISE);
			setParameterNumber(fo100);
			setParameterString(pvSearch);
			setParameterString(pvSearchStem);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString();
			if(resultData != null){
				listResult = new ArrayList<K100SummaryInfo>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					K100SummaryInfo k100SummaryInfo = new K100SummaryInfo();
					k100SummaryInfo.setId(Integer.parseInt(jsonObject.getString("_id")));
					k100SummaryInfo.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					k100SummaryInfo.setKv101(jsonObject.getString("KV101"));
					k100SummaryInfo.setNv106(jsonObject.getString("NV106"));
					k100SummaryInfo.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					k100SummaryInfo.setFollowingStt(Integer.parseInt(jsonObject.getString("FOLLOWING_STT")));
					listResult.add(k100SummaryInfo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

}
