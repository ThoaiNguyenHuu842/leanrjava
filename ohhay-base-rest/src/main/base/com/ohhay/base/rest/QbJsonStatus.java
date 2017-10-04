package com.ohhay.base.rest;

import com.google.gson.JsonObject;


/**
 * @author ThoaiNH
 * create 12/08/2014
 * object return after execute service
 */
public class QbJsonStatus {
	public String getSuccessStatus(){
		JsonObject jsonObject=new  JsonObject();
		jsonObject.addProperty("status", "success");
		return jsonObject.toString();
	}
	public String getErrorStatus(){
		JsonObject jsonObject=new  JsonObject();
		jsonObject.addProperty("status", "error");
		return jsonObject.toString();
	}
	//result khi execute function
	public String getFuctionResult(Object object)
	{
		JsonObject jsonObject=new  JsonObject();
		jsonObject.addProperty("result", object.toString());
		return jsonObject.toString();
	}
}
