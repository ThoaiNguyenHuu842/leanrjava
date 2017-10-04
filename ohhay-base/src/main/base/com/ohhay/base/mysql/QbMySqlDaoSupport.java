package com.ohhay.base.mysql;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbExecuteQuery;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.dao.QbStoreProcedure;
import com.ohhay.base.mongo.MongoDBManager;
import com.ohhay.base.util.BaseHelper;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author ThoaiNH 
 * create 15/07/2014 
 * modify MySQL function parameter, database access...
 */
public class QbMySqlDaoSupport {
	private static Logger log = Logger.getLogger(QbMySqlDaoSupport.class);
	private Object objectReturn;// object return after execute
	private JdbcTemplate jdbcTemplate;
	public QbStoreProcedure queenbStoreProcedure;
	private String uri;
	@Autowired
	@Qualifier(value = "qbMySqlExecuteQuery")
	private QbExecuteQuery queenbExcuteQuery;

	public QbMySqlDaoSupport() {
		// TODO Auto-generated constructor stub
		try {
			jdbcTemplate = new JdbcTemplate(MySQLManager.getDBCenterInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queenbStoreProcedure = new QbStoreProcedure(jdbcTemplate);
	}
	/**
	 * ex: access db center -> setQbListSqlParams(listParams, null, MySQLManager.QUERY_TYPE_IGNORE);
	 * access db of user has fo100 = 775 -> setQbListSqlParams(listParams, 775, MySQLManager.QUERY_TYPE_FO100);
	 * access db of user has sdt = 840914615540 -> setQbListSqlParams(listParams, '84##qb##0914615540', MySQLManager.QUERY_TYPE_MO);
	 * @param listSqlQbParams
	 * @param objectID 0 to access db center, FO100, ov101 or email
	 * @param queryType MySQLManager.QUERY_TYPE..
	 * @param fo100 set 0 to access db center, set fo100 if access fo100's db
	 */
	public void setQbListSqlParams(List<QbSqlParam> listSqlQbParams, Object objectID, int queryType) {
		try {
			if (objectID == null || queryType == MySQLManager.QUERY_TYPE_IGNORE)
				jdbcTemplate = new JdbcTemplate(MySQLManager.getDBCenterInstance());
			else
				jdbcTemplate = new JdbcTemplate(MySQLManager.getDBInstance(objectID,queryType));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queenbStoreProcedure = new QbStoreProcedure(jdbcTemplate);
		queenbStoreProcedure.setListSqlQbParam(listSqlQbParams);
	}
	public void setQbListSqlParams(List<QbSqlParam> listSqlQbParams) {
		try {
			jdbcTemplate = new JdbcTemplate(MySQLManager.getDBInstance(uri));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queenbStoreProcedure = new QbStoreProcedure(jdbcTemplate);
		queenbStoreProcedure.setListSqlQbParam(listSqlQbParams);
	}
	
	/**
	 * set procedure or function name
	 * @param sqlName
	 */
	public void setQbSqlName(String sqlName) {
		queenbStoreProcedure.setSql(sqlName);
	}

	/**
	 * execute query
	 */
	public void executeQbQuery() {
		Map<String, Object> mapResult = queenbExcuteQuery.excuteQuery(queenbStoreProcedure);
		objectReturn = mapResult.get(ApplicationConstant.OUT_PARAM_VALUE);
	}

	/**
	 * set return type after execute function
	 */
	public void setQbFunctionReturnType(int type) {
		queenbStoreProcedure.setFucitonReturnType(type);
	}

	/**
	 * set return type after execute procedure
	 */
	public void setQBEntityMapper(QbEntityMapper entityMapper) {
		queenbStoreProcedure.setEntityMapper(entityMapper);
	}

	/**
	 * get object after execute procedure
	 */
	public List getListReturn() {
		return (List) objectReturn;
	}

	/**
	 * get object after execute fuction
	 */
	public Object getObjectReturnFunction() {
		return objectReturn;
	}

	public void setQbFunction(boolean b) {
		queenbStoreProcedure.setFunction(b);
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
