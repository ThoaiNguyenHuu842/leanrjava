package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Mar 5, 2016
 * paramerter designer finish backup version
 */
public class DesignerFinishE150Criteria implements Serializable{
	private int pe150;
	private int fe400Cus;
	private int fe400Backup;
	public int getPe150() {
		return pe150;
	}
	public void setPe150(int pe150) {
		this.pe150 = pe150;
	}
	public int getFe400Cus() {
		return fe400Cus;
	}
	public void setFe400Cus(int fe400Cus) {
		this.fe400Cus = fe400Cus;
	}
	public int getFe400Backup() {
		return fe400Backup;
	}
	public void setFe400Backup(int fe400Backup) {
		this.fe400Backup = fe400Backup;
	}
	@Override
	public String toString() {
		return "DesignerFInishE150Criteria [pe150=" + pe150 + ", fe400Cus="
				+ fe400Cus + ", fe400Backup=" + fe400Backup + "]";
	}
	
}
