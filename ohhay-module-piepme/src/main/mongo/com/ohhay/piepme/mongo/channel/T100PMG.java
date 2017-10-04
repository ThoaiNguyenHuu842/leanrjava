package com.ohhay.piepme.mongo.channel;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.COMInfo;

/**
 * MONGODB COLLECTION - channel
 * @author ThoaiNH
 * create Sep 21, 2016
 */
@Document(collection = QbMongoCollectionsName.T100)
public class T100PMG implements Serializable{
	public static String TYPE_COM = "COM";
	public static String TYPE_EOM = "EOM";
	public static String TYPE_PER = "PER";
	public static String COM_TYPE_EDU = "EDU";
	public static String COM_TYPE_ADM = "ADM";//nguoi dc giao quyen COM
	public static String EOM_STT_OFF_NOTI = "OFF";
	@Id
	protected int id;
	@Field("FO100")
	protected int fo100;
	@Field("TV101")
	protected String tv101;//otag
	@Field("TV102")
	protected String tv102;//image
	@Field("TV103")
	protected String tv103;//title
	@Field("TV104")
	protected String tv104;//type of otag ("DEF" = channel default, "COM" = channel pieper tao cho khach hang than thiet, "EOM" = channel cua doanh nghiep user theo doi -> otag la FO100 cua DN)
	@Field("TV105")
	protected String tv105;
	@Field("TV105_STEM")
	protected String tv105Stem;
	@Field("TV106")
	protected String tv106;
	@Field("TL146")
	protected Date tl146;//update date
	@Field("TL148")
	protected Date tl148;//created date
	@Field("FO100E")
	protected int fo100e;
	@Field("EOM_STT")
	protected String eomSTT;//setting of customer "OFF": k nhan thong bao tu doanh nghiep
	@Field("COM_INFO")
	protected COMInfo comInfo;//thong tin COM doanh nghiep setup
	@Field("COM_TYPE")
	private String comType; //loai channel (null or 'EDU')
	@Transient
	protected int totalPieper;
	@Transient
	protected int totalCus;//number of customer in channel COM
	@Transient
	protected String channelTit;
	@Transient
	protected int nn110;
	@Transient
	private String kv101;
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getTv101() {
		return tv101;
	}
	public void setTv101(String tv101) {
		this.tv101 = tv101;
	}
	public Date getTl146() {
		return tl146;
	}
	public void setTl146(Date tl146) {
		this.tl146 = tl146;
	}
	public Date getTl148() {
		return tl148;
	}
	public void setTl148(Date tl148) {
		this.tl148 = tl148;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTv102() {
		return tv102;
	}
	public void setTv102(String tv102) {
		this.tv102 = tv102;
	}
	public String getTv103() {
		return tv103;
	}
	public void setTv103(String tv103) {
		this.tv103 = tv103;
	}
	public String getTv104() {
		return tv104;
	}
	public void setTv104(String tv104) {
		this.tv104 = tv104;
	}
	public String getTv105() {
		return tv105;
	}
	public void setTv105(String tv105) {
		this.tv105 = tv105;
	}
	public String getTv105Stem() {
		return tv105Stem;
	}
	public void setTv105Stem(String tv105Stem) {
		this.tv105Stem = tv105Stem;
	}
	public int getTotalPieper() {
		return totalPieper;
	}
	public void setTotalPieper(int totalPieper) {
		this.totalPieper = totalPieper;
	}
	public int getFo100e() {
		return fo100e;
	}
	public void setFo100e(int fo100e) {
		this.fo100e = fo100e;
	}
	public int getTotalCus() {
		return totalCus;
	}
	public void setTotalCus(int totalCus) {
		this.totalCus = totalCus;
	}
	public String getTv106() {
		return tv106;
	}
	public void setTv106(String tv106) {
		this.tv106 = tv106;
	}
	public String getEomSTT() {
		return eomSTT;
	}
	public void setEomSTT(String eomSTT) {
		this.eomSTT = eomSTT;
	}
	public COMInfo getComInfo() {
		return comInfo;
	}
	public void setComInfo(COMInfo comInfo) {
		this.comInfo = comInfo;
	}
	public String getChannelTit() {
		return channelTit;
	}
	public void setChannelTit(String channelTit) {
		this.channelTit = channelTit;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public int getNn110() {
		return nn110;
	}
	public void setNn110(int nn110) {
		this.nn110 = nn110;
	}
	public String getKv101() {
		return kv101;
	}
	public void setKv101(String kv101) {
		this.kv101 = kv101;
	}
	
}
