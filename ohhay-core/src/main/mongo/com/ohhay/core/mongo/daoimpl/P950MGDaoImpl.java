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
import com.ohhay.core.entities.mongo.other.P950MG;
import com.ohhay.core.mongo.dao.P950MGDao;

/**
 * @author TuNt
 * create date Jul 6, 2016
 * ohhay-core
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P950MG)
@Scope("prototype")
public class P950MGDaoImpl extends QbMongoDaoSupport implements P950MGDao{

	@Override
	public List<P950MG> listOfTabP950(int fo100, String src, int offset, int limit) {
		try {
//			System.err.println("Offset : "+offset+" - Limit : "+limit);
			List<P950MG> list = new ArrayList<P950MG>();
			setFunctionName(QbMongoFunctionNames.P950_LISTOFTABP950);
			setParameterNumber(fo100);
			setParameterString(src);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String result = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			JSONArray jsonArray = new JSONArray(result);
			for(int i=0; i<jsonArray.length(); i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				P950MG p950mg =new P950MG();
				p950mg.setId(Long.parseLong(jsonObject.getString("_id")));
				p950mg.setFo100(jsonObject.getInt("FO100"));
				p950mg.setPv951(jsonObject.getString("PV951"));
				p950mg.setPv952(jsonObject.getString("PV952"));
				p950mg.setSrc(jsonObject.getString("SRC"));
				p950mg.setWebId(jsonObject.getInt("WEBID"));
				if(offset==0 && i==0){
					p950mg.setRowss(jsonObject.getInt("ROWSS"));
				}
				list.add(p950mg);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
