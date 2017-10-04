package com.ohhay.bean.web.requestmapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
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
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.entities.ItemTabN750;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mysql.service.N750Service;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;

/**
 * @author ThoaiNH create Oct 7, 2015 mapping root path request
 */
@Controller
@Scope("prototype")
public class RootBean extends WebBaseRequestMapping {
	private static Logger log = Logger.getLogger(RootBean.class);
	@Autowired
	private TemplateService templateService;

	/**
	 * logout
	 * 
	 * @param action
	 * @return
	 */
	@RequestMapping(value = "/logout", headers = "Host=" + ApplicationConstant.HOST)
	public String mapRootLogout(@RequestParam(required = false, value = RequestParamRule.PARAM_ACTION) String action) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 == null)
			return ApplicationHelper.getRedirectString("");
		else
			return OhhayPagesName.OHHAY_PAGE_LOGOUT;
	}

	/**
	 * login
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", headers = "Host=" + ApplicationConstant.HOST)
	public String mapRootLogin(ModelMap model, HttpServletRequest request) {
		renderLanguageByIp(null, request);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null)
			return ApplicationHelper.getRedirectString("/");
		OhhayViewer ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
		if (ohhayViewer == null) {
			renderLanguageByIp(null, request);
			ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
		}
		if (ohhayViewer != null)
			model.addAttribute("ohhayViewer", ohhayViewer);
		return OhhayPagesName.OHHAY_PAGE_LOGIN;
	}

	/**
	 * update 15/01/2016: redirect to evo-lading page when access from mobile
	 * device update 03/03/2016: chuyen ve dung voi ngon ngu cua nguoi dung root
	 * path
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String mapRootRequest(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = RequestParamRule.PARAM_LANGUAGE) String languageCode) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		String requestUrl = ApplicationHelper.getDomainFromRequest(request);
		log.info("---request url:" + requestUrl);
		if (!requestUrl.equals(ApplicationConstant.HOST)) {
			U920MG u920mgTemp = templateMGService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, U920MG.class,
					new QbCriteria(QbMongoFiledsName.UV921, requestUrl), new QbCriteria(QbMongoFiledsName.UN923, 1));
			if (u920mgTemp != null) {
				E400MG e400mg = templateMGService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN,
						u920mgTemp.getFe400(), E400MG.class);
				if (e400mg != null) {
					model.addAttribute("pe400", e400mg.getId());
					OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
					E950MG e950mg = templateService.findDocument(e400mg.getFo100(), E950MG.class,
							new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
					model.addAttribute("e950mg", e950mg);
					model.addAttribute("e400mg", e400mg);
					model.addAttribute("device", ohhayViewer.getDevice());
					model.addAttribute("fo100Encrypted", AESUtils.encrypt(String.valueOf(e400mg.getFo100())));
					return OhhayPagesName.EVO_PAGE_WEB_PUBLISH;
				}
			}
		}
		if (q100 != null) {
			model.addAttribute("userLogin", q100);
			if (languageCode == null) {
				if (q100.getM900mg().getLanguage() != null)
					return ApplicationHelper.getRedirectString("/?language=" + q100.getM900mg().getLanguage());
				String redirectLang = renderLanguageByIp(languageCode, request);
				if (redirectLang != null)
					return redirectLang;
			}
			return ApplicationHelper.getRedirectString("/mysites");
		} else {
			List<E400MG> listE400 = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class,
					new QbCriteria("EN402", 1), new QbCriteria("EN407", 1));

			OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
			if (ohhayViewer != null)
				model.addAttribute("ohhayViewer", ohhayViewer);
			String redirectLang = renderLanguageByIp(languageCode, request);
			model.addAttribute("listE400", listE400);
			if (redirectLang != null)
				return redirectLang;
			else
				return OhhayPagesName.OHHAY_PAGE_HOME;
		}
	}

	/**
	 * signup
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signup", headers = "Host=" + ApplicationConstant.HOST)
	public String mapRootSignUp(ModelMap model, HttpServletRequest request) {
		renderLanguageByIp(null, request);
		ApplicationHelper.removeSession(SessionConstant.SIGNUP_CONFIRMOTP_SUCCESS);
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null)
			return ApplicationHelper.getRedirectString("/");
		N750Service n750Service = (N750Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N750);
		List<ItemTabN750> list = n750Service.nhayCombTabN750(ApplicationConstant.PVLOGIN_ANONYMOUS);
		model.addAttribute("listN750", list);
		OhhayViewer ohhayViewer = (OhhayViewer) ApplicationHelper.getSession(SessionConstant.OHHAY_VIEWER);
		if (ohhayViewer != null)
			model.addAttribute("ohhayViewer", ohhayViewer);
		return OhhayPagesName.OHHAY_PAGE_SIGNUP;
	}

	/*
	 * ThoaiVt 09/03/2017 contact-imprint
	 */
	@RequestMapping(value = "/contact-imprint", headers = "Host=" + ApplicationConstant.HOST)
	public ModelAndView contactImprint(HttpServletRequest request, HttpServletResponse response) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		return new ModelAndView(OhhayPagesName.EVO_PAGE_CONTACT_IMPRINT, "userLogin", q100);
	}

	/*
	 * ThoaiVt 10/03/2017 contact-imprint
	 */
	@RequestMapping(value = "/registration-successful", headers = "Host=" + ApplicationConstant.HOST)
	public String registrationSuccessful(HttpServletRequest request, HttpServletResponse response) {
		return OhhayPagesName.EVO_PAGE_REGISTRY_SUCCESSFUL;
	}

	/*
	 * ThoaiVt 11/03/2017 contact-imprint
	 */
	@RequestMapping(value = "/privacy", headers = "Host=" + ApplicationConstant.HOST)
	public ModelAndView privacy(HttpServletRequest request, HttpServletResponse response) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if ("de".equals(MVCResourceBundleUtil.getCurrentLocale().getLanguage()))
			return new ModelAndView(OhhayPagesName.EVO_PAGE_PRIVACY_DE, "userLogin", q100);
		return new ModelAndView(OhhayPagesName.EVO_PAGE_PRIVACY, "userLogin", q100);
	}

	/*
	 * ThoaiVt 10/03/2017 term-of-use
	 */
	@RequestMapping(value = "/term-of-use", headers = "Host=" + ApplicationConstant.HOST)
	public ModelAndView termOfUse(HttpServletRequest request, HttpServletResponse response) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		return new ModelAndView(OhhayPagesName.EVO_PAGE_TERM_OF_USE, "userLogin", q100);
	}

	/**
	 * ThoaiVt 28/12/2015 create web by templates
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/evo-templates", headers = "Host=" + ApplicationConstant.HOST)
	public ModelAndView evoTemplates(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null)
			return new ModelAndView(OhhayPagesName.EVO_PAGE_TEMPLATES, "userLogin", q100);
		return new ModelAndView(ApplicationHelper.getRedirectString("/"));
	}

	/**
	 * ThoaiVt create date 18/02/2016
	 */
	@RequestMapping(value = "/forgotpass", headers = "Host=" + ApplicationConstant.HOST)
	public String mapRootForgotPass(HttpServletRequest request, ModelMap model, HttpServletResponse httpResponse,
			@RequestParam(required = false, value = RequestParamRule.PARAM_EMAIL) String email,
			@RequestParam(required = false, value = RequestParamRule.PARAM_EID) String key) {
		log.info(email);
		log.info(key);
		if (email.isEmpty() || key.isEmpty())
			return OhhayPagesName.OHHAY_PAGE_404;
		return OhhayPagesName.OHHAY_PAGE_FORGOTPASS;
	}

	/**
	 * pricing page
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pricing", headers = "Host=" + ApplicationConstant.HOST)
	public String mapPricing(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Q100 q100 = AuthenticationHelper.getUserLogin();
		model.addAttribute("userLogin", q100);
		return OhhayPagesName.EVO_PAGE_PRICING;
	}

	/**
	 * @param request
	 * @param model
	 * @param pathOne
	 * @return
	 */
	@RequestMapping(value = "pricing/{pathOne:.*}", method = RequestMethod.GET)
	public String pricingPage(HttpServletRequest request, ModelMap model,
			@PathVariable(value = "pathOne") String pathOne) {
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null && pathOne != null) {
			// create digistore api url
			try {
				String encodeFirstName = URLEncoder.encode(q100.getM900mg().getMv901Decrypt(), "UTF-8");
				String encodeLastName = URLEncoder.encode(q100.getM900mg().getMv902Decrypt(), "UTF-8");
				String encodeEmail = URLEncoder.encode(q100.getM900mg().getMv903Decrypt(), "UTF-8");
				String apiURL = "https://www.digistore24.com/api/call/" + ApplicationConstant.DIGISTORE_API_KEY
						+ "/json/createBuyUrl/?" +
						// digistore product id
						"product_id=" + pathOne
						// user info
						+ "&buyer[first_name]=" + encodeFirstName + "&buyer[last_name]=" + encodeLastName
						+ "&buyer[email]=" + encodeEmail
						// cannot edit name and email
						+ "&buyer[readonly_keys]=email_and_name"
						// time until expire: 1 hour
						+ "&valid_until=1h";
				log.info("DIGISTORE_API_URL: " + apiURL);
				try {
					// read json response from api url
					URL url = new URL(apiURL);
					BufferedReader br;
					try {
						br = new BufferedReader(new InputStreamReader(url.openStream()));
						String strTemp = "";
						while (null != (strTemp = br.readLine())) {
							try {
								// create json object from json response
								JSONObject json = new JSONObject(strTemp);
								String result = json.getString("result");
								if (result.equals("success")) {
									JSONObject data = json.getJSONObject("data");
									// get buy url
									String buyURL = data.getString("url");
									return ApplicationHelper.getRedirectString(buyURL);
								}
							} catch (org.json.JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}
		model.addAttribute("userLogin", q100);
		return OhhayPagesName.EVO_PAGE_PRICING;
	}
}
