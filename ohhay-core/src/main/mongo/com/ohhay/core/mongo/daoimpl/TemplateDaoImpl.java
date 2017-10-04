package com.ohhay.core.mongo.daoimpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.dao.TemplateDao;
import com.ohhay.core.mongo.util.QbCriteria;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_TEMPLATEDAO)
@Scope("prototype")
public class TemplateDaoImpl extends QbMongoDaoSupport implements TemplateDao {
	private static Logger log = Logger.getLogger(TemplateDaoImpl.class);
	private int dbType = ApplicationConstant.DB_TYPE_OHHAY;

	@Override
	public <T> int updateOneFieldArray(int fo100, Class<T> mClass, int id, String updateField, Object[] values,
			String scentField) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			Update update = new Update();
			update.set(updateField, values);
			// luu vet thoi gian cap nhat
			update.set(scentField, new Date());
			return getMongoOperations(dbType, fo100).updateFirst(query, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int updateOneField(int fo100, Class<T> mClass, int id, String updateField, Object values,
			String scentField) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			Update update = new Update();
			update.set(updateField, values);
			// luu vet thoi gian
			if (scentField != null) {
				update.set(scentField, new Date());
			}
			return getMongoOperations(dbType, fo100).upsert(query, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int updateOneField(int fo100, Class<T> mClass, String conditionField, Object conditionValue,
			String updateField, Object values, String scentField) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(conditionField).is(conditionValue));
			query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			Update update = new Update();
			update.set(updateField, values);
			// luu vet thoi gian
			if (scentField != null) {
				update.set(scentField, new Date());
			}
			return getMongoOperations(dbType, fo100).upsert(query, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertWebStructure(int fo100, String webJsonString, String colectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject dbObject = (DBObject) JSON.parse(webJsonString);
			getMongoOperations(dbType, fo100).save(dbObject, colectionName);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> T findDocumentById(int fo100, int id, Class<T> mClass) {
		// TODO Auto-generated method stub
		try {
			log.info("---findDocumentById:" + id + "," + mClass);
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			T object = getMongoOperations(dbType, fo100).findOne(query2, mClass);
			return object;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> int saveDocument(int fo100, T object, String colectionName) {
		// TODO Auto-generated method stub
		try {
			getMongoOperations(dbType, fo100).save(object, colectionName);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> T findDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			for (QbCriteria qbCriteria : qbCriterias) {
				// not equal statement
				if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
					query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
				// equal statement
				else
					query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
			}
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			T object = getMongoOperations(dbType, fo100).findOne(query2, mClass);
			return object;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> findDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			List<T> list = null;
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			} 
			list = getMongoOperations(dbType, fo100).find(query2, mClass);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T, P> List<T> findDocumentsInc(int fo100, Class<T> mClass, String conditionField, List<P> listInc,
			QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(conditionField).in(listInc));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			return getMongoOperations(dbType, fo100).find(query2, mClass);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> int removeDocumentById(int fo100, int id, Class<T> mClass) {
		// TODO Auto-generated method stub
		try {
			log.info("---removeDocumentById:" + id + "," + mClass);
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			Update update = new Update();
			update.set(QbMongoFiledsName.DATE_DELETE, new Date());
			return getMongoOperations(dbType, fo100).updateFirst(query2, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int removeDocuments(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder builder = new StringBuilder("---removeDocuments:" + mClass);
			Query query2 = new Query();
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
					builder.append("," + qbCriteria);
				}
			}
			Update update = new Update();
			update.set(QbMongoFiledsName.DATE_DELETE, new Date());
			return getMongoOperations(dbType, fo100).updateMulti(query2, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int updateOneField(int fo100, Class<T> mClass, String updateField, Object values, String scentField,
			QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			Update update = new Update();
			update.set(updateField, values);
			// luu vet thoi gian
			if (scentField != null) {
				update.set(scentField, new Date());
			}
			return getMongoOperations(dbType, fo100).updateFirst(query2, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> T findDocumentById(int fo100, int id, Class<T> mClass, String fieldInclude) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			if (fieldInclude != null)
				query2.fields().include(fieldInclude).include(QbMongoFiledsName.ID);
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			T object = getMongoOperations(dbType, fo100).findOne(query2, mClass);
			return object;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> T findAndModify(int fo100, Class<T> mClass, int id, String updateField, Object values, String scentField)
			throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
		query.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
		Update update = new Update();
		update.set(updateField, values);
		// luu vet thoi gian
		if (scentField != null)
			update.set(scentField, new Date());
		T t = getMongoOperations(dbType, fo100).findAndModify(query, update,
				new FindAndModifyOptions().returnNew(false), mClass);
		return t;
	}

	public void setOperation(int dbId) {
		this.dbType = dbId;
	}

	@Override
	public <T> int updateOneFieldMultil(int fo100, Class<T> mClass, String updateField, Object values,
			String scentField, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			Update update = new Update();
			update.set(updateField, values);
			// luu vet thoi gian
			if (scentField != null) {
				update.set(scentField, new Date());
			}
			return getMongoOperations(dbType, fo100).updateMulti(query2, update, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> T findDocument(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocument:" + mClass + "," + fieldNotInclude);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			if (fieldNotInclude != null)
				query2.fields().exclude(fieldNotInclude);
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			T object = getMongoOperations(dbType, fo100).findOne(query2, mClass);
			return object;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> findDocuments(int fo100, Class<T> mClass, String fieldNotInclude, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass + "," + fieldNotInclude);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			if (fieldNotInclude != null)
				query2.fields().exclude(fieldNotInclude);
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			return getMongoOperations(dbType, fo100).find(query2, mClass);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> int removeRealDocumentById(int fo100, int id, Class<T> mClass) {
		// TODO Auto-generated method stub
		try {
			log.info("---removeRealDocumentById:" + id + "," + mClass);
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(id));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			return getMongoOperations(dbType, fo100).remove(query2, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> int removeRealDocument(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---removeRealDocument:" + mClass);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			return getMongoOperations(dbType, fo100).remove(query2, mClass).getN();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public <T> List<T> findDocuments(int fo100, Class<T> mClass, String sortField, Direction sortType, int offset,
			int limit, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass);
			logString.append(sortField + "," + sortType + "," + offset + "," + limit);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			List<T> list = null;
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
				query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			}
			// sort predicate
			query2.with(new Sort(sortType, sortField));
			// skip limit
			query2.skip(offset);
			query2.limit(limit);
			list = getMongoOperations(dbType, fo100).find(query2, mClass);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isAccessDBcentPiepme() {
		return this.isAccessDBcent();
	}

	@Override
	public void setAccessDBcentPiepme(boolean accessDBcent) {
		this.setAccessDBcent(accessDBcent);
	}

	@Override
	public <T> T findDocumentWithMaximunCriteria(int fo100, Class<T> mClass, String maxField,
			QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			for (QbCriteria qbCriteria : qbCriterias) {
				query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
			}
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			query2.with(new Sort(Sort.Direction.DESC, maxField));
			query2.limit(1);
			T object = getMongoOperations(dbType, fo100).findOne(query2, mClass);
			return object;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> int count(int fo100, Class<T> mClass, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			if (qbCriterias != null && qbCriterias.length > 0) {
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					if (qbCriteria.getType() != null && qbCriteria.getType().equals(qbCriteria.TYPE_NE))
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).ne(qbCriteria.getValue()));
					// equal statement
					else
						query2.addCriteria(Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue()));
				}
			}
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			long count = 0;
			count = getMongoOperations(dbType, fo100).count(query2, mClass);
			return (int) count;
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
		}
		return 0;
	}

	@Override
	public <T> List<T> findDocumentsOr(int fo100, Class<T> mClass, String sortField, Direction sortType, QbCriteria criteriaAnd, QbCriteria... qbCriterias) {
		// TODO Auto-generated method stub
		try {
			StringBuilder logString = new StringBuilder("---findDocuments:" + mClass);
			for (QbCriteria qbCriteria : qbCriterias)
				logString.append("," + qbCriteria);
			log.info(logString);
			Query query2 = new Query();
			List<T> list = null;
			if (qbCriterias != null && qbCriterias.length > 0) {
				int index = 0;
				Criteria[] orCriteria = new Criteria[qbCriterias.length];
				for (QbCriteria qbCriteria : qbCriterias) {
					// not equal statement
					orCriteria[index] = Criteria.where(qbCriteria.getConditionField()).is(qbCriteria.getValue());
					index++;
				}
				query2.addCriteria(Criteria.where(criteriaAnd.getConditionField()).is(criteriaAnd.getValue())
						.orOperator(orCriteria));
				query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
				query2.with(new Sort(sortType, sortField));
				log.info(query2);
				list = getMongoOperations(dbType, fo100).find(query2, mClass);
			} else
				list = getMongoOperations(dbType, fo100).findAll(mClass);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
