package com.ohhay.web.core.api.service;

import java.util.List;

/**
 * @author ThoaiNH
 * create 09/11/2014
 * o!hay web child service
 */
public interface WebChildService {
	<T> T findOneWebChild(Class<T> mClass, int id, int fo100);
	<T> List<T> getListLitteWeb(Class<T> mClass, int parentId, String extendType, int visible, int fo100);
	int copyWebChild(int fc500, int fo100, String ov101);
	int deleteWebChild(int fc500, int fo100, String ov101);
}
