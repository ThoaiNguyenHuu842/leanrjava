package com.ohhay.web.core.api.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.web.core.api.dao.Q100OrelDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_Q100OREL)
@Scope("prototype")
public class Q100OrelDaoImpl extends QbWsClientSupport implements Q100OrelDao{

	@Override
	public int qrelChecktabQ100code(String pvQV101, String pvQV106, String pvQV109,String pvLOGIN){
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pvQV101", pvQV101.toString()));
			listClientParams.add(new QbWsClientParam("pvQV106", pvQV106));
			listClientParams.add(new QbWsClientParam("pvQV109", pvQV109));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLOGIN));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_Q100_CHECKTABQ100CODE, listClientParams);
			int result = 0;
			if(jsonObject.getString("status").equals("success"))
			{
				JSONObject val = new JSONObject(jsonObject.getString("elements"));
				if (val.getString("result") != null)
					result = Integer.parseInt(val.getString("result"));
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
