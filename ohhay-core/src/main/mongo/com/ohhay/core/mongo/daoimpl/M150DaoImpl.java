package com.ohhay.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M150CMG;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.other.UserOtag;
import com.ohhay.core.mongo.dao.M150MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M150MG)
@Scope("prototype")
public class M150DaoImpl extends QbMongoDaoSupport implements M150MGDao{
	private static Logger log = Logger.getLogger(M150DaoImpl.class);
	@Override
	public String getNewTopic(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M150_GETNEWTOPIC);			
			setParameterNumber(fo100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC,fo100).toString();
			return kq;
		} catch (Exception ex) {
			log.info("---FO100:"+fo100+" doesn't has any comment!");
			return null;
		}
	}
	@Override
	public List<M150MG> findM150Index(int limit) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.MN159).is(1));
			query2.with(new Sort(Sort.Direction.ASC, QbMongoFiledsName.ID));
			query2.limit(limit);
			List<M150MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_TOPIC, ApplicationConstant.FO100_SUPER_ADMIN).find(query2, M150MG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * @author ThongQB
	 * get list tags cloud
	 * */
	public List<UserOtag> getListUserOtags(int fo100){
		try {
			List<UserOtag> list = new ArrayList<UserOtag>();
			setFunctionName(QbMongoFunctionNames.M150_LISTOFTAB_USEROTAGS);
			setParameterNumber(fo100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC, fo100).toString();
			JSONArray jsonArray = new JSONArray(kq);
			for (int i = 0; i < jsonArray.length(); i++) {
		      JSONObject jsonObject2 = jsonArray.getJSONObject(i);
		      UserOtag userOtags = new UserOtag();
		      userOtags.setId(jsonObject2.getString("_id"));
		      Double total = 0.0;
		      if(!jsonObject2.getString("TOTAL").toString().isEmpty()){
		    	total = Double.parseDouble(jsonObject2.getString("TOTAL"));
		      }
		      userOtags.setTotal(total.intValue());
		      list.add(userOtags);
		     }
			log.info("+++++kq:"+list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public JSONArray listOfTabM150(int fo100Login, int fo100View, int offset, int limit, String langCode) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M150_LISTOFTABM150);
			setParameterNumber(fo100Login);
			setParameterNumber(fo100View);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterString(langCode);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC, fo100View).toString();
			JSONArray jsonArray = new JSONArray(kq);
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public JSONArray listOfTabM150one(int pnFO100, int pnFM150){
		try{
			setFunctionName(QbMongoFunctionNames.M150_LISTOFTABM150_ONE);
			setParameterNumber(pnFO100);
			setParameterNumber(pnFM150);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC, pnFO100).toString();
			JSONArray jsonArray = new JSONArray(kq);
			return jsonArray;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<M150CMG> findM150C(int pnFM150) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.FM150).is(pnFM150));
			query2.with(new Sort(Sort.Direction.ASC, QbMongoFiledsName.ID));
			
			List<M150CMG> list = getMongoOperations(ApplicationConstant.DB_TYPE_TOPIC, ApplicationConstant.FO100_SUPER_ADMIN).find(query2, M150CMG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
