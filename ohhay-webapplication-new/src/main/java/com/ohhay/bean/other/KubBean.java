package com.ohhay.bean.other;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH create Jan 21, 2016
 */
@Controller
@Scope("prototype")
public class KubBean {
	private static Logger log = Logger.getLogger(KubBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private M900MGService m900mgService;
	/**
	 * create landing page from KUB
	 * @param model
	 * @param fname
	 * @param lname
	 * @param email
	 * @param template
	 * @param kubvideo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "landing-via-kub"}, method = RequestMethod.GET)
	public String landingViaKub(Model model, @RequestParam(value = "fname", required = true) String fname,
												 @RequestParam(value = "lname", required = true) String lname,
												 @RequestParam(value = "email", required = true) String email,
												 @RequestParam(value = "template", required = true) int template,
												 @RequestParam(value = "kubvideo", required = true) String kubvideo, HttpServletRequest request) {
		//blank template
		if(template == 0)
		{
			E400MG e400mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class, new QbCriteria(QbMongoFiledsName.EN404, 3));
			if(e400mg != null)
				template = (int) e400mg.getId();
			else
				return OhhayPagesName.OHHAY_PAGE_404;
		}
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			//create site if logined with the same email in KUB
			if(q100.getQv101().equals(email.trim().toLowerCase())){
				int kq = webLegoService.createKubLandingPage(q100.getPo100(), template, kubvideo);
				if(kq > 0)
					return ApplicationHelper.getRedirectString("/e"+kq+"/evo-editer?editmode=element");
				else
					return OhhayPagesName.OHHAY_PAGE_404;
			} else
				//confirm logout to register new evo account
				return ApplicationHelper.getRedirectString("/logout?type=confirm&re="+ApplicationHelper.encodeURI(request.getRequestURL().toString() + "?" + request.getQueryString()));
		}
		else {
			//show popup enter password to continues if email already exists in EVO
			if(m900mgService.emailIsExists(email))
				return ApplicationHelper.getRedirectString("/signup?fname="+fname+"&lname="+lname+"&email="+email+"&template="+template+"&kubvideo="+kubvideo+"&action=confirmpass");
		}
		//create new account
		return ApplicationHelper.getRedirectString("/signup?fname="+fname+"&lname="+lname+"&email="+email+"&template="+template+"&kubvideo="+kubvideo);
	}
}
