package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiVt
 * create date Mar 18, 2016
 * ohhay-core
 */
public class ChartPieAnalyst implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameComponent;
	private float rate;
	private int rateReal;
	
	public String getNameComponent() {
		return nameComponent;
	}
	public void setNameComponent(String nameComponent) {
		this.nameComponent = nameComponent;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getRateReal() {
		return rateReal;
	}
	public void setRateReal(int rateReal) {
		this.rateReal = rateReal;
	}

	
	
	
}
