package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.security.AuthenticatedUser;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mongo.dao.U920MGDao;
import com.ohhay.other.mongo.daoimpl.U920MGDaoImpl;
import com.ohhay.web.core.mongo.dao.E400MGDao;

/**
 * @author TuNt create date Mar 28, 2016 ohhay-webapplication-new
 */
@Controller
public class AdministratorDomainManagement implements Serializable {

	@Autowired
	TemplateService templateService;
	
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_U920MG)
	U920MGDao u920mgDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(AdministratorDomainManagement.class);

	@RequestMapping(value = "/adminAccountBean.loadListDomain", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListDomain(@ModelAttribute(value="offset") int offset, @ModelAttribute(value="limit") int limit, @ModelAttribute(value="status") int status ) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<U920MG> listDomain = u920mgDao.listOfTabU920(offset, limit, status);
			jsr.setResult(listDomain);
		}
		return jsr;
	}
}
