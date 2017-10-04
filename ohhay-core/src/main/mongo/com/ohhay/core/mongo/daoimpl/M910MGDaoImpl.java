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
import com.ohhay.core.entities.mongo.profile.M910MG;
import com.ohhay.core.mongo.dao.M910MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M910MG)
@Scope("prototype")
public class M910MGDaoImpl extends QbMongoDaoSupport implements M910MGDao {
	
	@Override
	public List<M910MG> listOfShare(int fo100, int offset, int limit, String pvSearch){
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M910_LISTOFTABSHARE);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterString(pvSearch);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			
			List<M910MG> m910 = new ArrayList<M910MG>();
			JSONArray array = new JSONArray(kq);
			for(int i = 0; i < array.length() ; i++){
				JSONObject obj = array.getJSONObject(i);
				M910MG m = new M910MG();
				m.setId(Integer.parseInt(obj.getString("_id")));
				
				m.setFo100(Integer.parseInt(obj.getString("FO100")));
				m.setFo100c(Integer.parseInt(obj.getString("FO100C")));
				m.setMv907(obj.getString("MV907"));
				m.setMv911(obj.getString("MV911"));
				
				/*m.setMn912(Integer.parseInt(obj.getString("MN912")));*/
				
				m.setHoten(obj.getString("HOTEN"));
				m.setPhone(obj.getString("PHONE"));
				m.setAvatar(obj.getString("AVARTA"));
				m.setLink(obj.getString("LINK"));
				m910.add(m);
			}
			
			return m910;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
