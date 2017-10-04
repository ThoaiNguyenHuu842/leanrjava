package com.ohhay.piepme.mongo.userprofile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.Q100Piepme;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.piepme.mongo.entities.other.S250PMG;
import com.ohhay.piepme.mongo.nestedentities.A100PMG;
import com.ohhay.piepme.mongo.nestedentities.K100PMG;

/**
 * MONGODB COLLECTION - piepme user profile
 * @author ThoaiNH
 * create Jun 9, 2016
 */
@Document(collection = QbMongoCollectionsName.N100)
public class N100PMG implements Serializable{
	@Id
	private int id;
	@Field("NV101")
	private String nv101;//unique user name (nick name + secret number)
	@Field("NV101_STEM")
	private String nv101Stem;//unique user name using to search
	@Field("NV102")
	private String nv102;//key
	@Field("NV103")
	private String nv103;//itan pdf file url
	@Field("NV104")
	private String nv104;//location label
	@Field("NV106")
	private String nv106;//nick name
	@Field("NV107")
	private String nv107;//secret number (three digits)
	@Field("NV108")
	private String nv108;//color code of user
	@Field("NN109")
	private int nn109;//total views public pieper
	@Field("NN110")
	private int nn110;//total following
	@Field("NV111")
	private String nv111;//device token
	@Field("NN112")
	private int nn112;//user year of birthday
	@Field("NN113")
	private int nn113;//total views commercial pieper
	@Field("NV114")
	private String nv114;//device name
	@Field("NV116")
	private String nv116;//user role ("AFF","ADM","BUS","NOR")
	@Field("NV117")
	private String nv117;//ma so gioi thieu cho ban be
	@Field("NV118")
	private String nv118;//ma voucher nhan dc sau khi dk
	@Field("NN119")
	private int nn119;//trang thai user
	@Field("NN120")
	private int nn120;//1: quyen duoc dang tin BDS
	@Field("NL166")
	private Date nl166;
	@Field("NL168")
	private Date nl168;
	@Field("LOC")
	private GeoDataPointMG location;//provider location
	@Field("LOC_R")
	private GeoDataPointMG locationR;//location of user when online
	@Field("FO100")
	private int fo100;
	@Field("FO100_RE")
	private int fo100Re;//fo100 nguoi gioi thieu
	@Field("K100")
	private K100PMG k100;//business info
	@Field("K100_CON")
	private K100PMG k100Con;//business info has confirmed by admin
	@Field("A100")
	private A100PMG a100;//affiliate info
	@Field("A100_CON")
	private A100PMG a100Con;//affiliate info has confirmed by admin
	@Field("OTAG")
	private String[] otags;
	@Field("FO100RES")
	private List<Integer> listFO100RE;//list user da duoc gioi thieu
	@Transient
	private int friendStt;
	@Transient
	private int fc100;
	@Transient
	private M900MG m900mg;
	@Transient
	private Q100Piepme q100;
	@Transient
	protected String urlAvarta;
	@Transient
	private String nl166Str;
	@Transient
	private String nl168Str;
	@Transient
	private S250PMG s250pmg;
	@Transient
	protected String nickName;
	@Transient
	protected long timeAgo;
	public String getEncryptKey(){
		String tempKey = nv102.substring(0, 16);
		int fo100Length = String.valueOf(fo100).length();
		StringBuilder key2 = new StringBuilder(); 
		for(int i=0;i<16-fo100Length;i++)
			key2.append("0");
		key2.append(fo100);
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(key2.toString(), key2.toString());
		return aesUtilsPiepme.encrypt(tempKey).substring(0,16);
	}
	public int getFriendStt() {
		return friendStt;
	}
	public void setFriendStt(int friend_stt) {
		this.friendStt = friend_stt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String[] getOtags() {
		return otags;
	}
	public void setOtags(String[] otags) {
		this.otags = otags;
	}
	public String getNv102() {
		return nv102;
	}
	public void setNv102(String nv102) {
		this.nv102 = nv102;
	}
	public String getNv103() {
		return nv103;
	}
	public void setNv103(String nv103) {
		this.nv103 = nv103;
	}
	
	public M900MG getM900mg() {
		return m900mg;
	}
	public void setM900mg(M900MG m900mg) {
		this.m900mg = m900mg;
	}
	public String getNv104() {
		return nv104;
	}
	public void setNv104(String nv104) {
		this.nv104 = nv104;
	}
	public GeoDataPointMG getLocation() {
		return location;
	}
	public void setLocation(GeoDataPointMG location) {
		this.location = location;
	}
	
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	public String getNv107() {
		return nv107;
	}
	public void setNv107(String nv107) {
		this.nv107 = nv107;
	}
	public Date getNl166() {
		return nl166;
	}
	public void setNl166(Date nl166) {
		this.nl166 = nl166;
	}
	public Date getNl168() {
		return nl168;
	}
	public void setNl168(Date nl168) {
		this.nl168 = nl168;
	}
	public String getUrlAvarta() {
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	public String getNv101Stem() {
		return nv101Stem;
	}
	public void setNv101Stem(String nv101Stem) {
		this.nv101Stem = nv101Stem;
	}
	public String getNv108() {
		return nv108;
	}
	public void setNv108(String nv108) {
		this.nv108 = nv108;
	}
	public int getNn109() {
		return nn109;
	}
	public void setNn109(int nn109) {
		this.nn109 = nn109;
	}
	public int getNn110() {
		return nn110;
	}
	public void setNn110(int nn110) {
		this.nn110 = nn110;
	}
	public String getNv111() {
		return nv111;
	}
	public void setNv111(String nv111) {
		this.nv111 = nv111;
	}
	public K100PMG getK100() {
		return k100;
	}
	public void setK100(K100PMG k100) {
		this.k100 = k100;
	}
	public int getNn112() {
		return nn112;
	}
	public void setNn112(int nn112) {
		this.nn112 = nn112;
	}
	public int getNn113() {
		return nn113;
	}
	public void setNn113(int nn113) {
		this.nn113 = nn113;
	}
	public String getNv114() {
		return nv114;
	}
	public void setNv114(String nv114) {
		this.nv114 = nv114;
	}
	public String getNv116() {
		return nv116;
	}
	public void setNv116(String nv116) {
		this.nv116 = nv116;
	}
	public int getFc100() {
		return fc100;
	}
	public void setFc100(int fc100) {
		this.fc100 = fc100;
	}
	
	public Q100Piepme getQ100() {
		return q100;
	}
	public void setQ100(Q100Piepme q100) {
		this.q100 = q100;
	}
	public String getNl166Str() {
		return nl166Str;
	}
	public void setNl166Str(String nl166Str) {
		this.nl166Str = nl166Str;
	}
	public String getNl168Str() {
		return nl168Str;
	}
	public void setNl168Str(String nl168Str) {
		this.nl168Str = nl168Str;
	}
	public A100PMG getA100() {
		return a100;
	}
	public void setA100(A100PMG a100) {
		this.a100 = a100;
	}
	public K100PMG getK100Con() {
		return k100Con;
	}
	public void setK100Con(K100PMG k100Con) {
		this.k100Con = k100Con;
	}
	public A100PMG getA100Con() {
		return a100Con;
	}
	public void setA100Con(A100PMG a100Con) {
		this.a100Con = a100Con;
	}
	public String getNv117() {
		return nv117;
	}
	public void setNv117(String nv117) {
		this.nv117 = nv117;
	}
	public List<Integer> getListFO100RE() {
		return listFO100RE;
	}
	public void setListFO100RE(List<Integer> listFO100RE) {
		this.listFO100RE = listFO100RE;
	}
	public int getFo100Re() {
		return fo100Re;
	}
	public void setFo100Re(int fo100Re) {
		this.fo100Re = fo100Re;
	}
	public String getNv118() {
		return nv118;
	}
	public void setNv118(String nv118) {
		this.nv118 = nv118;
	}
	public S250PMG getS250pmg() {
		return s250pmg;
	}
	public void setS250pmg(S250PMG s250pmg) {
		this.s250pmg = s250pmg;
	}
	
	public int getNn119() {
		return nn119;
	}
	public void setNn119(int nn119) {
		this.nn119 = nn119;
	}
	
	public GeoDataPointMG getLocationR() {
		return locationR;
	}
	public void setLocationR(GeoDataPointMG locationR) {
		this.locationR = locationR;
	}
	public int getNn120() {
		return nn120;
	}
	public void setNn120(int nn120) {
		this.nn120 = nn120;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getTimeAgo() {
		return timeAgo;
	}
	public void setTimeAgo(long timeAgo) {
		this.timeAgo = timeAgo;
	}
	@Override
	public String toString() {
		return "N100PMG [id=" + id + ", nv101=" + nv101 + ", nv101Stem=" + nv101Stem + ", nv102=" + nv102 + ", nv103="
				+ nv103 + ", nv104=" + nv104 + ", nv106=" + nv106 + ", nv107=" + nv107 + ", nv108=" + nv108 + ", nn109="
				+ nn109 + ", nn110=" + nn110 + ", nv111=" + nv111 + ", nn112=" + nn112 + ", nn113=" + nn113 + ", nn119="
				+ nn119 + ", nv114=" + nv114 + ", nv116=" + nv116 + ", nv117=" + nv117 + ", nv118=" + nv118 + ", nl166="
				+ nl166 + ", nl168=" + nl168 + ", location=" + location + ", fo100=" + fo100 + ", fo100Re=" + fo100Re
				+ ", k100=" + k100 + ", k100Con=" + k100Con + ", a100=" + a100 + ", a100Con=" + a100Con + ", otags="
				+ Arrays.toString(otags) + ", listFO100RE=" + listFO100RE + ", friendStt=" + friendStt + ", fc100="
				+ fc100 + ", m900mg=" + m900mg + ", q100=" + q100 + ", urlAvarta=" + urlAvarta + ", nl166Str="
				+ nl166Str + ", nl168Str=" + nl168Str + ", s250pmg=" + s250pmg + "]";
	}
	
}
