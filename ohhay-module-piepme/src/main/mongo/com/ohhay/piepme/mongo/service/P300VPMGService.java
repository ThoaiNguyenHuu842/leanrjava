package com.ohhay.piepme.mongo.service;

import java.util.Date;
import java.util.List;

import com.ohhay.piepme.mongo.nestedentities.P300VConInfo;
import com.ohhay.piepme.mongo.nestedentities.P300VDetailInfo;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.nestedentities.VoucherInfo;

/**
 * voucher tu tuc cho DN
 * @author ThoaiNH
 * create Jul 4, 2017
 */
public interface P300VPMGService {
	/**
	 * @param fo100
	 * @param pv301 title
	 * @param pn303 piepder basic type: 1 pieper text, 2 pieper image, 3 pieper link, 4 pieper location
	 * @param pv304 location label, image url or link
	 * @param pv305 pieper text content
	 * @param loc pieper location
	 * @return pieper id created
	 */
	int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, 
					 String pv304Thumb, String pv305, int pn306, float pn309, 
					 String pv314, String otags, List<Integer> listFO100R, List<PieperImg> listImgs,
					 Date vd303, Date vd304, String vv308, int vn309);
	/**
	 * @param pp300
	 * @param vn304
	 * @return
	 */
	int updateVN304(int fo100, int pp300, int vn304);
	/**
	 * @param fo100
	 * @param pvSearch
	 * @param ft300
	 * @param sort
	 * @param pnOffset
	 * @param pnLimit
	 * @return
	 */
	List<P300VConInfo> getListPieperOwner(int fo100, String pvSearch, int sort, int pnOffset, int pnLimit);
	/**
	 * @param p300vmg
	 * @param fo100
	 * @return
	 */
	int pushVoucher(int fo100, int fv300OR, String uuid, String pvLogin);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int storNoTabP300V(int fo100, int pp300, String pvLogin);
	/**
	 * @param fo100
	 * @param fo100b
	 * @param voucherCode
	 * @param pvLogin
	 * @return
	 */
	int useVoucher(int fo100, int fo100b, String voucherCode, String pvLogin);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	P300VDetailInfo getPieperDetail(int fo100,int pp300);
	/**
	 * @param fo100
	 * @param pp300
	 * @return
	 */
	int deActTabV300O(int fo100, int pp300, String vv307,String pvLogin);
	/**
	 * @param fo100
	 * @param pv300
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<VoucherInfo> listAllVoucher(int fo100, int pv300, int offset, int limit);
	/**
	 * @param fo100
	 * @param pv300
	 * @return
	 */
	int getTotalVoucher(int fo100, int pv300);
}
