package com.ohhay.piepme.mongo.entities.pieper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.OhhayDefaultData;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.entities.mongo.other.LIKES;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.userprofile.N100PMG;
/**
 * common info of all pieper type
 * @author ThoaiNH
 * create Nov 7, 2016
 */
public class Pieper implements Serializable{
	public static final String PV302_OFF = "OFF";
	public static final String PV302_FRI = "FRI";
	public static final String PV302_PIE = "PIE";
	@Id
	protected int id;
	@Field("FO100")
	protected int fo100;
	@Field("PV301")
	protected String pv301;//title
	@Field("PV302")
	protected String pv302;//key đi kèm giải mã khi pieper public
	@Field("PN303")
	protected int pn303;//PIEPER basic type: 1 PIEPER text, 2 PIEPER image, 3 PIEPER link, 4 PIEPER location
	@Field("PV304")
	protected String pv304;//location label, image url or link
	@Field("PV304THUMB")
	protected String pv304Thumb;//thumbnail of image
	@Field("PV305")
	protected String pv305;//PIEPER text content
	@Field("PN306")
	protected int pn306;// = 1 is draft PIEPER, = 2 is P300B PIEPER business account sent to customer
	@Field("PD308")
	protected Date pd308;//created date
	@Field("PD306")
	protected Date pd306;//update date
	@Transient
	protected String[] otags;
	@Field("OTAGS")
	protected List<Otag> listOtags;
	@Field("LOC")
	protected GeoDataPointMG location;//PIEPER location
	@Field("PN309")
	protected float pn309;//ti le, kich thuoc hinh pieper
	@Field("PN310")
	protected int pn310;//tong so luot xem (tang khi moi lan xem, khac vo du lieu trong r100b: khong tang khi cung 1 user xem lai)
	@Field("PN311")
	protected int pn311;//id like ke tiep
	@Field("PN312")
	protected int pn312;//total like
	@Field("PN313")
	protected int pn313;//total comments
	@Field("PV314")
	protected String pv314;//preview content
	@Field("PA315")
	protected List<Date> pa315;//history of repiep time
	@Field("PD316")
	protected Date pd316;//newest piep time
	@Field("LIKES")
	protected List<LIKES> listUserLikes;
	@Transient
	private int liked;
	@Transient
	protected List<N100PMG> relications;
	@Transient
	protected String mv903;
	@Transient
	protected String mv905;
	@Transient
	protected String mv908;
	@Transient
	protected String nv101;
	@Transient
	protected String nv106;
	@Transient
	protected String nv100;
	@Transient
	protected String region;
	@Transient
	protected String urlAvarta;
	@Transient
	protected String nv108;
	@Transient
	protected int nn109;//total of view
	@Transient
	protected int nn110;//total of following
	@Transient
	protected int totalNearestPieper;
	@Transient
	protected int totalHotPieper;
	@Transient
	protected String pd308String;
	@Transient
	protected int followingStt; 
	@Transient
	protected long timeAgo;
	public String getNv108() {
		return nv108;
	}
	public void setNv108(String nv108) {
		this.nv108 = nv108;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getPv301() {
		return pv301;
	}
	public void setPv301(String pv301) {
		this.pv301 = pv301;
	}
	
	public String getPv302() {
		return pv302;
	}
	public void setPv302(String pv302) {
		this.pv302 = pv302;
	}
	public Date getPd308() {
		return pd308;
	}
	public void setPd308(Date pd308) {
		this.pd308 = pd308;
	}
	
	public String[] getOtags() {
		return otags;
	}
	public void setOtags(String[] otags) {
		this.otags = otags;
	}
	public List<N100PMG> getRelications() {
		return relications;
	}
	public void setRelications(List<N100PMG> relications) {
		this.relications = relications;
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public String getPV301Decrypt(){
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(pv302.substring(0, 16), pv302.substring(0, 16));
		return aesUtilsPiepme.decrypt(pv301);
	}
	public int getPn303() {
		return pn303;
	}
	public void setPn303(int pn303) {
		this.pn303 = pn303;
	}
	public String getPv304() {
		return pv304;
	}
	public void setPv304(String pv304) {
		this.pv304 = pv304;
	}
	public GeoDataPointMG getLocation() {
		return location;
	}
	public void setLocation(GeoDataPointMG location) {
		this.location = location;
	}
	public String getPv305() {
		return pv305;
	}
	public void setPv305(String pv305) {
		this.pv305 = pv305;
	}
	
	public int getPn306() {
		return pn306;
	}
	public void setPn306(int pn306) {
		this.pn306 = pn306;
	}
	
	public float getPn309() {
		return pn309;
	}
	public void setPn309(float pn309) {
		this.pn309 = pn309;
	}
	public String getMv903() {
		return mv903;
	}
	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}
	public String getMv908() {
		return mv908;
	}
	public void setMv908(String mv908) {
		this.mv908 = mv908;
	}
	
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	public String getUrlAvarta() {
		String urlAvarta = null;
		if (mv908 != null && mv908.length() > 0)
			urlAvarta = getImgLink() + mv908;
		else
		{
			if(mv905.toLowerCase().equals("m"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_M;
			else if(mv905.toLowerCase().equals("f"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_F;
			else
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_N;
		}
		return urlAvarta;
	}
	public String getImgLink()
	{
		if(region != null)
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+fo100+"/";
		else
			return "https://oohhay.s3.amazonaws.com/"+fo100+"/";
	}
	public String getMv905() {
		return mv905;
	}
	public void setMv905(String mv905) {
		this.mv905 = mv905;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	
	public int getPn310() {
		return pn310;
	}
	public void setPn310(int pn310) {
		this.pn310 = pn310;
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
	public int getPn311() {
		return pn311;
	}
	public void setPn311(int pn311) {
		this.pn311 = pn311;
	}
	public int getPn312() {
		return pn312;
	}
	public void setPn312(int pn312) {
		this.pn312 = pn312;
	}
	public List<LIKES> getListUserLikes() {
		return listUserLikes;
	}
	public void setListUserLikes(List<LIKES> listUserLikes) {
		this.listUserLikes = listUserLikes;
	}
	public int getPn313() {
		return pn313;
	}
	public void setPn313(int pn313) {
		this.pn313 = pn313;
	}
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public String getPv314() {
		return pv314;
	}
	public void setPv314(String pv314) {
		this.pv314 = pv314;
	}
	public List<Otag> getListOtags() {
		return listOtags;
	}
	public void setListOtags(List<Otag> listOtags) {
		this.listOtags = listOtags;
	}
	public int getTotalNearestPieper() {
		return totalNearestPieper;
	}
	public void setTotalNearestPieper(int totalNearestPieper) {
		this.totalNearestPieper = totalNearestPieper;
	}
	public int getTotalHotPieper() {
		return totalHotPieper;
	}
	public void setTotalHotPieper(int totalHotPieper) {
		this.totalHotPieper = totalHotPieper;
	}
	public String getPd308String() {
		return pd308String;
	}
	public void setPd308String(String pd308String) {
		this.pd308String = pd308String;
	}
	public String getPv304Thumb() {
		return pv304Thumb;
	}
	public void setPv304Thumb(String pv304Thumb) {
		this.pv304Thumb = pv304Thumb;
	}
	public List<Date> getPa315() {
		return pa315;
	}
	public void setPa315(List<Date> pa315) {
		this.pa315 = pa315;
	}
	public Date getPd316() {
		return pd316;
	}
	public void setPd316(Date pd316) {
		this.pd316 = pd316;
	}
	public int getFollowingStt() {
		return followingStt;
	}
	public void setFollowingStt(int followingStt) {
		this.followingStt = followingStt;
	}
	public Date getPd306() {
		return pd306;
	}
	public void setPd306(Date pd306) {
		this.pd306 = pd306;
	}
	public long getTimeAgo() {
		return timeAgo;
	}
	public void setTimeAgo(long timeAgo) {
		this.timeAgo = timeAgo;
	}
	
}
