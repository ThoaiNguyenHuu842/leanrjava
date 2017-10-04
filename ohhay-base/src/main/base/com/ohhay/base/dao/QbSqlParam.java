package com.ohhay.base.dao;

import org.springframework.jdbc.core.SqlParameter;


/**
 * @author ThoaiNH
 * create 13/07/2014
 * sql parameter pass to function
 */
public class QbSqlParam  extends SqlParameter{
	private Object objectValue;
	public QbSqlParam(int sqlType, Object object) {
		super(sqlType);
		this.objectValue = object;
	}
	public Object getObjectValue() {
		return objectValue;
	}
	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}
	
	
}
