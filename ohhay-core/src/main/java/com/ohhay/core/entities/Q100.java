package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.util.List;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbMapping;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.shop.J910MG;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;

/**
 * @author ThoaiNH
 * create 08/07/2014
 * user info 
 */
public class Q100 implements Serializable {
	private List<LanguageMG> listLanguages;
	private M900MG m900mg;
	private boolean useApp;
	private int packet;
	/*
	 * user right info
	 */
	private AuthenticationRightInfo authenticationRightInfo;
	/*
	 * attribute send to app
	 */
	private String draftPageThumb;
	private List<V910MG> listV910mgs;
	private J910MG j910mg;
	/*
	 * end attribute send to app
	 */
	@QbMapping(name = "pq100", typeMapping = Types.INTEGER)
	private int pq100;
	@QbMapping(name = "qv101", typeMapping = Types.CHAR)
	private String qv101;
	@QbMapping(name = "qv102", typeMapping = Types.CHAR)
	private String qv102;
	@QbMapping(name = "qv109", typeMapping = Types.CHAR)
	private String qv109;// key packet
	@QbMapping(name = "qd103", typeMapping = Types.DATE)
	private Date qd103;
	@QbMapping(name = "qd104", typeMapping = Types.DATE)
	private Date qd104; //han su dung
	@QbMapping(name = "qv105", typeMapping = Types.CHAR)
	private String qv105;
	@QbMapping(name = "qv112", typeMapping = Types.CHAR)
	private String qv112;
	@QbMapping(name = "qv113", typeMapping = Types.CHAR)
	private String qv113;
	@QbMapping(name = "fc800", typeMapping = Types.INTEGER)
	private int fc800;
	@QbMapping(name = "fn100", typeMapping = Types.INTEGER)
	private int fn100;
	@QbMapping(name = "po100", typeMapping = Types.INTEGER)
	private int po100;
	@QbMapping(name = "ov101", typeMapping = Types.CHAR)
	private String ov101;
	@QbMapping(name = "ov102", typeMapping = Types.CHAR)
	private String ov102;
	@QbMapping(name = "ov103", typeMapping = Types.CHAR)
	private String ov103;
	@QbMapping(name = "ov108", typeMapping = Types.CHAR)
	private String ov108;
	@QbMapping(name = "nv116", typeMapping = Types.CHAR)
	private String nv116;
	@QbMapping(name = "nv117", typeMapping = Types.CHAR)
	private String nv117;
	@QbMapping(name = "nv118", typeMapping = Types.CHAR)
	private String nv118;
	@QbMapping(name = "nv119", typeMapping = Types.CHAR)
	private String nv119;
	@QbMapping(name = "nv120", typeMapping = Types.CHAR)
	private String nv120;
	@QbMapping(name = "KONTO", typeMapping = Types.CHAR)
	private String konto;
	public String getQv101() {
		return qv101;
	}

	public void setQv101(String qv101) {
		this.qv101 = qv101;
	}

	public String getQv102() {
		return qv102;
	}

	public void setQv102(String qv102) {
		this.qv102 = qv102;
	}

	public Date getQd103() {
		return qd103;
	}

	public void setQd103(Date qd103) {
		this.qd103 = qd103;
	}

	public Date getQd104() {
		return qd104;
	}

	public void setQd104(Date qd104) {
		this.qd104 = qd104;
	}

	public int getPo100() {
		return po100;
	}

	public void setPo100(int po100) {
		this.po100 = po100;
	}

	public String getOv101() {
		return ov101;
	}

	public void setOv101(String ov101) {
		this.ov101 = ov101;
	}

	public String getOv102() {
		return ov102;
	}

	public void setOv102(String ov102) {
		this.ov102 = ov102;
	}

	public String getOv103() {
		return ov103;
	}

	public void setOv103(String ov103) {
		this.ov103 = ov103;
	}

	public boolean isUseApp() {
		return useApp;
	}

	public void setUseApp(boolean useApp) {
		this.useApp = useApp;
	}

	public M900MG getM900mg() {
		return m900mg;
	}

	public void setM900mg(M900MG m900mg) {
		this.m900mg = m900mg;
	}

	public int getFc800() {
		return fc800;
	}

	public void setFc800(int fc800) {
		this.fc800 = fc800;
	}

	public int getFn100() {
		return fn100;
	}

