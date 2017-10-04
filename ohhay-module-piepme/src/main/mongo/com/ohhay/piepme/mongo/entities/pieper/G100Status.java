package com.ohhay.piepme.mongo.entities.pieper;

import java.io.Serializable;
import java.util.List;

/**
 * @author ThoaiNH
 * create May 22, 2017
 */
public class G100Status implements Serializable{
	private List<Integer> fg100s;
	private List<Integer> fg100Owners;
	public List<Integer> getFg100s() {
		return fg100s;
	}
	public void setFg100s(List<Integer> fg100s) {
		this.fg100s = fg100s;
	}
	public List<Integer> getFg100Owners() {
		return fg100Owners;
	}
	public void setFg100Owners(List<Integer> fg100Owners) {
		this.fg100Owners = fg100Owners;
	}	
}
