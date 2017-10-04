package com.ohhay.core.authentication;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 06/07/2015
 *  all rights of o!hay user, load to session q100 where login success
 */
public class AuthenticationRightInfo implements Serializable{

	/**
	 * Domian redirect 1
	 */
	private boolean DOM_RED_OPT;
	/**
	 * Domian redirect 2
	 */
	private boolean DOM_RED_EXP;
	/**
	 * Domian redirect 3
	 */
	private boolean DOM_RED_PRO;
	/**
	 * unlimited Domain redirect
	 */
	private boolean DOM_RED_DES;
	/**
	 * 1 Newsletter
	 */
	private boolean NEW_LET_FRE;
	/**
	 * 3 Newsletters
	 */
	private boolean NEW_LET_OPT;
	/**
	 * 5 Newsletters
	 */
	private boolean NEW_LET_EXP;
	/**
	 * 10 Newsletters
	 */
	private boolean NEW_LET_PRO;
	/**
	 * unlimited Newsletters
	 */
	private boolean NEW_LET_DES;
	/**
	 * 5 Websites
	 */
	private boolean WEB_SIT_FRE;
	/**
	 * 10 Websites
	 */
	private boolean WEB_SIT_OPT;
	/**
	 * 30 Websites
	 */
	private boolean WEB_SIT_EXP;
	/**
	 * unlimited Websites
	 */
	private boolean WEB_SIT_DES;
	/**
	 * unlimited Websites
	 */
	private boolean WEB_SIT_PRO;
	/**
	 * 1 Project
	 */
	private boolean MER_TAS_FRE;
	/**
	 * 3 Projects
	 */
	private boolean MER_TAS_OPT;
	/**
	 * 10 Projects
	 */
	private boolean MER_TAS_EXP;
	/**
	 * unlimited Projects
	 */
	private boolean MER_TAS_PRO;
	/**
	 * unlimited Projects
	 */
	private boolean MER_TAS_DES;;
	/**
	 * Customer Management
	 */
	private boolean CUS_MAG_DES;
	/**
	 * Shop Online 3 Products
	 */
	private boolean SHO_ONL_FRE;
	/**
	 * Shop Online 20 Products
	 */
	private boolean SHO_ONL_OPT;
	/**
	 * Shop Online Unlimited
	 */
	private boolean SHO_ONL_PRO;
	/**
	 * able to add document
	 */
	private boolean ADD_DOC_ALL;
	public boolean isDOM_RED_OPT() {
		return DOM_RED_OPT;
	}
	public void setDOM_RED_OPT(boolean dOM_RED_OPT) {
		DOM_RED_OPT = dOM_RED_OPT;
	}
	public boolean isDOM_RED_EXP() {
		return DOM_RED_EXP;
	}
	public void setDOM_RED_EXP(boolean dOM_RED_EXP) {
		DOM_RED_EXP = dOM_RED_EXP;
	}
	public boolean isDOM_RED_PRO() {
		return DOM_RED_PRO;
	}
	public void setDOM_RED_PRO(boolean dOM_RED_PRO) {
		DOM_RED_PRO = dOM_RED_PRO;
	}
	public boolean isDOM_RED_DES() {
		return DOM_RED_DES;
	}
	public void setDOM_RED_DES(boolean dOM_RED_DES) {
		DOM_RED_DES = dOM_RED_DES;
	}
	public boolean isNEW_LET_FRE() {
		return NEW_LET_FRE;
	}
	public void setNEW_LET_FRE(boolean nEW_LET_FRE) {
		NEW_LET_FRE = nEW_LET_FRE;
	}
	public boolean isNEW_LET_OPT() {
		return NEW_LET_OPT;
	}
	public void setNEW_LET_OPT(boolean nEW_LET_OPT) {
		NEW_LET_OPT = nEW_LET_OPT;
	}
	public boolean isNEW_LET_EXP() {
		return NEW_LET_EXP;
	}
	public void setNEW_LET_EXP(boolean nEW_LET_EXP) {
		NEW_LET_EXP = nEW_LET_EXP;
	}
	public boolean isNEW_LET_PRO() {
		return NEW_LET_PRO;
	}
	public void setNEW_LET_PRO(boolean nEW_LET_PRO) {
		NEW_LET_PRO = nEW_LET_PRO;
	}
	public boolean isNEW_LET_DES() {
		return NEW_LET_DES;
	}
	public void setNEW_LET_DES(boolean nEW_LET_DES) {
		NEW_LET_DES = nEW_LET_DES;
	}
	public boolean isWEB_SIT_FRE() {
		return WEB_SIT_FRE;
	}
	public void setWEB_SIT_FRE(boolean wEB_SIT_FRE) {
		WEB_SIT_FRE = wEB_SIT_FRE;
	}
	public boolean isWEB_SIT_OPT() {
		return WEB_SIT_OPT;
	}
	public void setWEB_SIT_OPT(boolean wEB_SIT_OPT) {
		WEB_SIT_OPT = wEB_SIT_OPT;
	}
	public boolean isWEB_SIT_EXP() {
		return WEB_SIT_EXP;
	}
	public void setWEB_SIT_EXP(boolean wEB_SIT_EXP) {
		WEB_SIT_EXP = wEB_SIT_EXP;
	}
	public boolean isWEB_SIT_DES() {
		return WEB_SIT_DES;
	}
	public void setWEB_SIT_DES(boolean wEB_SIT_DES) {
		WEB_SIT_DES = wEB_SIT_DES;
	}
	public boolean isWEB_SIT_PRO() {
		return WEB_SIT_PRO;
	}
	public void setWEB_SIT_PRO(boolean wEB_SIT_PRO) {
		WEB_SIT_PRO = wEB_SIT_PRO;
	}
	public boolean isMER_TAS_FRE() {
		return MER_TAS_FRE;
	}
	public void setMER_TAS_FRE(boolean mER_TAS_FRE) {
		MER_TAS_FRE = mER_TAS_FRE;
	}
	public boolean isMER_TAS_OPT() {
		return MER_TAS_OPT;
	}
	public void setMER_TAS_OPT(boolean mER_TAS_OPT) {
		MER_TAS_OPT = mER_TAS_OPT;
	}
	public boolean isMER_TAS_EXP() {
		return MER_TAS_EXP;
	}
	public void setMER_TAS_EXP(boolean mER_TAS_EXP) {
		MER_TAS_EXP = mER_TAS_EXP;
	}
	public boolean isMER_TAS_PRO() {
		return MER_TAS_PRO;
	}
	public void setMER_TAS_PRO(boolean mER_TAS_PRO) {
		MER_TAS_PRO = mER_TAS_PRO;
	}
	public boolean isMER_TAS_DES() {
		return MER_TAS_DES;
	}
	public void setMER_TAS_DES(boolean mER_TAS_DES) {
		MER_TAS_DES = mER_TAS_DES;
	}
	public boolean isCUS_MAG_DES() {
		return CUS_MAG_DES;
	}
	public void setCUS_MAG_DES(boolean cUS_MAG_DES) {
		CUS_MAG_DES = cUS_MAG_DES;
	}
	public boolean isSHO_ONL_FRE() {
		return SHO_ONL_FRE;
	}
	public void setSHO_ONL_FRE(boolean sHO_ONL_FRE) {
		SHO_ONL_FRE = sHO_ONL_FRE;
	}
	public boolean isSHO_ONL_OPT() {
		return SHO_ONL_OPT;
	}
	public void setSHO_ONL_OPT(boolean sHO_ONL_OPT) {
		SHO_ONL_OPT = sHO_ONL_OPT;
	}
	public boolean isSHO_ONL_PRO() {
		return SHO_ONL_PRO;
	}
	public void setSHO_ONL_PRO(boolean sHO_ONL_PRO) {
		SHO_ONL_PRO = sHO_ONL_PRO;
	}
	public boolean isADD_DOC_ALL() {
		return ADD_DOC_ALL;
	}
	public void setADD_DOC_ALL(boolean aDD_DOC_ALL) {
		ADD_DOC_ALL = aDD_DOC_ALL;
	}
	@Override
	public String toString() {
		return "AuthenticationRightInfo [DOM_RED_OPT=" + DOM_RED_OPT
				+ ", DOM_RED_EXP=" + DOM_RED_EXP + ", DOM_RED_PRO="
				+ DOM_RED_PRO + ", DOM_RED_DES=" + DOM_RED_DES
				+ ", NEW_LET_FRE=" + NEW_LET_FRE + ", NEW_LET_OPT="
				+ NEW_LET_OPT + ", NEW_LET_EXP=" + NEW_LET_EXP
				+ ", NEW_LET_PRO=" + NEW_LET_PRO + ", NEW_LET_DES="
				+ NEW_LET_DES + ", WEB_SIT_FRE=" + WEB_SIT_FRE
				+ ", WEB_SIT_OPT=" + WEB_SIT_OPT + ", WEB_SIT_EXP="
				+ WEB_SIT_EXP + ", WEB_SIT_DES=" + WEB_SIT_DES
				+ ", WEB_SIT_PRO=" + WEB_SIT_PRO + ", MER_TAS_FRE="
				+ MER_TAS_FRE + ", MER_TAS_OPT=" + MER_TAS_OPT
				+ ", MER_TAS_EXP=" + MER_TAS_EXP + ", MER_TAS_PRO="
				+ MER_TAS_PRO + ", MER_TAS_DES=" + MER_TAS_DES
				+ ", CUS_MAG_DES=" + CUS_MAG_DES + ", SHO_ONL_FRE="
				+ SHO_ONL_FRE + ", SHO_ONL_OPT=" + SHO_ONL_OPT
				+ ", SHO_ONL_PRO=" + SHO_ONL_PRO + "]";
	}
	
}
