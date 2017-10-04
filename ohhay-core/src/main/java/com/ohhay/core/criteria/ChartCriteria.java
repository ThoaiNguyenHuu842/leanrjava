package com.ohhay.core.criteria;

/**
 * @author ThoaiNH
 * create 18/09/2014
 * chart view parameter
 */
public class ChartCriteria {
	private int rn120;//thoi gian muon xem 1 tuan, 1 thang , 3 thang, 6 thang, 1 nam
	private int type;//loai chart: 0 r100, 1:r9001, 2:r9002, 3: r9003
	private int webId;
	private String rv957;
	private String languageCode;//language code of current web site
	public ChartCriteria() {
		// TODO Auto-generated constructor stub
	}
	public ChartCriteria(int rn120, int type, String languageCode) {
		super();
		this.rn120 = rn120;
		this.type = type;
		this.languageCode = languageCode;
	}
	public int getRn120() {
		return rn120;
	}
	public void setRn120(int rn120) {
		this.rn120 = rn120;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public String getRv957() {
		return rv957;
	}
	public void setRv957(String rv957) {
		this.rv957 = rv957;
	}
	@Override
	public String toString() {
		return "ChartCriteria [rn120=" + rn120 + ", type=" + type
				+ ", languageCode=" + languageCode + "]";
	}

	
	
}
