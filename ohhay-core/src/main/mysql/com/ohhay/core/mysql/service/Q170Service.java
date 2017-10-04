package com.ohhay.core.mysql.service;

import java.util.List;

import com.ohhay.core.entities.Q170;

/**
 * @author ThoaiNH
 * date create: 06/07/2015
 * load list right of user
 */ 
public interface Q170Service {
	List<Q170> listOfTabQ170(int fo100, int fq300, int fq400, String pvLogin);
}
