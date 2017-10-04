package com.ohhay.web.core.element;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractElement;
import com.ohhay.web.core.load.util.PropertyValue;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.zuss.ZussUtils;

/**
 * @author ThoaiNH
 * create 10/10/2014
 * @deprecated
 */
@Service
public class BackgroundElement extends AbstractElement {
	private static Logger log = Logger.getLogger(BackgroundElement.class);
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
			log.error("PROPERTIES KEY NOT FOUND:"
					+ c900mg.getPc900());
			c900mg.setIndexProperty(-1);
		}
		// parse dom
		log.info("----user custom color:" + c900mg.getCv905());
		if(ApplicationHelper.validateRegex(c900mg.getCv905(), RegexConstant.COLOR_PATTERN))
			ZussUtils.M_COLOR = c900mg.getCv905();
		else
			ZussUtils.M_COLOR = null;
		element.attr("color", c900mg.getCv905());
		addClassForElement(element, TemplateRule.OHHAY_EDIT_BG_COLOR, webCreateParam.getRole());
	}
}
