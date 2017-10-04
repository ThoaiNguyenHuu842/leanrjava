package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH 
 * create: 9:20 am 04/06/2015
 * topic role info
 */
public class M950MG implements Serializable {
	// Viewable (Được xem): 1-Public (Cộng đồng), 2-Friends (Bạn bè), 3-Only me
	// (Cá nhân)
	@Field("MN951")
	private int mn951;
	// Accept comment (Được bình luận): 1-All (Tất cả), 2-Friends (Bạn bè),
	// 3-Disable (Không)
	@Field("MN953")
	private int mn953;
	// Accept share (Nhận chia sẻ): 1-Friends (Bạn bè), 2-Disable (Không)
	@Field("MN952")
	private int mn952;
	// Accept notification: 1-All (Tất cả), 2-Friends (Bạn bè),
	// 3-Disable (Không)
	@Field("MN954")
	private int mn954;

	public int getMn951() {
		return mn951;
	}

	public void setMn951(int mn951) {
		this.mn951 = mn951;
	}

	public int getMn952() {
		return mn952;
	}

	public void setMn952(int mn952) {
		this.mn952 = mn952;
	}

	public int getMn953() {
		return mn953;
	}

	public void setMn953(int mn953) {
		this.mn953 = mn953;
	}

	public int getMn954() {
		return mn954;
	}

	public void setMn954(int mn954) {
		this.mn954 = mn954;
	}

	@Override
	public String toString() {
		return "M950MG [mn951=" + mn951 + ", mn953=" + mn953 + ", mn952="
				+ mn952 + ", mn954=" + mn954 + "]";
	}
	
}
