package com.ohhay.web.load.tools;

import org.apache.log4j.Logger;

import com.ohhay.core.constant.TemplateRule;

/**
 * @author ThoaiNH
 * 23/10/2014
 * map cac link image, css, jquery dung voi thu vien css
 */
public class LinkMapper {
	private static Logger log = Logger.getLogger(LinkMapper.class);
	private StringBuilder stringBuilder;
	private String templatePath;
	private String userServerLinkValue;
	private int templateId;
	public LinkMapper(StringBuilder stringBuilder, String templatePath,String userServerLinkValue, int templateId) {
		super();
		this.stringBuilder = stringBuilder;
		this.templatePath = templatePath;
		this.userServerLinkValue = userServerLinkValue;
		this.templateId = templateId;
	}
	public static String mapLink(String linkUrl, String userServerLinkValue){
		StringBuilder builder = new StringBuilder(linkUrl);
		replaceElements(builder, TemplateRule.OHHAY_SERVER_LINK,
				userServerLinkValue);
		return builder.toString();
	}
	/**
	 *  map html key link to real link
	 */
	public void doMap() {
		// thay the base-link
		StringBuilder templateBuilder = new StringBuilder(
				TemplateRule.TEMPLATE_ROOT_PATH + templatePath);
		replaceElements(stringBuilder, TemplateRule.OHHAY_BASE_LINK,
				templateBuilder.toString());
		// link hinh tren server
		replaceElements(stringBuilder, TemplateRule.OHHAY_SERVER_LINK,
				userServerLinkValue);
		// replate ky tu dac biet
		replaceElements(stringBuilder, TemplateRule.OHHAY_2CHAM,
				TemplateRule.OHHAY_2CHAM_VALUE);
		//replace domain line
		replaceElements(stringBuilder, TemplateRule.OHHAY_DOMAIN_LINK,
				TemplateRule.TEMPLATE_ROOT_DOMAIN_PATH);
		replaceElements(stringBuilder, TemplateRule.OHHAY_STYLE_LINK,
				"/Style01/NN02");
		replaceElements(stringBuilder, TemplateRule.OHHAY_STYLE_LINK,
				"/Style01/NN02");
		//map template a900 link
		replaceElements(stringBuilder, TemplateRule.OHHAY_TEMPLATE_LINK,TemplateRule.TEMPLATE_ROOT_PATH_A900+templateId+"/");
	}

	/*
	 * replace noi dung theo dung url cua template
	 */
	private static void replaceElements(StringBuilder builder, String oldString,
			String newString) {
		int index = 0;
		int lenght = oldString.length();
		log.info(builder.indexOf(oldString));
		while ((index = builder.indexOf(oldString)) >= 0) {
			builder.replace(index, index + lenght, newString);
		}
	}
}
