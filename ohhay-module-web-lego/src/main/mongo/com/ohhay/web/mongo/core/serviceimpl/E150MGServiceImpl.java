package com.ohhay.web.mongo.core.serviceimpl;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.web.core.mongo.dao.E150MGDao;
import com.ohhay.web.core.mongo.service.E150MGService;
import com.ohhay.web.lego.entities.mongo.web.E150MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author TuNt
 * create date Nov 18, 2016
 * ohhay-module-web-lego
 */
@Service(value = SpringBeanNames.SERVICE_NAME_E150MG)
@Scope("prototype")
public class E150MGServiceImpl implements E150MGService{
	@Autowired
	E150MGDao e150MGDao;
	@Autowired
	WebLegoService webLegoService;
	@Autowired
	TemplateService templateService;
	/* (non-Javadoc)
	 * @see com.ohhay.web.core.mongo.service.E150MGService#listOfTabCus(int, java.lang.String, int, int)
	 */
	@Override
	public List<E150MG> listOfTabCus(int fo100, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		return e150MGDao.listOfTabCus(fo100, pvSearch, offset, limit);
	}
	/* (non-Javadoc)
	 * @see com.ohhay.web.core.mongo.service.E150MGService#listOfTabSitesOfCus(int, int, java.lang.String, int, int)
	 */
	@Override
	public List<E150MG> listOfTabSitesOfCus(int fo100d, int fo100c, int offset, int limit) {
		// TODO Auto-generated method stub
		return e150MGDao.listOfTabSitesOfCus(fo100d, fo100c, offset, limit);
	}
	/* (non-Javadoc)
	 * @see com.ohhay.web.core.mongo.service.E150MGService#storNoTab(int, int)
	 */
	@Override
	public int storNoTab(int fo100, int pe150) {
		// TODO Auto-generated method stub
		return e150MGDao.storNoTab(fo100, pe150);
	}
	@Override
	public String confirmTabE150(int fo100d, int pe150, int pe400) {
		// TODO Auto-generated method stub
		int webIdNew = webLegoService.createBytemplate(fo100d, (int)pe400, false);
		int kq2 = templateService.updateOneField(fo100d, E400MG.class, webIdNew, QbMongoFiledsName.EN402, 3, null);
		if(kq2!=0)
		{
			String result = e150MGDao.confirmTabE150(fo100d, pe150, webIdNew);
			JSONObject json = new JSONObject();
			try {
				json.put("webIdNew", webIdNew);
				json.put("startDate", result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json.toString();
		}
		return null;
	}
	@Override
	public E150MG listOfTabSitesOfCusDetail(int fo100, int pe150) {
		// TODO Auto-generated method stub
		return e150MGDao.listOfTabSitesOfCusDetail(fo100, pe150);
	}

}
