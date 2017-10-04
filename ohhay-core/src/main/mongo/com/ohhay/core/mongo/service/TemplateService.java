package com.ohhay.core.mongo.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.ohhay.core.mongo.util.QbCriteria;

/**
 * @author ThoaiNH
 *
 */
public interface TemplateService {
	
	/**
	 * @param dbId
	 * OPERATION_SHOPONLINE = 1;
	 * OPERATION_OHHAY = 2;
	 */
	 void setOperation(int dbId);
	/**
	 * @param mClass
	 * @param updateField
	 * @param values
	 * @param scentField
	 * @param qbCriterias
	 * @return
	 */
	<T> int updateOneFieldMultil(int fo100, Class<T> mClass, String updateField, Object values, String scentField, QbCriteria... qbCriterias);
	/**
	 * @param mClass
	 *            : class map collection muon cap nhat
	 * @param id
	 *            : id cua document
	 * @param updateField
	 *            : object field muon cap nhat
	 * @param values
	 *            :gia tri muon cap nhat
	 * @param scentField
	 *            : truong luu vet ngay cap nhat
	 * @return
	 */
	<T> int updateOneField(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField);

	/**
	 * @param mClass
	 *            : class map collection muon cap nhat
	 * @param id
	 *            : id cua document
	 * @param updateField
	 *            : array field muon cap nhat
	 * @param values
	 *            :gia tri muon cap nhat
	 * @param scentField
	 *            : truong luu vet ngay cap nhat
	 * @return
	 */
	<T> int updateOneFieldArray(int fo100, Class<T> mClass, int id, String updateField, Object[] values, String scentField);

	/**
	 * @param mClass
	 *            : class map collection muon cap nhat
	 * @param conditionField
	 *            : field dieu kien
	 * @param conditionValue
	 *            : gia tri dieu kien
	 * @param updateField
	 *            : field can cap nhat
	 * @param values
	 *            : gia tri can cap nhat
	 * @param scentField
	 *            : truong luu vet ngay cap nhat
	 * @return
	 */
	<T> int updateOneField(int fo100, Class<T> mClass, String conditionField, Object conditionValue, String updateField, Object values, String scentField);

	/**
	 * @param webJsonString
	 *            : json cua document
	 * @param colectionName
	 *            : ten collection
	 * @return
	 */
	int insertWebStructure(int fo100, String webJsonString, String colectionName);

	/**
	 * @param id
	 *            : _id cua document
	 * @param mClass
	 *            : class collection
	 * @return
	 */
	<T> T findDocumentById(int fo100, int id, Class<T> mClass);
	/**
	 * @param id
	 * @param mClass
	 * @param fieldInclude: chi lay ve 1 field nay
	 * @return
	 */
	<T> T findDocumentById(int fo100, int id, Class<T> mClass, String fieldInclude);
	/**
	 * @param conditionField
	 *            : field dieu kieu tim kiem
	 * @param conditionValue
	 *            : gia tri can tim
	 * @param mClass
	 *            : class colection
	 * @return
	 */
	<T> T findDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	/**
	 * @param mClass
	 * @param fieldNotInclude: field k muon lay ve
	 * @param qbCriterias
	 * @return
	 */
	<T> T findDocument(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias);
	/**
	 * @param mClass
	 * @param fieldNotInclude: field want to exclude from return data
	 * @param qbCriterias
	 * @return
	 */
	<T> List<T> findDocuments(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias);
	/**
	 * @param mClass
	 * @param qbCriterias
	 * @return
	 */
	<T> List<T> findDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias);

	/**
	 * @param mClass
	 * @param sortField
	 * @param sortType
	 * @param criteriaAnd
	 * @param qbCriterias
	 * @return
	 */
	<T> List<T> findDocumentsOr(int fo100, Class<T> mClass,String sortField, Direction sortType, QbCriteria criteriaAnd, QbCriteria... qbCriterias);

	
	/**
	 * @param object
	 * @param colectionName
	 * @return
	 */
	<T> int saveDocument(int fo100, T object, String colectionName);

	
	/**
	 * @param mClass
	 *            : class return
	 * @param conditionField
	 *            : condition field
	 * @param listInc
	 *            : list inc condition
	 * @return
	 */
	<T, P> List<T> findDocumentsInc(int fo100, Class<T> mClass, String conditionField, List<P> listInc, QbCriteria... qbCriterias);

	/**
	 * @param id
	 * @param mClass
	 * @return
	 */
	<T> int removeDocumentById(int fo100, int id, Class<T> mClass);

	/**
	 * @param mClass
	 * @param qbCriterias
	 * @return
	 */
	<T> int removeDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	/**
	 * @param mClass
	 * @param updateField
	 * @param values
	 * @param scentField
	 * @param qbCriterias
	 * @return
	 */
	<T> int updateOneField(int fo100, Class<T> mClass, String updateField, Object values, String scentField, QbCriteria... qbCriterias);
	/**
	 * @param mClass
	 * @param id
	 * @param updateField
	 * @param values
	 * @param scentField
	 * @return
	 * @throws Exception 
	 */
	<T> T findAndModify(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField) throws Exception;
	 /**
	 * @param fo100
	 * @param id
	 * @param mClass
	 * @return
	 */
	<T> int removeRealDocumentById(int fo100, int id, Class<T> mClass);
	/**
	 * @param fo100
	 * @param mClass
	 * @param qbCriterias
	 * @return
	 */
	<T> int removeRealDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	/**
	 * created 10/11/2016
	 * @param fo100
	 * @param mClass
	 * @param sortField
	 * @param sortType
	 * @param offset
	 * @param limit
	 * @param qbCriterias
	 * @return
	 */
	<T> List<T> findDocuments(int fo100, Class<T> mClass, String sortField, Direction sortType, int offset, int limit, QbCriteria... qbCriterias);
	/**
	 * @param fo100
	 * @param mClass
	 * @param maxField
	 * @param qbCriterias
	 * @return
	 */
	<T> T findDocumentWithMaximunCriteria(int fo100, Class<T> mClass, String maxField, QbCriteria... qbCriterias);
	/**
	 * @param: fo100
	 * @param: mClass
	 * @param: qbCriterias
	 * @return
	 */
	<T> int count(int fo100, Class<T> mClass, QbCriteria... qbCriterias);
	/**
	 * @return
	 */
	boolean isAccessDBcentPiepme();
	/**
	 * @param accessDBcent
	 */
	void setAccessDBcentPiepme(boolean accessDBcent);
}
