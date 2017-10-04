package com.ohhay.bean.designer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.E150D;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.mongo.service.E150MGService;
import com.ohhay.web.lego.entities.mongo.web.E150MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.DesignerService;
import com.ohhay.web.lego.service.WebLegoService;


/**
 * @author ThoaiNH
 * create Feb 25, 2016
 */
@Controller
@Scope("prototype")
public class DesignerBean {
	private static Logger log = Logger.getLogger(DesignerBean.class);
	@Autowired
	private DesignerService designerService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	E150MGService e150mgService;
	
	/**
	 * @return
	 * changed by Tunt 21/11/2016
	 */
	@RequestMapping(value = "/designerBean.loadListCus", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListCus(@ModelAttribute("pvSearch") String pvSearch, @ModelAttribute("offset") int offset, @ModelAttribute("limit") int limit){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			//old
			//List<E100D> listDes = designerService.listOfTabE100D(q100.getPo100(), q100.getQv101());
			//new
			List<E150MG> listDes = e150mgService.listOfTabCus(q100.getPo100(), pvSearch, offset, limit);
			log.info("designerBean.loadListCus ajax =================="+listDes);
			jsonResponse.setResult(listDes);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param fe100
	 * @return
	 */
	@RequestMapping(value = "/designerBean.loadListWebOfCus", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListWebOfCus(@ModelAttribute("fe100") int fe100){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			List<E150D> listDes = designerService.listOfTabE150D(fe100, q100.getQv101());
			jsonResponse.setResult(listDes);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param fe400Cus
	 * @return
	 */
	@RequestMapping(value = "/designerBean.getVersionSiteID", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getVersionSiteID(@ModelAttribute("fe400") int fe400Cus){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			E400MG e400mg = templateService.findDocument(q100.getPo100(), E400MG.class, new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()), 
														new QbCriteria(QbMongoFiledsName.FE400, fe400Cus),
														new QbCriteria(QbMongoFiledsName.EN402, 3));
			if(e400mg != null)
			{
				jsonResponse.setResult(e400mg.getId());
				return jsonResponse;
			}
		}
		jsonResponse.setResult(0);
		return jsonResponse;
	}
	/**
	 * @param pe400: id web of customer
	 * @return
	 */
	@RequestMapping(value = "/designerBean.updateVersionSite", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updateVersionSite(@ModelAttribute("fe400") int fe400Cus){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			int result = designerService.updateVersionSite(q100.getPo100(), fe400Cus);
			jsonResponse.setResult(result);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param pe400: id of web version
	 * @return
	 */
	@RequestMapping(value = "/designerBean.backupVersion", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse backupVersion(@ModelAttribute("fe400Cus") int fe400Cus){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			E400MG e400mgVersion = templateService.findDocument(q100.getPo100(), E400MG.class, new QbCriteria(QbMongoFiledsName.FE400, fe400Cus),
																new QbCriteria(QbMongoFiledsName.EN402, 3));
			int webIdNew = webLegoService.createBytemplate(q100.getPo100(), (int)e400mgVersion.getId(), false);
			templateService.updateOneField(q100.getPo100(), E400MG.class, webIdNew, QbMongoFiledsName.FE400, fe400Cus, null);
			templateService.updateOneField(q100.getPo100(), E400MG.class, webIdNew, QbMongoFiledsName.EN402, 4, null);
			jsonResponse.setResult(webIdNew);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param fe100
	 * @return
	 */
	@RequestMapping(value = "/designerBean.loadListBackup", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListBackup(@ModelAttribute("fe400Cus") int fe400Cus){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			List<E400MG> listE400 = templateService.findDocuments(q100.getPo100(), E400MG.class, new QbCriteria(QbMongoFiledsName.FE400, fe400Cus), 
																					new QbCriteria(QbMongoFiledsName.EN402, 4));
			jsonResponse.setResult(listE400);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param designerFinishE150Criteria
	 * @return
	 */
//	@RequestMapping(value = "/designerBean.submitToCustomer", method = RequestMethod.POST)
//	public @ResponseBody JacksonResponse submitToCustomer(@ModelAttribute("designerFinishE150Criteria") DesignerFinishE150Criteria designerFinishE150Criteria){
//		JacksonResponse jsonResponse = new JacksonResponse();
//		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
//		Q100 q100 = AuthenticationHelper.getUserLogin();
//		if(q100 != null)
//		{
//			int kq = designerService.finishTabE150(q100.getPo100(), designerFinishE150Criteria.getPe150(), designerFinishE150Criteria.getFe400Cus(),
//					designerFinishE150Criteria.getFe400Backup(), q100.getQv101());
//			jsonResponse.setResult(kq);
//		}
//		else
//			jsonResponse.setResult(null);
//		return jsonResponse;
//	}
	@RequestMapping(value = "/designerBean.submitToCustomer", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse submitToCustomer(@ModelAttribute("fe400s") int fe400s, @ModelAttribute("ev202") String ev202,@ModelAttribute("fe150") int fe150){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			String kq = designerService.submitToCustomer(q100.getPo100(), fe400s, ev202, fe150);
			jsonResponse.setResult(kq);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	@RequestMapping(value="/designerBean.loadListSiteOfCus")
	public @ResponseBody JacksonResponse loadListSiteOfCus(@ModelAttribute("fo100c") int fo100c, @ModelAttribute("offset") int offset, @ModelAttribute("limit") int limit){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			List<E150MG> listResult = e150mgService.listOfTabSitesOfCus(q100.getPo100(), fo100c, offset, limit);
			jsonResponse.setResult(listResult);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	@RequestMapping(value="/designerBean.confirmRequestOfCus")
	public @ResponseBody JacksonResponse confirmRequestOfCus(@ModelAttribute("pe150") int pe150, @ModelAttribute("pe400") int pe400){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			String result = e150mgService.confirmTabE150(q100.getPo100(), pe150, pe400);
			jsonResponse.setResult(result);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	@RequestMapping(value="/designerBean.rejectSiteOfCus", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse rejectSiteOfCus(@ModelAttribute("pe150") int pe150){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null){
			int result = templateService.removeDocumentById(q100.getPo100(), pe150, E150MG.class);
			jacksonResponse.setResult(result);
		}else{
			jacksonResponse.setResult(null);
		}
		return jacksonResponse;
	}
}
