package com.ohhay.web.other.mongo.service;

import java.util.List;

import com.ohhay.core.entities.mongo.videomarketing.V910MG;

public interface V910MGService {
	int changeM940Index(int fo100, int pv910, int videoId, int newIndex);
	int deleteVideo(int fo100, int pv910, int videoId);
	List<V910MG> loadListV910(int fo100);
}
