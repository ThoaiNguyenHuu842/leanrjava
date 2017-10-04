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
import com.ohhay.web.core.api.dao.N100OrelDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_N100OREL)
@Scope("prototype")
public class N100OrelDaoImpl extends QbWsClientSupport implements N100OrelDao{

	@Override
	public int insertTabN100(String nv101, String nv102, String nv103, String nv104, String nv105, String nv106, String nv107, String qv106, String mv102) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("NV101", nv101));
			listClientParams.add(new QbWsClientParam("NV102", nv102));
			listClientParams.add(new QbWsClientParam("NV103", nv103));
			listClientParams.add(new QbWsClientParam("NV104", nv104));
			listClientParams.add(new QbWsClientParam("NV105", nv105));
			listClientParams.add(new QbWsClientParam("NV106", nv106));
			listClientParams.add(new QbWsClientParam("NV107", nv107));
			listClientParams.add(new QbWsClientParam("QV106", qv106));
			listClientParams.add(new QbWsClientParam("MV102", mv102));
			JSONObject jsonObject = callPost(WsClinentName.OREL_N100_INSERTTABN100, listClientParams);
			int result = 0;
			if (jsonObject.getString("result") != null)
				result = Integer.parseInt(jsonObject.getString("result"));
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
