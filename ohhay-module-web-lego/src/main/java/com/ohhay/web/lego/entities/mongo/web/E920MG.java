package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.lego.entities.mongo.base.box.Box;

/**
 * @author ThoaiNH create Oct 13, 2015
 */
@Document(collection = QbMongoCollectionsName.E920)
public class E920MG extends Box implements Serializable {
	@Transient
	private String ownerName;
	@Transient
	private String webName;
	@Transient
	private float voteStarAvg;
	@Transient
	private int totalRate;
	@Transient
	private int reVote;

	public int getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(int totalRate) {
		this.totalRate = totalRate;
	}

	public float getVoteStarAvg() {
		return voteStarAvg;
	}

	public void setVoteStarAvg(float voteStarAvg) {
		this.voteStarAvg = voteStarAvg;
	}

	public E920MG() {
		// TODO Auto-generated constructor stub
	}

	public E920MG(E920MG e) {
		super(e);
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}
	
	public int getReVote() {
		return reVote;
	}

	public void setReVote(int reVote) {
		this.reVote = reVote;
	}

	@Override
	public String toString() {
		return "E920MG [ownerName=" + ownerName + ", webName=" + webName + ", voteStarAvg=" + voteStarAvg
				+ ", totalRate=" + totalRate + ", reVote=" + reVote + "]";
	}

}
