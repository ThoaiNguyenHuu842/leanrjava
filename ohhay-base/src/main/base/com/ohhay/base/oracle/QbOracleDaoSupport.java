package com.ohhay.base.oracle;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbEntityMapper;
import com.ohhay.base.dao.QbExecuteQuery;
import com.ohhay.base.dao.QbSqlParam;
import com.ohhay.base.dao.QbStoreProcedure;


/**
 * @author ThoaiNH
 * create 12/03/2015
 * modify  oracle function parameter
 */
public class QbOracleDaoSupport {
	private Object objectReturn;//object return sau khi execute
	private JdbcTemplate jdbcTemplate;
	public QbStoreProcedure queenbStoreProcedure;
	@Autowired
	@Qualifier(value = "qbOracleExecuteQuery")
	private QbExecuteQuery queenbExcuteQuery;

	@Autowired
	@Qualifier(value = "oracleDataSource")
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		queenbStoreProcedure = new QbStoreProcedure(jdbcTemplate);
	}
	//set tham so truyen  vao 
	public void setQbListSqlParams(List<QbSqlParam> listSqlQbParams) {
		queenbStoreProcedure.setListSqlQbParam(listSqlQbParams);
	}
	//set ten proc or function
	public void setQbSqlName(String sqlName) {
		queenbStoreProcedure.setSql(sqlName);
	}
	//execute query
	public void executeQbQuery() {
		Map<String, Object> mapResult = queenbExcuteQuery.excuteQuery(queenbStoreProcedure);
		objectReturn = mapResult.get(ApplicationConstant.OUT_PARAM_VALUE);
	}
	//set kieu tra ve khi excute function
	public void setQbFunctionReturnType(int type){
		queenbStoreProcedure.setFucitonReturnType(type);
	}
	//set kieu tra ve khi excute procedure
	public void setQBEntityMapper(QbEntityMapper entityMapper){
		queenbStoreProcedure.setEntityMapper(entityMapper);
	}
	//lay object ket qua tra ve khi execute procedure
	public List getListReturn() {
		return (List) objectReturn;
	}
	//lay object tra ve khi executefuction
	public Object getObjectReturnFunction(){
		return objectReturn;
	}
	public void setQbFunction(boolean b){
		queenbStoreProcedure.setFunction(b);
	}

	
	
	
	
	
}
