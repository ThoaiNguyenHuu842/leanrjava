package com.ohhay.base.oracle;

import java.util.Map;

import org.apache.catalina.util.ParameterMap;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbExecuteQuery;
import com.ohhay.base.dao.QbStoreProcedure;

import oracle.jdbc.OracleTypes;


/**
 * @author ThoaiNH
 * create 12/03/2015
 * execute oracle function
 */
@Component
@Scope(value = "prototype")
public class QbOracleExecuteQuery implements QbExecuteQuery {
	private static Logger log = Logger.getLogger(QbOracleExecuteQuery.class);
	public Map<String, Object> excuteQuery(
			QbStoreProcedure queenbStoreProcedure) {
		// TODO Auto-generated method stub
		log.info("----QBEXECUTE ORACLE:" + queenbStoreProcedure.getSql());
		//log.info("---QBRETURNTYPE:"+queenbStoreProcedure.getEntityMapper());
		//set return type cho function hoac procedure
		if (queenbStoreProcedure.isFunction()) {
			queenbStoreProcedure.declareParameter(new SqlOutParameter(ApplicationConstant.OUT_PARAM_VALUE, queenbStoreProcedure.getFucitonReturnType()));
		} else {
			queenbStoreProcedure.declareParameter(new SqlOutParameter(ApplicationConstant.OUT_PARAM_VALUE, OracleTypes.CURSOR, queenbStoreProcedure.getEntityMapper()));
		}
		//set tham so truyen vao
		ParameterMap parameterMap = new ParameterMap();
		for (int i = 0; i < queenbStoreProcedure.getListSqlQbParam().size(); i++) {
//			log.info("---type:"
//					+ queenbStoreProcedure.getListSqlQbParam().get(i)
//							.getSqlType());
//			log.info("---value:"
//					+ queenbStoreProcedure.getListSqlQbParam().get(i)
//							.getObjectValue());
			queenbStoreProcedure.declareParameter(new SqlParameter(
					ApplicationConstant.IN_PARAM + i, queenbStoreProcedure
							.getListSqlQbParam().get(i).getSqlType()));
			parameterMap.put(ApplicationConstant.IN_PARAM + i,
					queenbStoreProcedure.getListSqlQbParam().get(i)
							.getObjectValue());
		}
		queenbStoreProcedure.compile();
		Map<String, Object> map = queenbStoreProcedure.execute(parameterMap);
		//log.info("--map return:"+map);
		return map;
	}

}
