/*package com.ohhay.web.other.subcontainer;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.B050Wbn;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;
import com.ohhay.web.other.api.service.WebinarisService;

*//**
 * @author ThoaiNH
 *
 *//*
@Service
public class WebinarGalleryItemSubContainer extends AbstractSubContainer {
	private static Logger log = Logger.getLogger(WebinarGalleryItemSubContainer.class);
	
	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900s, int role, String webinarkey)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("----WebinarGalleryItemSubContainer: mapElementToBox webinarkey:" + webinarkey);
		WebinarisService b050WbnService = (WebinarisService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_WEBINARIS);
		List<B050Wbn> lisB050s = b050WbnService.getListB050(webinarkey);
		
		 * 1) find orginal gallery item of webinaris 
		 
		C900MG c900mgClone = null;
		for (int i = 0; i < listC900s.size(); i++) {
			C900MG c900mg = listC900s.get(i);
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
				log.info("---WebinarGalleryItemSubContainer: found webinaris element id:"+ c900mg.getPc900());
				c900mgClone = new C900MG(c900mg);
				break;
			}
		}
		StringBuilder builderDbSubContainer = new StringBuilder("");
		
		 * 2) create list C900 from list B050
		 
		if (c900mgClone != null) {
			log.info("---WebinarGalleryItemSubContainer: list b050 size:" + lisB050s.size());
			for (int i=0;i<lisB050s.size();i++) {
				C900MG c900mg2 = new C900MG(c900mgClone);
				c900mg2.setPc900(null);// khong cho edit
				builderDbSubContainer.append(processGalleryItem(c900mg2, role, lisB050s.get(i)));
			}
			log.info("---WebinarGalleryItemSubContainer: listc900 size:" + listC900s.size());
		}
		else
			throw new Exception("Template do'nt have any webinar element");
		element.append(builderDbSubContainer.toString());
		
		 * 3) remove attr if viewer
		 
		if (role == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER);
		}
	}

	
	 * mapB050ToCv906
	 
	private Document mapB050ToCv906(String cv906, B050Wbn b050) {
		Document document = Jsoup.parse(cv906);
		Elements ele1 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=title]");
		Elements ele2 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=description]");
		Elements ele3 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=link-register]");
		log.info("--ele1 size:" + ele1.size());
		// title
		if (ele1.size() > 0) {
			Element element = ele1.get(0);
			element.empty();
			element.append(b050.getBv051());
		}
		// description
		if (ele2.size() > 0) {
			Element element = ele2.get(0);
			element.empty();
			element.append(b050.getBv052());
		}
		// link-register
		if (ele3.size() > 0) {
			Element element = ele3.get(0);
			createLinkRegisterWebinaris(element, b050);
		}
		return document;
	}
	
	 * create link register webinaris
	 
	private void createLinkRegisterWebinaris(Element element, B050Wbn b050) {
		element.addClass(TemplateRule.OHHAY_QB_LINK_REGIST_CLASS);
		if (AuthenticationHelper.getUserLogin() != null) {
			Q100 q100 = AuthenticationHelper.getUserLogin();
			M900MG m900mg = q100.getM900mg();
			if(m900mg != null){
				//check profile name,email, phone not empty
				if(m900mg.getMv901() != null && m900mg.getMv901().length() >0 && 
				   m900mg.getMv902() != null && m900mg.getMv902().length() >0 &&
				   m900mg.getMv903() != null && m900mg.getMv903().length() >0 &&
				   m900mg.getMv907() != null && m900mg.getMv907().length() >0 ){
					StringBuilder stringBuilder = new StringBuilder(b050.getUrl());
					stringBuilder.append("?secret=true&fname="+m900mg.getMv901Decrypt());
					stringBuilder.append("&lname="+m900mg.getMv902Decrypt());
					stringBuilder.append("&email="+m900mg.getMv903Decrypt().trim());
					stringBuilder.append("&readonly=true&mobile="+m900mg.getMv907());
					stringBuilder.append("&source_id=ohay");
					element.attr("href", stringBuilder.toString());
				}
				else
					element.addClass(TemplateRule.OHHAY_QB_ACCOUNT_SHORT_FILED_CLASS);
			}
			else
				element.addClass(TemplateRule.OHHAY_QB_ERROR_CLASS);
		}
		else
			element.addClass(TemplateRule.OHHAY_QB_NO_LOGIN_CLASS);
	}

	
	 * process html for gallery item webinar
	 
	private String processGalleryItem(C900MG c900mg, int role, B050Wbn b050) {
		Document document = mapB050ToCv906(c900mg.getCv906(), b050);
		Elements elementsEditAble = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT + "~=]");
		if (elementsEditAble.size() > 0) {
			for (Element element : elementsEditAble) {
				if (element.hasClass(TemplateRule.OHHAY_NO_EDIT_CLASS)) {
					// truong hop nhung element nam trong elemnt a -> khong edit nhung element nay
				}
				else {
					processElementGroup(element, c900mg.getPc900(), role);
				}
				afterProccessElementOfCV906(element, role);
			}
		}
		Elements elements = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_TYPE + "~=]");
		if (elements.size() > 0)
			afterProccessCV906(elements.get(0), role);
		return document.body().html();
	}

	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key, WebRequestParam webRequestParam)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mapElementToBox(Element element, List<C900MG> listC900mgs, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		
	}
}
*/