package com.ohhay.rest.bonevo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.P950MG;
import com.ohhay.core.mongo.service.P950MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create Jul 8, 2016
 */
@Path("myCollectionWebService")
public class MyCollectionWebService {
	
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("uploadToMyCollection")
	@Produces("application/json")
	public String uploadToMyCollection(String postString) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			JSONObject jsonObject = new JSONObject(postString);
			String imgUrl = jsonObject.get("imgUrl").toString();
			String thumbUrl = jsonObject.get("thumbUrl").toString();
			String src = jsonObject.get("src").toString();
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			P950MG p950mg = new P950MG();
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			long p950NewId;
			p950NewId = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.P950);
			p950mg.setFo100(fo100);
			p950mg.setId(p950NewId);
			p950mg.setPv951(imgUrl);
			p950mg.setPv952(thumbUrl);
			p950mg.setSrc(src);
			int kq = templateService.saveDocument(fo100, p950mg, QbMongoCollectionsName.P950);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * @param fo100
	 * @param src
	 * @return
	 */
	@GET
	@Path("load")
	@Produces("application/json")
	public String load(@QueryParam("fo100") int fo100, @QueryParam("src") String src) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<P950MG> listP950;
			if (!"ALL".equals(src))
				listP950 = templateService.findDocuments(fo100, P950MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, 0), new QbCriteria(QbMongoFiledsName.SRC, src));
			else
				listP950 = templateService.findDocuments(fo100, P950MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, 0));
			return QbRestUtils.convertToJsonStringForProc(listP950);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	@GET
	@Path("listOfTabP950")
	@Produces("application/json")
	public String listOfTabP950(@QueryParam("fo100") int fo100, @QueryParam("src") String src, @QueryParam("limit") int limit, @QueryParam("offset") int offset) {
		try {
			P950MGService p950mgService = (P950MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P950MG);
				List<P950MG> listP950;
				listP950 = p950mgService.listOfTabP950(fo100, src, offset, limit);
			return QbRestUtils.convertToJsonStringForProc(listP950);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
