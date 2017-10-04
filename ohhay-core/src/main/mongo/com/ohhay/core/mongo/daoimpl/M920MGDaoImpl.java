package com.ohhay.core.mongo.daoimpl;

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
import com.ohhay.core.entities.mongo.profile.M920MG;
import com.ohhay.core.mongo.dao.M920MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M920MG)
@Scope("prototype")
public class M920MGDaoImpl extends QbMongoDaoSupport implements M920MGDao {
	
	public String checkFriendStatus (int fo100, int fo100c){
		try {
			setFunctionName(QbMongoFunctionNames.M920_CHECKFRIENDSTATUS);
			setParameterNumber(fo100);
			setParameterNumber(fo100c);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public String checkFollowStatus (int fo100, int fo100f){
		try {
			setFunctionName(QbMongoFunctionNames.F150_CHECKFOLLOWSTATUS);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC, ApplicationConstant.FO100_SUPER_ADMIN).toString();
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<M920MG> listOfTabM920Wait(int fo100c, int offset, int limit ){
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M920_LISTOFTABM920_WAIT);
			setParameterNumber(fo100c);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString();
			List<M920MG> m920 = new ArrayList<M920MG>();
			JSONArray array = new JSONArray(kq);
			for(int i = 0; i < array.length() ; i++){
				JSONObject obj = array.getJSONObject(i);
				M920MG m = new M920MG();
				m.setLabel(obj.getString("label"));
				m.setVal(Integer.parseInt(obj.getString("val")));
				
				m920.add(m);
			}
			
			return m920;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int stornoTabM920(int fo100, int pm920){
		try {
			setFunctionName(QbMongoFunctionNames.M920_STORNOTABM920);
			setParameterNumber(fo100);
			setParameterNumber(pm920);
			int kq = Integer.parseInt( executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public int stornoTabM920F(int fo100, int fo100c){
		try {
			setFunctionName(QbMongoFunctionNames.M920_STORNOTABM920F);
			setParameterNumber(fo100);
			setParameterNumber(fo100c);
			int kq = Integer.parseInt(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
