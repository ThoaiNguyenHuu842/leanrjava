package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.utils.ApplicationHelper;


/**
 * @author ThoaiNH
 * create 19/03/2015
 * Topic  info
 */
@Document(collection = QbMongoCollectionsName.M150)
public class M150MG implements Serializable{
	@Id
	private int id;
	
	@Field("FO100")
	private int fo100;
	
	@Field("FM200")
	private int fm200;
	
	@Field("MV151")
	private String mv151; //title topic
	
	@Field("MV152")
	private String mv152;//noi dung topic
	
	@Field("MN153")
	private int mn153;//
	
	@Field("MN154")
	private int mn154;//
	
	@Field("MN155")
	private int mn155;//nlike
	
	@Field("MV157")
	private String mv157;//tracking - objectname

	@Field("MN157")
	private int mn157;//ncomment

	@Field("MN158")
	private int mn158;//nshare
	
	@Field("MN159")
	private int mn159;//1: can danh chi muc, 0: k can danh chi muc
	
	@Field("MV163")
	private String mv163;//img logo
	
	@Field("MV164")
	private String mv164;//img logo
	
	@Field("MN165")
	private int mn165;//danh dau tieu diem
	
	@Field("MD165")
	private Date md165;//ngay xoa
	
	@Field("MD168")
	private Date md168;//ngay tao	
	
	@Field("MD169")
	private Date md169;//ngay danh chi muc
	
	@Field("MN169")
	private int mn169;//danh dau an gi o dau
	
	@Field("OTAGS")
	private List<String> otags;
	
	@Field("ROLE")
	private M160MG m160mg;
	
	@Field("IMGS")
	private List<M170MG> listM170mgs;
	
	@Field("IFRAME")
	private M180MG m180mg;
	
	@Field("HOTEN")
	private String hoten;//noi dung topic
	
	@Field("TOTALC")
	private int totalc;//ncomment
	
	@Field("LIKED")
	private boolean liked;//statuslike
	
	@Field("LIKES")
	private List<LIKES> listLIKESmgs;

	@Transient
	private int privacy;//1: public, 0: private ----> dung cho index
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
	public String getMv151() {
		return mv151;
	}
	public String getMv151Decode() {
		String s = ApplicationHelper.decodeTopicString(mv151).replace("\"", "'");
		org.jsoup.nodes.Document document = Jsoup.parse(s);
		return document.text();
	}
	public String getMv152Decode() {
		String s = ApplicationHelper.decodeTopicString(mv152).replace("\"", "'");
		org.jsoup.nodes.Document document = Jsoup.parse(s);
		return document.text();
	}
	public void setMv151(String mv151) {
		this.mv151 = mv151;
	}
	public String getMv152() {
		return mv152;
	}
	public void setMv152(String mv152) {
		this.mv152 = mv152;
	}
	
	public int getMn153() {
		return mn153;
	}
	public void setMn153(int mn153) {
		this.mn153 = mn153;
	}
	public int getMn154() {
		return mn154;
	}
	public void setMn154(int mn154) {
		this.mn154 = mn154;
	}
	public String getMv157() {
		return mv157;
	}
	public void setMv157(String mv157) {
		this.mv157 = mv157;
	}
	
	public int getMn157() {
		return mn157;
	}
	public void setMn157(int mn157) {
		this.mn157 = mn157;
	}
	public int getMn155() {
		return mn155;
	}
	public void setMn155(int mn155) {
		this.mn155 = mn155;
	}
	public int getMn158() {
		return mn158;
	}
	public void setMn158(int mn158) {
		this.mn158 = mn158;
	}
	public List<String> getOtags() {
		return otags;
	}
	public void setOtags(List<String> otags) {
		this.otags = otags;
	}
	
	public int getMn159() {
		return mn159;
	}
	public void setMn159(int mn159) {
		this.mn159 = mn159;
	}
	public Date getMd169() {
		return md169;
	}
	public void setMd169(Date md169) {
		this.md169 = md169;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	public M160MG getM160mg() {
		return m160mg;
	}
	public void setM160mg(M160MG m160mg) {
		this.m160mg = m160mg;
	}
	public String getMv163() {
		return mv163;
	}
	public void setMv163(String mv163) {
		this.mv163 = mv163;
	}
	public String getMv163Decode() {
		String s = ApplicationHelper.decodeTopicString(mv163).replace("\"", "'");
		org.jsoup.nodes.Document document = Jsoup.parse(s);
		return document.text();
	}
	public String getMv164() {
		return mv164;
	}
	public void setMv164(String mv164) {
		this.mv164 = mv164;
	}
	public List<M170MG> getListM170mgs() {
		return listM170mgs;
	}
	public void setListM170mgs(List<M170MG> listM170mgs) {
		this.listM170mgs = listM170mgs;
	}
	public M180MG getM180mg() {
		return m180mg;
	}
	public void setM180mg(M180MG m180mg) {
		this.m180mg = m180mg;
	}
	public Date getMd165() {
		return md165;
	}
	public void setMd165(Date md165) {
		this.md165 = md165;
	}
	
	public Date getMd168() {
		return md168;
	}
	public void setMd168(Date md168) {
		this.md168 = md168;
	}
	
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public int getTotalc() {
		return totalc;
	}
	public void setTotalc(int totalc) {
		this.totalc = totalc;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public List<LIKES> getListLIKESmgs() {
		return listLIKESmgs;
	}
	public void setListLIKESmgs(List<LIKES> listLIKESmgs) {
		this.listLIKESmgs = listLIKESmgs;
	}
	public int getFm200() {
		return fm200;
	}
	public void setFm200(int fm200) {
		this.fm200 = fm200;
	}
	public int getMn165() {
		return mn165;
	}
	public void setMn165(int mn165) {
		this.mn165 = mn165;
	}
	public int getMn169() {
		return mn169;
	}
	public void setMn169(int mn169) {
		this.mn169 = mn169;
	}
	@Override
	public String toString() {
		return "M150MG [id=" + id + ", fo100=" + fo100 + ", fm200=" + fm200
				+ ", mv151=" + mv151 + ", mv152=" + mv152 + ", mn153=" + mn153
				+ ", mn154=" + mn154 + ", mn155=" + mn155 + ", mv157=" + mv157
				+ ", mn157=" + mn157 + ", mn158=" + mn158 + ", mn159=" + mn159
				+ ", mv163=" + mv163 + ", mv164=" + mv164 + ", mn165=" + mn165
				+ ", md165=" + md165 + ", md168=" + md168 + ", md169=" + md169
				+ ", mn169=" + mn169 + ", otags=" + otags + ", m160mg=" + m160mg
				+ ", listM170mgs=" + listM170mgs + ", m180mg=" + m180mg
				+ ", hoten=" + hoten + ", totalc=" + totalc + ", liked=" + liked
				+ ", listLIKESmgs=" + listLIKESmgs + ", privacy=" + privacy
				+ "]";
	}
	
}	
