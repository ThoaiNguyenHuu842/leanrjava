package com.ohhay.web.core.load.util;

import org.jsoup.nodes.Element;

import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.utils.WebCreateParam;

/**
 * @author ThoaiNH
 * create 19/11/2014
 * element mapper 
 */
public interface IElementCreator {
	/**
	 * @param element: element lay duoc tu c920
	 * @param c900: c900 can map cua c920
	 * @param role
	 */
	void mapElementToBox(Element element, C900MG c900mg, WebCreateParam webCreateParam);
}
