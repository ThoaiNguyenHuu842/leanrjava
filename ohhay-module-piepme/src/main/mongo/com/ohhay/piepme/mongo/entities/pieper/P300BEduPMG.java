package com.ohhay.piepme.mongo.entities.pieper;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Jul 29, 2017
 */
public class P300BEduPMG extends P300BPMG{
	@Field("FT110")
	private int ft110;
	@Field("TV119")
	private String tv119;
	public int getFt110() {
		return ft110;
	}
	public void setFt110(int ft110) {
		this.ft110 = ft110;
	}
	public String getTv119() {
		return tv119;
	}
	public void setTv119(String tv119) {
		this.tv119 = tv119;
	}
	
}
