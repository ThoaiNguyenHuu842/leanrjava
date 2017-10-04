package com.ohhay.web.other.api.dao;
import java.util.List;

import com.ohhay.web.core.entities.V250;
public interface BsellDao {
	//tungns: kiem tra key cho bsell
	int checkTabV050Key(String key);
	//tungns: khi key dung thi tra ra danh sach
    List<V250> listOfTabV250KEY(String key);
    // tao sctipr cho bsell
    String printJavaScript();
}
