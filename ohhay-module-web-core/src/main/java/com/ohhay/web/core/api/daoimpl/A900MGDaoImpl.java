package com.ohhay.web.core.api.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R800MG;
import com.ohhay.web.core.api.dao.A900MGDao;
import com.ohhay.web.core.entities.mongo.web.A400MG;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_A900MG)
@Scope("prototype")
public class A900MGDaoImpl extends QbMongoDaoSupport implements A900MGDao {
	private static Logger log = Logger.getLogger(A900MGDaoImpl.class);
	@Override
	public List<A400MG> getListTemplate(int pnFA950, int pnAN402, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.A900_LISTOFTABA900);
			setParameterNumber(pnFA950);
			setParameterNumber(pnAN402);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY,0).toString();
			if(kq != null){
				List<A400MG> listA400MG = new ArrayList<A400MG>();
				JSONArray array = new JSONArray(kq);
				for(int i = 0; i < array.length() ; i++){
					JSONObject obj = array.getJSONObject(i);
					log.info("--template id:"+obj.getString("_id"));
					A400MG a400mg = new A400MG();
					a400mg.setId(Integer.parseInt(obj.getString("_id")));
					a400mg.setFo100(Integer.parseInt(obj.getString("FO100")));
					try {
						a400mg.setAv403(obj.getString("AV403"));
					} catch (Exception e) {
						// TODO: handle exception
					}
					a400mg.setFc800(Integer.parseInt(obj.getString("FC800")));
					a400mg.setFa950(Integer.parseInt(obj.getString("FA950")));
					a400mg.setAn402(Integer.parseInt(obj.getString("AN402")));
					a400mg.setNv100(obj.getString("NV100"));
					a400mg.setDateCreate(obj.getString("DATE_CREATE"));
					a400mg.setChilds(Integer.parseInt(obj.getString("CHILDS")));
					try {
						a400mg.setRowss(Integer.parseInt(obj.getString("ROWSS")));
					} catch (Exception e) {
						// TODO: handle exception
					}
					listA400MG.add(a400mg);
				}
				return listA400MG;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
