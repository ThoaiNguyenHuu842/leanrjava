package com.ohhay.web.core.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.web.core.entities.mongo.web.A950MG;

/**
 * @author Phongdt
 * - create 29/7/2015 -  - file nay dung o thuc hien cac tac vu vs Category gom them va` giam tong so luong Category
 */
public interface A950MGDao {
	int incCategory(int id);

	int decCategory(int id);
	
	List<A950MG> loadListA950(String type);
	/*
	 * public <T, P> List<T> findDocumentsInc(int fo100, Class<T> mClass, String conditionField, List<P> listInc) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(conditionField).in(listInc));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			return getMongoOperations(dbType, fo100).find(query2,mClass);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	 */
}
