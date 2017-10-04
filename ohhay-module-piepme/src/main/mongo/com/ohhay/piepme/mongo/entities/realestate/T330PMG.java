package com.ohhay.piepme.mongo.entities.realestate;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Jun 21, 2017
 */
@Document(collection = QbMongoCollectionsName.T330)
public class T330PMG implements Serializable{
	@Id
	private int id;
	@Field("TN331")
	private long tn331;
	@Field("TN332")
	private long tn332;
	@Field("TV333")
	private String tv333;
	@Field("FT310")
	private int ft310;
	@Field("FT320")
	private int ft320;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTn331() {
		return tn331;
	}
	public void setTn331(long tn331) {
		this.tn331 = tn331;
	}
	public long getTn332() {
		return tn332;
	}
	public void setTn332(long tn332) {
		this.tn332 = tn332;
	}
	public String getTv333() {
		return tv333;
	}
	public void setTv333(String tv333) {
		this.tv333 = tv333;
	}
	public int getFt310() {
		return ft310;
	}
	public void setFt310(int ft310) {
		this.ft310 = ft310;
	}
	public int getFt320() {
		return ft320;
	}
	public void setFt320(int ft320) {
		this.ft320 = ft320;
	}
	@Override
	public String toString() {
		return "T330PMG [id=" + id + ", tn331=" + tn331 + ", tn332=" + tn332
				+ ", tv333=" + tv333 + ", ft310=" + ft310 + ", ft320=" + ft320
				+ "]";
	}
	
}
