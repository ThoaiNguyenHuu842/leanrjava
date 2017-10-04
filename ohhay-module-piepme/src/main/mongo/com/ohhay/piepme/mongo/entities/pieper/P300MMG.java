package com.ohhay.piepme.mongo.entities.pieper;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.nestedentities.PieperScentInfo;

/**
 * MONGODB COLLECTION - PIEPER message
 * @author ThoaiNH
 * create Jun 9, 2016
 * 
 */
@Document(collection = QbMongoCollectionsName.P300M)
public class P300MMG extends Pieper{
	public static final int DELIVERY_STT_SENT = 1;
	public static final int DELIVERY_STT_RECEIVED = 2;
	public static final int DELIVERY_STT_SEEN = 3;
	@Field("FO100R")
	private List<Integer> listFO100R;
	@Field("FO100A")
	private List<PieperScentInfo> listFO100A;//archive list 
	@Field("FO100D")
	private List<PieperScentInfo> listFO100D;//deleted list
	@Field("PIEPER_IMGS")
	private List<PieperImg> pieperImgs;//luu rieng list hinh anh cho message
	@Transient
	protected String pd309;//seen time
	@Transient
	protected int fo100f;
	@Transient
	protected M900MG m900mg;
	@Transient
	protected String voucherCode;
	@Transient
	protected String vv308;
	/**
	 * 1 = SENT
	 * 2 = RECEIVED
	 * 3 = SEEN
	 */
	@Field("DELIVERY_STT")
	private int deliveryStt;
	@Transient
	protected String vd303;//han su dung voucher - tu ngay
	@Transient
	protected String vd304;//han su dung voucher - den ngay
	@Transient
	protected int pinned;
	public List<Integer> getListFO100R() {
		return listFO100R;
	}
	public List<PieperScentInfo> getListFO100A() {
		return listFO100A;
	}
	public void setListFO100A(List<PieperScentInfo> listFO100A) {
		this.listFO100A = listFO100A;
	}
	public List<PieperScentInfo> getListFO100D() {
		return listFO100D;
	}
	public void setListFO100D(List<PieperScentInfo> listFO100D) {
		this.listFO100D = listFO100D;
	}
	public String getPd309() {
		return pd309;
	}
	public void setPd309(String pd309) {
		this.pd309 = pd309;
	}
	public void setListFO100R(List<Integer> listFO100R) {
		this.listFO100R = listFO100R;
	}
	public int getFo100f() {
		return fo100f;
	}
	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}
	public M900MG getM900mg() {
		return m900mg;
	}
	public void setM900mg(M900MG m900mg) {
		this.m900mg = m900mg;
	}
	public int getDeliveryStt() {
		return deliveryStt;
	}
	public void setDeliveryStt(int deliveryStt) {
		this.deliveryStt = deliveryStt;
	}
	public List<PieperImg> getPieperImgs() {
		return pieperImgs;
	}
	public void setPieperImgs(List<PieperImg> pieperImgs) {
		this.pieperImgs = pieperImgs;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public String getVd303() {
		return vd303;
	}
	public void setVd303(String vd303) {
		this.vd303 = vd303;
	}
	public String getVd304() {
		return vd304;
	}
	public void setVd304(String vd304) {
		this.vd304 = vd304;
	}
	public String getVv308() {
		return vv308;
	}
	public void setVv308(String vv308) {
		this.vv308 = vv308;
	}
	public int getPinned() {
		return pinned;
	}
	public void setPinned(int pinned) {
		this.pinned = pinned;
	}
	
}
