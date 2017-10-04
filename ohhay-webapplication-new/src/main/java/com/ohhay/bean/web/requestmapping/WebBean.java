package com.ohhay.bean.web.requestmapping;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mysql.service.R000CentService;
import com.ohhay.base.util.AESUtils;
import com.ohhay.bean.webeditor.WebCreatorBean;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH create Oct 13, 2015 mapping WEB_EVO request
 */
@Controller
@Scope("prototype")
public class WebBean extends WebBaseRequestMapping {
	private static Logger log = Logger.getLogger(WebCreatorBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;

	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_E400MG)
	private E400MGService e400MgService;

	/**
	 * @param request
	 * @param model
	 * @param pathOne
	 * @param editMode
	 * @return
	 */
	@RequestMapping(value = { "{pathOne:.*}" }, method = RequestMethod.GET)
	public String mapOnePathRequest(HttpServletRequest request, ModelMap model,
			@PathVariable(value = "pathOne") String pathOne) {
		try {
			E400MG e400mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class,
					new QbCriteria(QbMongoFiledsName.EV405, pathOne));
			if (e400mg != null) {
				//get domain using
				String requestUrl = ApplicationHelper.getDomainFromRequest(request);
				log.info("--requestUrl:"+requestUrl);
				//compare with bonevo
				if(!requestUrl.equals(ApplicationConstant.HOST)){
					//get e400 from domain using
					U920MG u920mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, U920MG.class, new QbCriteria(QbMongoFiledsName.UV921, requestUrl),
																new QbCriteria(QbMongoFiledsName.UN923, 1));
					if(u920mg == null || e400mg.getFo100() != u920mg.getFo100())
						return OhhayPagesName.OHHAY_PAGE_404;
				}
				Q100 q100 = AuthenticationHelper.getUserLogin();
				model.addAttribute("pe400", e400mg.getId());
				OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
				E950MG e950mg = templateService.findDocument(e400mg.getFo100(), E950MG.class,
						new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
				model.addAttribute("e950mg", e950mg);
				model.addAttribute("e400mg", e400mg);
				model.addAttribute("userLogin", q100);
				model.addAttribute("fo100Encrypted", AESUtils.encrypt(String.valueOf(e400mg.getFo100())));
				model.addAttribute("device", ohhayViewer.getDevice());
				if("RE".equals(e400mg.getSrc()))
					return OhhayPagesName.EVO_PAGE_WEB_PUBLISH_RE;
				return OhhayPagesName.EVO_PAGE_WEB_PUBLISH;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}

	/**
	 * map all one path url request
	 * 
	 * @param request
	 * @param model
	 * @param pathOne
	 * @param pathTwo
	 * @param editMode
	 * @return
	 */
	@RequestMapping(value = { "e{pathOne:.*}/{pathTwo:.*}" }, method = RequestMethod.GET)
	public String mapTwoPathRequest(HttpServletRequest request, ModelMap model,
			@PathVariable(value = "pathOne") String pathOne, @PathVariable(value = "pathTwo") String pathTwo,
			@RequestParam(required = false, value = RequestParamRule.PARAM_EDIT_MODE) String editMode) {
		try {
			int pe400 = Integer.parseInt(pathOne);
			E400MG e400mg = templateService.findDocumentById(ApplicationConstant.FO100_ADMIN_TEMPLATE, pe400,
					E400MG.class);
			if (e400mg != null) {
				Q100 q100 = AuthenticationHelper.getUserLogin();
				if (editMode != null && editMode.equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_ELEMENT) && q100 != null
						&& e400mg.getFo100() == q100.getPo100()) {
					OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
					log.info("---ohhayViewer.getBrowser():" + ohhayViewer.getBrowser());
					model.addAttribute("pe400", e400mg.getId());
					model.addAttribute("userLogin", q100);
					// not support editor in IE
					
					if("MOBILE".equalsIgnoreCase(ohhayViewer.getDevice()))
						return OhhayPagesName.EVO_PAGE_WEB_UNSUPPORTDEVICE;
					else if (ApplicationHelper.checkUnsupportedBrowser(ohhayViewer))
						return OhhayPagesName.EVO_PAGE_WEB_UNSUPPORT;
					if("RE".equals(e400mg.getSrc()))
						return OhhayPagesName.EVO_PAGE_DEMO_AUTO_RESPONSIVE;
					return OhhayPagesName.EVO_PAGE_WEBHOME;
				} else if (editMode != null && editMode.equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_ELEMENTMB)
						&& q100 != null && e400mg.getFo100() == q100.getPo100()) {
					OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
					/*
					 * ThoaiVt - 29/07/2016
					 *  get number hidden
					 */
					int numberHidden = e400MgService.getTotalHiddenComponent(e400mg.getFo100(),(int) e400mg.getId());
					log.info("--NUMBER HIDDEN COMPNENT : " + numberHidden);
					model.addAttribute("pe400", e400mg.getId());
					model.addAttribute("userLogin", q100);
					model.addAttribute("hiddenComp",numberHidden);
					
					// not support editor in IE
					if (ApplicationHelper.checkUnsupportedBrowser(ohhayViewer))
						return OhhayPagesName.EVO_PAGE_WEB_UNSUPPORT;
					return OhhayPagesName.EVO_PAGE_WEBHOME_MB;
				} else
					return ApplicationHelper.getRedirectString("/" + e400mg.getEv405());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}

	/**
	 * web trial mapping
	 * 
	 * @param request
	 * @param model
	 * @param webId
	 * @return
	 */
	@RequestMapping(value = { "trial" }, headers="Host="+ApplicationConstant.HOST, method = RequestMethod.GET)
	public String mapTrialRequest(HttpServletRequest request, ModelMap model,
			@RequestParam(required = false, value = "webId") String webId) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null)
		{
			/*
			 * ThoaiNH
			 * update 22/02/2017
			 * create template for user has login
			 */
			String returnMess = webLegoService.checkRightCreateWeb(q100.getAuthenticationRightInfo(), q100.getPo100());
			int templateID = 0;
			if(webId != null)
				templateID = Integer.parseInt(webId);
			E400MG e400mg = templateMGService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, templateID, E400MG.class);
			if(e400mg != null && e400mg.getEn402() == 1 && ApplicationConstant.RE_VAILD_RIGHT.equals(returnMess)){
				int userWebId = webLegoService.createBytemplate(q100.getPo100(), templateID, true);
				return ApplicationHelper.getRedirectString("/e"+userWebId+"/evo-editer?editmode=element");
			}
			else
				return ApplicationHelper.getRedirectString("/mysites");
		}
		else {
			renderLanguageByIp(null, request);
			OhhayViewer ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
			if (ohhayViewer != null) {
				// not support editor in IE
				if("MOBILE".equalsIgnoreCase(ohhayViewer.getDevice()))
					return OhhayPagesName.EVO_PAGE_WEB_UNSUPPORTDEVICE;
				else if (ApplicationHelper.checkUnsupportedBrowser(ohhayViewer))
					return OhhayPagesName.EVO_PAGE_WEB_UNSUPPORT;
				// get id test
				try {
					String ipAddress = ApplicationHelper.getIpAddress(request);
					log.info("--ip:" + ipAddress);
					R000CentService r000CentService = (R000CentService) ApplicationHelper
							.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R000CENT);
					log.info("---getValTabR000:" + ipAddress);
					int id = r000CentService.getValTabR000(ipAddress);
					log.info("---id:" + id);
					model.addAttribute("idTest", id);
					try {
						E400MG e400mg = templateMGService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN,
								Integer.parseInt(webId), E400MG.class);
						if (e400mg != null && e400mg.getEn402() == 1)
							model.addAttribute("webId", webId);
						else
							model.addAttribute("webId", 0);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						model.addAttribute("webId", 0);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				model.addAttribute("ohhayViewer", ohhayViewer);
			}
			return OhhayPagesName.EVO_PAGE_WEBHOME_TRIAL;
		}
	}
	/**
	 * web trial with facebook
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "fast-with-fb" }, headers="Host="+ApplicationConstant.HOST, method = RequestMethod.GET)
	public String createFastWebWithFB(HttpServletRequest request, ModelMap model) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null)
			return ApplicationHelper.getRedirectString("/mysites");
		else {
			/*
			 * ramdom template
			 */
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			List<E400MG> list = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class,
					new QbCriteria(QbMongoFiledsName.EN404, 1));
			Random rand = new Random();
			int i = rand.nextInt(list.size());
			int webId = (int) list.get(i).getId();

			renderLanguageByIp(null, request);
			OhhayViewer ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
			if (ohhayViewer != null) {
				// get id test
				try {
					String ipAddress = ApplicationHelper.getIpAddress(request);
					log.info("--ip:" + ipAddress);
					R000CentService r000CentService = (R000CentService) ApplicationHelper
							.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_R000CENT);
					log.info("---getValTabR000:" + ipAddress);
					int id = r000CentService.getValTabR000(ipAddress);
					log.info("---id:" + id);
					model.addAttribute("idTest", id);
					model.addAttribute("webId", webId);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				model.addAttribute("ohhayViewer", ohhayViewer);
				model.addAttribute("trialType", "fast-with-fb");
			}
			return OhhayPagesName.EVO_PAGE_WEBHOME_TRIAL;
		}
	}
	/**
	 * @param request
	 * @param model
	 * @param webId
	 * @return
	 */
	@RequestMapping(value = { "trial-re" }, headers="Host="+ApplicationConstant.HOST, method = RequestMethod.GET)
	public String mapTrialReRequest(HttpServletRequest request, ModelMap model,
			@RequestParam(required = false, value = "webId") String webId) {
		return OhhayPagesName.EVO_PAGE_DEMO_AUTO_RESPONSIVE;
	}
}
