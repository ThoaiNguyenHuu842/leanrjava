package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.OhhayDefaultData;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create 10/10/2014
 * user info
 */
@Document(collection = QbMongoCollectionsName.M900)
public class M900MG implements Serializable {
	@Transient
	private String searchResult;
	@Transient
	private String urlAvarta;
	@Transient
	private String dateCreate;
	@Id
	private int id;// po100
	@Transient
	private int rowss;
	@Field("FN100")
	private int fn100;// fn100 cho merian
	@Field("FN100O")
	private int fn100O;// fn100
	@Field("HV101")
	private String hv101;
	@Field("NV100")
	private String nv100;// ho ten
	@Field("MV901")
	private String mv901;// first name
	@Field("MV902")
	private String mv902;// last name
	@Field("MV903")
	private String mv903;// email
	@Field("MD904")
	private Date md904;// birthday
	@Transient
	private String md904String;
	@Field("MV905")
	private String mv905;// M: nam, F:nu, N: khong xac dinh
	@Field("MN906")
	private int mn906;// 1 public, 0 private (for phone number)
	@Field("MV907")
	private String mv907;// so dt //
	@Field("MV908")
	private String mv908;// avarta
	@Field("MN909")
	private int mn909;// co hieu thong bao can danh chi muc: 1 can danh, 0: k
						// can
	@Transient
	private int mn910;// co hieu thong bao co webinaris: 1 co, 0: k co
	@Transient
	private int mn911;// so luong vote
	@Field("MN912")
	private int mn912;// 1 public, 0 private (for email)
	@Field("MV913")
	private String mv913;// merian key
	@Transient
	private int mn914;// bookmarked me
	@Transient
	private int mn915;// o hieu thong bao co bsell: 1 co, 0: k co
	@Transient
	private String mv916;// comment dau tien
	@Transient
	private int mn917;// co hieu thong bao co video marketing: 1 co, 0: k co
	@Field("MN918")
	private int mn918;// 1 unaccess, 0: normal
	@Field("MN919")
	private int mn919; //0: mail chưa xác nhận; 1: mail đã xác nhận
	@Field("MV920")
	private String mv920;// ma vung quoc gia vd: 84
	@Field("MV921")
	private String mv921;// email moi thay doi, chua xac nhan
	@Field("MV922")
	private String mv922;// user role
	@Field("MV923")
	private String mv923;// sub domain of bonevo.net to access web
	@Field("MV924")
	private String mv924;// piepme nickname + 3 ID-number
	@Field("MV925")
	private String mv925;// piepme key
	@Field("MV926")
	private String mv926;// packet using
	@Field("ML946")
	private Date ml946;// ngay cap nhat
	@Field("ML948")
	private Date ml948;// ngay  tao
	@Field("ML950")
	private Date ml950;// ngay danh chi muc
	@Field("MD965")
	private Date md965;//ngay xoa
	@Field("LANGUAGE")
	private String language;
	@Field("REGION")
	private String region;//chau luc
	@Field("KEYWORD")
	private List<KeyWord> listKeyWord;
	@Field("JOB")
	private M920MG m920MG;// nghe nghiep
	@Field("RLANGUAGE")
	private LanguageMG rLanguageMG;// ngon ngu thuc khi register (khi tao web se
									// dung ngon ngu nay mac dinh)
	@Field("ROLE_TOPIC")
	private M950MG m950mg;
	@Field("TOPIC_BG")
	private String topicBG;
	@Field("TOPIC_INFO")
	private M960MG m960mg;
	@Field("TOPIC_STYLE")
	private M970MG m970mg;//add: 27/06/2016, storage style of topic new 
	@Transient
	private M930MG m930mg;
	@Transient
	private String timeLastEdit;
	public M900MG() {
		super();
		this.hv101 = "";
		this.mv901 = "";
		this.mv902 = "";
		this.mv903 = "";
		//this.md904 = new Date();
		this.ml946 = new Date();
		this.ml948 = new Date();
		this.mv905 = "";
		this.mn906 = 1;
		this.mn909 = 1;
		this.mn912 = 1;
		this.nv100 = "";
		this.listKeyWord = new ArrayList<KeyWord>();
		this.m920MG = new M920MG();
		this.m950mg = new M950MG();
		this.m930mg = new M930MG();
		this.mv926 = ApplicationConstant.PACKET_FREE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHv101() {
		return hv101;
	}

	public void setHv101(String hv101) {
		this.hv101 = hv101;
	}

	public String getMv901() {
		return mv901;
	}

	public String getMv901Decrypt() {
		try {
			//String st = AESUtils.decrypt(mv901);
			//return st;
			return mv901;
		} catch (Exception ex) {
			return null;
		}
	}

	public void setMv901(String mv901) {
		this.mv901 = mv901;
	}

	public String getMv902() {
		return mv902;
	}

	public String getMv902Decrypt() {
		try {
			/*String st = AESUtils.decrypt(mv902);
			return st;*/
			return mv902;
		} catch (Exception ex) {
			return null;
		}
	}

	public void setMv902(String mv902) {
		this.mv902 = mv902;
	}

	public String getMv903() {
		return mv903;
	}

	public String getMv903Decrypt() {
		try {
			String st = AESUtils.decrypt(mv903);
			return st;
		} catch (Exception ex) {
			return null;
		}
	}

	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}

