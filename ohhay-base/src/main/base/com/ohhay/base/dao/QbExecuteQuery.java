package com.ohhay.base.dao;

import java.util.Map;


/**
 * @author ThoaiNH
 * create 13/07/2014
 * execute function or procedure
 */
public interface QbExecuteQuery {
	/**
	 * class nay co the la QueenbOracle hoac QueenbMySQL, dung DI dua vao QueenbDaoSupport
	 */
	Map<String, Object> excuteQuery(QbStoreProcedure procedure);
}
