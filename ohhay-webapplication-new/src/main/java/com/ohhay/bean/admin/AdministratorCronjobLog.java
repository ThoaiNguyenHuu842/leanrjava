package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.S000MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.mongo.dao.R000MGDao;

/**
 * @author TuNt create date Apr 22, 2016 ohhay-webapplication-new
 */
@Controller
public class AdministratorCronjobLog implements Serializable {
	Logger logger = Logger.getLogger(AdministratorCronjobLog.class);
	@Autowired
	private TemplateService templateservice;

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R000MG)
	R000MGDao r00mgDao;

	@RequestMapping(value = "/adminCronjobLog.loadCronjobLog", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadCronjobLog() {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<String> listTypeLog = r00mgDao.listOfTabRv001();
			jacksonResponse.setResult(listTypeLog);
		}
		S000MG s000mg = templateservice.findDocumentById(0, 1, S000MG.class);
		jacksonResponse.setResult2(s000mg);
		return jacksonResponse;
	}

	@RequestMapping(value = "/adminCronjobLog.loadCronjobLogContent", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadCronjobLogContent(@ModelAttribute(value = "typelog") String typelog) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<String> listLog = r00mgDao.listOfTabRv000(typelog);
			jacksonResponse.setResult(listLog);
		}
		return jacksonResponse;
	}

	@RequestMapping(value = "/adminCronjobLog.changeLogger", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse changeLogger(@ModelAttribute(value = "on") int on) {
		JacksonResponse jacksonResponse = new JacksonResponse();
		try {
			@SuppressWarnings("unchecked")
			List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
			Level[] listLogger = { Level.INFO, Level.DEBUG };
			loggers.add(LogManager.getRootLogger());
			if (on == 0) {
				for (Logger logger : loggers)
					logger.setLevel(Level.OFF);
			} else {
				for (Logger logger : loggers) {
					for (Level level : listLogger)
						logger.setLevel(level);
				}
			}
			templateservice.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
			Object data = templateservice.findAndModify(0, S000MG.class, 1, QbMongoFiledsName.SN001, on, null);
			System.out.println(data);
			jacksonResponse.setResult(JacksonResponse.AJAX_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			jacksonResponse.setResult(JacksonResponse.AJAX_ERROR);
		}
		return jacksonResponse;
	}
}
