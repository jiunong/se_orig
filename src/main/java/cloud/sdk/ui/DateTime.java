package cloud.sdk.ui;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * Title: 日期和时间
 * </p>
 * <p>
 * Description:提供日期和时间处理类
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
public class DateTime {

	/**
	 * 农历编码
	 */
	private final static long[] lunarCode = new long[] { 0x04bd8, 0x04ae0,
			0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0,
			0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540,
			0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5,
			0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
			0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3,
			0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
			0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0,
			0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8,
			0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570,
			0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5,
			0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
			0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50,
			0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0,
			0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
			0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7,
			0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50,
			0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954,
			0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
			0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0,
			0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0,
			0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20,
			0x0ada0 };

	/**
	 * 中文编码
	 */
	private final static String[] ChineseCode = { "", "一", "二", "三", "四", "五",
			"六", "七", "八", "九", "十", "十一", "十二" };

	/**
	 * 公历节日编码
	 * <p>
	 * "元旦","劳动节","国庆节"
	 * </p>
	 */
	private final static String gregorianFeastCode[] = { "0101", "0501", "1001" };

	/**
	 * 公历节日名称
	 * <p>
	 * "元旦","劳动节","国庆节"
	 * </p>
	 */
	private final static String gregorianFeastName[] = { "元旦", "劳动节", "国庆节" };

	/**
	 * 农历节日编码
	 * <p>
	 * "春节","初二","初三","初四","初五","初六","端午节","中秋节","除夕"
	 * </p>
	 */
	private final static String lunarFeastCode[] = { "0101", "0102", "0103",
			"0104", "0105", "0106", "0505", "0815", "1230" };

	/**
	 * 农历节日名称
	 * <p>
	 * "春节","初二","初三","初四","初五","初六","端午节","中秋节","除夕"
	 * </p>
	 */
	private final static String lunarFeastName[] = { "春节", "初二", "初三", "初四",
			"初五", "初六", "端午节", "中秋节", "除夕" };

	/**
	 * 24节气编码
	 * <p>
	 * "小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至",
	 * </p>
	 * <p>
	 * "小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至"
	 * </p>
	 */
	private final static long[] solarTermCode = new long[] { 0, 21208, 42467,
			63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072,
			240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447,
			419210, 440795, 462224, 483532, 504758 };

	/**
	 * 24节气名称
	 * <p>
	 * "小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至",
	 * </p>
	 * <p>
	 * "小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至"
	 * </p>
	 */
	private final static String[] solarTermName = { "小寒", "大寒", "立春", "雨水",
			"惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋",
			"处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至" };

	/**
	 * 系统时区
	 */
	private SimpleTimeZone timeZone = null;

	/**
	 * java.util.Calendar对象
	 */
	private Calendar gregorianCalendar = null;

	/**
	 * java.util.Date对象
	 */
	private Date date = null;

	/**
	 * 构造函数
	 */
	public DateTime() {
		this(new Date());
	}

	/**
	 * 构造函数
	 * 
	 * @param date
	 *            初始化的时间
	 *            <p>
	 *            支持时间类型:
	 *            <p>
	 *            1.yyyyMMdd
	 *            </p>
	 *            <p>
	 *            2.yyyy/MM/dd 或 yyyy-MM-dd
	 *            </p>
	 *            <p>
	 *            3.yyyy/MM/dd HH:mm 或 yyyy-MM-dd HH:mm
	 *            </p>
	 *            <p>
	 *            4.yyyy/MM/dd HH:mm:ss 或 yyyy-MM-dd HH:mm:ss
	 *            </p>
	 */
	public DateTime(String date) {
		this(convertDate(date));
	}

	/**
	 * 构造函数
	 */
	public DateTime(Date date) {
		if (date == null) {
			date = new Date();
		}
		this.date = date;
		int timezoneoffset = TimeZone.getDefault().getRawOffset();
		final String[] ids = TimeZone.getAvailableIDs(timezoneoffset);
		timeZone = new SimpleTimeZone(timezoneoffset, ids[0]);
		gregorianCalendar = new GregorianCalendar(timeZone);
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(date);
	}

