package dckj.arrange.gateway.util;

import org.apache.commons.lang.RandomStringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

public class MD5 {

	public static final String KEY_MD5 = "MD5";

	/**
	 * MD5加密算法
	 * @param data
	 * @return
	 * @throws
	 */
	public static byte[] encryptMD5(String data) {
		byte[] inputData = data.getBytes();
		if (data == null){
			return null;
		}else {
			try {
				MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
				md5.update(inputData);
				return md5.digest();
			}catch (Exception exception){
				return null;
			}
		}
	}

	/**
	 * BASE64加密算法
	 * @param data
	 * @return
	 */
	public static String encryptBASE64(byte[] data){
		//byte[] inputdate = data.getBytes();
		String encode = new BASE64Encoder().encode(data);
		return encode;
	}

	/**
	 * BASE64解密算法
	 * @param data
	 * @return
	 * @throws
	 */
	public static byte[] decryptBASE64(String data) {
		try {
			byte[] code = new BASE64Decoder().decodeBuffer(data);
			return code;
		}catch (IOException io){
			return null;
		}

	}

	public static String getRandomString(int length){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		String m = RandomStringUtils.randomAlphanumeric(5);
		System.out.println(m);
	}
}
