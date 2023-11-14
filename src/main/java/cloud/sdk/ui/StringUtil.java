package cloud.sdk.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: 字符串处理
 * </p>
 * <p>
 * Description:提供字符串相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: SICT
 * </p>
 * 
 * @author GuZhenhao
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 字符串是否有效,null,"","null","NULL"字符串均为无效
	 */
	public static boolean isValidStr(String str) {
		boolean result = false;

		try {
			if (str != null && !str.trim().equals("")
					&& !str.equalsIgnoreCase("null")) {
				result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static String replaceNull(String str) {
		String result = "";

		try {
			if (str == null) {
				result = "";
			} else if (str.equalsIgnoreCase("null")) {
				result = "";
			} else {
				result = str.trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static String toNull(String str) {
		String result = null;

		try {
			if ("".equals(str)) {
				result = null;
			} else {
				result = str;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 字符串编码转换
	 */
	public static String convertCode(String str, String oldCode, String newCode) {
		String result = null;

		try {
			if (isValidStr(str)) {
				result = new String(str.getBytes(oldCode), newCode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * String ->> double，转换失败则返回0
	 * 
	 * @param String
	 *            s
	 * @return double
	 */
	public static double toDouble(String s) {
		return toDouble(s, 0);
	}

	/**
	 * String ->> double，s为转换字符串，d为转换失败返回的默认值
	 * 
	 * @param String
	 *            s
	 * @param double d
	 * @return double
	 */
	public static double toDouble(String s, double d) {
		try {
			return Double.parseDouble(s);
		} catch (Exception ex) {
			return d;
		}
	}

	/**
	 * String ->> int，转换失败则返回0
	 * 
	 * @param String
	 *            s
	 * @return int
	 */
	public static int toInt(String s) {
		return toInt(s, 0);
	}

	/**
	 * String ->> int，s为转换字符串，i为转换失败返回的默认值
	 * 
	 * @param String
	 *            s
	 * @param int i
	 * @return int
	 */
	public static int toInt(String s, int i) {
		try {
			return Integer.parseInt(s);
		} catch (Exception ex) {
			return i;
		}
	}

	/**
	 * 判断字符串是否为日期格式
	 * 
	 * 支持格式:
	 * <p>
	 * 1.yyyyMMdd
	 * </p>
	 * <p>
	 * 2.yyyy/MM/dd 或 yyyy-MM-dd
	 * </p>
	 * <p>
	 * 3.yyyy/MM/dd HH:mm 或 yyyy-MM-dd HH:mm
	 * </p>
	 * <p>
	 * 4.yyyy/MM/dd HH:mm:ss 或 yyyy-MM-dd HH:mm:ss
	 * </p>
	 */
	public static boolean isDate(String str) {
		boolean result = false;

		try {
			if (isValidStr(str)) {
				int length = str.trim().length();
				if (length >= 8 && length <= 21) {
					if (length == 8) {
						// yyyyMMdd
						str = str.substring(0, 4) + "-" + str.substring(4, 6)
								+ "-" + str.substring(6, 8) + " 00:00:00";
					} else if (length == 10) {
						// yyyy/MM/dd 或yyyy-MM-dd
						str = str + " 00:00:00";
					} else if (length == 13) {
						// yyyy/MM/dd HH 或yyyy-MM-dd HH
						str = str + ":00:00";
					} else if (length == 16) {
						// yyyy/MM/dd HH:mm 或yyyy-MM-dd HH:mm
						str = str + ":00";
					} else if (length == 19) {
						// yyyy/MM/dd HH:mm:ss 或yyyy-MM-dd HH:mm:ss
					} else if (length == 21) {
						// yyyy/MM/dd HH:mm:ss.0 或yyyy-MM-dd HH:mm:ss.0
						str = str.substring(0, 19);
					}

					Pattern pattern = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|(1?[0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
					Matcher matcher = pattern.matcher(str);
					result = matcher.matches();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static String toChinese(String ss) {
		// 处理中文问题,实现编码转换
		if (ss != null) {
			try {
				String temp_p = ss;
				byte[] temp_t = temp_p.getBytes("ISO8859-1");
				ss = new String(temp_t);
			} catch (Exception e) {
				System.err.println("toChinese exception:" + e.getMessage());
				System.err.println("The String is:" + ss);
			}
		}
		return ss;
	}

}
