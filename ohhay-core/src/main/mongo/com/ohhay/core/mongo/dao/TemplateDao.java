package com.ohhay.core.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.ohhay.core.mongo.util.QbCriteria;

/**
 * @author ThoaiNH
 * create 10/10/2014
 * update 12/09/2015 - call method remove will set scent field DATE_DELETE 
 * template using fo all mongodb collection
 */
public interface TemplateDao {
	<T> int updateOneFieldMultil(int fo100, Class<T> mClass, String updateField, Object values, String scentField, QbCriteria... qbCriterias);
	<T> int updateOneField(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField);
	<T> int updateOneField(int fo100, Class<T> mClass, String updateField, Object values, String scentField, QbCriteria... qbCriterias);
	<T> int updateOneFieldArray(int fo100, Class<T> mClass, int id, String updateField, Object[] values, String scentField);
	<T> int updateOneField(int fo100, Class<T> mClass, String conditionField, Object conditionValue, String updateField, Object values, String scentField);
	int insertWebStructure(int fo100, String webJsonString, String colectionName);
	<T> T findDocumentById(int fo100, int id, Class<T> mClass);
	<T> T findDocumentById(int fo100, int id, Class<T> mClass, String fieldInclude);
	<T> T findDocument(int fo100, Class<T> mClass,QbCriteria... qbCriterias);
	<T> T findDocument(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias);
	<T> List<T> findDocuments(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias);
	<T> int saveDocument(int fo100, T object, String colectionName);
	<T> List<T> findDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	<T> List<T> findDocumentsOr(int fo100, Class<T> mClass,String sortField, Direction sortType,QbCriteria criteriaAnd, QbCriteria... qbCriterias);
	<T> List<T> findDocuments(int fo100, Class<T> mClass, String sortField, Direction sortType, int offset, int limit, QbCriteria... qbCriterias);
	<T,P>  List<T> findDocumentsInc(int fo100, Class<T> mClass,String conditionField, List<P> listInc, QbCriteria... qbCriterias);
	<T> int removeDocumentById(int fo100, int id, Class<T> mClass);
	<T> int removeRealDocumentById(int fo100, int id, Class<T> mClass);
	<T> int removeRealDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	<T> int removeDocuments(int fo100, Class<T> mClass,QbCriteria... qbCriterias);
	<T> T findAndModify(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField) throws Exception;
	<T> T findDocumentWithMaximunCriteria(int fo100, Class<T> mClass, String maxField, QbCriteria... qbCriterias);
	<T> int count(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	void setOperation(int dbId);
	boolean isAccessDBcentPiepme();
	void setAccessDBcentPiepme(boolean accessDBcent);
}
