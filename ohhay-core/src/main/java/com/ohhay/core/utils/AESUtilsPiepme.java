package com.ohhay.core.utils;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author ThoaiNH
 * crate 22/11/2014
 * AES utils for Piepme cryptography
 */
public class AESUtilsPiepme {
	private String key;
	private String ivString;
	public AESUtilsPiepme(String key, String iv) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.ivString = iv;
	}
	public  String encrypt(String value) {
		System.out.println(key.length());
		try {
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

			SecretKeySpec skeySpec = new SecretKeySpec(ivString.getBytes("UTF-8"),
					"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public  String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(ivString.getBytes("UTF-8"),
					"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
			return new String(original,Charset.forName("UTF-8"));
		} catch (Exception ex) {
			System.err.print("AESUtils: string input can not be encrypted");
		}
		return null;
	}
	
	/**
	 * @param uiid
	 * @param fo100
	 * @return
	 */
	public static String createPiepmeKey(String uiid, int fo100){
		String tempKey = uiid.substring(0, 16);
		int fn100Length = String.valueOf(fo100).length();
		StringBuilder key2 = new StringBuilder(); 
		for(int i = 0; i < 16 - fn100Length; i++)
			key2.append("0");
		key2.append(fo100);
		AESUtilsPiepme aesUtilsPiepme = new AESUtilsPiepme(key2.toString(), key2.toString());
		return aesUtilsPiepme.encrypt(tempKey);
	}
}
