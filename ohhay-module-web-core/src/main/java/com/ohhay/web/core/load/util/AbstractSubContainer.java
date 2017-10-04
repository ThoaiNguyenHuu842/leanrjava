package com.ohhay.web.core.load.util;

import org.jsoup.nodes.Element;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.TemplateRule;

/**
 * @author ThoaiNH 
 * create 11/11/2014
 * abstract for sub container type
 */
public abstract class AbstractSubContainer extends AbstractWebBase implements
		ISubContainer {
	/*
	 * remove attr if viewer
	 */
	protected void afterProccessElementOfCV906(Element element, int role) {
		if (role == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT);
			// for template faiclio
			element.removeAttr("data-qb-accept-edit");
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM);
			element.removeAttr("data-qb-accept-edit-item");
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_API_NAME);
			element.removeAttr("data-qb-api-name");
		}
	}

	/*
	 * afterProccessCV906
	 */
	protected void afterProccessCV906(Element element, int role) {
		if (role == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_TYPE);
			element.removeAttr(TemplateRule.OHHAY_QB_DATA_CONTENT);
		}
	}

}
