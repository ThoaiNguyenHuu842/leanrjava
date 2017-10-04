package com.ohhay.web.other.api.service;

import java.util.List;

import com.ohhay.web.core.entities.V250;

public interface BsellService {
	//tungns: kiem tra key cho bsell
	int checkTabV050Key(String key);
	//tungns: khi key dung thi tra ra danh sach
    List<V250> listOfTabV250KEY(String key);
    //tungns: tao landing page cho bsell tai ohay!
    int createBsellpage(int po100, String ov101, String key);
    // tao sctipr cho bsell
    String printJavaScript();
}
