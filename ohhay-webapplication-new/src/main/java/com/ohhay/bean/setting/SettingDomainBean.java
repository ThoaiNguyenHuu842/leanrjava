package com.ohhay.bean.setting;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mongo.service.U900MGService;
import com.ohhay.other.mysql.service.O200Service;

/**
 * @author ThoaiNH create Dec 30, 2015
 */
@Controller
@Scope("prototype")
public class SettingDomainBean {
	private static Logger log = Logger.getLogger(SettingDomainBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateMGService;
	@Autowired
	SequenceService sequenceService;
	@Autowired
	U900MGService u900mgService;

	/*
	 * get list domain
	 */
	@RequestMapping(value = "/setting.getListDomain", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListDomain(@ModelAttribute("pe400") int pe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<U920MG> listU920 = templateMGService.findDocuments(q100.getPo100(), U920MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()),
					new QbCriteria(QbMongoFiledsName.FE400, pe400));
			jsonResponse.setResult(listU920);
		} else
			jsonResponse.setResult(null);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}

	/*
	 * insertDomain MySQL
	 */
	@RequestMapping(value = "/setting.insertDomain", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse insertDomain(@RequestParam(required = true, value = "ov201") String ov201,
			@RequestParam(required = true, value = "fe400") int fe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int kq = 0;
			log.info(q100.getAuthenticationRightInfo());
			String returnMess = u900mgService.checkRightAddDomain(q100.getAuthenticationRightInfo(), q100.getPo100());
			if (returnMess.equals(ApplicationConstant.RE_VAILD_RIGHT)) {
				String domainName = ov201.trim().toLowerCase();
				List<U920MG> listU920MG = templateMGService.findDocuments(q100.getPo100(), U920MG.class,
						new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()),
						new QbCriteria(QbMongoFiledsName.UV921, domainName),
						new QbCriteria(QbMongoFiledsName.FE400, fe400));
				if (listU920MG.size() > 0) {
					log.info("listU920MG.size = "+listU920MG.size());
					kq = -1;
				} else {
					log.info("listU920MG.size = 0");
					String verificationCode = "";
					O200Service o200Service = (O200Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O200);
					verificationCode = o200Service.insertTabO200(q100.getPo100(), domainName,"B", ApplicationConstant.PVLOGIN_ANONYMOUS);
					try {
						long newId = sequenceService.getNextSequenceId(q100.getPo100(), QbMongoCollectionsName.U920);
						U920MG u920mg = new U920MG();
						u920mg.setId(newId);
						u920mg.setFo100(q100.getPo100());
						u920mg.setFe400(fe400);
						u920mg.setUv921(domainName);
						u920mg.setUv922(verificationCode);
						u920mg.setUn923(0);
						u920mg.setUd928(new Date());
						kq = templateMGService.saveDocument(q100.getPo100(), u920mg, QbMongoCollectionsName.U920);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				kq = -2;
				jsonResponse.setMesg(returnMess);
			}
			jsonResponse.setResult(kq);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		} else
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}

	/*
	 * delete domain
	 */
	@RequestMapping(value = "/setting.deleteDomainSQL", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse deleteDomainSQL(@RequestParam(required = true, value = "ov202") String ov202,
			@RequestParam(required = true, value = "id") int id) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			O200Service o200Service = (O200Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O200);
			int kq1 = o200Service.stornoTabO200(ov202, ApplicationConstant.PVLOGIN_ANONYMOUS);
			if (kq1 >= 1) {
				int kq2 = templateMGService.removeDocuments(q100.getPo100(), U920MG.class,
						new QbCriteria(QbMongoFiledsName.ID, id),
						new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()));
				jsonResponse.setResult(kq2);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}

	/**
	 * date create: 07/07/2015
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setting.checkRightAddDomain", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse checkRightAddDomain() {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		String returnCode = ApplicationConstant.RE_MUST_UPGRADE;
		if (q100 != null)
			returnCode = u900mgService.checkRightAddDomain(q100.getAuthenticationRightInfo(), q100.getPo100());
		jsonResponse.setResult(returnCode);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
}
