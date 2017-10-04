package com.ohhay.core.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class N700MG implements Serializable {
	@Id
	private int id;

	private Map<String, Integer> mapNotifyType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Integer> getMapNotifyType() {
		return mapNotifyType;
	}

	public void setMapNotifyType(Map<String, Integer> mapNotifiyType) {
		this.mapNotifyType = mapNotifiyType;
	}

	@Override
	public String toString() {
		return "N700MG [id=" + id + ", mapNotifiyType=" + mapNotifyType + "]";
	}
	
}
