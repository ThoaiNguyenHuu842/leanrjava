package com.ohhay.bean.other;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.User;
import com.ohhay.core.entities.mongo.other.T200MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.userprofile.N100PMG;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH 
 * create Apr 19, 2017
 */
@Controller
@Scope("prototype")
public class PiepmeBean {
	private static Logger log = Logger.getLogger(PiepmeBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private E400MGService e400mgService;

	/**
	 * @param model
	 * @param fo100
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"landing-via-piepme" }, method = RequestMethod.GET)
	public String landingViaKub(Model model, @RequestParam(value = "fo100", required = true) int fo100, @RequestParam(value = "token", required = true) String token, HttpServletRequest request, HttpServletResponse response) {
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		T200MG t200mg = templateService.findDocument(fo100, T200MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100), new QbCriteria("TV201", token));
		if (t200mg != null) {
			templateService.removeDocuments(fo100, T200MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			long timeGo = (System.currentTimeMillis() - t200mg.getTd208().getTime()) / 1000;
			log.info("---timeGo:"+timeGo);
			if (timeGo <= 5000)// 5m
			{
				M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
				if (m900mg != null) {
					if (m900mg.getMn919() == 0) {
						/*
						 * 1) update to db user
						 */
						m900mg.setMn909(1);
						m900mg.setMn919(1);// on flag confirmed email
						m900mg.setMv903(m900mg.getMv921());
						m900mg.setMv921("");
						templateService.saveDocument(fo100, m900mg, QbMongoCollectionsName.M900);
						/*
						 * 2) update center db
						 */
						M900MG m900mgCenter = templateService
								.findDocumentById(0, fo100, M900MG.class);
						m900mgCenter.setMn909(1);
						m900mgCenter.setMn919(1);// on flag confirmed email
						m900mgCenter.setMv903(m900mgCenter.getMv921());
						m900mgCenter.setMv921("");
						templateService.saveDocument(0, m900mgCenter, QbMongoCollectionsName.M900);
					}
					/*
					 * 3) login
					 */
					User user = new User();
					user.setPassword("0903387368");
					user.setPhone(m900mg.getMv903Decrypt());
					user.setRemember(1);
					user.setSrc("BO");
					Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
					log.info("---qbonCheckTabQ100:" + user.getPhone() + ","
							+ ApplicationHelper.convertToMD5(user.getPassword())
							+ "," + user.getSrc() + "," + user.getSrc() + ","
							+ ApplicationConstant.PVLOGIN_ANONYMOUS);
					Q100 q100 = q100Service.qbonCheckTabQ100(user.getPhone(), ApplicationHelper.convertToMD5(user.getPassword()), user.getSrc(), ApplicationConstant.PVLOGIN_ANONYMOUS);
					if (q100 != null) {
						// check email confirm before
						if (q100.getM900mg().getMv903() != null && q100.getM900mg().getMn919() == 1) {
							log.info(q100);
							log.info("--nv120:" + q100.getNv120());
							log.info("---user:" + user);
							AuthenticationHelper.onLoginSuccess(q100, user, response, true);
							/*
							 * 4) create BONEVO site for user if not exists
							 */
							List<E400MG> listE400s = e400mgService.getListMysite(q100.getPo100(), null, 0, 10);
							if (listE400s == null || listE400s.size() == 0) {
								templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
								N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
								boolean isBusinessAccount = null != n100pmg.getK100Con()? true: false;
								int webId = webLegoService.createPiepmeSite(fo100, isBusinessAccount);
								return ApplicationHelper.getRedirectString("/e" + webId + "/evo-editer?editmode=element");
							}
							return ApplicationHelper.getRedirectString("/");
						}
					}
				}
			}
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}
}
