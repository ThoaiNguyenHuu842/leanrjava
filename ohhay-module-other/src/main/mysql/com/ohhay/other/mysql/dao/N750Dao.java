package com.ohhay.other.mysql.dao;

import java.util.List;

import com.ohhay.other.entities.ItemTabN750;


public interface N750Dao {
	List<ItemTabN750> nhayCombTabN750(String pvLOGIN);
	List<ItemTabN750> nhayCombTabN750Set(String pvLOGIN);
	List<ItemTabN750> listOfTabN750(String inLocation);
}
