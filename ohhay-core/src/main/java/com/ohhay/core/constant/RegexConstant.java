package com.ohhay.core.constant;

/**
 * @author ThoaiNH
 * create 03/03/2015
 * all regex pattern using in application
 */
public class RegexConstant{

	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String COLOR_PATTERN = "#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})";
	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32})";
	public static final String FRIENDLYKEY_PATTERN = "[a-zA-Z0-9-.]+";
}