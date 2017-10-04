package com.ohhay.core.mongo.daoimpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N700MG;
import com.ohhay.core.entities.N800MG;
import com.ohhay.core.mongo.dao.N700MGDao;

/**
 * @author TuNt
 * create date Dec 9, 2016
 * ohhay-core
 */
@Repository(value=SpringBeanNames.REPOSITORY_NAME_N700MG)
@Scope("prototype")
public class N700MGDaoImpl extends QbMongoDaoSupport implements N700MGDao{
	Logger log = Logger.getLogger(N700MGDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.dao.N700MGDao#updateTabN700(int, int, java.lang.String)
	 */
	@Override
	public int updateTabN700(int typeDb, int fo100, String objectName) {
		try {
			setFunctionName(QbMongoFunctionNames.N700_UPDATETABN700);
			setParameterNumber(fo100);
			setParameterString(objectName);
			int result = (int) Float.parseFloat(executeFunction(typeDb, fo100).toString());
			return result;
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.dao.N700MGDao#getValueOfKey(int, int, java.lang.String)
	 */
	@Override
	public int getValueOfKey(int typeDb, int fo100, String objectName) {
		try {
			setFunctionName(QbMongoFunctionNames.N700_GETVALUE_OF_KEY);
			setParameterNumber(fo100);
			setParameterString(objectName);
			int result = Integer.parseInt( executeFunction(typeDb, fo100).toString());
			return result;
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.ohhay.core.mongo.dao.N700MGDao#listOfTabN700(int)
	 */
	@Override
	public N700MG listOfTabN700(int typeDb, int fo100) {
		N700MG n700mg = new N700MG();
		try {
			setFunctionName(QbMongoFunctionNames.N700_LISTOFTABN700);
			setParameterNumber(fo100);
			String result =executeFunction(typeDb, fo100).toString();
			JSONObject object = new JSONObject(result);
			Map<String, Integer> mapNotifiyType = new HashMap<>();
			Iterator<?> keys = object.keys();
			while( keys.hasNext() ) {
				String key = (String)keys.next();
				if (key.equals("_id")) {
					n700mg.setId((int) object.get(key));
				}
				else{
					mapNotifiyType.put(key, (Integer) object.get(key));
				}
			}
			n700mg.setMapNotifyType(mapNotifiyType);

			
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		return n700mg;
	}
}
