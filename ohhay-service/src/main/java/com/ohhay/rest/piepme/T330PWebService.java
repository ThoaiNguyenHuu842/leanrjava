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
import com.ohhay.piepme.mongo.entities.realestate.T330PMG;


/**
 * mobile.bonevo.net/service/t330PWebService
 * danh sach chi phi
 * @author ThoaiNH
 * create Jun 21, 2017
 */
@Path("t330PWebService")
public class T330PWebService {
	/**
	 * @param FO100 int
	 * @param FT310 int
	 * @param FT320 int
	 * @return
	 */
	@GET
	@Path("getListT330")
	@Produces("application/json")
	public String getListT330(@QueryParam("FO100") int fo100, @QueryParam("FT310") int ft310, @QueryParam("FT320") int ft320){
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if(ft320 > 0)
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T330PMG.class, new QbCriteria("FT310", ft310), new QbCriteria("FT320", ft320)));
			else
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T330PMG.class, new QbCriteria("FT310", ft310)));
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
