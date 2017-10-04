package com.ohhay.bean.designer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.NotificationKey;
import com.ohhay.core.entities.N700MG;
import com.ohhay.core.entities.N800MG;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.N700MGService;
import com.ohhay.core.mongo.service.N800MGService;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.mongo.service.E150MGService;
import com.ohhay.web.lego.entities.mongo.web.E150MG;

/**
 * @author TuNt
 * create date Dec 13, 2016
 * ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class NotifyBean {
	private Logger log = Logger.getLogger(NotifyBean.class);
	@Autowired
	private E150MGService e150Service;

	@Autowired
	private N700MGService n700mgService;
	@Autowired
	private N800MGService n800mgService;
	
	@RequestMapping(value="/notifyBean.listOfTabSitesOfCusDetail", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse listOfTabSitesOfCusDetail(@ModelAttribute("pe150") int pe150){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null){
			E150MG  e150mg = e150Service.listOfTabSitesOfCusDetail(q100.getPo100(), pe150);
			jacksonResponse.setResult(e150mg);
		}else{
			jacksonResponse.setResult(null);
		}
		return jacksonResponse;
	}
	@RequestMapping(value="/notifyBean.listOfTabN700", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse listOfTabN700(){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null){
			N700MG n700mg = n700mgService.listOfTabN700(ApplicationConstant.DB_TYPE_OHHAY, q100.getPo100());
			jacksonResponse.setResult(n700mg);
		}else{
			jacksonResponse.setResult(null);
		}
		return jacksonResponse;
	}
	@RequestMapping(value="/notifyBean.updateTabN700", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse updateTabN700(@ModelAttribute("objectName") String objectName){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null){
			int result = n700mgService.updateTabN700(ApplicationConstant.DB_TYPE_OHHAY, q100.getPo100(), objectName);
			jacksonResponse.setResult(result);
		}else{
			jacksonResponse.setResult(null);
		}
		return jacksonResponse;
	}
	@RequestMapping(value="/notifyBean.listOfTabN800", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse listOfTabN800(@ModelAttribute("offset") int offset, @ModelAttribute("limit") int limit){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null){
			List<N800MG> listN800mg = n800mgService.listOfTabN800(ApplicationConstant.DB_TYPE_OHHAY, q100.getPo100(), offset, limit, NotificationKey.BONEVO_NOTIFICATION_KEY.toString());
//			if(limit==7){
//				n700mgService.updateTabN700(ApplicationConstant.DB_TYPE_OHHAY, q100.getPo100(), "N800_NEW");
//			}
			jacksonResponse.setResult(listN800mg);
		}else{
			jacksonResponse.setResult(null);
		}
		return jacksonResponse;
	}
	@RequestMapping(value= "/notifyBean.insertTabN800", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse insertTabN800(@ModelAttribute("fo100n") int fo100n, @ModelAttribute("nv801") String nv801, @ModelAttribute("nn802") int nn802, @ModelAttribute("nv803") String nv803){
		JacksonResponse jacksonResponse = new JacksonResponse();
		jacksonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null){
			int result  = n800mgService.insertTabN800(ApplicationConstant.DB_TYPE_OHHAY, q100.getPo100(), fo100n, nv801, nn802, nv803, NotificationKey.BONEVO_NOTIFICATION_KEY.toString());
			jacksonResponse.setResult(result);
		}else{
			jacksonResponse.setResult(null);
		}
		return jacksonResponse;
	}
	
}
