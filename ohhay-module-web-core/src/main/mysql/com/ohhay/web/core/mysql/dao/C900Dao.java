package com.ohhay.web.core.mysql.dao;

import java.util.List;

import com.ohhay.web.core.entities.C900;


public interface C900Dao {
	List<C900> chayListOfTabC900(int pnFC900, int pnFC850, int pnFC920, String pvLOGIN);
}
