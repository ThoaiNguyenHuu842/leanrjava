package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import org.springframework.data.annotation.Transient;

import com.ohhay.base.dao.QbMapping;

public class V170OR implements Serializable{
	@QbMapping(name = "PV170", typeMapping = Types.INTEGER)
	private int pv170;
	@QbMapping(name = "VD171", typeMapping = Types.DATE)
	private Date vd171;
	@QbMapping(name = "VV172", typeMapping = Types.CHAR)
	private String vv172;
	@QbMapping(name = "VD173", typeMapping = Types.DATE)
	private Date vd173;
	@QbMapping(name = "VV174", typeMapping = Types.CHAR)
	private String vv174;
	@QbMapping(name = "VN175", typeMapping = Types.INTEGER)
	private int vn175;
	@QbMapping(name = "VV176", typeMapping = Types.CHAR)
	private String vv176;
	@QbMapping(name = "VD177", typeMapping = Types.DATE)
	private Date vd177;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "FP100", typeMapping = Types.INTEGER)
	private int fp100;
	@QbMapping(name = "FO100D", typeMapping = Types.INTEGER)
	private int fo100d;
	@QbMapping(name = "VL197", typeMapping = Types.CHAR)
	private String vl197;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name = "VD171", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd171Str;
	@QbMapping(name = "VD173", typeMapping = Types.DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd173Str;
	@QbMapping(name = "VD177", typeMapping = Types.	DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd177Str;
	@QbMapping(name = "VV178", typeMapping = Types.CHAR)
	private String vv178;
	@QbMapping(name = "VD179", typeMapping = Types.DATE)
	private Date vd179;
	@QbMapping(name = "VD179", typeMapping = Types.	DATE, format = true, pattern = "dd-MM-yyyy HH:mm")
	private String vd179Str;
	@Transient
	private String urlAvarta;
	public int getPv170() {
		return pv170;
	}
	public void setPv170(int pv170) {
		this.pv170 = pv170;
	}
	public Date getVd171() {
		return vd171;
	}
	public void setVd171(Date vd171) {
		this.vd171 = vd171;
	}
	public String getVv172() {
		return vv172;
	}
	public void setVv172(String vv172) {
		this.vv172 = vv172;
	}
	public Date getVd173() {
		return vd173;
	}
	public void setVd173(Date vd173) {
		this.vd173 = vd173;
	}
	public String getVv174() {
		return vv174;
	}
	public void setVv174(String vv174) {
		this.vv174 = vv174;
	}
	public int getVn175() {
		return vn175;
	}
	public void setVn175(int vn175) {
		this.vn175 = vn175;
	}
	public String getVv176() {
		return vv176;
	}
	public void setVv176(String vv176) {
		this.vv176 = vv176;
	}
	public Date getVd177() {
		return vd177;
	}
	public void setVd177(Date vd177) {
		this.vd177 = vd177;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFp100() {
		return fp100;
	}
	public void setFp100(int fp100) {
		this.fp100 = fp100;
	}
	public int getFo100d() {
		return fo100d;
	}
	public void setFo100d(int fo100d) {
		this.fo100d = fo100d;
	}
	
	public String getVl197() {
		return vl197;
	}
	public void setVl197(String vl197) {
		this.vl197 = vl197;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getVd171Str() {
		return vd171Str;
	}
	public void setVd171Str(String vd171Str) {
		this.vd171Str = vd171Str;
	}
	public String getVd173Str() {
		return vd173Str;
	}
	public void setVd173Str(String vd173Str) {
		this.vd173Str = vd173Str;
	}
	public String getVd177Str() {
		return vd177Str;
	}
	
	public void setVd177Str(String vd177Str) {
		this.vd177Str = vd177Str;
	}
	public String getVv178() {
		return vv178;
	}
	public void setVv178(String vv178) {
		this.vv178 = vv178;
	}
	public Date getVd179() {
		return vd179;
	}
	public void setVd179(Date vd179) {
		this.vd179 = vd179;
	}
	public String getVd179Str() {
		return vd179Str;
	}
	public void setVd179Str(String vd179Str) {
		this.vd179Str = vd179Str;
	}
	public String getUrlAvarta() {
		return urlAvarta;
	}
	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}
	@Override
	public String toString() {
		return "V170OR [pv170=" + pv170 + ", vd171=" + vd171 + ", vv172="
				+ vv172 + ", vd173=" + vd173 + ", vv174=" + vv174 + ", vn175="
				+ vn175 + ", vv176=" + vv176 + ", vd177=" + vd177 + ", fo100="
				+ fo100 + ", fp100=" + fp100 + ", fo100d=" + fo100d + ", vl197="
				+ vl197 + ", rowss=" + rowss + ", vd171Str=" + vd171Str
				+ ", vd173Str=" + vd173Str + ", vd177Str=" + vd177Str
				+ ", vv178=" + vv178 + ", vd179=" + vd179 + ", vd179Str="
				+ vd179Str + ", urlAvarta=" + urlAvarta + "]";
	}
	
}
