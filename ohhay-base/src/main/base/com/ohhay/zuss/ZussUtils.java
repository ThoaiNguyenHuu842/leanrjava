package com.ohhay.zuss;

/**
 * @author ThoaiNH
 * create 08/09/2014
 * color mapper of ZUSS
 */
public class ZussUtils {
	public static String M_COLOR;//mau web hien tai
	public static String O_COLOR;//mau chinh chu
	public static String getColor1() {
		if (M_COLOR == null || M_COLOR.trim().length() == 0)
			return "#6e6e6e";
		return M_COLOR;
	}
	public static String getColor0() {
		if (O_COLOR == null || O_COLOR.trim().length() == 0)
			return "#6e6e6e";
		return O_COLOR;
	}
}
