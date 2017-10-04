package com.ohhay.core.criteria;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author ThoaiVt
 * create date Mar 7, 2016
 * ohhay-core
 */
public class AdministratorGeneralCriteria implements Serializable{
	
	/**
	 * using page Criteria Admin , Account
	 */
	private static final long serialVersionUID = 1L;
	private String nameAccount; //NAME ACCOUNT OR ID WEB
	private int offset;
	private int limit;
	private String pvSearch;
	private String fromDate;
	private String toDate;
	private int pnSORT;
	private int pnDIRECTION;
	private int payMent;
	
	public int getPnSORT() {
		return pnSORT;
	}
	public void setPnSORT(int pnSORT) {
		this.pnSORT = pnSORT;
	}
	public int getPnDIRECTION() {
		return pnDIRECTION;
	}
	public void setPnDIRECTION(int pnDIRECTION) {
		this.pnDIRECTION = pnDIRECTION;
	}
	
	public String getPvSearch() {
		return pvSearch;
	}
	public void setPvSearch(String pvSearch) {
		this.pvSearch = pvSearch;
	}
	public String getNameAccount() {
		return nameAccount;
	}
	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public Date getFromDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFrom = null;
		try {
			dateFrom = new Date(formatter.parse(fromDate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return dateFrom;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		Date dateTo = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dateTo = new Date(formatter.parse(toDate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return dateTo;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public int getPayMent() {
		return payMent;
	}
	public void setPayMent(int payMent) {
		this.payMent = payMent;
	}
	@Override
	public String toString() {
		return "AdministratorGeneralCriteria [nameAccount=" + nameAccount + ", offset=" + offset + ", limit=" + limit
				+ ", pvSearch=" + pvSearch + ", fromDate=" + fromDate + ", toDate=" + toDate + ", pnSORT=" + pnSORT
				+ ", pnDIRECTION=" + pnDIRECTION + "]";
	}
}
