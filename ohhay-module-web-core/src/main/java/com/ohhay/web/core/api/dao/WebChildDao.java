package com.ohhay.web.core.api.dao;

import java.util.List;

public interface WebChildDao {
	<T> T findOneWebChild(Class<T> mClass, int id, int fo100);
	<T> List<T> getListLitteWeb(Class<T> mClass, int parentId, String extendType, int visible, int fo100);
}
