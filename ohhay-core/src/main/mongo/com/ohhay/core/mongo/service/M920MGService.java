package com.ohhay.core.mongo.service;

import java.util.List;

import com.ohhay.core.entities.mongo.profile.M920MG;


public interface M920MGService {
	String checkFriendStatus(int fo100, int fo100c);
	String checkFollowStatus(int fo100, int fo100f);
	List<M920MG> listOfTabM920Wait(int fo100c, int offset, int limit);
	int stornoTabM920(int fo100, int pm920);
	int stornoTabM920F(int fo100, int fo100c);
}
