package com.ohhay.web.core.load.util;

import java.util.List;

import org.jsoup.nodes.Element;

import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;

/**
 * @author ThoaiNH
 * create 19/11/2014
 * sub container interface
 */
public interface ISubContainer {
	void mapElementToBox(Element element, List<C900MG> listC900mgs, WebCreateParam webCreateParam);
	void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key, int fo100) throws Exception;
	void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key, WebRequestParam webRequestParam) throws Exception;
}
