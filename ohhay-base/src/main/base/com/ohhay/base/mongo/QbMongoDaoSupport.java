package com.ohhay.base.mongo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.ohhay.base.constant.ApplicationConstant;

/**
 * @author ThoaiNH
 * create 12/07/2014
 * modify  mongoDB shell function parameter
 */
public class QbMongoDaoSupport {
	private static Logger log = Logger.getLogger(QbMongoDaoSupport.class);
	private BasicDBObject basicDBObject = new BasicDBObject();
	private StringBuilder functionModifierString = new StringBuilder("");
	private boolean accessDBcent = false;
	/**
	 * get database operation
	 * @param dbType topic->ApplicationConstant.OPERATION_TOPIC or o!hay->ApplicationConstant.OPERATION_OHHAY
	 * @param fo100 set 0 to access db center 
	 * @throws java.lang.Exception 
	 */
	protected MongoOperations getMongoOperations(int dbType, int fo100) throws java.lang.Exception {
		if(fo100 == 0)
			return MongoDBManager.getDBOhhayCenterInstance(dbType);
		/*
		 * update 26/12/2016
		 * users in PIEPME have center server by country code got by FO100
		 */
		else if(accessDBcent)
			return MongoDBManager.getDBOhhayCenterInstance(dbType,fo100);
		else 
			return MongoDBManager.getDBInstance(fo100, dbType);
	}
	protected void setFunctionName(String functionName) {
		functionModifierString = new StringBuilder("");
		functionModifierString.append(functionName + "(");
	}

	protected void setParameterNumber(Object number) {
		if (functionModifierString.charAt(functionModifierString.length() - 1) != '(')
			functionModifierString.append("," + String.valueOf(number));
		else
			functionModifierString.append(String.valueOf(number));
	}

	protected void setParameterString(String paramOr) {
		String param = null;
		if(paramOr != null)
			param = paramOr.replace("'", "\\'");
		if (functionModifierString.charAt(functionModifierString.length() - 1) != '(')
		{
			if(param == null)
				functionModifierString.append(",null");
			else
				functionModifierString.append(",'" + param + "'");
		}
		else
		{
			if(param == null)
				functionModifierString.append("null");
			else
				functionModifierString.append("'" + param + "'");
		}
	}
	
	protected void setParameterArrayNumber(List<Double> arr) {
		StringBuilder stringBuilder = new StringBuilder("[");
		for(int i = 0; i < arr.size(); i++){
			if(i < arr.size() - 1)
				stringBuilder.append(arr.get(i) + ",");
			else
				stringBuilder.append(arr.get(i));
		}
		stringBuilder.append("]");
		if (functionModifierString.charAt(functionModifierString.length() - 1) != '(')
			functionModifierString.append("," + stringBuilder.toString());
		else
			functionModifierString.append(stringBuilder.toString());
	}
	
	protected void setParameterArrayNumberInt(List<Integer> arr) {
		StringBuilder stringBuilder = new StringBuilder("[");
		for(int i = 0; i < arr.size(); i++){
			if(i < arr.size() - 1)
				stringBuilder.append(arr.get(i) + ",");
			else
				stringBuilder.append(arr.get(i));
		}
		stringBuilder.append("]");
		if (functionModifierString.charAt(functionModifierString.length() - 1) != '(')
			functionModifierString.append("," + stringBuilder.toString());
		else
			functionModifierString.append(stringBuilder.toString());
	}
	
	protected void setParameterArrayString(List<String> arr) {
		StringBuilder stringBuilder = new StringBuilder("[");
		for(int i = 0; i < arr.size(); i++){
			if(i < arr.size() - 1)
				stringBuilder.append("'" + arr.get(i) + "',");
			else
				stringBuilder.append("'" + arr.get(i) + "'");
		}
		stringBuilder.append("]");
		if (functionModifierString.charAt(functionModifierString.length() - 1) != '(')
			functionModifierString.append("," + stringBuilder.toString());
		else
			functionModifierString.append(stringBuilder.toString());
	}
	
	/**
	 * execute function
	 * @param type topic->ApplicationConstant.OPERATION_TOPIC or o!hay->ApplicationConstant.OPERATION_OHHAY
	 * @param fo100 set 0 to access db center 
	 * @throws java.lang.Exception 
	 */
	protected Object executeFunction(int type, int fo100) throws java.lang.Exception {
		functionModifierString.append(")");
		basicDBObject.append("$eval", functionModifierString.toString());
		log.info("--QB EXECUTE MONGO FUNCTION:"
				+ functionModifierString.toString());
		CommandResult t = getMongoOperations(type, fo100).executeCommand(basicDBObject);
		if(t.get("retval") != null)
			return t.get("retval").toString();
		else
			return "{}";
	}
	/**
	 * @return
	 */
	protected boolean isAccessDBcent() {
		return accessDBcent;
	}
	/**
	 * @param accessDBcent
	 */
	protected void setAccessDBcent(boolean accessDBcent) {
		this.accessDBcent = accessDBcent;
	}
}
