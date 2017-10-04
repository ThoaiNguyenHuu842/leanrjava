package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

public interface D000Dao {
	List<ComtabItem> combTabD000(String pvDv002, String pvLogin);
	List<ComtabItem> combTabD000Regis(String pvDv002, String pvLogin);
}
