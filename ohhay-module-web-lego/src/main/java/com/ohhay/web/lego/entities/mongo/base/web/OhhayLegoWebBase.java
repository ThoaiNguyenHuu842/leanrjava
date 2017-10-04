package com.ohhay.web.lego.entities.mongo.base.web;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThoaiNH
 * create Oct 13, 2015
 */
public class OhhayLegoWebBase implements Serializable{
	@Id
	private long id;//web id
	@Field("HV101")
	private String hv101;
	@Field("FO100")
	private int fo100; 
	@Field("EL446")
	private Date el446;//update
	@Field("EL448")
	private Date el448;//create
	@Field("EV401")
	private String ev401;//template name
	@Field("EN402")
	private int en402;//0 or not exists: normal web EVO, 1: is EVO template, 2: web created by card, 3: designer 'site of customer, 4: backup of site 
	@Field("EV403")
	private String ev403;//site thumb
	@Field("EN404")
	private int en404;//1: is facebook-fast template, 2: kub template, 3: kub-template-blank, 4: evo card
	@Field("EV405")
	private String ev405;//site name
	@Field("EV406")
	private String ev406;//extra data (luu noi dung loi chuc thiep) hoac status ve trang thai backup version cua designer
	@Field("EN407")
	private int en407;//1: is template showing in BonEvo landing page
	@Field("BG")
	private BgWebBase bg;
	@Field ("CONFIG")
	private ConfigWebBase config;
	@Field ("ELEME_CR_ID")
	private ElementCrId elementCrId; 
	@Field ("FE400")
	private long fe400;
	@Field ("BONEVO_VERSION")
	private String bonevoVersion;//version of code
	@Field("SEO")
	private N950MG n950mg;
	@Field("SRC")
	private String src;//02/08/2016 be or re (responsive site)
	@Transient
	private Map<Object, Object> listBox;
	@Transient
	private Map<Object, Object> listComponent;
	public OhhayLegoWebBase() {
		// TODO Auto-generated constructor stub
		listBox = new HashMap<Object, Object>();
		listComponent  = new HashMap<Object, Object>();
		this.bonevoVersion = ApplicationConstant.BONEVO_VERSION;
	}
	
	public OhhayLegoWebBase(E400MG e400mg) {
		super();
		this.id = e400mg.getId();
		this.fe400 = e400mg.getId();
		this.fo100 = e400mg.getFo100();
		this.el446 = new Date();
		this.el448 = new Date();
		this.bg = new BgWebBase(e400mg.getBg());
		this.config = new ConfigWebBase(e400mg.getConfig());
		this.elementCrId = e400mg.getElementCrId();
		this.ev403 = e400mg.getEv403();
		this.n950mg = new N950MG(e400mg.getN950mg());
		this.bonevoVersion = e400mg.getBonevoVersion();
	}
	
	public String getEv406() {
		return ev406;
	}

	public void setEv406(String ev406) {
		this.ev406 = ev406;
	}

	public N950MG getN950mg() {
		return n950mg;
	}

	public void setN950mg(N950MG n950mg) {
		this.n950mg = n950mg;
	}

	public String getEv403() {
		return ev403;
	}

	public void setEv403(String ev403) {
		this.ev403 = ev403;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.ev405 = "evo-site-"+id;
		this.id = id;
	}
	public String getHv101() {
		return hv101;
	}
	public void setHv101(String hv101) {
		this.hv101 = hv101;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public Date getEl446() {
		return el446;
	}
	public String getEl446String() {
		DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(el446);
	}
	public void setEl446(Date el446) {
		this.el446 = el446;
	}
	public Date getEl448() {
		return el448;
	}
	public String getEl448String() {
		DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(el448);
	}
	public void setEl448(Date el448) {
		this.el448 = el448;
	}
	public String getEv401() {
		return ev401;
	}
	public void setEv401(String ev401) {
		this.ev401 = ev401;
	}
	public BgWebBase getBg() {
		return bg;
	}
	public void setBg(BgWebBase bg) {
		this.bg = bg;
	}
	public ConfigWebBase getConfig() {
		return config;
	}
	public void setConfig(ConfigWebBase config) {
		this.config = config;
	}
	
	public Map<Object, Object> getListBox() {
		return listBox;
	}

	public void setListBox(Map<Object, Object> listBox) {
		this.listBox = listBox;
	}

	public Map<Object, Object> getListComponent() {
		return listComponent;
	}

	public void setListComponent(Map<Object, Object> listComponent) {
		this.listComponent = listComponent;
	}

	public ElementCrId getElementCrId() {
		return elementCrId;
	}
	public void setElementCrId(ElementCrId elementCrId) {
		this.elementCrId = elementCrId;
	}
	public int getEn402() {
		return en402;
	}
	public void setEn402(int en402) {
		this.en402 = en402;
	}

	public long getFe400() {
		return fe400;
	}

	public void setFe400(long fe400) {
		this.fe400 = fe400;
	}
	
	public int getEn404() {
		return en404;
	}

	public void setEn404(int en404) {
		this.en404 = en404;
	}
	
	public String getEv405() {
		return ev405;
	}

	public void setEv405(String ev405) {
		this.ev405 = ev405;
	}

	public String getBonevoVersion() {
		return bonevoVersion;
	}

	public void setBonevoVersion(String bonevoVersion) {
		this.bonevoVersion = bonevoVersion;
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	
	public int getEn407() {
		return en407;
	}

	public void setEn407(int en407) {
		this.en407 = en407;
	}

	@Override
	public String toString() {
		return "OhhayLegoWebBase [id=" + id + ", hv101=" + hv101 + ", fo100="
				+ fo100 + ", el446=" + el446 + ", el448=" + el448 + ", ev401="
				+ ev401 + ", en402=" + en402 + ", ev403=" + ev403 + ", en404="
				+ en404 + ", ev405=" + ev405 + ", ev406=" + ev406 + ", bg=" + bg
				+ ", config=" + config + ", elementCrId=" + elementCrId
				+ ", fe400=" + fe400 + ", bonevoVersion=" + bonevoVersion
				+ ", n950mg=" + n950mg + ", listBox=" + listBox
				+ ", listComponent=" + listComponent + "]";
	}
	
	
}
