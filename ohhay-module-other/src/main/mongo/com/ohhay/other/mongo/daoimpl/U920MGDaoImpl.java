package com.ohhay.other.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mongo.dao.U920MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_U920MG)
@Scope("prototype")
public class U920MGDaoImpl extends QbMongoDaoSupport implements U920MGDao {
	Logger log = (Logger.getLogger(U920MGDaoImpl.class));

	@Override
	public List<U920MG> listOfTabU920(int offset, int limit, int un923) {
		// TODO Auto-generated method stub
		List<U920MG> listU920s = new ArrayList<>();
		try {
			setFunctionName(QbMongoFunctionNames.U920_LISTOFTABU920);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterNumber(un923);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY,
					ApplicationConstant.FO100_SUPER_ADMIN).toString();

			if (resultData != null) {
				JSONArray jsonArray = new JSONArray(resultData);

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					U920MG u920mg = new U920MG();
					u920mg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					u920mg.setUv921(jsonObject.getString("UV921"));
					try{
					u920mg.setUv922(jsonObject.getString("UV922"));
					}catch(JSONException e){
						log.info(e);
					}
					u920mg.setUn923((int)Double.parseDouble(jsonObject.getString("UN923")));
					u920mg.setHoten(jsonObject.getString("HOTEN"));
					if(offset==0 & i==0){
						u920mg.setRowss(jsonObject.getInt("ROWSS"));
					}
					listU920s.add(u920mg);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listU920s;
	}

}
