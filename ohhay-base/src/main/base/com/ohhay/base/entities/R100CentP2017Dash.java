package com.ohhay.base.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;

/**
 * @author TuNt
 * create date May 22, 2017
 * ohhay-base
 */
public class R100CentP2017Dash implements Serializable{
	@QbMapping(name = "REACH", typeMapping = Types.DECIMAL)
	private BigDecimal reach;
	@QbMapping(name = "VIEWS", typeMapping = Types.DECIMAL)
	private BigDecimal views;
	@QbMapping(name = "LIKES", typeMapping = Types.DECIMAL)
	private BigDecimal likes;
	@QbMapping(name = "FOLLO", typeMapping = Types.DECIMAL)
	private BigDecimal follo;
	@QbMapping(name = "PIEPS", typeMapping = Types.DECIMAL)
	private BigDecimal pieps;
	@QbMapping(name = "PIRAT", typeMapping = Types.DECIMAL)
	private BigDecimal pirat;
	@QbMapping(name = "DAYSS", typeMapping = Types.INTEGER)
	private int dayss;
	@QbMapping(name = "VIREA", typeMapping = Types.DECIMAL)
	private BigDecimal virea;
	@QbMapping(name = "ENGAG", typeMapping = Types.DECIMAL)
	private BigDecimal engag;
	@QbMapping(name = "REAMO", typeMapping = Types.INTEGER)
	private int reamo;
	@QbMapping(name = "VIEMO", typeMapping = Types.INTEGER)
	private int viemo;
	@QbMapping(name = "LIKMO", typeMapping = Types.INTEGER)
	private int likmo;
	@QbMapping(name = "FOLMO", typeMapping = Types.INTEGER)
	private int folmo;
	@QbMapping(name = "REALA", typeMapping = Types.DECIMAL)
	private BigDecimal reala;
	@QbMapping(name = "VIELA", typeMapping = Types.DECIMAL)
	private BigDecimal viela;
	@QbMapping(name = "LIKLA", typeMapping = Types.DECIMAL)
	private BigDecimal likla;
	@QbMapping(name = "FOLLA", typeMapping = Types.DECIMAL)
	private BigDecimal folla;
	@QbMapping(name = "TAGVI", typeMapping = Types.CHAR)
	private String tagvi;
	public BigDecimal getReach() {
		return reach;
	}
	public void setReach(BigDecimal reach) {
		this.reach = reach;
	}
	public BigDecimal getViews() {
		return views;
	}
	public void setViews(BigDecimal views) {
		this.views = views;
	}
	public BigDecimal getLikes() {
		return likes;
	}
	public void setLikes(BigDecimal likes) {
		this.likes = likes;
	}
	public BigDecimal getFollo() {
		return follo;
	}
	public void setFollo(BigDecimal follo) {
		this.follo = follo;
	}
	public BigDecimal getPieps() {
		return pieps;
	}
	public void setPieps(BigDecimal pieps) {
		this.pieps = pieps;
	}
	public BigDecimal getPirat() {
		return pirat;
	}
	public void setPirat(BigDecimal pirat) {
		this.pirat = pirat;
	}
	public int getDayss() {
		return dayss;
	}
	public void setDayss(int dayss) {
		this.dayss = dayss;
	}
	public BigDecimal getVirea() {
		return virea;
	}
	public void setVirea(BigDecimal virea) {
		this.virea = virea;
	}
	public BigDecimal getEngag() {
		return engag;
	}
	public void setEngag(BigDecimal engag) {
		this.engag = engag;
	}
	public int getReamo() {
		return reamo;
	}
	public void setReamo(int reamo) {
		this.reamo = reamo;
	}
	public int getViemo() {
		return viemo;
	}
	public void setViemo(int viemo) {
		this.viemo = viemo;
	}
	public int getLikmo() {
		return likmo;
	}
	public void setLikmo(int likmo) {
		this.likmo = likmo;
	}
	public int getFolmo() {
		return folmo;
	}
	public void setFolmo(int folmo) {
		this.folmo = folmo;
	}
	public BigDecimal getReala() {
		return reala;
	}
	public void setReala(BigDecimal reala) {
		this.reala = reala;
	}
	public BigDecimal getViela() {
		return viela;
	}
	public void setViela(BigDecimal viela) {
		this.viela = viela;
	}
	public BigDecimal getLikla() {
		return likla;
	}
	public void setLikla(BigDecimal likla) {
		this.likla = likla;
	}
	public BigDecimal getFolla() {
		return folla;
	}
	public void setFolla(BigDecimal folla) {
		this.folla = folla;
	}
	public String getTagvi() {
		return tagvi;
	}
	public void setTagvi(String tagvi) {
		this.tagvi = tagvi;
	}
	@Override
	public String toString() {
		return "R100CentP2017Dash [reach=" + reach + ", views=" + views + ", likes=" + likes + ", follo=" + follo + ", pieps=" + pieps + ", pirat=" + pirat
				+ ", dayss=" + dayss + ", virea=" + virea + ", engag=" + engag + ", reamo=" + reamo + ", viemo=" + viemo + ", likmo=" + likmo + ", folmo="
				+ folmo + ", reala=" + reala + ", viela=" + viela + ", likla=" + likla + ", folla=" + folla + ", tagvi=" + tagvi + "]";
	}
}