	public DateTime toDateTime(Date date) {
		return new DateTime(date);
	}

	/**
	 * 重载toString
	 */
	public String toString() {
		return getGregorianCalendar(1, 6);
	}

	/**
	 * 重载clone
	 * 
	 * @return Object
	 */
	public Object clone() {
		return new DateTime();
	}

	/**
	 * 重载hashCode
	 * 
	 * @return int
	 */
	public int hashCode() {
		return (int) getTime();
	}

	/**
	 * 重载equals
	 * 
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if (obj instanceof DateTime) {
			DateTime date = (DateTime) obj;
			if (date.hashCode() == this.hashCode()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 转换成java.util.Date类型
	 */
	public Date toDate() {
		return date;
	}

	/**
	 * 获取年份
	 * 
	 * @return int 年份
	 */
	public int getYear() {
		return gregorianCalendar.get(Calendar.YEAR);
	}

	/**
	 * 获取月份
	 * 
	 * @return int 月份
	 */
	public int getMonth() {
		return gregorianCalendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日
	 * 
	 * @return int 日
	 */
	public int getDay() {
		return gregorianCalendar.get(Calendar.DATE);
	}

	/**
	 * 获取小时
	 * 
	 * @return int 小时
	 */
	public int getHour() {
		return gregorianCalendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取分钟
	 * 
	 * @return int 分钟
	 */
	public int getMinute() {
		return gregorianCalendar.get(Calendar.MINUTE);
	}

	/**
	 * 获取秒
	 * 
	 * @return int 秒
	 */
	public int getSecond() {
		return gregorianCalendar.get(Calendar.SECOND);
	}

	/**
	 * 获取毫秒
	 * 
	 * @return int 毫秒
	 */
	public int getMillisecond() {
		return gregorianCalendar.get(Calendar.MILLISECOND);
	}

	/**
	 * 获取当前时间的距离1970-01-01 00:00:00的毫秒数
	 * 
	 * @return long 毫秒
	 */
	public long getTime() {
		return date.getTime();
	}

	/**
	 * 获取星期
	 * 
	 * @return int 星期
	 */
	public int getWeek() {
		int week = gregorianCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			return 7;
		} else {
			return week;
		}
	}

	/**
	 * 获取星期
	 * 
	 * @param type
	 *            星期显示类型
	 *            <p>
	 *            1:数字，如星期一返回1，星期二返回2，星期日返回7
	 *            </p>
	 *            <p>
	 *            2:中文，如星期一，星期二，星期日
	 *            </p>
	 *            <p>
	 *            3:英文，如MONDAY，TUESDAY，SUNDAY
	 *            </p>
	 *            <p>
	 *            4:英文缩写，如Mon.，Tues.，Sun.
	 *            </p>
	 * 
	 * @return 星期 String 默认返回""
	 */
	public String getWeek(int type) {
		String result = "";

		try {
			int week = getWeek();
			switch (type) {
			case 1: {
				result = String.valueOf(week);
				break;
			}
			case 2: {
				switch (week) {
				case 1: {
					result = "星期一";
					break;
				}
				case 2: {
					result = "星期二";
					break;
				}
				case 3: {
					result = "星期三";
					break;
				}
				case 4: {
					result = "星期四";
					break;
				}
				case 5: {
					result = "星期五";
					break;
				}
				case 6: {
					result = "星期六";
					break;
				}
				case 7: {
					result = "星期日";
					break;
				}
				}
				break;
			}
			case 3: {
				switch (week) {
				case 1: {
					result = "MONDAY";
					break;
				}
				case 2: {
					result = "TUESDAY";
					break;
				}
				case 3: {
					result = "WEDNESDAY";
					break;
				}
				case 4: {
					result = "THURSDAY";
					break;
				}
				case 5: {
					result = "FRIDAY";
					break;
				}
				case 6: {
					result = "SATURDAY";
					break;
				}
				case 7: {
					result = "SUNDAY";
					break;
				}
				}
				break;
			}
			case 4: {
				switch (week) {
				case 1: {
					result = "Mon.";
					break;
				}
				case 2: {
					result = "Tues.";
					break;
				}
				case 3: {
					result = "Wed.";
					break;
				}
				case 4: {
					result = "Thurs.";
					break;
				}
				case 5: {
					result = "Fri.";
					break;
				}
				case 6: {
					result = "Sat.";
					break;
				}
				case 7: {
					result = "Sun.";
					break;
				}
				}
				break;
			}
			default: {
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取当前日期是所在年的第几天
	 * 
	 * @return int 第几天
	 */
	public int getDayOfYear() {
		return gregorianCalendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取当前日期所在年的第一天
	 * 
	 * @return Date 日期
	 */
	public DateTime getBeginOfYear() {
		Calendar c = new GregorianCalendar(timeZone);
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return new DateTime(c.getTime());
	}

	/**
	 * 获取当前日期所在年的第一天
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * @return 公历日期 String 默认返回""
	 */
	public String getBeginOfYear(int type, int length) {
		return getGregorianCalendar(getBeginOfYear(), type, length);
	}

	/**
	 * 获取当前日期所在年的最后一天
	 * 
	 * @return Date 日期
	 */
	public DateTime getEndOfYear() {
		Calendar c = new GregorianCalendar(timeZone);
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		c.set(Calendar.DAY_OF_YEAR, 1);
		c.add(Calendar.DATE, -1);
		return new DateTime(c.getTime());
	}

	/**
	 * 获取当前日期所在年的最后一天
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * @return 公历日期 String 默认返回""
	 */
	public String getEndOfYear(int type, int length) {
		return getGregorianCalendar(getEndOfYear(), type, length);
	}

	/**
	 * 获取当前日期所在月份的第一天
	 * 
	 * @return Date 日期
	 */
	public DateTime getBeginOfMonth() {
		Calendar c = new GregorianCalendar(timeZone);
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return new DateTime(c.getTime());
	}

	/**
	 * 获取当前日期所在月份的第一天
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * @return 公历日期 String 默认返回""
	 */
	public String getBeginOfMonth(int type, int length) {
		return getGregorianCalendar(getBeginOfMonth(), type, length);
	}

	/**
	 * 获取当前日期所在月份的最后一天
	 * 
	 * @return Date 日期
	 */
	public DateTime getEndOfMonth() {
		Calendar c = new GregorianCalendar(timeZone);
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DATE, -1);
		return new DateTime(c.getTime());
	}

	/**
	 * 获取当前日期所在月份的最后一天
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * @return 公历日期 String 默认返回""
	 */
	public String getEndOfMonth(int type, int length) {
		return getGregorianCalendar(getEndOfMonth(), type, length);
	}

	/**
	 * 获取当前日期所在周周一的日期
	 * 
	 * @return Date 日期
	 */
	public DateTime getBeginOfWeek() {
		int week = getWeek();
		if (week == 1) {
			return this;
		} else {
			Calendar c = new GregorianCalendar(timeZone);
			c.setTime(date);
			c.add(Calendar.DATE, 1 - week);
			return new DateTime(c.getTime());
		}
	}

	/**
	 * 获取当前日期所在周周一的日期
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * @return 公历日期 String 默认返回""
	 */
	public String getBeginOfWeek(int type, int length) {
		return getGregorianCalendar(getBeginOfWeek(), type, length);
	}

	/**
	 * 获取当前日期所在周周日的日期
	 * 
	 * @return Date 日期
	 */
	public DateTime getEndOfWeek() {
		int week = getWeek();
		if (week == 0) {
			return this;
		} else {
			Calendar c = new GregorianCalendar(timeZone);
			c.setTime(date);
			c.add(Calendar.DATE, 7 - week);
			return new DateTime(c.getTime());
		}
	}

	/**
	 * 获取当前日期所在周周日的日期
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * @return 公历日期 String 默认返回""
	 */
	public String getEndOfWeek(int type, int length) {
		return getGregorianCalendar(getEndOfWeek(), type, length);
	}

	/**
	 * 获取当前日期所在周是当年的第几周
	 * 
	 * @return int 第几周
	 */
	public int getWeekOfYear() {
		return gregorianCalendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取当前日期所在周是当月的第几周
	 * 
	 * @return int 第几周
	 */
	public int getWeekOfMonth() {
		return gregorianCalendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取当前时间和输入时间之差
	 * 
	 * @param start
	 *            时间
	 * @param type
	 *            差值显示类型 1:年.2:月.3:日.4:时.5:分.6:秒
	 * 
	 * @return long 差值 默认返回0
	 */
	public long getBefore(DateTime start, int type) {
		long result = 0;

		try {
			long diff = date.getTime() / 1000 - start.getTime() / 1000;
			switch (type) {
			case 1: {
				result = getYear() - start.getYear();
				break;
			}
			case 2: {
				result = 12 * (getYear() - start.getYear()) + getMonth()
						- start.getMonth();
				break;
			}
			case 3: {
				result = diff / (24 * 60 * 60);
				break;
			}
			case 4: {
				result = diff / (60 * 60);
				break;
			}
			case 5: {
				result = diff / 60;
				break;
			}
			case 6: {
				result = diff;
				break;
			}
			default: {
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取输入时间和当前时间之差
	 * 
	 * @param end
	 *            时间
	 * @param type
	 *            差值显示类型 1:年.2:月.3:日.4:时.5:分.6:秒
	 * 
	 * @return long 差值 默认返回0
	 */
	public long getAfter(DateTime end, int type) {
		long result = 0;

		try {
			long diff = end.getTime() / 1000 - date.getTime() / 1000;
			switch (type) {
			case 1: {
				result = end.getYear() - getYear();
				break;
			}
			case 2: {
				result = 12 * (end.getYear() - getYear()) + end.getMonth()
						- getMonth();
				break;
			}
			case 3: {
				result = diff / (24 * 60 * 60);
				break;
			}
			case 4: {
				result = diff / (60 * 60);
				break;
			}
			case 5: {
				result = diff / 60;
				break;
			}
			case 6: {
				result = diff;
				break;
			}
			default: {
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 时间操作
	 * 
	 * @param field
	 *            操作类型 1:年.2:月.3:日.4:时.5:分.6:秒
	 * @param amount
	 *            操作数目
	 * @return 日期 Date 默认返回当前时间
	 */
	public DateTime calculate(int field, int amount) {
		DateTime result = new DateTime();

		try {
			Calendar gregorianCalendar = new GregorianCalendar(timeZone);
			gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
			gregorianCalendar.setTime(this.toDate());
			switch (field) {
			case 1: {
				gregorianCalendar.add(Calendar.YEAR, amount);
				break;
			}
			case 2: {
				gregorianCalendar.add(Calendar.MONTH, amount);
				break;
			}
			case 3: {
				gregorianCalendar.add(Calendar.DATE, amount);
				break;
			}
			case 4: {
				gregorianCalendar.add(Calendar.HOUR, amount);
				break;
			}
			case 5: {
				gregorianCalendar.add(Calendar.MINUTE, amount);
				break;
			}
			case 6: {
				gregorianCalendar.add(Calendar.SECOND, amount);
				break;
			}
			default: {
				break;
			}
			}
			result = toDateTime(gregorianCalendar.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 当前时间是否早于输入时间
	 * 
	 * @param dt
	 *            时间
	 * 
	 * @return 当前时间早于输入时间，返回true，否则返回false
	 */
	public boolean before(DateTime dt) {
		if (this.toDate().before(dt.toDate())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 当前时间是否晚于输入时间
	 * 
	 * @param dt
	 *            时间
	 * 
	 * @return 当前时间晚于输入时间，返回true，否则返回false
	 */
	public boolean after(DateTime dt) {
		if (this.toDate().after(dt.toDate())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否是闰年
	 * 
	 * @param year
	 *            int
	 * @return boolean 是闰年true，否则false
	 */
	public boolean isLeapYear() {
		int year = getYear();
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 获取公历日期
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度类型
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * 
	 * @return 公历日期 String 默认返回""
	 */
	public String getGregorianCalendar(int type, int length) {
		return getGregorianCalendar(this, type, length);
	}

	/**
	 * 获取农历日期
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:MMdd
	 *            </p>
	 *            <p>
	 *            2:农历M月dd
	 *            </p>
	 *            <p>
	 *            3:农历M月dd 农历节日
	 *            </p>
	 *            <p>
	 *            4:农历M月dd 农历节日 节气
	 *            </p>
	 * 
	 * @return 农历日期 String 默认返回""
	 */
	public String getLunarCalendar(int type) {
		String result = "";

		try {
			int[] lunarCalender = getLunarCalendar();
			int leap = lunarCalender[0];
			int month = lunarCalender[2];
			int day = lunarCalender[3];

			String sMonth = null;
			String sDay = null;

			switch (type) {
			case 1: {
				sMonth = month < 10 ? "0" + month : String.valueOf(month);
				sDay = day < 10 ? "0" + day : String.valueOf(day);
				result = sMonth + sDay;
				break;
			}
			case 2: {
				if (leap == 1) {
					if (month == 1) {
						sMonth = "润正";
					} else {
						sMonth = "润" + ChineseCode[month];
					}
				} else {
					if (month == 1) {
						sMonth = "正";
					} else {
						sMonth = ChineseCode[month];
					}
				}
				if (day <= 10) {
					sDay = "初" + ChineseCode[day];
				} else if (day > 10 && day < 20) {
					sDay = "十" + ChineseCode[day - 10];
				} else if (day == 20) {
					sDay = "二十";
				} else if (day > 20 && day < 30) {
					sDay = "廿" + ChineseCode[day - 20];
				} else if (day == 30) {
					sDay = "三十";
				}
				result = "农历" + sMonth + "月" + sDay;
				break;
			}
			case 3: {
				if (leap == 1) {
					if (month == 1) {
						sMonth = "润正";
					} else {
						sMonth = "润" + ChineseCode[month];
					}
				} else {
					if (month == 1) {
						sMonth = "正";
					} else {
						sMonth = ChineseCode[month];
					}
				}
				if (day <= 10) {
					sDay = "初" + ChineseCode[day];
				} else if (day > 10 && day < 20) {
					sDay = "十" + ChineseCode[day - 10];
				} else if (day == 20) {
					sDay = "二十";
				} else if (day > 20 && day < 30) {
					sDay = "廿" + ChineseCode[day - 20];
				} else if (day == 30) {
					sDay = "三十";
				}
				String lunarFeast = getLunarFeast(month, day);
				if (lunarFeast == "") {
					result = "农历" + sMonth + "月" + sDay;
				} else {
					result = "农历" + sMonth + "月" + sDay + " " + lunarFeast;
				}

				break;
			}
			case 4: {
				if (leap == 1) {
					if (month == 1) {
						sMonth = "润正";
					} else {
						sMonth = "润" + ChineseCode[month];
					}
				} else {
					if (month == 1) {
						sMonth = "正";
					} else {
						sMonth = ChineseCode[month];
					}
				}
				if (day <= 10) {
					sDay = "初" + ChineseCode[day];
				} else if (day > 10 && day < 20) {
					sDay = "十" + ChineseCode[day - 10];
				} else if (day == 20) {
					sDay = "二十";
				} else if (day > 20 && day < 30) {
					sDay = "廿" + ChineseCode[day - 20];
				} else if (day == 30) {
					sDay = "三十";
				}
				String lunarFeast = getLunarFeast(month, day);
				String solarTerm = getSolarTerm(getYear(), getMonth(), getDay());
				result = "农历" + sMonth + "月" + sDay + " " + lunarFeast + " "
						+ solarTerm;
				result = result.replaceAll("  ", " ");
				break;
			}
			default: {
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取公历节日
	 * 
	 * @return 公历节日 String 默认返回""
	 */
	public String getGregorianFeast() {
		String result = "";

		try {
			String month = getMonth() < 10 ? "0" + getMonth() : String
					.valueOf(getMonth());
			String day = getDay() < 10 ? "0" + getDay() : String
					.valueOf(getDay());
			String str = month + day;

			int size = gregorianFeastCode.length;
			for (int i = 0; i < size; i++) {
				if (str.trim().equals(gregorianFeastCode[i])) {
					result = gregorianFeastName[i];
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取农历节日
	 * 
	 * @return 农历节日 String 默认返回""
	 */
	public String getLunarFeast() {
		String result = "";

		try {
			int[] lunarCalender = getLunarCalendar();
			int leap = lunarCalender[0];
			int month = lunarCalender[2];
			int day = lunarCalender[3];
			if (leap != 1) {
				result = getLunarFeast(month, day);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取节气
	 * 
	 * @return 节气 String 默认返回""
	 */
	public String getSolarTerm() {
		return getSolarTerm(getYear(), getMonth(), getDay());
	}

	/**
	 * 获取中文日期
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy年MM月dd日
	 *            </p>
	 *            <p>
	 *            2:yyyy年MM月dd日 HH时
	 *            </p>
	 *            <p>
	 *            3:yyyy年MM月dd日 HH时mm分
	 *            </p>
	 *            <p>
	 *            4:yyyy年MM月dd日 HH时mm分ss秒
	 *            </p>
	 * 
	 * @return 公历日期 String 默认返回""
	 */
	public String getChineseCalendar(int type) {
		String result = "";

		try {
			String format = null;
			switch (type) {
			case 1: {
				format = "yyyy年MM月dd日";
				break;
			}
			case 2: {
				format = "yyyy年MM月dd日 HH时";
				break;
			}
			case 3: {
				format = "yyyy年MM月dd日 HH时mm分";
				break;
			}
			case 4: {
				format = "yyyy年MM月dd日 HH时mm分ss秒";
				break;
			}
			default: {
				format = "yyyy年MM月dd日";
				break;
			}
			}
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			result = formatter.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取时间戳
	 * 
	 * @param type
	 *            显示类型
	 *            <p>
	 *            1:yyyyMMdd
	 *            </p>
	 *            <p>
	 *            2:yyyyMMddHH
	 *            </p>
	 *            <p>
	 *            3:yyyyMMddHHmm
	 *            </p>
	 *            <p>
	 *            4:yyyyMMddHHmmss
	 *            </p>
	 * 
	 * @return 时间戳 String 默认返回""
	 */
	public String getTimeStamp(int type) {
		String result = "";

		try {
			String format = null;
			switch (type) {
			case 1: {
				format = "yyyyMMdd";
				break;
			}
			case 2: {
				format = "yyyyMMddHH";
				break;
			}
			case 3: {
				format = "yyyyMMddHHmm";
				break;
			}
			case 4: {
				format = "yyyyMMddHHmmss";
				break;
			}
			default: {
				format = "yyyyMMddHHmmss";
				break;
			}
			}
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			result = formatter.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 字符串转换时间
	 * 
	 * @param strDate
	 *            被操作的时间
	 *            <p>
	 *            支持时间类型:
	 *            <p>
	 *            1.yyyyMMdd
	 *            </p>
	 *            <p>
	 *            2.yyyy/MM/dd 或 yyyy-MM-dd
	 *            </p>
	 *            <p>
	 *            3.yyyy/MM/dd HH:mm 或 yyyy-MM-dd HH:mm
	 *            </p>
	 *            <p>
	 *            4.yyyy/MM/dd HH:mm:ss 或 yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @return 日期 Date 默认返回null
	 */
	private static Date convertDate(String strDate) {
		Date result = null;

		try {
			if (strDate != null) {
				Calendar c = Calendar.getInstance();
				int length = strDate.length();
				switch (length) {
				case 8:
					c.set(Integer.parseInt(strDate.substring(0, 4)),
							Integer.parseInt(strDate.substring(4, 6)) - 1,
							Integer.parseInt(strDate.substring(6, 8)), 0, 0, 0);
					result = c.getTime();
					break;
				case 10: {
					c.set(Integer.parseInt(strDate.substring(0, 4)),
							Integer.parseInt(strDate.substring(5, 7)) - 1,
							Integer.parseInt(strDate.substring(8, 10)), 0, 0, 0);
					result = c.getTime();
					break;
				}
				case 16: {
					c.set(Integer.parseInt(strDate.substring(0, 4)),
							Integer.parseInt(strDate.substring(5, 7)) - 1,
							Integer.parseInt(strDate.substring(8, 10)),
							Integer.parseInt(strDate.substring(11, 13)),
							Integer.parseInt(strDate.substring(14, 16)), 0);
					result = c.getTime();
					break;
				}
				case 19: {
					c.set(Integer.parseInt(strDate.substring(0, 4)),
							Integer.parseInt(strDate.substring(5, 7)) - 1,
							Integer.parseInt(strDate.substring(8, 10)),
							Integer.parseInt(strDate.substring(11, 13)),
							Integer.parseInt(strDate.substring(14, 16)),
							Integer.parseInt(strDate.substring(17, 19)));
					result = c.getTime();
					break;
				}
				case 21: {
					c.set(Integer.parseInt(strDate.substring(0, 4)),
							Integer.parseInt(strDate.substring(5, 7)) - 1,
							Integer.parseInt(strDate.substring(8, 10)),
							Integer.parseInt(strDate.substring(11, 13)),
							Integer.parseInt(strDate.substring(14, 16)),
							Integer.parseInt(strDate.substring(17, 19)));
					result = c.getTime();
					break;
				}
				default: {
					break;
				}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取输入日期的公历日期
	 * 
	 * @param type
	 *            日期显示类型
	 *            <p>
	 *            1:yyyy/MM/dd HH:mm:ss
	 *            </p>
	 *            <p>
	 *            2:yyyy-MM-dd HH:mm:ss
	 *            </p>
	 * @param length
	 *            日期显示长度
	 *            <p>
	 *            1:显示年
	 *            </p>
	 *            <p>
	 *            2:显示年月
	 *            </p>
	 *            <p>
	 *            3:显示年月日
	 *            </p>
	 *            <p>
	 *            4:显示年月日时
	 *            </p>
	 *            <p>
	 *            5:显示年月日时分
	 *            </p>
	 *            <p>
	 *            6:显示年月日时分秒
	 *            </p>
	 *            <p>
	 *            7:显示月日
	 *            </p>
	 *            <p>
	 *            8:显示时分
	 *            </p>
	 *            <p>
	 *            9:显示时分秒
	 *            </p>
	 * 
	 * @return 公历日期 String 默认返回""
	 */
	private String getGregorianCalendar(DateTime date, int type, int length) {
		String result = "";

		try {
			if (date != null) {
				String format = null;
				switch (type) {
				case 1: {
					format = "yyyy/MM/dd HH:mm:ss";
					break;
				}
				case 2: {
					format = "yyyy-MM-dd HH:mm:ss";
					break;
				}
				default: {
					format = "yyyy/MM/dd HH:mm:ss";
					break;
				}
				}

				SimpleDateFormat formatter = new SimpleDateFormat(format);
				String strDate = formatter.format(date.toDate());

				switch (length) {
				case 1: {
					result = strDate.substring(0, 4);
					break;
				}
				case 2: {
					result = strDate.substring(0, 7);
					break;
				}
				case 3: {
					result = strDate.substring(0, 10);
					break;
				}
				case 4: {
					result = strDate.substring(0, 13);
					break;
				}
				case 5: {
					result = strDate.substring(0, 16);
					break;
				}
				case 6: {
					result = strDate.substring(0, 19);
					break;
				}
				case 7: {
					result = strDate.substring(5, 10);
					break;
				}
				case 8: {
					result = strDate.substring(11, 16);
					break;
				}
				case 9: {
					result = strDate.substring(11, 19);
					break;
				}
				default: {
					result = strDate.substring(0, 10);
					break;
				}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取农历日期
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return int[0] 是否闰月 是1, 否0
	 * @return int[1] 农历年
	 * @return int[2] 农历月
	 * @return int[3] 农历日
	 */
	private int[] getLunarCalendar() {
		int[] result = new int[4];

		try {
			// 求出和1900年1月31日相差的天数
			DateTime basedate = new DateTime("1900-01-31");
			long offset = getBefore(basedate, 3);

			// 用offset减去每农历年的天数
			// 计算当天是农历第几天
			// i最终结果是农历的年份
			// offset是当年的第几天

			int leapMonth = 0;
			int year;
			int month;
			int day;
			boolean leap;

			int iYear, daysOfYear = 0;
			for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
				daysOfYear = yearDays(iYear);
				offset -= daysOfYear;
			}
			if (offset < 0) {
				offset += daysOfYear;
				iYear--;
			}
			// 农历年份
			year = iYear;

			leapMonth = leapMonth(iYear); // 闰哪个月,1-12
			leap = false;

			// 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
			int iMonth, daysOfMonth = 0;
			for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
				// 闰月
				if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
					--iMonth;
					leap = true;
					daysOfMonth = leapDays(year);
				} else
					daysOfMonth = monthDays(year, iMonth);

				offset -= daysOfMonth;
				// 解除闰月
				if (leap && iMonth == (leapMonth + 1)) {
					leap = false;
				}
			}
			// offset为0时，并且刚才计算的月份是闰月，要校正
			if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
				if (leap) {
					leap = false;
				} else {
					leap = true;
					--iMonth;
				}
			}
			// offset小于0时，校正
			if (offset < 0) {
				offset += daysOfMonth;
				--iMonth;
			}
			month = iMonth;
			day = (int) offset + 1;

			result[0] = leap ? 1 : 0;
			result[1] = year;
			result[2] = month;
			result[3] = day;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取农历 y年的总天数
	 */
	private int yearDays(int y) {
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarCode[y - 1900] & i) != 0) {
				sum += 1;
			}
		}
		return sum + leapDays(y);
	}

	/**
	 * 获取农历 y年闰月的天数
	 */
	private int leapDays(int y) {
		if (leapMonth(y) != 0) {
			if ((lunarCode[y - 1900] & 0x10000) != 0) {
				return 30;
			} else {
				return 29;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 获取农历 y年闰哪个月 1-12 , 没闰返回 0
	 */
	private int leapMonth(int y) {
		return (int) (lunarCode[y - 1900] & 0xf);
	}

	/**
	 * 获取农历 y年m月的总天数
	 */
	private int monthDays(int y, int m) {
		if ((lunarCode[y - 1900] & (0x10000 >> m)) == 0) {
			return 29;
		} else {
			return 30;
		}
	}

	/**
	 * 获取农历节日
	 * 
	 * @param month
	 *            农历月
	 * @param day
	 *            农历日
	 * 
	 * @return 农历节日 String 默认返回""
	 */
	private String getLunarFeast(int month, int day) {
		String result = "";

		try {
			String sMonth = month < 10 ? "0" + month : String.valueOf(month);
			String sDay = day < 10 ? "0" + day : String.valueOf(day);
			String str = sMonth + sDay;
			int size = lunarFeastCode.length;
			for (int i = 0; i < size; i++) {
				if (str.trim().equals(lunarFeastCode[i])) {
					result = lunarFeastName[i];
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取节气
	 * 
	 * @param year
	 *            公历年
	 * @param month
	 *            公历月
	 * @param day
	 *            公历日
	 * 
	 * @return 节气 String 默认返回""
	 */
	private String getSolarTerm(int year, int month, int day) {
		String result = "";

		try {
			if (day == solarTerm(year, (month - 1) * 2)) {
				result = solarTermName[(month - 1) * 2];
			} else if (day == solarTerm(year, (month - 1) * 2 + 1)) {
				result = solarTermName[(month - 1) * 2 + 1];
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取某年的第n个节气日期(从0小寒起算)
	 */
	private int solarTerm(int year, int n) {
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 6, 2, 5, 0);
		c.setTime(new Date(
				(long) ((31556925974.7 * (year - 1900) + solarTermCode[n] * 60000L) + c
						.getTime().getTime())));
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) {
		try {
			Date d1 = new Date();
			DateTime dt = new DateTime(d1);
			DateTime dt2 = dt.calculate(4, 8);
			System.out.println(dt.getHour());
			System.out.println(dt2.getHour());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
