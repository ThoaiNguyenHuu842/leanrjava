package com.ohhay.piepme.mongo.entities.pieper;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Jun 9, 2017
 */
public class N100Status3 implements Serializable{
	private N100Status fo100s;
	private N100Status fo100follows;
	public N100Status getFo100s() {
		return fo100s;
	}
	public void setFo100s(N100Status fo100s) {
		this.fo100s = fo100s;
	}
	public N100Status getFo100follows() {
		return fo100follows;
	}
	public void setFo100follows(N100Status fo100follows) {
		this.fo100follows = fo100follows;
	}
	
}
