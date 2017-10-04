package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.dao.TemplateDao;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;

@Service(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
@Scope("prototype")
public class TemplateServiceImpl implements TemplateService {
	private static Logger log = Logger.getLogger(TemplateServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_TEMPLATEDAO)
	TemplateDao dao;

	@Override
	public <T> int updateOneField(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField) {
		// TODO Auto-generated method stub
		log.info("---updateOneField:"+mClass+","+id+","+updateField+","+values+","+scentField);
		return dao.updateOneField(fo100, mClass, id, updateField, values, scentField);
	}

	@Override
	public <T> int updateOneFieldArray(int fo100, Class<T> mClass, int id, String updateField, Object[] values, String scentField) {
		// TODO Auto-generated method stub
		return dao.updateOneFieldArray(fo100, mClass, id, updateField, values, scentField);
	}

	@Override
	public int insertWebStructure(int fo100, String webJsonString, String colectionName) {
		// TODO Auto-generated method stub
		return dao.insertWebStructure(fo100, webJsonString, colectionName);
	}

	@Override
	public <T> int updateOneField(int fo100, Class<T> mClass, String conditionField, Object conditionValue, String updateField, Object values, String scentField) {
		// TODO Auto-generated method stub
		return dao.updateOneField(fo100, mClass, conditionField, conditionValue, updateField, values, scentField);
	}

	@Override
	public <T> T findDocumentById(int fo100, int id, Class<T> mClass) {
		// TODO Auto-generated method stub
		log.info("---mongo findDocumentById:"+id+","+mClass);
		return dao.findDocumentById(fo100, id, mClass);
	}

	@Override
	public <T> int saveDocument(int fo100, T object, String colectionName) {
		// TODO Auto-generated method stub
		log.info("---mongo saveDocument:" +","+ fo100 +","+ object +","+ colectionName);
		return dao.saveDocument(fo100, object, colectionName);
	}

	@Override
	public <T> T findDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocument(fo100, mClass, qbCriterias);
	}

	@Override
	public <T> List<T> findDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocuments(fo100, mClass, qbCriterias);
	}

	@Override
	public <T, P> List<T> findDocumentsInc(int fo100, Class<T> mClass, String conditionField, List<P> listInc, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocumentsInc(fo100, mClass, conditionField, listInc, qbCriterias);
	}

	@Override
	public <T> int removeDocumentById(int fo100, int id, Class<T> mClass) {
		// TODO Auto-generated method stub
		return dao.removeDocumentById(fo100, id, mClass);
	}

	@Override
	public <T> int removeDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.removeDocuments(fo100, mClass, qbCriterias);
	}

	@Override
	public <T> int updateOneField(int fo100, Class<T> mClass, String updateField, Object values, String scentField, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.updateOneField(fo100, mClass, updateField, values, scentField, qbCriterias);
	}

	@Override
	public <T> T findDocumentById(int fo100, int id, Class<T> mClass, String fieldInclude) {
		// TODO Auto-generated method stub
		return dao.findDocumentById(fo100, id, mClass, fieldInclude);
	}

	@Override
	public <T> T findAndModify(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAndModify(fo100, mClass, id, updateField, values, scentField);
	}

	@Override
	public void setOperation(int dbId) {
		// TODO Auto-generated method stub
		dao.setOperation(dbId);
	}

	@Override
	public <T> int updateOneFieldMultil(int fo100, Class<T> mClass, String updateField, Object values, String scentField, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.updateOneFieldMultil(fo100, mClass, updateField, values, scentField, qbCriterias);
	}

	@Override
	public <T> T findDocument(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocument(fo100, mClass, fieldNotInclude, qbCriterias);
	}

	@Override
	public <T> List<T> findDocuments(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocuments(fo100, mClass, fieldNotInclude, qbCriterias);
	}

	@Override
	public <T> int removeRealDocumentById(int fo100, int id, Class<T> mClass) {
		// TODO Auto-generated method stub
		return dao.removeRealDocumentById(fo100, id, mClass);
	}

	@Override
	public <T> int removeRealDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.removeRealDocument(fo100, mClass, qbCriterias);
	}

	@Override
	public <T> List<T> findDocuments(int fo100, Class<T> mClass, String sortField, Direction sortType, int offset, int limit, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocuments(fo100, mClass, sortField, sortType, offset, limit, qbCriterias);
	}

	@Override
	public boolean isAccessDBcentPiepme() {
		// TODO Auto-generated method stub
		return dao.isAccessDBcentPiepme();
	}

	@Override
	public void setAccessDBcentPiepme(boolean accessDBcent) {
		// TODO Auto-generated method stub
		dao.setAccessDBcentPiepme(accessDBcent);
	}

	@Override
	public <T> T findDocumentWithMaximunCriteria(int fo100, Class<T> mClass, String maxField, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocumentWithMaximunCriteria(fo100, mClass, maxField, qbCriterias);
	}

	@Override
	public <T> int count(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.count(fo100, mClass, qbCriterias);
	}

	@Override
	public <T> List<T> findDocumentsOr(int fo100, Class<T> mClass,String sortField, Direction sortType, QbCriteria criteriaAnd, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		return dao.findDocumentsOr(fo100, mClass,sortField,sortType, criteriaAnd, qbCriterias);
	}
	
	
}