	public Date getMd904() {
		return md904;
	}

	public void setMd904(Date md904) {
		this.md904 = md904;
	}

	public String getMv905() {
		return mv905;
	}

	public void setMv905(String mv905) {
		this.mv905 = mv905;
	}

	public int getMn906() {
		return mn906;
	}

	public void setMn906(int mn906) {
		this.mn906 = mn906;
	}

	public Date getMl946() {
		return ml946;
	}

	public void setMl946(Date ml946) {
		this.ml946 = ml946;
	}

	public Date getMl948() {
		return ml948;
	}

	public void setMl948(Date ml948) {
		this.ml948 = ml948;
	}

	public List<KeyWord> getListKeyWord() {
		return listKeyWord;
	}

	public void setListKeyWord(List<KeyWord> listKeyWord) {
		this.listKeyWord = listKeyWord;
	}

	public String getMd904String() {
		DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
		if (md904 != null)
			md904String = dateFormat.format(md904);
		return md904String;
	}

	public void setMd904String(String md904String) {
		this.md904String = md904String;
	}

	public String getNv100() {
		return nv100;
	}

	public String getNv100Decrypt() {
		try {
			//String st = AESUtils.decrypt(nv100);
			//return st;
			return nv100;
		} catch (Exception ex) {
			return null;
		}
	}

	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}

	public String getMv907() {
		return mv907;
	}

	public void setMv907(String mv907) {
		this.mv907 = mv907;
	}

	public String getMv908() {
		return mv908;
	}

	public void setMv908(String mv908) {
		this.mv908 = mv908;
	}

	public int getMn909() {
		return mn909;
	}

	public void setMn909(int mn909) {
		this.mn909 = mn909;
	}

	public Date getMl950() {
		return ml950;
	}

	public void setMl950(Date ml950) {
		this.ml950 = ml950;
	}

	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}

	public int getMn910() {
		return mn910;
	}

	public void setMn910(int mn910) {
		this.mn910 = mn910;
	}

	public String getUrlAvarta() {
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
	public String getTopicCover() {
		if (m960mg != null && m960mg.getMv965() != null)
			//urlAvarta = getImgLink() + m960mg.getMv965();
			urlAvarta = m960mg.getMv965();
		else
			urlAvarta = OhhayDefaultData.DEFAULT_TOPIC_COVER;
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}

	public int getMn911() {
		return mn911;
	}

	public void setMn911(int mn911) {
		this.mn911 = mn911;
	}

	public int getMn912() {
		return mn912;
	}

	public void setMn912(int mn912) {
		this.mn912 = mn912;
	}

	public String getMv913() {
		return mv913;
	}

	public void setMv913(String mv913) {
		this.mv913 = mv913;
	}

	public int getMn914() {
		return mn914;
	}

	public void setMn914(int mn914) {
		this.mn914 = mn914;
	}

	public int getMn915() {
		return mn915;
	}

	public void setMn915(int mn915) {
		this.mn915 = mn915;
	}

	public int getFn100() {
		return fn100;
	}

	public void setFn100(int fn100) {
		this.fn100 = fn100;
	}

	public M920MG getM920MG() {
		return m920MG;
	}

	public void setM920MG(M920MG m920mg) {
		this.m920MG = m920mg;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMv916() {
		return mv916;
	}

	public void setMv916(String mv916) {
		this.mv916 = mv916;
	}
	/*
	 * user name show to html
	 */
	public String getUserNameToShow() {
		if (getNv100Decrypt() != null && getNv100Decrypt().length() > 0)
			return getNv100Decrypt();
		else if (mn906 == 1)
			return mv907;
		else
			return "NO NAME";
	}
	
	public String getUserNameWhenSendEmail() {
		if (getNv100Decrypt() != null && getNv100Decrypt().length() > 0)
			return getNv100Decrypt();
		else
			return mv907;
	}
	/*
	 * url to show render by public/private
	 */
	public String getUrlOhhay() {
		// neu public thi url thi sdt, nguoc lai url la md5(sdt)
		if (mn906 == 1)
			return mv907;
		else
			return hv101;
	}

	public String getFo100ToShow() {
		return AESUtils.encrypt(String.valueOf(id));
	}
	public String getRegionToShow() {
		return AESUtils.encrypt(String.valueOf(region));
	}	
	public LanguageMG getrLanguageMG() {
		return rLanguageMG;
	}

	public void setrLanguageMG(LanguageMG rLanguageMG) {
		this.rLanguageMG = rLanguageMG;
	}
	public int getMn917() {
		return mn917;
	}

	public void setMn917(int mn917) {
		this.mn917 = mn917;
	}

	public M950MG getM950mg() {
		return m950mg;
	}

	public void setM950mg(M950MG m950mg) {
		this.m950mg = m950mg;
	}

	public String getTopicBG() {
		return topicBG;
	}

	public void setTopicBG(String topicBG) {
		this.topicBG = topicBG;
	}
	
	public int getMn918() {
		return mn918;
	}

	public void setMn918(int mn918) {
		this.mn918 = mn918;
	}
	
	public int getFn100O() {
		return fn100O;
	}

	public void setFn100O(int fn100o) {
		fn100O = fn100o;
	}
	
	public String getMv920() {
		return mv920;
	}

	public void setMv920(String mv920) {
		this.mv920 = mv920;
	}

	/*
	 * topic background link full
	 */
	public String getTopicBGFull()
	{
		if(topicBG != null)
		{
			//default bg
			if(topicBG.indexOf("{{base_link}}")>=0)
				return ApplicationHelper.replaceString(topicBG,TemplateRule.OHHAY_BASE_LINK, ApplicationConstant.BACKGROUND_TOPIC_PATH);
			else
				return getImgLinkCloudFront()+topicBG;
		}
		else
			return null;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public String getAwsBucket()
	{
		if(region != null)
			return region.toLowerCase()+"-oohhay";
		else
			return "oohhay";
	}
	public String getImgLinkCloudFront()
	{
		if(region != null)
		{
			switch (region.trim().toLowerCase()) {
			case "as":
				return "https://d2g7dc0hcuz3eo.cloudfront.net/"+id+"/";
			case "au":
				return "https://d2p729ugpxtyfh.cloudfront.net/"+id+"/";	
			case "de":
				return "https://dwb8bma0pr0to.cloudfront.net/"+id+"/";
			case "eu":
				return "https://d3da30y70ds7j5.cloudfront.net/"+id+"/";
			case "na":
				return "https://d6c4vop3h18s9.cloudfront.net/"+id+"/";
			case "us":
				return "https://d3376tpt4fcj7g.cloudfront.net/"+id+"/";
			}
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+id+"/";
		}
		else
			return "https://oohhay.s3.amazonaws.com/"+id+"/";
	}
	
	public String getImgLink()
	{
		if(region != null)
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+id+"/";
		else
			return "https://oohhay.s3.amazonaws.com/"+id+"/";
	}
	
	public String getImgLinkNoSLL()
	{
		if(region != null)
		{
			switch (region.trim().toLowerCase()) {
			case "as":
				return "http://d2g7dc0hcuz3eo.cloudfront.net/"+id+"/";
			case "au":
				return "http://d2p729ugpxtyfh.cloudfront.net/"+id+"/";	
			case "de":
				return "http://dwb8bma0pr0to.cloudfront.net/"+id+"/";
			case "eu":
				return "http://d3da30y70ds7j5.cloudfront.net/"+id+"/";
			case "na":
				return "http://d6c4vop3h18s9.cloudfront.net/"+id+"/";
			case "us":
				return "http://d3376tpt4fcj7g.cloudfront.net/"+id+"/";
			}
			return "http://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+id+"/";
		}
		else
			return "http://oohhay.s3.amazonaws.com/"+id+"/";
	}
	public int getMn919() {
		return mn919;
	}
	public void setMn919(int mn919) {
		this.mn919 = mn919;
	}

	public String getMv921() {
		return mv921;
	}
	
	public String getMv921Decrypt() {
		try {
			String st = AESUtils.decrypt(mv921);
			return st;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public void setMv921(String mv921) {
		this.mv921 = mv921;
	}
	
	public Date getMd965() {
		return md965;
	}

	public void setMd965(Date md965) {
		this.md965 = md965;
	}
	
	
	public String getMv922() {
		return mv922;
	}

	public void setMv922(String mv922) {
		this.mv922 = mv922;
	}
	
	
	public M960MG getM960mg() {
		return m960mg;
	}

	public void setM960mg(M960MG m960mg) {
		this.m960mg = m960mg;
	}

	public M930MG getM930mg() {
		return m930mg;
	}

	public void setM930mg(M930MG m930mg) {
		this.m930mg = m930mg;
	}
	public String getTopicFriendLyUrl(){
		if(m960mg != null && m960mg.getMv961() != null && !m960mg.getMv961().isEmpty())
			return ApplicationConstant.TOPIC_CONTEXT_PATH + "h" + id +"/"+m960mg.getMv961();
		return null;
	}
	public String getTopiclUrl(){
		if(getTopicFriendLyUrl() != null)
			return getTopicFriendLyUrl();
		else
			return ApplicationConstant.TOPIC_CONTEXT_PATH +getUrlOhhay();
	}
	public String getTopicFeatureImg(){
		if(m960mg != null && m960mg.getMv964() != null)
			return getImgLinkCloudFront() + m960mg.getMv964();
		return getUrlAvarta();
	}
	public String getMv923() {
		return mv923;
	}

	public void setMv923(String mv923) {
		this.mv923 = mv923;
	}
	
	public int getRowss() {
		return rowss;
	}

	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	
	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	public M970MG getM970mg() {
		return m970mg;
	}

	public void setM970mg(M970MG m970mg) {
		this.m970mg = m970mg;
	}
	
	public String getMv924() {
		return mv924;
	}

	public void setMv924(String mv924) {
		this.mv924 = mv924;
	}

	public String getMv925() {
		return mv925;
	}

	public void setMv925(String mv925) {
		this.mv925 = mv925;
	}
	
	public String getMv926() {
		return mv926;
	}

	public void setMv926(String mv926) {
		this.mv926 = mv926;
	}
	
	public String getTimeLastEdit() {
		return timeLastEdit;
	}

	public void setTimeLastEdit(String timeLastEdit) {
		this.timeLastEdit = timeLastEdit;
	}

	@Override
	public String toString() {
		return "M900MG [searchResult=" + searchResult + ", urlAvarta=" + urlAvarta + ", dateCreate=" + dateCreate
				+ ", id=" + id + ", rowss=" + rowss + ", fn100=" + fn100 + ", fn100O=" + fn100O + ", hv101=" + hv101
				+ ", nv100=" + nv100 + ", mv901=" + mv901 + ", mv902=" + mv902 + ", mv903=" + mv903 + ", md904=" + md904
				+ ", md904String=" + md904String + ", mv905=" + mv905 + ", mn906=" + mn906 + ", mv907=" + mv907
				+ ", mv908=" + mv908 + ", mn909=" + mn909 + ", mn910=" + mn910 + ", mn911=" + mn911 + ", mn912=" + mn912
				+ ", mv913=" + mv913 + ", mn914=" + mn914 + ", mn915=" + mn915 + ", mv916=" + mv916 + ", mn917=" + mn917
				+ ", mn918=" + mn918 + ", mn919=" + mn919 + ", mv920=" + mv920 + ", mv921=" + mv921 + ", mv922=" + mv922
				+ ", mv923=" + mv923 + ", mv924=" + mv924 + ", mv925=" + mv925 + ", mv926=" + mv926 + ", ml946=" + ml946
				+ ", ml948=" + ml948 + ", ml950=" + ml950 + ", md965=" + md965 + ", language=" + language + ", region="
				+ region + ", listKeyWord=" + listKeyWord + ", m920MG=" + m920MG + ", rLanguageMG=" + rLanguageMG
				+ ", m950mg=" + m950mg + ", topicBG=" + topicBG + ", m960mg=" + m960mg + ", m970mg=" + m970mg
				+ ", m930mg=" + m930mg + ", timeLastEdit=" + timeLastEdit + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof M900MG))
			return false;
		else{
			M900MG m900mg = (M900MG) obj;
			if(id == m900mg.getId())
				return true;
			return false;
		}
	}
	
}
