/*package com.ohhay.web.other.subcontainer;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.V250;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;
import com.ohhay.web.other.api.service.BsellService;

*//**
 * @author ThoaiNH
 *
 *//*
@Service
public class BsellGalleryItemSubContainer extends AbstractSubContainer {

//	@Override
//	public void mapElementToBox_bsell(Element element, String cv923, List<C900MG> listC900s, int role, String key) {
//		// TODO Auto-generated method stub
//		BsellService bsellService = (BsellService) ApplicationHelper.findBean(ApplicationConstant.SERVICE_NAME_BSELLSER);
//		List<V250> lisV250s = bsellService.listOfTabV250KEY(key);
//		StringBuilder builderDbSubContainer = new StringBuilder("");
//		if(lisV250s != null && lisV250s.size() > 0){
//			builderDbSubContainer.append(processgetthml(cv923, role, lisV250s.get(0)));
//		}
//		log.info("TUNGNS01----------------"+builderDbSubContainer.toString());
//		element.append(builderDbSubContainer.toString());
//	}
//	
//	private String processgetthml(String element,int role, V250 v250){
//		Document document = gethtml(element,v250);
//		return document.body().html();
//	}
//	
//	private Document gethtml(String element, V250 v250){
//		Document document = Jsoup.parse(element);
//		Elements title = document.body().select("["+ ApplicationConstant.OHHAY_QB_DATA_CONTENT + "=b78a3223503896721cca1303f776159b]");
//		if(title.size() > 0){
//			Element ele = title.get(0);
//			ele.empty();
//			ele.append(v250.getVv502());
//		}
//		return document;
//	}
	
	 * mapV250ToCv906
	 
	private Document mapV250ToCv906(String cv906, V250 v250) {
		Document document = Jsoup.parse(cv906);
		Elements tagli = document.body().select("li").attr("class", "infoProduct");
		Elements tagli_dir = document.body().select("li").attr("lang", v250.getPv250());
		Elements tagli_dir_succ = document.body().select("li").attr("data-id","success");
		
		Elements ele1 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM + "=1]");
		Elements ele2 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM + "=2]");
		Elements ele3 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM+ "=3]");
		Elements ele4 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM + "=4]");
		Elements ele5 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM + "=5]");
		Elements ele6 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM+ "=6]");
		Elements ele7 = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM+ "=7]");
		// title
		if (ele1.size() > 0) {
			Element element = ele1.get(0);
			element.empty();
			StringBuilder stringBuilder = new StringBuilder("http://oohhay.s3.amazonaws.com/template/html/landing/profile/Portfolio-6-bSELL/images/ceo-img.png");
			element.attr("src", stringBuilder.toString());
			//element.append(v250.getUrlimg());
		}
		// description
		if (ele2.size() > 0) {
			Element element = ele2.get(0);
			element.empty();
			element.append(v250.getVv501());
		}
		// description
		if (ele3.size() > 0) {
			Element element = ele3.get(0);
			element.empty();
			element.append(v250.getVv502());
		}
		// description
		if (ele4.size() > 0) {
			Element element = ele4.get(0);
			element.empty();
			element.append("Description of product...");
		}
		// Gia cua san pham
		if (ele5.size() > 0) {
			Element element = ele5.get(0);
			element.empty();
			element.append(v250.getVn254());
		}
		// link chi tiet cua san pham, can phai lam them.
		if (ele6.size() > 0) {
			Element element = ele6.get(0);
			element.empty();
			StringBuilder stringBuilder = new StringBuilder(v250.getUrlimg());
			stringBuilder.append("&source_id=ohay");
			element.attr("href", stringBuilder.toString());
			element.append("XEM CHI TIET");
		}

		// link-register
//		if (ele3.size() > 0) {
//			Element element = ele3.get(0);
//			createLinkRegisterWebinaris(element, v250);
//		}
		return document;
	}
	
	 * process html for gallery item bsell
	 
	private String processGalleryItem(C900MG c900mg, int role, V250 v250) {
		Document document = mapV250ToCv906(c900mg.getCv906(), v250);
		Elements elementsEditAble = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT + "~=]");
		// for template facilio
		if (elementsEditAble == null || elementsEditAble.size() == 0) {
			elementsEditAble = document.body().select("["+ "data-qb-accept-edit" + "~=]");
		}
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
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				BsellService bsellService = (BsellService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_BSELLSER);
				List<V250> lisV250s = bsellService.listOfTabV250KEY(key);
				// create c900 form b0050
				C900MG c900mgClone = null;
				int flagFound = 0;
				//tim element webinaris goc cua template sau do remove di de dua du lieu webinar cua user vao
				for (int i = 0; i < listC900mgs.size(); i++) {
					C900MG c900mg = listC900mgs.get(i);
					if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
						c900mgClone = new C900MG(c900mg);
						listC900mgs.remove(i);
						flagFound = 1;
					}
				}
				// create C900 from V250
				if (flagFound > 0) {
					for (int i=0;i<lisV250s.size();i++) {
						C900MG c900mg2 = new C900MG(c900mgClone);
						c900mg2.setPc900(null);// khong cho edit
						listC900mgs.add(c900mg2);
					}
				}
				else
					throw new Exception("Template do'nt have any webinar element");
				
				 * end create html form webinaris
				 
				StringBuilder builderDbSubContainer = new StringBuilder("");
				int i = 0;// V250 position to map
				for (C900MG c900mg : listC900mgs) {
					i++;
					if(i < lisV250s.size()){
						if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
							builderDbSubContainer.append(processGalleryItem(c900mg, role, lisV250s.get(i)));
						}
					}
				}
				//log.info("TUNGNS002222----------------"+builderDbSubContainer.toString());
				element.append(builderDbSubContainer.toString());
				// remove attr if viewer
				if (role == ApplicationConstant.ROLE_VIEWER) {
					element.removeAttr(TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER);
				}
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