package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.realestate.T320Status1;


/**
 * mobile.bonevo.net/service/t320PWebService
 * danh sach loai giao dich
 * @author ThoaiNH
 * create Jun 21, 2017
 */
@Path("t320PWebService")
public class T320PWebService {
	/**
	 * @param FO100 int
	 * @param FT310 int
	 * @return
	 */
	@GET
	@Path("getListT320")
	@Produces("application/json")
	public String getListT320(@QueryParam("FO100") int fo100, @QueryParam("FT310") int ft310){
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if(ft310 > 0)
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T320Status1.class, new QbCriteria("FT310S", ft310)));
			else
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T320Status1.class));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
