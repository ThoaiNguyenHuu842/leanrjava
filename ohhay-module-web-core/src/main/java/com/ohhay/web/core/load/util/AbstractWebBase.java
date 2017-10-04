package com.ohhay.web.core.load.util;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.MVCResourceBundleUtil;

/**
 * @author ThoaiNH
 * create 12/11/2014
 * process base web create functions 
 */
public abstract class AbstractWebBase {
	private static Logger log = Logger.getLogger(AbstractWebBase.class);
	/*
	 * add class to element
	 */
	protected void addClassForElement(Element element, String newClass, int role) {
		// add class if is owner
		if (role == ApplicationConstant.ROLE_OWNER) {
			element.addClass(newClass);
		}
	}

	/*
	 * remove element
	 */
	protected void emptyElement(Element element) {
		String dataType = element.attr(TemplateRule.OHHAY_DATA_QB_TYPE);
		if (dataType != null && dataType.length() > 0) {
			// log.info("----data type:" + dataType);
			// khong xoa noi dung khi data type la OHHAY_DATA_QB_TYPE_PERCENT
			if (dataType.equals(TemplateRule.OHHAY_DATA_QB_TYPE_PERCENT))
				return;
			else
				element.empty();
		}
		else
			element.empty();
	}
	/*
	 * add attribute for element
	 */
	protected void addAttr(Element element, String attribute, String value, int role) {
		if (role == ApplicationConstant.ROLE_OWNER) {
			element.attr(attribute, value);
		}
	}
	/*
	 * add super id to element
	 */
	protected void addIdToElement(Element element, String id, int role) {
		if (role == ApplicationConstant.ROLE_OWNER) {
			element.attr(TemplateRule.OHHAY_SUPER_ID, id);
		}
	}
	/*
	 * add id to box for copy, clone
	 */
	protected void addIdToBox(Element element, String id, int role) {
		if (role == ApplicationConstant.ROLE_OWNER) {
			element.attr(TemplateRule.OHHAY_BOX_ID, id);
		}
	}
	/*
	 * add fc850 to galleryItem for copy, clone
	 */
	protected void addFc850ToGalleryItem(Element element, String id, int role) {
		if (role == ApplicationConstant.ROLE_OWNER) {
			element.attr(TemplateRule.OHHAY_EDIT_FC850, id);
		}
	}
	/*
	 * process element group and gallery item
	 */
	protected void processElementGroup(Element element, String id, int role, PropertyValue ...propertyValues) {
		try{
			/*
			 * 1) add id and edit class to element
			 */
			if (id != null) {
				addClassForElement(element, TemplateRule.OHHAY_EDIT_CLASS, role);
				addClassForElement(element, TemplateRule.OHHAY_EDIT_GALLERY_ITEM_FIELD_CLASS, role);
				addIdToElement(element, id, role);
			}
			/*
			 * 2) add class no edit for tag <a>, add class edit to it's children element
			 */
			if (element.tagName().equals("a")) {
				Elements subElements = element.getAllElements();
				for (Element subElement : subElements) {
					addClassForElement(subElement, TemplateRule.OHHAY_NO_EDIT_CLASS, role);
					removeQbClass(subElement, role);
				}
				// set group type class
				Elements tagElements = element.select("tag");
				Elements labelElements = element.select("label");
				Elements imgElements = element.select("img");
				if ((tagElements.size() > 0 || labelElements.size() > 0)
						&& imgElements.size() > 0) {
					addClassForElement(element, TemplateRule.OHHAY_EDIT_GROUP_LINK_TEXT_IMAGE, role);
				}
				else if (tagElements.size() > 0 || labelElements.size() > 0) {
					addClassForElement(element, TemplateRule.OHHAY_EDIT_GROUP_LINK_TEXT, role);
				}
				else if (imgElements.size() > 0) {
					addClassForElement(element, TemplateRule.OHHAY_EDIT_GROUP_LINK_IMAGE, role);
				}
				PropertyValue propertyValue = null;
				if(propertyValues != null && propertyValues.length > 0)
					propertyValue = propertyValues[0];
				//add tracking html 
				if((propertyValue.getCn114() == 1 || role == ApplicationConstant.ROLE_OWNER) && propertyValue.getCv113() != null && propertyValue.getCv113().trim().length() > 0)
				{
					element.attr(TemplateRule.OHHAY_TRACKING_ATTR_OB, propertyValue.getCv113());
					element.attr(TemplateRule.OHHAY_TRACKING_ATTR_STT, String.valueOf(propertyValue.getCn114()));
				}
				if(propertyValue!= null && propertyValue.getCn114() == 1 && propertyValue.getCv113().trim().length() > 0)
					element.addClass(TemplateRule.OHHAY_TRACKING_CLASS);
			}
			/*
			 * 3) process text element
			 */
			if(element.attr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT) == "" && role == 2){
				/*
				 * 3.1) check case embed iframe
				 */ 
				Elements iframeElements = element.select("iframe");
				if (iframeElements.size() > 0) {
					/*
					 * 3.2) get iframe information
					 */
					Element iframeElement = iframeElements.get(0);
					String iframeHeight = iframeElement.attr("height");
					String iframeWidth = iframeElement.attr("width");
					/*
					 * 3.3) get iframe type (normal iframe or RSS)
					 */
					String iframeType = iframeElement.attr(TemplateRule.OHHAY_DATA_QB_IFRAME_TYPE);
					log.info("---Abstract web edit iframe type:"+iframeType);
					if(iframeType != null && iframeType.equals(TemplateRule.OHHAY_DATA_QB_IFRAME_TYPE_RSS))
					{
						String src = iframeElement.attr("src");
						int f = src.indexOf("link=");
						String rssUrl = src.substring(f+5, src.length());
						element.attr(TemplateRule.OHHAY_IFRAME_SRC_ATTR, rssUrl);
					}
					else
						element.attr(TemplateRule.OHHAY_IFRAME_SRC_ATTR, element.html());
					/*
					 * 3.4) use to create black div has size equal iframe
					 */
					if(iframeHeight!=null && iframeHeight.trim().length() > 0)
						element.attr(TemplateRule.OHHAY_IFRAME_HEIGHT,iframeHeight);
					else
						element.attr(TemplateRule.OHHAY_IFRAME_HEIGHT,"0");
					if(iframeWidth!=null && iframeWidth.trim().length() > 0)
						element.attr(TemplateRule.OHHAY_IFRAME_WIDTH,iframeWidth);
					else
						element.attr(TemplateRule.OHHAY_IFRAME_WIDTH,"0");
					/*
					 * 3.5) replace iframe by black div with content below
					 */
					//element.empty();
					if(iframeType != null && iframeType.equals(TemplateRule.OHHAY_DATA_QB_IFRAME_TYPE_RSS))
						element.append("<span title='"
							+ MVCResourceBundleUtil.getResourceBundle("edit.hover")
							+ "' class='fa fa-pencil btn-edit-iframe'></span>");
					else
						element.append("<span title='"
								+ MVCResourceBundleUtil.getResourceBundle("edit.hover")
								+ "' class='fa fa-pencil btn-edit-iframe'></span>");
					/*
					 * 3.5) add class to copy,edit (date update: 27/12/2014)
					 */
					element.addClass(TemplateRule.OHHAY_EDIT_TEXT_IFRAME_GALLERY_CLASS);
				}
				else
				{
					/*
					 * 3.6) if text null and role is owner show default text
					 */
					if ((element.html() == null || element.html().trim().length() == 0)
							&& role == ApplicationConstant.ROLE_OWNER) {
						element.addClass(TemplateRule.OHHAY_EDIT_TEXT_NULL_CLASS);
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/*
	 * remove class
	 */
	protected void removeQbClass(Element element, int role) {
		if (role == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_TYPE);
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_STYLE_TYPE);
			//element.removeAttr(ApplicationConstant.OHHAY_QB_DATA_CONTENT);//comment tam cho box success bsell chay
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT);
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM);
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_API_NAME);
		}
	}
}
