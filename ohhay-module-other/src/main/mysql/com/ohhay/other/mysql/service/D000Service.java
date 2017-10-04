package com.ohhay.other.mysql.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

/**
 * @author ThoaiNH
 * create 16/09/2014
 * category job service
 */
public interface D000Service {
	List<ComtabItem> combTabD000(String pvDv002, String pvLogin);
	List<ComtabItem> combTabD000Regis(String pvDv002, String pvLogin);
}
