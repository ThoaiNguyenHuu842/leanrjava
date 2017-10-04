package com.ohhay.other.api.service;

import com.ohhay.core.entities.Q100;

/**
 * @author ThoaiNH
 *
 */
public interface ShopService {
	/**
	 * @param q100
	 * @param menuName
	 * @param menuIcon
	 * @return -2 if name, email not fill
	 */
	int createWebShop(Q100 q100,String menuName, String menuIcon);
}
