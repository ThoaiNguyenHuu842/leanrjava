package com.ohhay.core.criteria;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * @author TuNt
 * create date Aug 12, 2016
 * ohhay-core
 */
public class ChartCriteriaNew implements Serializable{
	private int pnRN905;
	private String pvRV951;
	private int pnRN906;
	private int pnINTER;
	private String pdFRDAT;
	private String pdTODAT;
	private int type; //0->10;
	public int getPnRN905() {
		return pnRN905;
	}
	public void setPnRN905(int pnRN905) {
		this.pnRN905 = pnRN905;
	}
	public String getPvRV951() {
		if(pvRV951=="")
			return null;
		return pvRV951;
	}
	public void setPvRV951(String pvRV951) {
		this.pvRV951 = pvRV951;
	}

	public int getPnRN906() {
		return pnRN906;
	}
	public void setPnRN906(int pnRN906) {
		this.pnRN906 = pnRN906;
	}
	public int getPnINTER() {
		return pnINTER;
	}
	public void setPnINTER(int pnINTER) {
		this.pnINTER = pnINTER;
	}	
	public Date getPdFRDAT() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFrom = null;
		try {
			dateFrom = new Date(formatter.parse(pdFRDAT).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return dateFrom;
	}
	
	public Date getPdTODAT() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFrom = null;
		try {
			dateFrom = new Date(formatter.parse(pdTODAT).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return dateFrom;
	}
	
	public void setPdFRDAT(String pdFRDAT) {
		this.pdFRDAT = pdFRDAT;
	}
	public void setPdTODAT(String pdTODAT) {
		this.pdTODAT = pdTODAT;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public ChartCriteriaNew(int pnRN905, String pvRV951, int pnRN906, int pnINTER, String pdFRDAT, String pdTODAT,
			int type) {
		super();
		this.pnRN905 = pnRN905;
		this.pvRV951 = pvRV951;
		this.pnRN906 = pnRN906;
		this.pnINTER = pnINTER;
		this.pdFRDAT = pdFRDAT;
		this.pdTODAT = pdTODAT;
		this.type = type;
	}
	public ChartCriteriaNew() {
		super();
	}
	@Override
	public String toString() {
		return "ChartCriteriaNew [pnRN905=" + pnRN905 + ", pvRV951=" + pvRV951 + ", pnRN906=" + pnRN906 + ", pnINTER="
				+ pnINTER + ", pdFRDAT=" + pdFRDAT + ", pdTODAT=" + pdTODAT + ", type=" + type + "]";
	}
	
	
}
