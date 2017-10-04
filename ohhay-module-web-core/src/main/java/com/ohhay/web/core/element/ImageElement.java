package com.ohhay.web.core.element;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mysql.dao.C100CentDao;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractElement;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.utils.WebCreateParam;

/**
 * @author ThoaiNH
 * create 19/10/2014
 * img element
 */
@Service
public class ImageElement extends AbstractElement {
	private static Logger log = Logger.getLogger(ImageElement.class);
	@Override
	public void mapElementToBox(Element element, C900MG c900mg, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		// load property bundle from C100
		PropertyValue c110Value = webCreateParam.getMapProperties().get(c900mg
				.getPc900());
		if (c110Value != null) {
			c900mg.setCv905(c110Value.getValue());
			c900mg.setIndexProperty(c110Value.getIndex());
		}
		else {
			log.error("ERROR: PROPERTIES KEY NOT FOUND:"
					+ c900mg.getPc900());
			c900mg.setIndexProperty(-1);
		}
		// parse dom
		// xoa ky tu "/" neu ky tu nay nam o dau tien
		StringBuilder builder = new StringBuilder(c900mg.getCv905());
		String src = null;
		if (builder.indexOf("/") == 0)
			src = builder.replace(0, 1, "").toString();
		else
			src = builder.toString();
		element.attr("src", src);
		// add class de edit
		addClassForElement(element, TemplateRule.OHHAY_EDIT_IMAGE_CLASS, webCreateParam.getRole());
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
		 * 6) link image
		 */
		if(c110Value != null && c110Value.getCv117() != null)
			element.attr(TemplateRule.OHHAY_IMGS_LINK, c110Value.getCv117());
	}

}
