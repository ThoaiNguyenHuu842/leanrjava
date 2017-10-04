package com.ohhay.web.other.subcontainer;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;

/**
 * @author ThoaiNH
 *
 */
@Service
public class GalleryItemSubContainer extends AbstractSubContainer {
	private static Logger log = Logger.getLogger(AbstractSubContainer.class);
	@Override	
	public void mapElementToBox(Element element, List<C900MG> listC900s, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		StringBuilder builderDbSubContainer = new StringBuilder("");
		// add all cv906 of c900 to element
		for (C900MG c900mg : listC900s) {
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
				builderDbSubContainer.append(processGalleryItem(c900mg, webCreateParam.getRole(), webCreateParam));
			}
		}
		element.append(builderDbSubContainer.toString());
		// remove attr if viewer
		if (webCreateParam.getRole() == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER);
		}
	}

	/*
	 * process html for gallery item
	 */
	private String processGalleryItem(C900MG c900mg, int role, WebCreateParam webCreateParam) {
		if(c900mg.getVisible() != -1 || role == 2){
			/*
			 * 1) load property bundle from C100
			 */
			PropertyValue c110Value = webCreateParam.getMapProperties().get(c900mg.getPc900());
			if (c110Value != null)
			{
				c900mg.setCv906(c110Value.getValue());
				c900mg.setIndexProperty(c110Value.getIndex());
			}
			else {
				log.error("ERROR: PROPERTIES KEY NOT FOUND:"+ c900mg.getPc900());
				c900mg.setIndexProperty(-1);
			}
			/*
			 * 2) parse dom
			 */
			Document document = Jsoup.parse(c900mg.getCv906());
			Elements elementsEditAble = document.body().select("["
					+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT + "~=]");
			if (elementsEditAble.size() > 0) {
				for (Element element : elementsEditAble) {
					if (element.hasClass(TemplateRule.OHHAY_NO_EDIT_CLASS)) {
						//truong hop nhung element nam trong elemnt a -> khong edit nhung element nay
					}
					else {
						processElementGroup(element, c900mg.getSuperID(), role, c110Value);
					}
					afterProccessElementOfCV906(element, role);
				}
			}
			Elements elements = document.body().select("["
					+ TemplateRule.OHHAY_DATA_QB_TYPE + "~=]");
			if (elements.size() > 0)
				afterProccessCV906(elements.get(0), role);
			/*
			 * 3) add class to copy,edit (date update: 27/12/2014)
			 */
			addClassForElement(document.body().child(0), TemplateRule.OHHAY_EDIT_GALLERY_ITEM_CLASS, role);
			addFc850ToGalleryItem(document.body().child(0), String.valueOf(c900mg.getFc850()), role);
			addIdToElement(document.body().child(0), c900mg.getSuperID(), role);
			addAttr(document.body().child(0), TemplateRule.OHHAY_EDIT_BOX_VISIBLE, String.valueOf(c900mg.getVisible()), role);
			/*
			 * 4) add css postion update: 30/06/2015
			 */
			if(c110Value != null && c110Value.getCv115() != null)
			{
				String style = "";
				if(document.body().child(0).attr("style") != null)
					style = document.body().child(0).attr("style");
				document.body().child(0).attr("style", style+c110Value.getCv115());
				addClassForElement(document.body().child(0), TemplateRule.OHHAY_CHANGEPOSITION_CLASS, ApplicationConstant.ROLE_OWNER);
			}
			return document.body().html();
		}
		return "";
	}
	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String webinarkey, int fo100) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key, WebRequestParam webRequestParam)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
