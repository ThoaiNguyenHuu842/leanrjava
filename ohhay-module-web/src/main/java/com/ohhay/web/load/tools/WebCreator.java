package com.ohhay.web.load.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.C800MG;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayLibraryJS;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.load.util.WebOhhay;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.zuss.ZussUtils;

/**
 * @author ThoaiNH
 * create 22/11/2014
 * create O!hay web
 */
public class WebCreator {
	private static Logger log = Logger.getLogger(WebCreator.class);
	private WebOhhay ohhay;
	private String templatePath;
	private TemplateCreator templateLoader;
	private UserHtmlCreator userHtmlLoader;
	private WebCreateParam webParam;
	private Set<OhhayLibraryJS> setOhhayLibraryJS = new java.util.HashSet<OhhayLibraryJS>();
	/**
	 * @param c400mg
	 *            : c400mg to create web 
	 * @param role
	 *            : role
	 * @param extend
	 *            : extend type (webinaris, bsell, merian...)
	 * @param key
	 *            : key of extend
	 */
	public WebCreator(WebCreateParam webParam) {
		super();
		log.info("***BAT DAU TAO WEB...");
		/*
		 * 1) prepare web structure
		 */
		try {
			prepareWebStructure(webParam.getOhhayWebBase());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.webParam = webParam;
		userHtmlLoader = new UserHtmlCreator(webParam, setOhhayLibraryJS);
		run();
	}
	/*
	 * map cv923 from template to current web structure
	 */
	private void prepareWebStructure(OhhayWebBase ohhayWebBase) throws Exception {
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		C800MG c800mg = templateService.findDocumentById(0, ohhayWebBase
				.getFc800(), C800MG.class);
		if (c800mg != null) {
			//set link html template
			ohhayWebBase.setCv802(c800mg.getCv802());
			try {
				Map<Integer, C920MG> mapCv923 = new HashMap<Integer, C920MG>();
				//create map box
				for (C920MG c920mg : c800mg.getListC920mg()) {
					mapCv923.put(new Integer(c920mg.getFc820()), c920mg);
				}
				//map box
				for (C920MG c920mg : ohhayWebBase.getListC920mg()) {
					C920MG c920mgOrginal = mapCv923.get(new Integer(c920mg.getFc820()));
					c920mg.setCv923(c920mgOrginal.getCv923());
					c920mg.setCv927(c920mgOrginal.getCv927());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else
			throw new Exception("WEBSITE OF USER "+ohhayWebBase.getFo100()+" DON'T HAVE ANY TEMPLATE!");
	}

	private void run() {
		try {
			/*
			 * 2) load template web page
			 */
			if (webParam.getOhhayWebBase().getCv802() != null) {
				templatePath = webParam.getOhhayWebBase().getCv802();
				log.info("templatePath--1---" + templatePath);
			}
			else {
				templatePath = webParam.getOhhayWebBase().getListC920mg()
						.get(0).getCv802();
				log.info("templatePath---2--" + templatePath);
			}
			templateLoader = TemplateCreator.getInstance(templatePath);
			Document doc = templateLoader.getDocument();
			/*
			 * 3) load user html
			 */
			String userHtml = userHtmlLoader.getUserHtml();
			Elements elements = doc
					.body()
					.select("[" + TemplateRule.OHHAY_QB_DATA_CONTAINER
							+ "]").empty();
			if (elements.size() > 0) {
				elements.get(0)
						.removeAttr(TemplateRule.OHHAY_QB_DATA_CONTAINER);
				elements.get(0).append(userHtml);
			}
			/*
			 * 4) load color to ZUSS
			 */
			if(webParam.getOhhayWebBase().getCv807()!= null && ApplicationHelper.validateRegex(webParam.getOhhayWebBase().getCv807(), RegexConstant.COLOR_PATTERN))
			{
				log.info("----set web color ZUSS:"+webParam.getOhhayWebBase().getCv807());
				ZussUtils.M_COLOR = webParam.getOhhayWebBase().getCv807();
			}
			else
				ZussUtils.M_COLOR = null;
			/*
			 * 5) map web profile
			 */
			N950MG n950mg = webParam.getOhhayWebBase().getN950mg();
			mapWebInfoToElement(n950mg, doc);
			/*
			 * 6) map real link to pattern link
			 */
			StringBuilder builder = new StringBuilder(doc.toString());
			log.info("---fo100toLoadImg:"+webParam.getOhhayWebBase().getFo100());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(webParam.getOhhayWebBase().getFo100(), webParam.getOhhayWebBase().getFo100(), M900MG.class, QbMongoFiledsName.REGION);
			log.info("---imgLink:"+m900mg.getImgLinkCloudFront());
			//template id to map template img, if web is a900 template id is webid else template id is fa900
			int templateId = webParam.getOhhayWebBase().getFa900();
			if(templateId == 0)
				templateId = webParam.getOhhayWebBase().getId();
			LinkMapper linkMapper = new LinkMapper(builder, templatePath, m900mg.getImgLinkCloudFront(), templateId);
			linkMapper.doMap();
			String webContentFinal = builder.toString();
			Document finalDocument = Jsoup.parse(webContentFinal);
			ohhay = new WebOhhay();
			ohhay.setWebId(webParam.getOhhayWebBase().getId());
			ohhay.setHeader(finalDocument.head().html());
			ohhay.setBody(finalDocument.body().html());
			ohhay.setColor(webParam.getOhhayWebBase().getCv807());
			ohhay.setSetOhhayLibraryJS(setOhhayLibraryJS);
			if(webParam.getOhhayWebBase().getCv809()!="")
				ohhay.setBackgroundImg(ApplicationConstant.BACKGROUND_OHAY_ROOT_PATH+webParam.getOhhayWebBase().getCv809());
			else
				ohhay.setBackgroundImg("");
			log.info("***TAO WEB XONG...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * map web info to element
	 */
	public void mapWebInfoToElement(N950MG n950mg, Document document)
	{
		/*
		 * address
		 */
		Elements elementAddress = document.body().select(".qb-out-web-info-address");
		if(elementAddress.size()> 0)
		{
			for(Element e: elementAddress){
				if(n950mg != null && n950mg.getNv958() != null && n950mg.getNv958().length() > 0)
				{
					log.info("--vao xoa info");
					e.empty();
					e.append(n950mg.getNv958());
				}
				e.removeClass("qb-out-web-info-address");
				if(webParam.getRole() == ApplicationConstant.ROLE_OWNER){
					e.addClass(TemplateRule.OHHAY_WEB_PROFILE_CLASS);
					e.addClass(TemplateRule.OHHAY_EDIT_CLASS);
					e.addClass(TemplateRule.OHHAY_WEB_PROFILE_CLASS_ADDRESS);
				}
			}
		}
		/*
		 * email
		 */
		Elements elementEmail = document.body().select(".qb-out-web-info-email");
		if(elementEmail.size()> 0)
		{
			for(Element e: elementEmail){
				if(n950mg != null && n950mg.getNv957() != null && n950mg.getNv957().length() > 0)
				{
					e.empty();
					e.append(n950mg.getNv957());
				}
				e.removeClass("qb-out-web-info-email");	
				if(webParam.getRole() == ApplicationConstant.ROLE_OWNER){
					e.addClass(TemplateRule.OHHAY_WEB_PROFILE_CLASS);
					e.addClass(TemplateRule.OHHAY_EDIT_CLASS);
					e.addClass(TemplateRule.OHHAY_WEB_PROFILE_CLASS_EMAIL);
				}
			}
		}
		/*
		 * phone
		 */
		Elements elementPhone = document.body().select(".qb-out-web-info-phone");
		if(elementPhone.size()> 0)
		{
			for(Element e: elementPhone){
				if(n950mg != null && n950mg.getNv956() != null && n950mg.getNv956().length() > 0)
				{
					e.empty();
					e.append(n950mg.getNv956());
				}
				e.removeClass("qb-out-web-info-phone");
				if(webParam.getRole() == ApplicationConstant.ROLE_OWNER){
					e.addClass(TemplateRule.OHHAY_WEB_PROFILE_CLASS);
					e.addClass(TemplateRule.OHHAY_EDIT_CLASS);
					e.addClass(TemplateRule.OHHAY_WEB_PROFILE_CLASS_PHONE);
				}
			}
		}
	}
	public WebOhhay getOhhay() {
		return ohhay;
	}

	public void setOhhay(WebOhhay ohhay) {
		this.ohhay = ohhay;
	}

}
