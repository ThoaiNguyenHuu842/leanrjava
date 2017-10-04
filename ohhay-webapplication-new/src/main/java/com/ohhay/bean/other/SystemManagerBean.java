package com.ohhay.bean.other;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.SessionConstant;

/**
 * @author ThoaiNH
 * create Mar 12, 2016
 */
@Controller
@Scope("prototype")
public class SystemManagerBean {
	private static Logger log = Logger.getLogger(SystemManagerBean.class);
	/**
	 * handle when user change language
	 * @param languageCode
	 * @return
	 */
	@RequestMapping(value = "/systemManagerBean.changeLanguage", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse changeLanguage(@ModelAttribute(value = "languageCode") String languageCode) {
		log.info("---changeLanguage:"+languageCode);
		ApplicationHelper.setSession(SessionConstant.USER_SYS_LANGUAGE, languageCode);
		return null;
	}
}