	public void setFn100(int fn100) {
		this.fn100 = fn100;
	}

	public String getDraftPageThumb() {
		return draftPageThumb;
	}

	public void setDraftPageThumb(String draftPageThumb) {
		this.draftPageThumb = draftPageThumb;
	}

	public List<V910MG> getListV910mgs() {
		return listV910mgs;
	}

	public void setListV910mgs(List<V910MG> listV910mgs) {
		this.listV910mgs = listV910mgs;
	}

	public J910MG getJ910mg() {
		return j910mg;
	}

	public void setJ910mg(J910MG j910mg) {
		this.j910mg = j910mg;
	}

	public AuthenticationRightInfo getAuthenticationRightInfo() {
		return authenticationRightInfo;
	}

	public void setAuthenticationRightInfo(AuthenticationRightInfo authenticationRightInfo) {
		this.authenticationRightInfo = authenticationRightInfo;
	}
	
	public String getQv109() {
		return qv109;
	}

	public void setQv109(String qv109) {
		this.qv109 = qv109;
	}
	
	public void setPacket(int packet) {
		this.packet = packet;
	}
	
	public int getPacket() {
		if (qv109.equals(ApplicationConstant.PACKET_OPTIMAL))
			return 2;
		else if (qv109.equals(ApplicationConstant.PACKET_EXPERT))
			return 3;
		else if (qv109.equals(ApplicationConstant.PACKET_PRO))
			return 4;
		else if (qv109.equals(ApplicationConstant.PACKET_DESIGNER))
			return 5;
		else
			return 1;
	}
	
	public String getNv116() {
		return nv116;
	}

	public void setNv116(String nv116) {
		this.nv116 = nv116;
	}

	public String getNv117() {
		return nv117;
	}

	public void setNv117(String nv117) {
		this.nv117 = nv117;
	}

	public String getNv118() {
		return nv118;
	}

	public void setNv118(String nv118) {
		this.nv118 = nv118;
	}

	public String getNv119() {
		return nv119;
	}

	public void setNv119(String nv119) {
		this.nv119 = nv119;
	}

	public String getNv120() {
		return nv120;
	}

	public void setNv120(String nv120) {
		this.nv120 = nv120;
	}
	
	public List<LanguageMG> getListLanguages() {
		return listLanguages;
	}

	public void setListLanguages(List<LanguageMG> listLanguages) {
		this.listLanguages = listLanguages;
	}
	
	public String getOv108() {
		return ov108;
	}

	public void setOv108(String ov108) {
		this.ov108 = ov108;
	}
	
	public int getPq100() {
		return pq100;
	}

	public void setPq100(int pq100) {
		this.pq100 = pq100;
	}

	public String getKonto() {
		return konto;
	}

	public void setKonto(String konto) {
		this.konto = konto;
	}

	public String getQv105() {
		return qv105;
	}

	public void setQv105(String qv105) {
		this.qv105 = qv105;
	}
	
	public String getQv112() {
		return qv112;
	}

	public void setQv112(String qv112) {
		this.qv112 = qv112;
	}
	
	public String getQv113() {
		return qv113;
	}

	public void setQv113(String qv113) {
		this.qv113 = qv113;
	}

	@Override
	public String toString() {
		return "Q100 [listLanguages=" + listLanguages + ", m900mg=" + m900mg
				+ ", useApp=" + useApp + ", packet=" + packet
				+ ", authenticationRightInfo=" + authenticationRightInfo
				+ ", draftPageThumb=" + draftPageThumb + ", listV910mgs="
				+ listV910mgs + ", j910mg=" + j910mg + ", pq100=" + pq100
				+ ", qv101=" + qv101 + ", qv102=" + qv102 + ", qv109=" + qv109
				+ ", qd103=" + qd103 + ", qd104=" + qd104 + ", qv105=" + qv105
				+ ", fc800=" + fc800 + ", fn100=" + fn100 + ", po100=" + po100
				+ ", ov101=" + ov101 + ", ov102=" + ov102 + ", ov103=" + ov103
				+ ", ov108=" + ov108 + ", nv116=" + nv116 + ", nv117=" + nv117
				+ ", nv118=" + nv118 + ", nv119=" + nv119 + ", nv120=" + nv120
				+ ", konto=" + konto + "]";
	}
}
