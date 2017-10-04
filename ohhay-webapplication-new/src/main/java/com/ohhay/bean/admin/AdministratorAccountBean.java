package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.ohhay.core.criteria.AdministratorCMSCriteria;
import com.ohhay.core.criteria.AdministratorGeneralCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.SessionManager;
import com.ohhay.other.entities.O100Das;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mysql.service.O100Service;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThoaiVt create date Mar 4, 2016 ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class AdministratorAccountBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_M900MG)
	private M900MGService m900mgService;
	@Autowired
	private TemplateService templateService;
	private O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);;
	private Logger logger = Logger.getLogger(AdministratorAccountBean.class);

	@RequestMapping(value = "/adminAccountBean.loadListAccount", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListAccount(
			@ModelAttribute(value = "contentSearch") AdministratorGeneralCriteria administratorAccountCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		// List<M900MG> m900mgs = null;
		logger.info("DATA : " + administratorAccountCriteria.toString());
		if (q100 != null) {
			logger.info("DATA MISSING : "+administratorAccountCriteria.getFromDate() +" / TO DATE : "+administratorAccountCriteria.getToDate());
			List<O100Das> listO100Das = o100Service.getListAccount(administratorAccountCriteria.getNameAccount(), administratorAccountCriteria.getPayMent(),
					administratorAccountCriteria.getFromDate(), administratorAccountCriteria.getToDate(),
					administratorAccountCriteria.getOffset(), administratorAccountCriteria.getLimit(),
					administratorAccountCriteria.getPnSORT(), administratorAccountCriteria.getPnDIRECTION(), "");
			jacksonResponse.setResult(listO100Das);
			logger.info(listO100Das);
			// get account online
//			if (administratorAccountCriteria.getOffset() == 0) {
				List<M900MG> listCurrentUser = templateService.findDocuments(0, M900MG.class,
						new QbCriteria(QbMongoFiledsName.IS_ONLINE, "Y"));
				jacksonResponse.setResult2(listCurrentUser);
				jacksonResponse.setResultString(String.valueOf(listCurrentUser.size()));
//			}
		}
		return jacksonResponse;
	}

	/*
	 * ThoaiVt 16/03/2016 search account
	 */
	@RequestMapping(value = "/adminAccountBean.searchAccount", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse searchAccount(
			@ModelAttribute(value = "contentSearch") AdministratorGeneralCriteria administratorAccountCriteria) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		List<M900MG> listM900Mg = null;
		logger.info("DATA SEND : " + administratorAccountCriteria.toString());
		if (q100 != null) {
			jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			listM900Mg = m900mgService.getListAccount(administratorAccountCriteria.getNameAccount(), q100.getPo100(),
					administratorAccountCriteria.getOffset(), administratorAccountCriteria.getLimit());
			jacksonResponse.setResult(listM900Mg);
		}
		return jacksonResponse;

	}

	/**
	 * create: 21/03/2016 author: ThoaiNH
	 * 
	 * @param po100
	 * @return
	 */
	@RequestMapping(value = "/adminAccountBean.delete", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse deleteAccount(@ModelAttribute(value = "po100") int po100) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		// check q100
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null && "ADMIN".equalsIgnoreCase(q100.getM900mg().getMv922())) {
			// result value
			int result = o100Service.removeAccount(po100);
			jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jacksonResponse.setResult(result);
		}
		return jacksonResponse;
	}
}
