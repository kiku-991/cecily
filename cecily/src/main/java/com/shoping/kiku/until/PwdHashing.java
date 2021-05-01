package com.shoping.kiku.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PwdHashing {
	public static String pwdEnCode(String pwd) {
		try {

			MessageDigest enCode = MessageDigest.getInstance("MD5");

			byte[] res = enCode.digest(pwd.getBytes());

			int[] x = new int[res.length];
			StringBuffer str = new StringBuffer();
			for (int y = 0; y < res.length; y++) {
				x[y] = (int) res[y] & 0xff;
				if (x[y] <= 15) {
					str.append("0");
				}
				str.append(Integer.toHexString(x[y]));
			}
			pwd = str.toString();
		} catch (NoSuchAlgorithmException x) {

		}

		return pwd;
	}

}
