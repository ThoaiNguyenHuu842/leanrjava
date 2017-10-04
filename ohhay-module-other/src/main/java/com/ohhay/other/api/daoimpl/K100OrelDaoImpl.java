package com.ohhay.other.api.daoimpl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.other.api.dao.K100OrelDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_K100OREL)
@Scope("prototype")
public class K100OrelDaoImpl extends QbWsClientSupport implements K100OrelDao{

	@Override
	public String insertTabK100Orel(int pnPK100, String pvKV101, String pvKV102, String pvKV103, int pnKN104, int pnFO100, String pvOV101, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pnPK100", pnPK100));
			listClientParams.add(new QbWsClientParam("pvKV101", pvKV101));
			listClientParams.add(new QbWsClientParam("pvKV102", pvKV102));
			listClientParams.add(new QbWsClientParam("pvKV103", pvKV103));
			listClientParams.add(new QbWsClientParam("pnKN104", pnKN104));
			listClientParams.add(new QbWsClientParam("pnFO100", pnFO100));
			listClientParams.add(new QbWsClientParam("pvOV101", pvOV101));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLogin));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_N950_INSERTTABK100, listClientParams);
			String result = null;
			if(jsonObject.getString("status").equals("success"))
			{
				JSONObject val = new JSONObject(jsonObject.getString("elements"));
				if (val.getString("result") != null)
					System.out.println("tungns------FK10------"+val.getString("result"));
					result = val.getString("result");
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
