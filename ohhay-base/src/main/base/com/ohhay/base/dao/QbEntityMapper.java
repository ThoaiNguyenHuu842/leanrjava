package com.ohhay.base.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author ThoaiNH
 * create 12/07/2014
 * map entity attribute to mysql field return
 */
@SuppressWarnings("rawtypes")
public class QbEntityMapper implements RowMapper {

	private Class classMapper;
	private static final Log logger = LogFactory.getLog(QbEntityMapper.class);
	public QbEntityMapper(Class nclass) {
		classMapper = nclass;
	}

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsMt = rs.getMetaData();
		int columnNameCount = rsMt.getColumnCount();
		Object objClass = null;
		try {
			objClass = classMapper.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Field[] fields = classMapper.getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			Field field = fields[j];
			QbMapping mapping = field.getAnnotation(QbMapping.class);
			try {
				mappingFields(mapping, columnNameCount, rsMt, field, objClass,
						rs);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return objClass;
	}

	private void mappingFields(QbMapping mapping, int columnNameCount,
			ResultSetMetaData rsMt, Field field, Object objClass, ResultSet rs)
			throws SQLException, IllegalArgumentException,
			IllegalAccessException {
		if (mapping != null) {
			String mappingName = mapping.name();
			for (int i = 0; i < columnNameCount; i++) {
				// log.info("--paramname" + rsMt.getColumnName(i + 1));
				/*
				 * CHU Y: spring 4.0 phai la getComlunLabel Spring 2 la getComlunName
				 */
				String columnName = rsMt.getColumnLabel(i + 1);
				if (mappingName.equalsIgnoreCase(columnName)) {
					int typeMapping = mapping.typeMapping();
					boolean format = mapping.format();
					String patern = mapping.pattern();
					mappingType(typeMapping, field, objClass, rs, columnName,format,patern);
					break;
				}
			}
		}
	}

	private void mappingType(int typeMapping, Field field, Object objClass,
			ResultSet rs, String columnName, boolean format, String patern) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		String methodName = "set" + shiftStartChar(field.getName());
		try {
			Class[] types = new Class[1];
			switch (typeMapping) {
			case Types.DECIMAL:
				types[0] = BigDecimal.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getBigDecimal(columnName));
				break;
			case Types.BIGINT:
				types[0] = BigDecimal.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getBigDecimal(columnName));
				break;
			case Types.INTEGER:
				types[0] = int.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getInt(columnName));
				break;
			case Types.NUMERIC:
				types[0] = int.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getInt(columnName));
				break;
			case Types.DOUBLE:
				types[0] = double.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getDouble(columnName));
				break;
			case Types.CHAR:
				types[0] = String.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getString(columnName));
				break;
			case Types.CLOB:
				types[0] = String.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getString(columnName));
				break;
			case Types.BLOB:
				types[0] = byte[].class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getBytes(columnName));
				System.out.println(rs.getBytes(columnName).length);
				break;
			case Types.DATE:
				if (!format){
					types[0] = java.sql.Date.class;
					objClass.getClass().getMethod(methodName, types)
					.invoke(objClass, rs.getDate(columnName));					
				}else{
					try {
						types[0] = String.class;
						String dateStr = null;
						SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(rs.getString(columnName) != null){
							java.util.Date date = simpDate.parse(rs.getString(columnName));
							SimpleDateFormat simpDate2 = new SimpleDateFormat(patern);
							dateStr = simpDate2.format(date);
						}
						objClass.getClass().getMethod(methodName, types)
								.invoke(objClass, dateStr);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case Types.TIMESTAMP:
				types[0] = Timestamp.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getTimestamp(columnName));
				break;
			case Types.TIME:
				types[0] = Time.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getTime(columnName));
				break;
			case Types.BOOLEAN:
				types[0] = Boolean.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getBoolean(columnName));
				break;
			case Types.OTHER:
				types[0] = Object.class;
				objClass.getClass().getMethod(methodName, types)
						.invoke(objClass, rs.getObject(columnName));
				break;
			default:
				logger.debug("Invalid type mapping:" + typeMapping);
			}
		} catch (InvocationTargetException ex) {

		} catch (NoSuchMethodException ex) {

		} catch (SecurityException ex) {

		}
	}

	private String shiftStartChar(String text) {
		String reVal = text;
		if (text != null && !text.equals("")) {
			reVal = text.substring(0, 1).toUpperCase() + text.substring(1);
		}
		return reVal;
	}

}
