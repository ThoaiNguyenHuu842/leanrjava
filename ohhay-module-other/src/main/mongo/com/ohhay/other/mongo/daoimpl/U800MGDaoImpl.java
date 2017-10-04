package com.ohhay.other.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.mongo.domain.topic.U810MG;
import com.ohhay.other.mongo.dao.U800MGDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_U800MG)
@Scope("prototype")
public class U800MGDaoImpl extends QbMongoDaoSupport implements U800MGDao {

	@Override
	public int insertTabU800(int fo100, int u810Pos, String uv811, String uv812, int un813) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.U800_INSERTTABU800);
			setParameterNumber(fo100);
			setParameterNumber(u810Pos);
			setParameterString(uv811);
			setParameterString(uv812);
			setParameterNumber(un813);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_TOPIC,0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public U810MG checkTabDomain(String uv811) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.U800_CHECKTABDOMAIN);
			setParameterString(uv811);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC, 0).toString();
			U810MG u810mg = new U810MG();
			try {
				JSONObject jsonObject = new JSONObject(kq);
				String uv812 = jsonObject.get("UV812")
						.toString();
				int un813 = (int) Double.parseDouble(jsonObject
						.get("UN813").toString());
				int un814 = (int) Double.parseDouble(jsonObject.get("UN814")
						.toString());
				u810mg.setUn813(un813);
				u810mg.setUn814(un814);
				u810mg.setUv812(uv812);
				u810mg.setUv811(uv811);
				return u810mg;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

}
