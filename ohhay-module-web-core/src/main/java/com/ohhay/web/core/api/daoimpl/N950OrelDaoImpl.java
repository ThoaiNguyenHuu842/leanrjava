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
import com.ohhay.web.core.api.dao.N950OrelDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_N950OREL)
@Scope("prototype")
public class N950OrelDaoImpl extends QbWsClientSupport implements N950OrelDao{

	@Override
	public int inserTabN950(int pnPN950, String pvNV951, String pvNV952, String pvNV953, 
			String pvNV954, String pvNV955, String pvNV956, String pvNV957, String pvNV958, String pvNV959, int pnFN100, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pnPN950", pnPN950));
			listClientParams.add(new QbWsClientParam("pvNV951", pvNV951));
			listClientParams.add(new QbWsClientParam("pvNV952", pvNV952));
			listClientParams.add(new QbWsClientParam("pvNV953", pvNV953));
			listClientParams.add(new QbWsClientParam("pvNV954", pvNV954));
			listClientParams.add(new QbWsClientParam("pvNV955", pvNV955));
			listClientParams.add(new QbWsClientParam("pvNV956", pvNV956));
			listClientParams.add(new QbWsClientParam("pvNV957", pvNV957));
			listClientParams.add(new QbWsClientParam("pvNV958", pvNV958));
			listClientParams.add(new QbWsClientParam("pvNV959", pvNV959));
			listClientParams.add(new QbWsClientParam("pnFN100", pnFN100));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLogin));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_PAYMENTWALL_GETWIDGET, listClientParams);
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
