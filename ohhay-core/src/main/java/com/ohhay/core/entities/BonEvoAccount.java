package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * create Apr 2, 2016
 */
public class BonEvoAccount implements Serializable{
	@QbMapping(name = "LOGID", typeMapping = Types.INTEGER)
	private int id;
	@QbMapping(name = "QV101", typeMapping = Types.CHAR)
	private String email;
	@QbMapping(name = "KONTO", typeMapping = Types.CHAR)
	private String konto;
	@QbMapping(name = "PASS", typeMapping = Types.CHAR)
	private String pass;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKonto() {
		return konto;
	}
	public void setKonto(String konto) {
		this.konto = konto;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "BonEvoAccount [id=" + id + ", email=" + email + ", konto="
				+ konto + ", pass=" + pass + "]";
	}
	
}
