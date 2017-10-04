package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * date create: 08/07/2015
 */
public class N100OR implements Serializable{
	@QbMapping(name = "PN100", typeMapping = Types.INTEGER)
	private int pn100;
	@QbMapping(name = "NV101", typeMapping = Types.CHAR)
	private String nv101;
	@QbMapping(name = "NV102", typeMapping = Types.CHAR)
	private String nv102;
	@QbMapping(name = "NV103", typeMapping = Types.CHAR)
	private String nv103;
	@QbMapping(name = "NV104", typeMapping = Types.CHAR)
	private String nv104;
	@QbMapping(name = "NV105", typeMapping = Types.CHAR)
	private String nv105;
	@QbMapping(name = "NV106", typeMapping = Types.CHAR)
	private String nv106;
	@QbMapping(name = "NV107", typeMapping = Types.CHAR)
	private String nv107;
	@QbMapping(name = "NV108", typeMapping = Types.CHAR)
	private String nv108;
	@QbMapping(name = "NV109", typeMapping = Types.CHAR)
	private String nv109;
	@QbMapping(name = "NV112", typeMapping = Types.CHAR)
	private String nv112;
	@QbMapping(name = "NV113", typeMapping = Types.CHAR)
	private String nv113;
	@QbMapping(name = "ND114", typeMapping = Types.DATE)
	private Date nd114;
	@QbMapping(name = "NN115", typeMapping = Types.DOUBLE)
	private double nn115;
	@QbMapping(name = "ND117", typeMapping = Types.DATE)
	private Date nd117;
	@QbMapping(name = "NV118", typeMapping = Types.CHAR)
	private String nv118;
	@QbMapping(name = "NV119", typeMapping = Types.CHAR)
	private String nv119;
	@QbMapping(name = "NV126", typeMapping = Types.CHAR)
	private String nv126;
	@QbMapping(name = "NV127", typeMapping = Types.CHAR)
	private String nv127;
	@QbMapping(name = "NV128", typeMapping = Types.CHAR)
	private String nv128;
	@QbMapping(name = "ND129", typeMapping = Types.DATE)
	private Date nd129;
	@QbMapping(name = "NV130", typeMapping = Types.CHAR)
	private String nv130;
	@QbMapping(name = "NV131", typeMapping = Types.CHAR)
	private String nv131;
	@QbMapping(name = "ND132", typeMapping = Types.DATE)
	private Date nd132;
	@QbMapping(name = "NV133", typeMapping = Types.CHAR)
	private String nv133;
	@QbMapping(name = "NV134", typeMapping = Types.CHAR)
	private String nv134;
	@QbMapping(name = "NV135", typeMapping = Types.CHAR)
	private String nv135;
	@QbMapping(name = "NV136", typeMapping = Types.CHAR)
	private String nv136;
	@QbMapping(name = "NV137", typeMapping = Types.CHAR)
	private String nv137;
	@QbMapping(name = "NV138", typeMapping = Types.CHAR)
	private String nv138;
	@QbMapping(name = "NV139", typeMapping = Types.CHAR)
	private String nv139;
	@QbMapping(name = "FV500", typeMapping = Types.INTEGER)
	private int fv500;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "FK100", typeMapping = Types.INTEGER)
	private int fk100;
	@QbMapping(name = "VV503", typeMapping = Types.CHAR)
	private String vv503;
	@QbMapping(name = "NL146", typeMapping = Types.DATE)
	private Date nl146;
	@QbMapping(name = "NL147", typeMapping = Types.CHAR)
	private Date nl147;
	@QbMapping(name = "NL148", typeMapping = Types.DATE)
	private Date nl148;
	@QbMapping(name = "NL149", typeMapping = Types.CHAR)
	private Date nl149;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	public int getPn100() {
		return pn100;
	}
	public void setPn100(int pn100) {
		this.pn100 = pn100;
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
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
	public String getNv104() {
		return nv104;
	}
	public void setNv104(String nv104) {
		this.nv104 = nv104;
	}
	public String getNv105() {
		return nv105;
	}
	public void setNv105(String nv105) {
		this.nv105 = nv105;
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
	public String getNv108() {
		return nv108;
	}
	public void setNv108(String nv108) {
		this.nv108 = nv108;
	}
	public String getNv109() {
		return nv109;
	}
	public void setNv109(String nv109) {
		this.nv109 = nv109;
	}
	public String getNv113() {
		return nv113;
	}
	public void setNv113(String nv113) {
		this.nv113 = nv113;
	}
	public Date getNd114() {
		return nd114;
	}
	public void setNd114(Date nd114) {
		this.nd114 = nd114;
	}
	public Date getNd117() {
		return nd117;
	}
	public void setNd117(Date nd117) {
		this.nd117 = nd117;
	}
	public String getNv118() {
		return nv118;
	}
	public void setNv118(String nv118) {
		this.nv118 = nv118;
	}
	public int getFv500() {
		return fv500;
	}
	public void setFv500(int fv500) {
		this.fv500 = fv500;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFk100() {
		return fk100;
	}
	public void setFk100(int fk100) {
		this.fk100 = fk100;
	}
	public String getVv503() {
		return vv503;
	}
	public void setVv503(String vv503) {
		this.vv503 = vv503;
	}
	public String getNv126() {
		return nv126;
	}
	public void setNv126(String nv126) {
		this.nv126 = nv126;
	}
	public String getNv127() {
		return nv127;
	}
	public void setNv127(String nv127) {
		this.nv127 = nv127;
	}
	public String getNv128() {
		return nv128;
	}
	public void setNv128(String nv128) {
		this.nv128 = nv128;
	}
	public Date getNd129() {
		return nd129;
	}
	public void setNd129(Date nd129) {
		this.nd129 = nd129;
	}
	public String getNv130() {
		return nv130;
	}
	public void setNv130(String nv130) {
		this.nv130 = nv130;
	}
	public String getNv131() {
		return nv131;
	}
	public void setNv131(String nv131) {
		this.nv131 = nv131;
	}
	public Date getNd132() {
		return nd132;
	}
	public void setNd132(Date nd132) {
		this.nd132 = nd132;
	}
	public String getNv133() {
		return nv133;
	}
	public void setNv133(String nv133) {
		this.nv133 = nv133;
	}
	public String getNv134() {
		return nv134;
	}
	public void setNv134(String nv134) {
		this.nv134 = nv134;
	}
	public String getNv135() {
		return nv135;
	}
	public void setNv135(String nv135) {
		this.nv135 = nv135;
	}
	public String getNv136() {
		return nv136;
	}
	public void setNv136(String nv136) {
		this.nv136 = nv136;
	}
	public String getNv137() {
		return nv137;
	}
	public void setNv137(String nv137) {
		this.nv137 = nv137;
	}
	public String getNv138() {
		return nv138;
	}
	public void setNv138(String nv138) {
		this.nv138 = nv138;
	}
	public String getNv139() {
		return nv139;
	}
	public void setNv139(String nv139) {
		this.nv139 = nv139;
	}
	public double getNn115() {
		return nn115;
	}
	public void setNn115(double nn115) {
		this.nn115 = nn115;
	}
	public String getNv112() {
		return nv112;
	}
	public void setNv112(String nv112) {
		this.nv112 = nv112;
	}
	
	public Date getNl146() {
		return nl146;
	}
	public void setNl146(Date nl146) {
		this.nl146 = nl146;
	}
	public Date getNl147() {
		return nl147;
	}
	public void setNl147(Date nl147) {
		this.nl147 = nl147;
	}
	public Date getNl148() {
		return nl148;
	}
	public void setNl148(Date nl148) {
		this.nl148 = nl148;
	}
	public Date getNl149() {
		return nl149;
	}
	public void setNl149(Date nl149) {
		this.nl149 = nl149;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getNv119() {
		return nv119;
	}
	public void setNv119(String nv119) {
		this.nv119 = nv119;
	}
	@Override
	public String toString() {
		return "N100OR [pn100=" + pn100 + ", nv101=" + nv101 + ", nv102="
				+ nv102 + ", nv103=" + nv103 + ", nv104=" + nv104 + ", nv105="
				+ nv105 + ", nv106=" + nv106 + ", nv107=" + nv107 + ", nv108="
				+ nv108 + ", nv109=" + nv109 + ", nv112=" + nv112 + ", nv113="
				+ nv113 + ", nd114=" + nd114 + ", nn115=" + nn115 + ", nd117="
				+ nd117 + ", nv118=" + nv118 + ", nv126=" + nv126 + ", nv127="
				+ nv127 + ", nv128=" + nv128 + ", nd129=" + nd129 + ", nv130="
				+ nv130 + ", nv131=" + nv131 + ", nd132=" + nd132 + ", nv133="
				+ nv133 + ", nv134=" + nv134 + ", nv135=" + nv135 + ", nv136="
				+ nv136 + ", nv137=" + nv137 + ", nv138=" + nv138 + ", nv139="
				+ nv139 + ", fv500=" + fv500 + ", fo100=" + fo100 + ", fk100="
				+ fk100 + ", vv503=" + vv503 + "]";
	}
	
}
