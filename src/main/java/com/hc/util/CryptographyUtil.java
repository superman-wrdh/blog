package com.hc.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 加密工具
 * @author hc
 *
 */
public class CryptographyUtil {

	/**
	 * Md5加密
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}

	public static void main(String[] args) {
		String password="hcissuperman";

		System.out.println("Md5加密："+CryptographyUtil.md5(password, "hc"));
	}

	/**
	 * 自定义Md5加密
	 * @param string
	 * @param salt
	 * @return
	 */
	public static String toMd5(String string,String salt){
		return toMd5(string+salt);
	}

	/**
	 * 自定义Md5加密
	 * @param string
	 * @return
	 */
	public static String toMd5(String string){
		byte[] by=string.getBytes();
		MessageDigest digest;
		String str="";
		String str16;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(by);
			byte[] data=digest.digest();
			for (int i = 0; i < data.length; i++) {
				str16=Integer.toHexString(0XFF & data[i]);
				if (str16.length()==1) {
					str=str+"0"+str16;
				}
				else{
					str=str+str16;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("code failed--superman");
			e.printStackTrace();
		}
		return str;
	}


	/**
	 * 动态秘钥生成器
	 * @param salt
	 * @return
	 */
	public static String generateRandKey(String salt){
		String YMDHM= DateUtil.formatDate(new Date(),"yyyyMMddmm");
		if(StringUtil.isNotEmpty(salt)){
			YMDHM=YMDHM+salt;
		}
		return CryptographyUtil.toMd5(YMDHM);
	}
}
