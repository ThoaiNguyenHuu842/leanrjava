package com.ohhay.web.core.element;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractElement;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.utils.WebCreateParam;

/**
 * @author ThoaiNH
 * create 09/08/2014
 * GROUP: VISIBLEABLE ELEMENT
 * able to change visible status element
 *
 */
@Service
public class GroupElement extends AbstractElement {
	private static Logger log = Logger.getLogger(GroupElement.class);
	@Override
	public void mapElementToBox(Element element, C900MG c900mg, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		if (c900mg.getVisible() != -1 || webCreateParam.getRole() == 2) {
			try {
				/*
				 * 1) load property bundle from C100
				 */
				PropertyValue c110Value = webCreateParam.getMapProperties().get(c900mg.getPc900());
				if (c110Value != null) {
					c900mg.setCv906(c110Value.getValue());
					c900mg.setIndexProperty(c110Value.getIndex());
				}
				else {
					log.error("PROPERTIES KEY NOT FOUND:"+ c900mg.getPc900());
					c900mg.setIndexProperty(-1);
				}
				log.info("----cv906 tracking:"+ c900mg.getCv906());
				log.info("----cv906 tracking cv113:"+ c110Value.getCv113());
				Document document = Jsoup.parse(c900mg.getCv906());
				/*
				 * 2) select editable elements
				 */
				Elements elementsEditAble = document.body().select("["
								+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT
								+ "~=]");
				// for template facilio
				if (elementsEditAble == null || elementsEditAble.size() == 0) {
					elementsEditAble = document.body().select("["
							+ "data-qb-accept-edit" + "~=]");
				}
				/*
				 * 3) process editable elements
				 */
				if (elementsEditAble.size() > 0) {
					for (Element childElement : elementsEditAble) {
						if (childElement.hasClass(TemplateRule.OHHAY_NO_EDIT_CLASS)) {
							//truong hop nhung element nam trong elemnt a -> khong edit nhung element nay
						}
						else {
							processElementGroup(childElement, c900mg.getSuperID(), webCreateParam.getRole(), c110Value);
						}
					}
				}
				String kqHtml = document.body().html();
				element.append(kqHtml);
				/*
				 * 4) add class to copy,edit (date update: 27/12/2014)
				 */
				//addClassForElement(element, TemplateRule.OHHAY_EDIT_GALLERY_ITEM_CLASS, webCreateParam.getRole());
				addFc850ToGalleryItem(element, String.valueOf(c900mg.getFc850()), webCreateParam.getRole());
				addIdToElement(element, c900mg.getSuperID(), webCreateParam.getRole());
				addAttr(element, TemplateRule.OHHAY_EDIT_BOX_VISIBLE, String.valueOf(c900mg.getVisible()), webCreateParam.getRole());
				/*
				 * 5) add css postion update: 30/06/2015
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
