package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author ThoaiNH
 * create 02/02/2015
 */
@SuppressWarnings("serial")
public class N850 implements Serializable {
	/*
	 * CHU Y LA KHAI BAO BIEN KHONG DUOC VIET HOA.
	 */
	@QbMapping(name = "VAL", typeMapping = Types.INTEGER)
	private int val;
	@QbMapping(name = "LABEL", typeMapping = Types.CHAR)
	private String label;
	@QbMapping(name = "pn850", typeMapping = Types.INTEGER)
	private int pn850;

	@QbMapping(name = "nv851", typeMapping = Types.CHAR)
	private String nv851;

	@QbMapping(name = "nv852", typeMapping = Types.CHAR)
	private String nv852;

	@QbMapping(name = "nv853", typeMapping = Types.CHAR)
	private String nv853;

	@QbMapping(name = "nv854", typeMapping = Types.CHAR)
	private String nv854;

	@QbMapping(name = "nv855", typeMapping = Types.CHAR)
	private String nv855;

	@QbMapping(name = "nl895", typeMapping = Types.DATE)
	private Date nl895;

	@QbMapping(name = "nl896", typeMapping = Types.DATE)
	private Date nl896;

	@QbMapping(name = "nl898", typeMapping = Types.DATE)
	private Date nl898;

	@QbMapping(name = "nl897", typeMapping = Types.CHAR)
	private String nl897;

	@QbMapping(name = "nl899", typeMapping = Types.CHAR)
	private String nl899;

	@QbMapping(name = "fs200", typeMapping = Types.INTEGER)
	private int fs200;

	@QbMapping(name = "fh000", typeMapping = Types.INTEGER)
	private int fh000;

	@QbMapping(name = "fh200", typeMapping = Types.INTEGER)
	private int fh200;

	@QbMapping(name = "sl", typeMapping = Types.INTEGER)
	private int sl;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getPn850() {
		return pn850;
	}

	public void setPn850(int pn850) {
		this.pn850 = pn850;
	}

	public String getNv851() {
		return nv851;
	}

	public void setNv851(String nv851) {
		this.nv851 = nv851;
	}

	public String getNv852() {
		return nv852;
	}

	public void setNv852(String nv852) {
		this.nv852 = nv852;
	}

	public String getNv853() {
		return nv853;
	}

	public void setNv853(String nv853) {
		this.nv853 = nv853;
	}

	public String getNv854() {
		return nv854;
	}

	public void setNv854(String nv854) {
		this.nv854 = nv854;
	}

	public String getNv855() {
		return nv855;
	}

	public void setNv855(String nv855) {
		this.nv855 = nv855;
	}

	public Date getNl895() {
		return nl895;
	}

	public void setNl895(Date nl895) {
		this.nl895 = nl895;
	}

	public Date getNl896() {
		return nl896;
	}

	public void setNl896(Date nl896) {
		this.nl896 = nl896;
	}

	public Date getNl898() {
		return nl898;
	}

	public void setNl898(Date nl898) {
		this.nl898 = nl898;
	}

	public String getNl897() {
		return nl897;
	}

	public void setNl897(String nl897) {
		this.nl897 = nl897;
	}

	public String getNl899() {
		return nl899;
	}

	public void setNl899(String nl899) {
		this.nl899 = nl899;
	}

	public int getFs200() {
		return fs200;
	}

	public void setFs200(int fs200) {
		this.fs200 = fs200;
	}

	public int getFh000() {
		return fh000;
	}

	public void setFh000(int fh000) {
		this.fh000 = fh000;
	}

	public int getFh200() {
		return fh200;
	}

	public void setFh200(int fh200) {
		this.fh200 = fh200;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

}
