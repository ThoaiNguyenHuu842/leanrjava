package com.ohhay.web.other.mongo.service;

import java.util.List;

import com.ohhay.web.core.entities.mongo.web.W400MG;

public interface W900MGService {
	W400MG findWebinaris(int fo100);
	List<W400MG> findWebinarRoom(int fo100);
}
