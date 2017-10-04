package com.ohhay.bean.designer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.entities.mongo.profile.M900DesMG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author TuNt
 * create date Nov 25, 2016
 * ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class UserInfoBean {
	private static Logger log = Logger.getLogger(UserInfoBean.class);
	
	@Autowired
	private M900MGService m900mgService;
	
	/**
	 * @param fo100
	 * @return
	 */
	@RequestMapping(value = "/userInfoBean.loadDesignerInfo")
	public @ResponseBody JacksonResponse loadDesignerInfo (@ModelAttribute("fo100") int fo100){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		M900DesMG m900DesMG = m900mgService.listOfTabDesignerOne(fo100);
		jsonResponse.setResult(m900DesMG);
		return jsonResponse;
	}
}
