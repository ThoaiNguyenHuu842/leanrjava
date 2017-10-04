package com.ohhay.rest.bonevo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.mongo.domain.U900MG;
import com.ohhay.other.mongo.service.U900MGService;

/**
 * @author ThoaiNH
 *
 */
@Path("u900WebService")
public class U900WebService {
	private static Logger log = Logger.getLogger(U900WebService.class);
	/*
	 * change Domain Ohhay Function visibility
	 */
	@POST
	@Path("changeDomainOhhayFunction")
	@Produces("application/json")
	public String changeDomainOhhayFunction(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int index = Integer.parseInt(jsonObject.get("index").toString());
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int mn914 = Integer.parseInt(jsonObject.get("mn914").toString());
			U900MGService domainService =(U900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_U900MG);
			log.info("---changeDomainOhhayFunction:"+index+","+fo100);
			int kq = domainService.changeDomainOhhayFunction(index, mn914, fo100);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * save domain
	 */
	@POST
	@Path("saveDomain")
	@Produces("application/json")
	public String saveDomain(String postString) {
		try {
			//get attribute
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			String domainName = jsonObject.get("domainName").toString();
			String ov101 = jsonObject.get("ov101").toString();
			int domainType = Integer.parseInt(jsonObject.get("domainType").toString());
			//create domain criteria
			U900MGService domainService =(U900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_U900MG);
			DomainCriteria domainCriteria = new DomainCriteria();
			domainCriteria.setPos(-1);
			domainCriteria.setType(domainType);//domain type: 1 (home)
			domainCriteria.setDomainName(domainName);//ex: abc.net
			int kq = domainService.saveDomain(ov101, domainCriteria, fo100, null, true);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * remove domain
	 */
	@POST
	@Path("removeDomain")
	@Produces("application/json")
	public String removeDomain(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int domainIndex = Integer.parseInt(jsonObject.get("index").toString());
			U900MGService domainService =(U900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_U900MG);
			log.info("---deleteDomain:"+domainIndex+","+fo100);
			int kq = domainService.deleteDomain(domainIndex,fo100);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/*
	 * get list domain
	 */
	@GET
	@Path("getListDomain")
	@Produces("application/json")
	public String getListDomain(@QueryParam("fo100") int fo100) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			U900MG u900mg = templateMGService
					.findDocumentById(fo100, fo100, U900MG.class);
			if(u900mg != null)
				return QbRestUtils.convertToJsonStringForProc(u900mg.getListU910mgs());	
			else
				return QbRestUtils.getErrorStatus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
