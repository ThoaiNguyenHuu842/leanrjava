package com.ohhay.other.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.entities.mongo.domain.U910MG;
import com.ohhay.other.mongo.dao.U900MGDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_U900MG)
@Scope("prototype")
public class U900MGDaoImpl extends QbMongoDaoSupport implements U900MGDao {

	@Override
	public int insertTabU900(int fo100, int u910Pos, String uv911, String uv912, int un913) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.U900_INSERTTABU900);
			setParameterNumber(fo100);
			setParameterNumber(u910Pos);
			setParameterString(uv911);
			setParameterString(uv912);
			setParameterNumber(un913);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public U910MG checkTabDomain(String uv911) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.U900_CHECKTABDOMAIN);
			setParameterString(uv911);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY,0).toString();
			U910MG u910mg = new U910MG();
			try {
				JSONObject jsonObject = new JSONObject(kq);
				String uv912 = jsonObject.get("UV912").toString();
				int un913 = (int) Double.parseDouble(jsonObject.get("UN913").toString());
				int un914 = (int) Double.parseDouble(jsonObject.get("UN914").toString());
				u910mg.setUn913(un913);
				u910mg.setUn914(un914);
				u910mg.setUv912(uv912);
				u910mg.setUv911(uv911);
				return u910mg;
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
