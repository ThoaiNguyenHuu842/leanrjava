package com.ohhay.base.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ThoaiNH
 * create 21/09/2015
 * decrypt AES string from mysql
 */
public class AESCenterUtil {
	/**
	 * @param key
	 * @param encoding
	 * @return
	 */
	public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for(byte b : key.getBytes(encoding))
				finalKey[i++%16] ^= b;			
			return new SecretKeySpec(finalKey, "AES");
		} catch(UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * @param s
	 * @param fo100
	 * @return
	 */
	public static String decrypt(String s, Object objectID){
		Cipher decryptCipher;
		try {
			decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(objectID.toString() + "QB72","latin1"));
			return new String(decryptCipher.doFinal(s.toString().getBytes("latin1")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     		
		return null;
	}
}	
