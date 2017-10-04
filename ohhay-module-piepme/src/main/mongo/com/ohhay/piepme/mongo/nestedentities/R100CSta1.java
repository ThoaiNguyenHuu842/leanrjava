package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.List;

import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Mar 25, 2017
 * report for circle pieper
 */
public class R100CSta1 implements Serializable{
	private int totalViews;
	private int totalLikes;
	private int totalReaches;
	private List<R100PSta01Sum> piepsSta;
	private List<N100PMG> listUsersLike;
	private List<N100PMG> listUsersView;
	public int getTotalViews() {
		return totalViews;
	}
	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}
	public int getTotalLikes() {
		return totalLikes;
	}
	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}
	public List<R100PSta01Sum> getPiepsSta() {
		return piepsSta;
	}
	public void setPiepsSta(List<R100PSta01Sum> piepsSta) {
		this.piepsSta = piepsSta;
	}
	public List<N100PMG> getListUsersLike() {
		return listUsersLike;
	}
	public void setListUsersLike(List<N100PMG> listUsersLike) {
		this.listUsersLike = listUsersLike;
	}
	public List<N100PMG> getListUsersView() {
		return listUsersView;
	}
	public void setListUsersView(List<N100PMG> listUsersView) {
		this.listUsersView = listUsersView;
	}
	public int getTotalReaches() {
		return totalReaches;
	}
	public void setTotalReaches(int totalReaches) {
		this.totalReaches = totalReaches;
	}
	@Override
	public String toString() {
		return "R100CSta1 [totalViews=" + totalViews + ", totalLikes="
				+ totalLikes + ", totalReaches=" + totalReaches + ", piepsSta="
				+ piepsSta + ", listUsersLike=" + listUsersLike
				+ ", listUsersView=" + listUsersView + "]";
	}
	
}
