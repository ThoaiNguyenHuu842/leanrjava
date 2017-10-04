package com.ohhay.base.util;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ohhay.base.constant.ApplicationConstant;

/**
 * @author ThoaiNH
 * crate 22/11/2014
 * AES util
 */
public class AESUtils {
	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(ApplicationConstant.AES_KEY_2.trim().getBytes("UTF-8"));

			SecretKeySpec skeySpec = new SecretKeySpec(ApplicationConstant.AES_KEY_1.trim().getBytes("UTF-8"),
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

	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(ApplicationConstant.AES_KEY_2.trim().getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(ApplicationConstant.AES_KEY_1.trim().getBytes("UTF-8"),
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
}
