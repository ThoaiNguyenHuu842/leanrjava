package com.ohhay.piepme.mongo.channel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Jul 27, 2017
 */
@Document(collection = QbMongoCollectionsName.T110)
public class T110PMG implements Serializable{
	public static String TYPE_COM1 = "COM1";
	public static String TYPE_COM2 = "COM2";
	public static String TYPE_EOM= "EOM";
	public static String TYPE_EOM_PH = "EOM_PH";
	public static String TYPE_EOM_HS = "EOM_HS";
	@Id
	private int id;
	@Field("FT100")
	private int ft100; //channel COM truc thuoc
	@Field("FT110")
	private int ft110; //channel COM1 truc thuoc
	@Field("FO100")
	private int fo100;
	@Field("TV111")
	private String tv111; //tieu de channel
	@Field("TV112")
	private String tv112; //hinh dai dien
	@Field("TV113")
	private String tv113; //code join cho phu huynh
	@Field("TV114")
	private String tv114; //code join cho hoc sinh
	@Field("TV115")
	private String tv115; //yeu cau phai xac nhan khi user tham gia kenh ("C" or null);
	@Field("TL116")
	private Date tl116;
	@Field("TL118")
	private Date tl118;
	@Field("TV119")
	private String tv119; //COM_TYPE (COM1, COM2, EOM, EOM_PH, EOM_HS)
	@Field("TV120")
	private String tv120;//cover image
	@Field("LOC")
	private GeoDataPointMG location;//channel location
	@Field("FO100S")
	private List<Integer> fo100s; //danh sach nguoi phu trach
	@Transient
	protected int totalPieper;
	@Transient
	private List<N100SummaryInfo> listAdmin;//thong tin nguoi phu trach
	@Transient
	private String role;
	public T110PMG(){}
	//constructor for COM1
	public T110PMG(int id, int ft100, int fo100, String tv111, String tv112, GeoDataPointMG location, List<Integer> fo100s) {
		super();
		this.id = id;
		this.ft100 = ft100;
		this.fo100 = fo100;
		this.tv111 = tv111;
		this.tv112 = tv112;
		this.location = location;
		this.fo100s = fo100s;
		this.tv119 = TYPE_COM1;
		tl116 = new Date();
		tl118 = new Date();
	}
	//constructor for COM2
	public T110PMG(int id, int ft100, int ft110, int fo100, String tv111, String tv112, String tv115, List<Integer> fo100s) {
		super();
		this.id = id;
		this.ft100 = ft100;
		this.ft110 = ft110;
		this.fo100 = fo100;
		this.tv111 = tv111;
		this.tv112 = tv112;
		this.tv113 = ApplicationHelper.getRandomOTP();
		this.tv114 = ApplicationHelper.getRandomOTP();
		this.tv115 = tv115;
		this.fo100s = fo100s;
		this.tv119 = TYPE_COM2;
		tl116 = new Date();
		tl118 = new Date();
	}
	//constructor for EOM1
	public T110PMG(int id, int ft100, int ft110, int fo100, String tv119) {
		super();
		this.id = id;
		this.ft100 = ft100;
		this.ft110 = ft110;
		this.fo100 = fo100;
		this.tv119 = tv119;
		tl116 = new Date();
		tl118 = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFt100() {
		return ft100;
	}
	public void setFt100(int ft100) {
		this.ft100 = ft100;
	}
	public int getFt110() {
		return ft110;
	}
	public void setFt110(int ft110) {
		this.ft110 = ft110;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getTv111() {
		return tv111;
	}
	public void setTv111(String tv111) {
		this.tv111 = tv111;
	}
	public String getTv113() {
		return tv113;
	}
	public void setTv113(String tv113) {
		this.tv113 = tv113;
	}
	public String getTv114() {
		return tv114;
	}
	public void setTv114(String tv114) {
		this.tv114 = tv114;
	}
	public String getTv115() {
		return tv115;
	}
	public void setTv115(String tv115) {
		this.tv115 = tv115;
	}
	public GeoDataPointMG getLocation() {
		return location;
	}
	public void setLocation(GeoDataPointMG location) {
		this.location = location;
	}
	public List<Integer> getFo100s() {
		return fo100s;
	}
	public void setFo100s(List<Integer> fo100s) {
		this.fo100s = fo100s;
	}
	public Date getTl116() {
		return tl116;
	}
	public void setTl116(Date tl116) {
		this.tl116 = tl116;
	}
	public Date getTl118() {
		return tl118;
	}
	public void setTl118(Date tl118) {
		this.tl118 = tl118;
	}
	public String getTv119() {
		return tv119;
	}
	public void setTv119(String tv119) {
		this.tv119 = tv119;
	}
	public String getTv112() {
		return tv112;
	}
	public void setTv112(String tv112) {
		this.tv112 = tv112;
	}
	public int getTotalPieper() {
		return totalPieper;
	}
	public void setTotalPieper(int totalPieper) {
		this.totalPieper = totalPieper;
	}
	public List<N100SummaryInfo> getListAdmin() {
		return listAdmin;
	}
	public void setListAdmin(List<N100SummaryInfo> listAdmin) {
		this.listAdmin = listAdmin;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTv120() {
		return tv120;
	}
	public void setTv120(String tv120) {
		this.tv120 = tv120;
	}
	@Override
	public String toString() {
		return "T110PMG [id=" + id + ", ft100=" + ft100 + ", ft110=" + ft110
				+ ", fo100=" + fo100 + ", tv111=" + tv111 + ", tv112=" + tv112
				+ ", tv113=" + tv113 + ", tv114=" + tv114 + ", tv115=" + tv115
				+ ", tl116=" + tl116 + ", tl118=" + tl118 + ", tv119=" + tv119
				+ ", location=" + location + ", fo100s=" + fo100s + "]";
	}
	
}
