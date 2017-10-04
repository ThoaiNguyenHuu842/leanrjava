package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * @author ThongQB
 * create 24/8/2015
 * tag cloud of topic
 */

@SuppressWarnings("serial")
public class UserOtag implements Serializable{
	@Id
	private String id;//name
	@Field("TOTAL")
	private int total;//number search
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getNumberSearch(){
		if(total==0)
			return 0;
		else if(total<=10)
			return 1;
		else if(total<=20)
			return 2;
		else if(total<=30)
			return 3;
		else if(total<=40)
			return 4;
		else if(total<=50)
			return 5;
		else if(total<=60)
			return 6;
		else if(total<=70)
			return 7;
		else if(total<=80)
			return 8;
		else
			return 9;
	}
}	
