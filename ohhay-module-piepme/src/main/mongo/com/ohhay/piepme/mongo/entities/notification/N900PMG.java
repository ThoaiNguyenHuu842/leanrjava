package com.ohhay.piepme.mongo.entities.notification;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/**
 * @author ThoaiNH
 * create Jun 20, 2017
 */
@Document(collection = QbMongoCollectionsName.N900)
public class N900PMG implements Serializable {
	@Id
	private int id;
	@Field("MAP_NOTIFICATION")
	private Map<String, Integer> mapNotification;
	public int getId() {
		return id;
	}
	public Map<String, Integer> getMapNotification() {
		return mapNotification;
	}
	public void setMapNotification(Map<String, Integer> mapNotification) {
		this.mapNotification = mapNotification;
	}
	public void setId(int id) {
		this.id = id;
	}
}
