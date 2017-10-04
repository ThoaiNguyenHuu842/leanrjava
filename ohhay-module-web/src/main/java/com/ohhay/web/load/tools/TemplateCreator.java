package com.ohhay.web.load.tools;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.ohhay.core.constant.TemplateRule;

/**
 * @author ThoaiNH
 * ket noi template tren server de lay ra document
 *
 */
public class TemplateCreator {
	private static Logger log = Logger.getLogger(TemplateCreator.class);
	private String templatePath;
	private Document document;
	public static TemplateCreator getInstance(String templatePath) {
		return new TemplateCreator(templatePath);
	}
	private TemplateCreator(String templatePath) {
		super();
		this.templatePath = templatePath;
		run();
	}

	private void run() {
		try {
			// tao landing page
			String landingPage = TemplateRule.TEMPLATE_ROOT_PATH
					+ templatePath + TemplateRule.TEMPLATE_INDEX_PAGE;
			log.info("--landing:" + landingPage);
			document = Jsoup.connect(landingPage).timeout(15000).get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
}
