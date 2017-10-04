package com.ohhay.web.core.element;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractElement;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.utils.WebCreateParam;

/**
 * @author ThoaiNH
 * create 22/10/2014
 * update: 30/06/2015: add position css
 */
@Service
public class TextElement extends AbstractElement {
	private static Logger log = Logger.getLogger(TextElement.class);
	@Override
	public void mapElementToBox(Element element, C900MG c900mg, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		/*
		 * 1) load property bundle from C100
		 */
		PropertyValue c110Value = webCreateParam.getMapProperties().get(c900mg.getPc900());
		if (c110Value != null) {
			c900mg.setCv905(c110Value.getValue());
			c900mg.setIndexProperty(c110Value.getIndex());
		}
		else {
			log.error("PROPERTIES KEY NOT FOUND:"+ c900mg.getPc900());
			c900mg.setIndexProperty(-1);
		}
		/*
		 * 2) map content to element
		 */
		Document document = Jsoup.parse(c900mg.getCv905());
		/*
		 * 2.1) check case embed iframe
		 */ 
		Elements iframeElements = document.select("iframe");
		if (iframeElements.size() > 0 && webCreateParam.getRole() == ApplicationConstant.ROLE_OWNER) {
			/*
			 * 2.2) get iframe information
			 */
			Element iframeElement = iframeElements.get(0);
			String iframeHeight = iframeElement.attr("height");
			String iframeWidth = iframeElement.attr("width");
			/*
			 * 2.3) get iframe type (normal iframe or RSS)
			 */
			String iframeType = iframeElement.attr(TemplateRule.OHHAY_DATA_QB_IFRAME_TYPE);
			log.info("---Text element iframe type:"+iframeType);
			if(iframeType != null && iframeType.equals(TemplateRule.OHHAY_DATA_QB_IFRAME_TYPE_RSS))
			{
				//get source RSS to show when edit
				String src = iframeElement.attr("src");
				int f = src.indexOf("link=");
				String rssUrl = src.substring(f+5, src.length());
				element.attr(TemplateRule.OHHAY_IFRAME_SRC_ATTR, rssUrl);
			}
			else
				//show iframe tag when edit
				element.attr(TemplateRule.OHHAY_IFRAME_SRC_ATTR, c900mg.getCv905());
			/*
			 * 2.4) use to create black div has size equal iframe
			 */
			if(iframeHeight!=null && iframeHeight.trim().length() > 0)
				element.attr(TemplateRule.OHHAY_IFRAME_HEIGHT,iframeHeight);
			else
				element.attr(TemplateRule.OHHAY_IFRAME_HEIGHT,"0");
			if(iframeWidth!=null && iframeWidth.trim().length() > 0)
				element.attr(TemplateRule.OHHAY_IFRAME_WIDTH,iframeWidth);
			else
				element.attr(TemplateRule.OHHAY_IFRAME_WIDTH,"0");
			element.addClass(TemplateRule.OHHAY_EDIT_TEXT_IFRAME_CLASS);
			/*
			 * 2.5) replace iframe by black div with content below
			 */
			element.append(c900mg.getCv905());
			element.append("<span title='"
						+ MVCResourceBundleUtil.getResourceBundle("edit.hover")
						+ "' class='fa fa-pencil btn-edit-iframe'></span>");
		}
		else {
			element.append(c900mg.getCv905());
			/*
			 * 3) if text null and role is owner show default text
			 */
			if ((c900mg.getCv905() == null || c900mg.getCv905().trim().length() == 0)
					&& webCreateParam.getRole() == ApplicationConstant.ROLE_OWNER) {
				element.addClass(TemplateRule.OHHAY_EDIT_TEXT_NULL_CLASS);
			}
			/*
			 * 4) add class to edit
			 */
			addClassForElement(element, TemplateRule.OHHAY_EDIT_TEXT_CLASS, webCreateParam.getRole());
			/*
			 * 5) add css position
			 */
			if(c110Value != null && c110Value.getCv115() != null)
			{
				String style = "";
				if(element.attr("style") != null)
					style = element.attr("style");
				element.attr("style",style+c110Value.getCv115());
				addClassForElement(element, TemplateRule.OHHAY_CHANGEPOSITION_CLASS, ApplicationConstant.ROLE_OWNER);
			}
			/*
			 * 6) add more info to html
			 */
			if(c110Value != null && c110Value.getCv116() != null){
				element.attr(TemplateRule.OHHAY_ADDED_INFO,c110Value.getCv116());
			}
		}
	}
}
