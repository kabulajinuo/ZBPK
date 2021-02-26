package dckj.arrange.common.util;



/**
 * MD5加密和密文比对
 *
 * @author icefrog.su@qq.com
 *
 */
public class MD5Utils {

//	public static void main(String[] args) {
//		String s = "123456";
//		System.out.println(MD5Utils.encodeMemberPassword(s));
//	}

	/**
	 * MD5加密
	 * @param password 待加密的明文
	 * @param salt 盐值
	 * @return 加密后的密文
	 */
	public static String MD5Encoder(String password,String salt){
		return MD5.MD5Encode(salt + MD5.MD5Encode(password, "utf-8"), "utf-8");
	}

	/**
	 * 用于加密会员密码
	 *
	 * @author Dun
	 * @param password
	 * @return
	 */
	public final static String encodeMemberPassword(String password) {
		if (password == null || "".equals(password.trim())) {
			return null;
		}
		return encode2HexStr(encode(password.getBytes()));
	}

	/**
	 * 用MD5算法加密后再转换成hex String
	 *
	 * @param bytes
	 * @return String
	 */
	public static String encode2HexStr(byte[] bytes) {
		return HexUtils.bytes2HexStr(MD5Utils.encode(bytes));
	}

	/**
	 * 用MD5算法加密字节数组
	 *
	 * @param bytes
	 *            要加密的字节
	 * @return byte[] 加密后的字节数组，若加密失败，则返回null
	 */
	public static byte[] encode(byte[] bytes) {
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(bytes);
			byte[] digesta = digest.digest();
			return digesta;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * MD5比对
	 * @param md5 密文
	 * @param salt 盐值
	 * @param compValue 明文
	 * @return 如果比对成功,返回true, 否则返回false
	 */
	public static boolean MD5Comparator(String md5,String salt,String compValue){
		return MD5Encoder(compValue, salt).equals(md5);
	}
}