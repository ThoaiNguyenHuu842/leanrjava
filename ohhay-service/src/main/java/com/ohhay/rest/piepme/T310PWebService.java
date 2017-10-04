package com.ohhay.rest.piepme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.realestate.T310PMG;
import com.ohhay.piepme.mongo.entities.realestate.T320PMG;


/**
 * mobile.bonevo.net/service/t310PWebService
 * danh sach loai bat dong san
 * @author ThoaiNH
 * create Jun 21, 2017
 */
@Path("t310PWebService")
public class T310PWebService {
	/**
	 * @param FO100 int
	 * @param FT320 int
	 * @return
	 */
	@GET
	@Path("getListT310")
	@Produces("application/json")
	public String getListT310(@QueryParam("FO100") int fo100, @QueryParam("FT320") int ft320){
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if(ft320 == 0)
				return QbRestUtils.convertToJsonStringForProc(templateService.findDocuments(fo100, T310PMG.class));
			else {
				T320PMG t320pmg = templateService.findDocumentById(fo100, ft320, T320PMG.class, "FT310S");
				if(t320pmg != null)
					return QbRestUtils.convertToJsonStringForProc(templateService.findDocumentsInc(fo100, T310PMG.class, QbMongoFiledsName.ID, t320pmg.getFt310s()));
				return QbRestUtils.convertToJsonStringForProc(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
