package com.ohhay.web.core.element;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.TemplateRule;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractElement;
import com.ohhay.web.core.utils.WebCreateParam;

/**
 * @author ThoaiNH
 * create 20/10/2014
 * js percent element
 */
@Service
public class PercentElement extends AbstractElement{

	@Override
	public void mapElementToBox(Element element, C900MG c900mg, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		c900mg.setIndexProperty(-1);
		element.attr(TemplateRule.OHHAY_DATA_QB_TYPE_PERCENT,c900mg.getCv905());
		addClassForElement(element,TemplateRule.OHHAY_EDIT_PERCENT_CLASS, webCreateParam.getRole());
	}

}
