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
import com.ohhay.piepme.mongo.dao.T300PMGDao;
import com.ohhay.piepme.mongo.entities.other.T300PMG;


/**
 * @author ThoaiNH
 * create Apr 10, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_T300P)
@Scope("prototype")
public class T300PMGDaoImpl extends QbMongoDaoSupport implements T300PMGDao{

	@Override
	public List<T300PMG> listOfTabT300(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T300_LISTOFTABT300);
			setParameterNumber(fo100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<T300PMG> listResult = new ArrayList<T300PMG>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					T300PMG t300pmg = new T300PMG();
					t300pmg.setId(obj.getInt("_id"));
					t300pmg.setTv301(obj.getString("TV301"));
					if(obj.has("TV301_KEY_LANG"))
						t300pmg.setTv301KeyLang(obj.getString("TV301_KEY_LANG"));
					t300pmg.setTv302(obj.getString("TV302"));
					t300pmg.setTotalPieper(obj.getInt("TOTAL_PIEPER"));
					listResult.add(t300pmg);
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
	public List<T300PMG> listOfTabT300Test(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T300_LISTOFTABT300);
			setParameterNumber(fo100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<T300PMG> listResult = new ArrayList<T300PMG>();
				//Mapping mapping=new Mapping();
				//listResult = (List<T300PMG>) mapping.mappingAuto(kq, T300PMG.class);
				return null;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
