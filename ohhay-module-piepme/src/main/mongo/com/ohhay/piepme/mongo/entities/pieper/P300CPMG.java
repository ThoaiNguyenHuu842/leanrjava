package com.ohhay.piepme.mongo.entities.pieper;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.G100SummaryInfo;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * MONGODB COLLECTION - circle pieper
 * @author ThoaiNH
 * create Nov 5, 2016
 * 
 */
@Document(collection = QbMongoCollectionsName.P300C)
public class P300CPMG extends Pieper{
	@Field("FG100S")
	private List<Integer> listFG100S;
	@Transient
	private int views;
	@Field("FO100S")
	private List<Integer> listFO100S;
	@Transient
	private List<N100SummaryInfo> recipients;
	@Transient
	private List<G100SummaryInfo> groups;
	@Transient
	private int totalRecipients;
	public List<Integer> getListFG100S() {
		return listFG100S;
	}

	public void setListFG100S(List<Integer> listFG100S) {
		this.listFG100S = listFG100S;
	}

	public List<Integer> getListFO100S() {
		return listFO100S;
	}

	public void setListFO100S(List<Integer> listFO100S) {
		this.listFO100S = listFO100S;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<N100SummaryInfo> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<N100SummaryInfo> recipients) {
		this.recipients = recipients;
	}

	public List<G100SummaryInfo> getGroups() {
		return groups;
	}

	public void setGroups(List<G100SummaryInfo> groups) {
		this.groups = groups;
	}

	public int getTotalRecipients() {
		return totalRecipients;
	}

	public void setTotalRecipients(int totalRecipients) {
		this.totalRecipients = totalRecipients;
	}
	
}
