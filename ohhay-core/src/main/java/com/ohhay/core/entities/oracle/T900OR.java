package com.ohhay.core.entities.oracle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiVt Mar 28, 2017
 */
public class T900OR implements Serializable {
	
	@QbMapping(name = "PT900", typeMapping = Types.INTEGER)
	private int pt900;
	@QbMapping(name = "TN901", typeMapping = Types.DOUBLE)
	private double tn901;
	@QbMapping(name = "TV902", typeMapping = Types.CHAR)
	private String tv902;
	@QbMapping(name = "TD903", typeMapping = Types.DATE)
	private Date td903;
	@QbMapping(name = "TD903", typeMapping = Types.CHAR)
	private String td903Str;
	@QbMapping(name = "TD904", typeMapping = Types.DATE)
	private Date td904;
	@QbMapping(name = "TD904", typeMapping = Types.CHAR)
	private String td904Str;
	@QbMapping(name = "TV905", typeMapping = Types.CHAR)
	private String tv905;
	@QbMapping(name = "TV906", typeMapping = Types.CHAR)
	private String tv906;
	@QbMapping(name = "FT000", typeMapping = Types.INTEGER)
	private int ft100;
	@QbMapping(name = "FN100", typeMapping = Types.INTEGER)
	private int fn100;
	@QbMapping(name = "FO100", typeMapping = Types.INTEGER)
	private int fo100;
	@QbMapping(name = "FT800", typeMapping = Types.INTEGER)
	private int ft800;
	@QbMapping(name = "TL927", typeMapping = Types.CHAR)
	private String tl927;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	public int getPt900() {
		return pt900;
	}
	public void setPt900(int pt900) {
		this.pt900 = pt900;
	}
	public double getTn901() {
		return tn901;
	}
	public void setTn901(double tn901) {
		this.tn901 = tn901;
	}
	public String getTv902() {
		return tv902;
	}
	public void setTv902(String tv902) {
		this.tv902 = tv902;
	}
	public Date getTd903() {
		return td903;
	}
	public void setTd903(Date td903) {
		this.td903 = td903;
	}
	public Date getTd904() {
		return td904;
	}
	public void setTd904(Date td904) {
		this.td904 = td904;
	}
	public String getTv905() {
		return tv905;
	}
	public void setTv905(String tv905) {
		this.tv905 = tv905;
	}
	public String getTv906() {
		return tv906;
	}
	public void setTv906(String tv906) {
		this.tv906 = tv906;
	}
	public int getFt100() {
		return ft100;
	}
	public void setFt100(int ft100) {
		this.ft100 = ft100;
	}
	public int getFn100() {
		return fn100;
	}
	public void setFn100(int fn100) {
		this.fn100 = fn100;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getFt800() {
		return ft800;
	}
	public void setFt800(int ft800) {
		this.ft800 = ft800;
	}
	public String getTl927() {
		return tl927;
	}
	public void setTl927(String tl927) {
		this.tl927 = tl927;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getTd903Str() {
		return td903Str;
	}
	public void setTd903Str(String td903Str) {
		td903Str = td903Str.split("\\.")[0];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			java.util.Date date = formatter.parse(td903Str);
			this.td903Str = formatter2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getTd904Str() {
		return td904Str;
	}
	public void setTd904Str(String td904Str) {
		td904Str = td904Str.split("\\.")[0];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			java.util.Date date = formatter.parse(td904Str);
			this.td904Str = formatter2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "T900OR [pt900=" + pt900 + ", tn901=" + tn901 + ", tv902=" + tv902 + ", td903=" + td903 + ", td904="
				+ td904 + ", tv905=" + tv905 + ", tv906=" + tv906 + ", ft100=" + ft100 + ", fn100=" + fn100 + ", fo100="
				+ fo100 + ", ft800=" + ft800 + ", tl927=" + tl927 + "]";
	}
	
	
}
