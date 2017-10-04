package com.ohhay.piepme.mongo.entities.pieper;

import java.io.Serializable;
import java.util.List;

/**
 * @author ThoaiNH
 * create May 22, 2017
 */
public class N100Status implements Serializable{
	private List<Integer> usersOnl;
	private List<Integer> usersOff;
	public List<Integer> getUsersOnl() {
		return usersOnl;
	}
	public void setUsersOnl(List<Integer> usersOnl) {
		this.usersOnl = usersOnl;
	}
	public List<Integer> getUsersOff() {
		return usersOff;
	}
	public void setUsersOff(List<Integer> usersOff) {
		this.usersOff = usersOff;
	}
	
}
