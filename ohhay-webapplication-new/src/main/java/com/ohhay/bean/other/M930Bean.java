package com.ohhay.bean.other;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M930MG;
import com.ohhay.core.mongo.service.M930MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.SessionConstant;

/** 
 * @author ThongQB
 * create: 15/08/2015
 */
@Controller
@Scope("prototype")
public class M930Bean {
	@Autowired
	TemplateService templateMGService;
	@Autowired
	M930MGService m930mgService;
	private static Logger log = Logger.getLogger(M930Bean.class);
	/**
	 * update key for history
	 * @param key
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/m930Bean.updateKey", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updateKey(@ModelAttribute("key") String key,HttpServletRequest request, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		try{
			if (result.hasErrors()) {
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				jsonResponse.setResult(result.getAllErrors());
			}
			else {
				Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
				if(q100 !=null)
				{
					log.info("---insertTabM930:"+q100.getPo100()+","+key);
					int kq = m930mgService.insertTabM930(q100.getPo100(), key);
					//update M930MG in session
					if(kq > 0)
					{
						M930MG m930mg = templateMGService.findDocumentById(q100.getPo100(), q100.getPo100(), M930MG.class);
						q100.getM900mg().setM930mg(m930mg);
						ApplicationHelper.setSession(SessionConstant.USER_LOGIN, q100);
					}
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
					jsonResponse.setResult(kq);
				}
			}
			return jsonResponse;
		}catch(Exception e){
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(0);
			return jsonResponse;
		}
	}
}
