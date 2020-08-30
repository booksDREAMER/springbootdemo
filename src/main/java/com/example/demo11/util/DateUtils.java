package com.example.demo11.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DateUtils {
	/** 日期时间 */
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 日期 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_FORMAT_YEAR_MONTH = "yyyy-MM";
	/** 时间 */
	private final static String TIME_PATTERN = "HH:mm:ss";

	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 每天的毫秒数
	 */
	public final static long MILLIS_IN_DAY = 1000L * 60 * 60 * 24;

	/**
	 * * 判断一个对象是否为空
	 *
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object o) {
		return o == null;
	}

	/**
	 * 获取当天
	 * 
	 * @return
	 */
	public static Date getToday() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获取昨天
	 * 
	 * @return
	 */
	public static Date getYesterDay() {
		return getYesterDay(getToday());
	}

	/**
	 * 获取指定日期的昨天
	 * 
	 * @return
	 */
	public static Date getYesterDay(Date date) {
		Calendar calendar = getZeroCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static Date getMonthStart() {
		return getMonthStart(getToday());
	}

	/**
	 * 获取指定日期月份的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取本月最后一天
	 * 
	 * @return
	 */
	public static Date getMonthEnd() {
		return getMonthEnd(getToday());
	}

	/**
	 * 获取指定日期月份的最后一天
	 * 
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = getZeroCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取上月第一天
	 * 
	 * @return
	 */
	public static Date getPreMonthStart() {
		Calendar calendar = getZeroCalendar();
		calendar.add(Calendar.MONTH, -1);
		return getMonthStart(calendar.getTime());
	}

	/**
	 * 获取上月最后一天
	 * 
	 * @return
	 */
	public static Date getPreMonthEnd() {
		Calendar calendar = getZeroCalendar();
		calendar.add(Calendar.MONTH, -1);
		return getMonthEnd(calendar.getTime());
	}

	/**
	 * 获取本年第一天
	 * 
	 * @return
	 */
	public static Date getYearStart() {
		Calendar calendar = getZeroCalendar();
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/** 时,分,秒全部为0 */
	public static Calendar getZeroCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 获取指定日期的周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMondayByDate(Date date) {
		if (isNull(date)) {
			return null;
		}
		Calendar calendar = getZeroCalendar();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的周日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSundayByDate(Date date) {
		if (isNull(date)) {
			return null;
		}
		Calendar calendar = getZeroCalendar();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7);
		return calendar.getTime();
	}

	/**
	 * 清空的日期的时,分,秒都为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDateZero(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 将指定日期的时分秒设为 23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDateFull(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/* 字符串转换为日期类型 */

	/**
	 * Parse date like "yyyy-MM-dd".
	 */
	public static Date parseDate(String date) {
		return parseDate(date, DATE_FORMAT);
	}

	/**
	 * Parse date and time like "yyyy-MM-dd HH:mm:ss".
	 */
	public static Date parseDateTime(String date) {
		return parseDate(date, DATETIME_FORMAT);
	}

	/**
	 * 解析指定格式的日期
	 */
	public static Date parseDate(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * 解析多个指定格式的日期
	 * @param date
	 * @param formats
	 * @return
	 */
	public static Date[] parseDate(String date, String... formats) {
		if (StringUtils.isBlank(date) || null == formats || formats.length < 1) {
			return null;
		}
		int length = formats.length;
		SimpleDateFormat format = null;
		List<Date> list = null;
		try {
			list = new ArrayList<Date>();
			for (int i = 0; i < length; i++) {
				if (null != formats[i]) {
					format = new SimpleDateFormat(formats[i]);
					Date dt = new Date();
					dt = format.parse(date);
					list.add(dt);
				}
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		Date[] result = new Date[list.size()];
		result = list.toArray(result);
		return result;
	}

	/* 日期类型转换为字符串 */

	/** yyyy-MM */
	public static String formatYearMonth(Date date) {
		return format(date, DATE_FORMAT_YEAR_MONTH);
	}

	/** yyyy-MM-dd */
	public static String formatDate(Date date) {
		return format(date, DATE_FORMAT);
	}

	/** yyyy-MM-dd HH:mm:ss */
	public static String formatDateTime(Date date) {
		return format(date, DATETIME_FORMAT);
	}

	/** 转换日期为指定格式的字符串类型 */
	public static String format(Date date, String format) {
		return DateFormatUtils.format(date, format);
	}

	/**
	 * 获取指定天数以前的日期
	 * 
	 * @param days
	 *            天数
	 * @return 以前的日期
	 */
	public static Date previous(int days) {
		return new Date(System.currentTimeMillis() - days * MILLIS_IN_DAY);
	}

	/**
	 * 获取指定日期多少天以前/以后的日期
	 * 
	 * @param days
	 *            正数：*天后 负数：*天前
	 * @param date
	 * @return
	 */
	public static Date previous(int days, Date date) {
		if (isNull(date)) {
			return null;
		}
		Calendar calendar = getZeroCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		return calendar.getTime();
	}

	/**
	 * 比较日期大小
	 * 
	 * @param date1
	 * @param date2
	 * @return Integer; -1:date1<date1; 0:date1=date1; 1:date1>date1
	 */
	public static Integer compare_date(Date date1, Date date2) {
		if (isNull(date1) || isNull(date2)) {
			return null;
		}
		long l = date1.getTime() - date2.getTime();
		if (l > 0) {
			return 1;
		} else if (l == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * 比较日期大小
	 * 
	 * @param date1
	 * @param date2
	 * @return Integer; -1:date1<date1; 0:date1=date1; 1:date1>date1
	 */
	public static Integer compare_date(String date1, String date2, String formater) {
		Date d1 = parseDate(date1, formater);
		Date d2 = parseDate(date2, formater);
		if (d1 != null && d2 != null) {
			Integer compare_date = compare_date(d1, d2);
			return compare_date;
		}
		return null;
	}

	/**
	 * 判断两个日期是否为同一天
	 *
	 * @return
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (isNull(date1) || isNull(date2)) {
			return false;
		}
		Calendar cal1 = getZeroCalendar();
		cal1.setTime(date1);
		Calendar cal2 = getZeroCalendar();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * 计算两个时间的相隔天数
	 * @return
	 */
	public static int getDaysBetween(Date date1, Date date2) {
		if (isNull(date1) || isNull(date2)) {
			return -1;
		}

		long l = Math.abs(parseDateZero(date1).getTime() - parseDateZero(date2).getTime());
		return (int) (l / MILLIS_IN_DAY);
	}

	/**
	 * 计算两个时间的相隔天数
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return
	 */
	public static int getDaysBetween(Calendar date1, Calendar date2) {
		if (isNull(date1) || isNull(date2)) {
			return -1;
		}
		return getDaysBetween(date1.getTime(), date2.getTime());
	}

	/**
	 * 计算两个时间的相隔天数
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return
	 */
	public static int getDaysBetween(String date1, String date2, String formater) {
		if (isNull(date1) || isNull(date2)) {
			return -1;
		}
		return getDaysBetween(parseDate(date1, formater), parseDate(date2, formater));
	}

	/**
	 * 获取两个时间的间隔月份
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthsBetween(Date date1, Date date2) {
		if (date1.after(date2)) {
			Date swap = date1;
			date1 = date2;
			date2 = swap;
		}
		Calendar ca1 = getZeroCalendar();
		ca1.setTime(date1);
		Calendar ca2 = getZeroCalendar();
		ca2.setTime(date2);
		int months = ca2.get(Calendar.MONTH) - ca1.get(Calendar.MONTH);
		int years = ca2.get(Calendar.YEAR) - ca1.get(Calendar.YEAR);
		months += years * 12;
		return months;
	}

	/**
	 * 获取两个时间的间隔月份
	 * 
	 * @param date1
	 * @param date2
	 * @param formater
	 * @return
	 */
	public static int getMonthsBetween(String date1, String date2, String formater) {
		Date d1 = parseDate(date1, formater);
		Date d2 = parseDate(date2, formater);
		if (d1 != null && d2 != null) {
			int monthsBetween = getMonthsBetween(d1, d2);
			return monthsBetween;
		}
		return 0;

	}

	/**
	 * 获取两个时间内的工作日
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static int getWorkingDay(Calendar beginDate, Calendar endDate) {
		int result = -1;
		if (beginDate.after(endDate)) {
			Calendar swap = beginDate;
			beginDate = endDate;
			endDate = swap;
		}
		int charge_start_date = 0;
		int charge_end_date = 0;
		int stmp;
		int etmp;
		stmp = 7 - beginDate.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - endDate.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6) {
			charge_start_date = stmp - 1;
		}
		if (etmp != 0 && etmp != 6) {
			charge_end_date = etmp - 1;
		}
		result = (getDaysBetween(getNextMonday(beginDate), getNextMonday(endDate)) / 7) * 5 + charge_start_date
				- charge_end_date;
		return result;
	}

	/**
	 * 根据当前给定的日期获取当前天是星期几(中国版的)
	 * 
	 * @param date
	 *            任意时间
	 * @return
	 */
	public static String getChineseWeek(Calendar date) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		return dayNames[dayOfWeek - 1];

	}

	/**
	 * 获得日期的下一个星期一的日期
	 * 
	 * @param date
	 *            任意时间
	 * @return
	 */
	public static Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(Calendar.DAY_OF_MONTH, 1);
		} while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}

	/**
	 * 获取两个日期之间的休息时间
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static int getHolidays(Calendar beginDate, Calendar endDate) {
		return getDaysBetween(beginDate, endDate) - getWorkingDay(beginDate, endDate);

	}

	/**
	 * 计算两端时间的小时差
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getHour(Date begin, Date end) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(begin);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);
		Long millisecond = c2.getTimeInMillis() - c1.getTimeInMillis();
		Long hour = millisecond / 1000 / 60 / 60;
		Long minute = (millisecond / 1000 / 60) % 60;
		if (minute >= 30) {
			hour++;
		}
		return hour.intValue();
	}

	/*****************************************
	 * @功能 判断某年是否为闰年
	 * @return boolean
	 * @throws ParseException
	 ****************************************/
	public static boolean isLeapYear(int yearNum) {
		boolean isLeep = false;
		/** 判断是否为闰年，赋值给一标识符flag */
		if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
			isLeep = true;
		} else if (yearNum % 400 == 0) {
			isLeep = true;
		} else {
			isLeep = false;
		}
		return isLeep;
	}

	/** 获取当前的整数时间:yyMMddHHmm */
	public static int getNowTime() {
		Calendar cal = Calendar.getInstance();
		String fmtTime = format(cal.getTime(), "yyMMddHHmm");
		return Integer.valueOf(fmtTime);
	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DATETIME_FORMAT);
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}

	/**
	 * 获取日期月份
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getMonth(Date date) {
		return format(date).substring(5, 7);
	}

	/**
	 * 
	 * 获取某月最后一天
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		return cal.getMaximum(Calendar.DAY_OF_MONTH) + "";
	}

	/**
	 * 
	 * 日期转化为日历
	 */
	public static Calendar dataToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 
	 * Long转化为字符串
	 */
	public static String LangToString(Long date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		String format = "";
		try {
			format=sdf.format(date);
		} catch (Exception e) {
		}
		return format;
	}
	/**
	 * 
	 * 获取一个日历中的年月日时分秒（int型）
	 */
	public static Map<String ,Integer> getYearAndOmit(Calendar calendar) {
		int year=calendar.get(Calendar.YEAR);//获取年份
		int month=calendar.get(Calendar.MONTH)+1;//获取月份，因为从0开始的，所以要加1
		int day=calendar.get(Calendar.DAY_OF_MONTH);//获取天
		int hours=calendar.get(Calendar.HOUR_OF_DAY);//获取小时
		int minutes=calendar.get(Calendar.MINUTE);//获取分钟
		int seconds=calendar.get(Calendar.SECOND);//获取秒
		Map<String ,Integer> map= new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("hours", hours);
		map.put("minutes", minutes);
		map.put("seconds", seconds);
		return map;
	}
	/**
	 * 获取当前年份
	 * @return
	 */
	public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
	}
	
	/**
     * 把data默认格式(Mon Jul 20 00:00:00 CST 2020)的时间转换为标准格式(2020-07-08 12:00:00)的时间
     *
     * @param date 默认格式(Mon Jul 20 00:00:00 CST 2020)的时间
     */
    public static String defaultToStandard(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date d2 = null;
        try {
            d2 = sdf.parse(date);
        } catch (ParseException e) {
        	log.error(e.getMessage(),e);
        	return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        //再把Date的时间转换为需要的格式
        String format = simpleDateFormat.format(d2);
        return format;
    }
 
    /**
     * 把data标准格式(2020-07-08 12:00:00)的时间转换为默认格式(Mon Jul 20 00:00:00 CST 2020)的时间
     *
     * @param date 标准格式(2020-07-08 12:00:00)的时间
     */
    public static String standardToDefault(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        Date d2 = null;
        try {
            d2 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
        	log.error(e.getMessage(),e);
        	return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        String format = sdf.format(d2);
        return format;
    }
    
    /**
	 * 获取前一年的今天或者后一年的今天
	 *
	 * @return
	 */
	public static String getTodayByYear(int index) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR,index);
		return formatDateTime(calendar.getTime());
	}
    
	public static void main(String[] args) {
		Calendar ca = getZeroCalendar();
		ca.set(Calendar.DAY_OF_MONTH, 9);
		ca.set(2019, 0, 25, 8, 10, 30);
		System.out.println("昨天：" + formatDate(getYesterDay()));
		System.out.println("指定日期的昨天：" + formatDate(getYesterDay(ca.getTime())));
		System.out.println("本月第一天：" + formatDate(getMonthStart()));
		System.out.println("指定日期的第一天：" + formatDate(getMonthStart(ca.getTime())));
		System.out.println("本月最后一天：" + formatDate(getMonthEnd()));
		System.out.println("指定日期的最后一天：" + formatDate(getMonthEnd(ca.getTime())));
		System.out.println("上月第一天：" + formatDate(getPreMonthStart()));
		System.out.println("上月最后一天：" + formatDate(getPreMonthEnd()));
		System.out.println("本年第一天：" + formatDate(getYearStart()));
		System.out.println("指定日期的周一：" + formatDate(getMondayByDate(new Date())));
		System.out.println("指定日期的周日：" + formatDate(getSundayByDate(new Date())));
		System.out.println("时分秒归零：" + formatDateTime(parseDateZero(ca.getTime())));
		System.out.println("时分秒归整：" + formatDateTime(parseDateFull(ca.getTime())));
		System.out.println("5天后：" + formatDate(previous(5, new Date())));
		System.out.println("10天前：" + formatDate(previous(-10, new Date())));
		Date date1 = new Date(System.currentTimeMillis());
		Date date2 = new Date(System.currentTimeMillis() - 1000);
		System.out.println("比较大小" + compare_date(date1, date2));
		System.out.println("比较大小" + compare_date("2019-05-10", "2019-05-01", "yyyy-MM-dd"));
		System.out.println("相差天数" + getDaysBetween(date1, date2));
		String dateTime = "2020-07-20 13:53:00";
		dateTime = standardToDefault(dateTime);
		System.out.println("解析标准格式为默认格式：" + dateTime);
		dateTime = defaultToStandard(dateTime);
		System.out.println("解析默认格式为标准格式：" + dateTime);
	}
}
