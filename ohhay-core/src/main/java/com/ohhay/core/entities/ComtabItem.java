package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;
import com.ohhay.core.constant.TemplateRule;

/**
 * @author ThoaiNH
 * create 12/09/2014
 */
public class ComtabItem implements Serializable{
	@QbMapping(name = "VAL", typeMapping = Types.INTEGER)
	private int val;
	@QbMapping(name = "VAL2", typeMapping = Types.INTEGER)
	private int val2;
	@QbMapping(name = "LABEL", typeMapping = Types.CHAR)
	private String label;//name of template
	@QbMapping(name = "LABEL2", typeMapping = Types.CHAR)
	private String label2;
	private String a900Thumbnail; // test fo a900
	public String getCv802Full() {
		if(a900Thumbnail == null)
			return TemplateRule.TEMPLATE_ROOT_PATH+label2+TemplateRule.TEMPLATE_TEMPLATE_SCREENSHOT;
		return a900Thumbnail;
	}
	public String getCv802Preview() {
		return TemplateRule.TEMPLATE_ROOT_PATH+label2+"index.html";
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public int getVal2() {
		return val2;
	}
	public void setVal2(int val2) {
		this.val2 = val2;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getA900Thumbnail() {
		return a900Thumbnail;
	}
	public void setA900Thumbnail(String a900Thumbnail) {
		this.a900Thumbnail = a900Thumbnail;
	}
	@Override
	public String toString() {
		return "ComtabItem [val=" + val + ", val2=" + val2 + ", label=" + label
				+ ", label2=" + label2 + ", a900Thumbnail=" + a900Thumbnail
				+ "]";
	}
	
}
