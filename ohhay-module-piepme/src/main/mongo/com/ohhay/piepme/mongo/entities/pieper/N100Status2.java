package com.ohhay.piepme.mongo.entities.pieper;

import java.io.Serializable;
import java.util.List;

/**
 * @author ThoaiNH
 * create May 22, 2017
 */
public class N100Status2 implements Serializable{
	private List<Integer> fo100s;
	private List<Integer> fo100follows;
	public List<Integer> getFo100s() {
		return fo100s;
	}
	public void setFo100s(List<Integer> fo100s) {
		this.fo100s = fo100s;
	}
	public List<Integer> getFo100follows() {
		return fo100follows;
	}
	public void setFo100follows(List<Integer> fo100follows) {
		this.fo100follows = fo100follows;
	}
}
