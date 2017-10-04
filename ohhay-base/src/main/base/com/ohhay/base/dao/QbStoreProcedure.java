package com.ohhay.base.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * @author ThoaiNH
 * create 12/07/2014
 * modify sql call function info
 */
public class QbStoreProcedure extends StoredProcedure {
	private QbEntityMapper entityMapper;//class map khi execute procedure
	private int fucitonReturnType;//kieu tra va theo constant cua SQL library khi execute function
	private List<QbSqlParam> listSqlQbParam;//tham so truyen vao
	public QbStoreProcedure(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		super();
		setJdbcTemplate(jdbcTemplate);
	}
	public List<QbSqlParam> getListSqlQbParam() {
		return listSqlQbParam;
	}
	public void setListSqlQbParam(List<QbSqlParam> listSqlQbParam) {
		this.listSqlQbParam = listSqlQbParam;
	}
	public QbEntityMapper getEntityMapper() {
		return entityMapper;
	}
	public void setEntityMapper(QbEntityMapper entityMapper) {
		this.entityMapper = entityMapper;
	}
	public int getFucitonReturnType() {
		return fucitonReturnType;
	}
	public void setFucitonReturnType(int fucitonReturnType) {
		this.fucitonReturnType = fucitonReturnType;
	}
	
	
	
}
