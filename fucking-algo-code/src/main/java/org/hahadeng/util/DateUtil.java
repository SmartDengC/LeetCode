package org.hahadeng.util;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @version 1.0
 */
public class DateUtil {

    /**
     * 预定义的日期格式:yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 预定义的日期格式:yyyyMMdd_HHmmss
     */
    public static final String YYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";

    /**
     * 预定义的日期格式:yyyy-MM-dd HH:mm
     */
    public static final String YYYYMMDD_HHMM = "yyyyMMdd HHmm";

    /**
     * 预定义的日期格式:yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 预定义的日期格式:yyyy-MM
     */
    public static final String YYYY_MM = "yyyy-MM";

    /**
     * 预定义的日期格式:yyyyMMdd
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 预定义的日期格式:yyyyMM
     */
    public static final String YYYYMM = "yyyyMM";

    /**
     * 预定义的日期格式:yyyy
     */
    public static final String YYYY = "yyyy";

    /**
     * 预定义的日期格式:MM-dd
     */
    public static final String MM_dd = "MM-dd";

    /**
     * 预定义的日期格式:MMdd
     */
    public static final String MMdd = "MMdd";

    /**
     * 预定义的日期格式:MM
     */
    public static final String MM = "MM";

    /**
     * 预定义的日期格式:HH:mm
     */
    public static final String HH_mm = "HH:mm";

    /**
     * 预定义的日期格式:HHmm
     */
    public static final String HHmm = "HHmm";

    /**
     * 格式化年月日
     */
    private static SimpleDateFormat formatDay = new SimpleDateFormat(YYYYMMDD);
    /**
     * 格式化年月
     */

    private static SimpleDateFormat formatMon = new SimpleDateFormat(YYYYMM);
    /**
     * 预定义的日期格式:YYYYMMDDHHMMSS
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 格式化年月日
     */
    public static final String YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm";

    /**
     * 按指定的格式sFormat将日期dDate转化为字符串，sFormat的取值在类中定义了常量，也可以自己设置字符串，默认为yyyy-MM-dd
     *
     * @param dDate   待转化的日期
     * @param sFormat 格式化指定的格式
     * @return String 格式为sFormat的日期字符串
     */
    public static String date2String(Date dDate, String sFormat) {
        if (dDate == null) {
            return "";
        } else {
            if (ImcStringUtil.isEmpty(sFormat)) {
                sFormat = YYYY_MM_DD;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
            return sdf.format(dDate);
        }
    }

    /**
     * 将字符串按指定格式转换为java.util.Date类型，format的取值在类中定义了常量，默认格式为yyyy-MM-dd HH:mm:ss
     *
     * @param str    待转化的字符串
     * @param format 指定格式
     * @return Date 返回指定格式为format的日期
     */
    public static Date string2Date(String str, String format) {
        if (ImcStringUtil.isEmpty(str)) {
            return null;
        }
        Date result = null;
        if (ImcStringUtil.isEmpty(format)) {
            return string2Date(str);
        }
        try {
            DateFormat mFormat = new SimpleDateFormat(format);
            result = mFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 字符串转换为java.util.Date类型,按字符串的长度来自动设置格式
     *
     * @param s 待转化的字符串
     * @return Date 按字符串长度设置格式，然后转化为java.util.Date类型
     */
    public static Date string2Date(String s) {
        if (ImcStringUtil.isEmpty(s)) {
            return null;
        }
        Date result = null;
        try {
            DateFormat format = null;
            if (s.length() > 15) {
                format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            } else if (s.length() > 8) {
                format = new SimpleDateFormat(YYYY_MM_DD);
            } else if (s.length() > 4) {
                format = new SimpleDateFormat(YYYY_MM);
            } else {
                format = new SimpleDateFormat(YYYY);
            }
            result = format.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按指定的格式sFormat格式化日期字符串，并转化为java.sql.Date
     *
     * @param str     待转化的字符串
     * @param sFormat 自定的格式
     * @return java.sql.Date 按格式sFormat格式化日期字符串转化为java.sql.Date类型的对象
     */
    public static java.sql.Date str2SqlDate(String str, String sFormat) {
        Date date = string2Date(str, sFormat);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    /**
     * 返回当天所在的年月
     *
     * @return String "yyyyMM"
     */
    public static String getCurrentYearMonth() {
        String res = "";
        Calendar caldTmp = Calendar.getInstance();
        caldTmp.setTime(new Date());
        if (caldTmp.get(Calendar.MONTH) + 1 < 10) {
			res = caldTmp.get(Calendar.YEAR) + "0" + (caldTmp.get(Calendar.MONTH) + 1);
		} else {
			res = caldTmp.get(Calendar.YEAR) + "" + (caldTmp.get(Calendar.MONTH) + 1);
		}
        return res;
    }

    /**
     * 取得当前日期的月份，以MM格式返回.
     *
     * @return 当前月份 MM
     */
    public static String getCurrentMonth() {
        return getFormatCurrentTime("MM");
    }

    /**
     * 取得当前日期的年份，以yyyy格式返回.
     *
     * @return 当年 yyyy
     */
    public static String getCurrentYear() {
        return getFormatCurrentTime("yyyy");
    }

    /**
     * 根据给定的格式，返回时间字符串
     * <p>
     * 参照DateFormator类，是调用了DateFormator类的date2String方法。
     *
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatCurrentTime(String format) {
        return date2String(new Date(), format);
    }

    /**
     * 添加n月份到一个日期对象，为负数则计算向前n个月
     *
     * @param dateInput     输入日期
     * @param numberOfMonth 月数
     * @return Date 计算后的结果日期
     */
    public static Date addMonths(Date dateInput, int numberOfMonth) {
        if (dateInput == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dateInput);
        c.add(Calendar.MONTH, numberOfMonth);
        return c.getTime();
    }

    /**
     * 对当前时间，取向前（为负值时向后）多少秒
     *
     * @param dInput         输入时间
     * @param numberOfSecond 偏移的秒数
     * @return Date 结果时间
     */
    public static Date addSecond(Date dInput, int numberOfSecond) {
        if (dInput == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dInput);
        c.add(Calendar.SECOND, numberOfSecond);
        return c.getTime();
    }
    /**
     * 获取时间段之间的整点小时数
     */
    public static String[] allHours(String start,String end){
        ArrayList<String> theList = new ArrayList<String>();
        int s = Integer.parseInt(start.substring(0,2));
        int e = Integer.parseInt(end.substring(0,2));
        int add = -1;
        String [] result = null;
        while (true){
            if(add == -1){
                add = s + 1 ;
            }else {
                add = add + 2;
            }
            if(add >= 24 && e<s){
                add = add -24;
            }
            if(add < 10){
                theList.add("0" + add);
            }else {
                theList.add(add + "");
            }
            if(add == e || add >= (e-1)){
                result = new String[theList.size()];
                result = (String [])theList.toArray(result);
                return result;
            }
        }
    }

    public static void main(String[] args) {
        List<String> week = getNDaysList("","2021-11-11",7);
        String[] weeks = new String[week.size()];
        week.toArray(weeks);
        for (String s : weeks) {
            System.out.println(s);
        }


//        int s = Integer.parseInt("06:40".substring(0,2));
//        int e = Integer.parseInt("15:10".substring(0,2));
//        System.out.println(s+":" + e);
    }
    /**
     * 取得前后day天数的日期,day为负数表示以前的日期
     *
     * @param date
     * @param day
     * @return
     */
    public static Date nextDate(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return calendar.getTime();
    }

    /**
     * 验证年份是否为闰年,闰年的条件是： ① 能被4整除，但不能被100整除； ② 能被100整除，又能被400整除。
     *
     * @param theYear 年份，如：2009
     * @return boolean 是闰年返回true，不是返回false
     */
    public static boolean isLeapYear(int theYear) {
        return ((theYear % 4 == 0 && theYear % 100 != 0) || (theYear % 400 == 0));
    }

    /**
     * 比较两个日期中某个时间单位的间隔数
     *
     * @param type     间隔时间单位类型，取值范围为：yyyy、m、d、h、n、s，对应为年、月、日、时、分、秒
     * @param fromDate 起始时间
     * @param toDate   终止时间
     * @return int 按类型用起始时间减去终止时间的值
     */
    public static int dateDiff(String type, Date fromDate, Date toDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);

        int fromDay = fromCalendar.get(Calendar.DAY_OF_YEAR);
        int toDay = toCalendar.get(Calendar.DAY_OF_YEAR);
        int fromMonth = fromCalendar.get(Calendar.MONTH);
        int toMonth = toCalendar.get(Calendar.MONTH);
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);
        int fromHour = fromCalendar.get(Calendar.HOUR_OF_DAY);
        int toHour = toCalendar.get(Calendar.HOUR_OF_DAY);
        int fromMinute = fromCalendar.get(Calendar.MINUTE);
        int toMinute = toCalendar.get(Calendar.MINUTE);
        int fromSecond = fromCalendar.get(Calendar.SECOND);
        int toSecond = toCalendar.get(Calendar.SECOND);

        int day = 0;
        int month = 0;
        int minute = 0;
        int second = 0;
        int hour = 0;

        for (int i = fromYear; i < toYear; i++) {
            int noOfDay;
            if (isLeapYear(i)) {
				noOfDay = 366;
			} else {
				noOfDay = 365;
			}
            day = day + noOfDay;
            hour = hour + (noOfDay * 24);
            minute = minute + (noOfDay * 24 * 60);
            second = second + (minute * 60);
            month = month + 12;
        }
        int daydiff = toDay - (fromDay - day);
        int hourdiff = toHour - (fromHour - hour) + daydiff * 24;
        int minutediff = toMinute - (fromMinute - minute) + hourdiff * 60;
        int secdiff = toSecond - (fromSecond - second) + minutediff * 60;
        if (type.equalsIgnoreCase("yyyy")) {
            return toYear - fromYear;
        } else if (type.equalsIgnoreCase("m")) {
            return toMonth - (fromMonth - month);
        } else if (type.equalsIgnoreCase("d")) {
            return daydiff;
        } else if (type.equalsIgnoreCase("h")) {
            return hourdiff;
        } else if (type.equalsIgnoreCase("n")) {
            return minutediff;
        } else if (type.equalsIgnoreCase("s")) {
            return secdiff;
        } else {
            return 0;
        }
    }

    /**
     * 比较两个日期的大小，精确到天
     *
     * @param fromDate 起始时间
     * @param toDate   终止时间
     * @return int 正数表示起始时间比终止时间大，负数表示起始时间比终止时间小
     */
    public static int dateDiffNoTime(Date fromDate, Date toDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);

        int fromDay = fromCalendar.get(Calendar.DAY_OF_YEAR);
        int toDay = toCalendar.get(Calendar.DAY_OF_YEAR);
        int fromMonth = fromCalendar.get(Calendar.MONTH);
        int toMonth = toCalendar.get(Calendar.MONTH);
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);

        // Calculate from value
        int fromDateVal = fromYear * 10000 + fromMonth * 100 + fromDay * 1;
        // Calculate to value
        int toDateVal = toYear * 10000 + toMonth * 100 + toDay * 1;

        return (fromDateVal - toDateVal);
    }

    /**
     * 取得指定日期对应月的第一天日期
     *
     * @param strYYYYMMDD 日期字符串,日期格式为yyyyMMdd
     * @return String 处理后的日期字符串,日期格式为yyyy-MM-dd
     */
    public static String getFirstDateOfMonth(String strYYYYMMDD) {
        Calendar caldTmp = Calendar.getInstance();
        // 取得该月第一天日期
        caldTmp.set(DateUtil.getIntYearOfDate(strYYYYMMDD), DateUtil.getIntMonthOfDate(strYYYYMMDD) - 1, 1);
        return DateUtil.date2String(caldTmp.getTime(), DateUtil.YYYY_MM_DD);
    }

    /**
     * 取得指定日期对应月的最后一天日期
     *
     * @param strYYYYMMDD 日期字符串,日期格式为yyyyMMdd
     * @return String 处理后的日期字符串,日期格式为yyyy-MM-dd
     */
    public static String getLastDateOfMonth(String strYYYYMMDD) {
        Calendar caldTmp = Calendar.getInstance();
        // 取得本月第一天日期
        caldTmp.set(getIntYearOfDate(strYYYYMMDD), (getIntMonthOfDate(strYYYYMMDD) - 1), 1);
        // 取得该月的下一个月第一天日期
        caldTmp.add(Calendar.MONTH, 1);
        // 下月第一天减一天即为本月最后一天
        caldTmp.add(Calendar.DATE, -1);
        return date2String(caldTmp.getTime(), YYYY_MM_DD);
    }

    /**
     * 取得指定日期所属week的周一的日期 注：此处返回周一，不是返回周日
     *
     * @param strYYYYMMDD 日期字符串，格式为yyyyMMdd或yyyy-MM-dd或yyyy/MM/dd
     * @return String 处理后的日期字符串,格式为yyyy-MM-dd
     */
    public static String getMondayOfThisWeek(String strYYYYMMDD) {
        Calendar caldTmp = Calendar.getInstance();
        // 注意：月份的起始值为０而不是１，所以要设置八月时，我们用７而不是8 -> calendar.set(Calendar.MONTH, 7);
        caldTmp.set(getIntYearOfDate(strYYYYMMDD), getIntMonthOfDate(strYYYYMMDD) - 1, getIntDayOfDate(strYYYYMMDD));
        int nDayOfWeek = caldTmp.get(Calendar.DAY_OF_WEEK);
        // System.out.println("  "+strYYYYMMDD+"||   Calendar.DAY_OF_WEEK="+Calendar.DAY_OF_WEEK+"; caldTmp.get(Calendar.DAY_OF_WEEK)="+caldTmp.get(Calendar.DAY_OF_WEEK));
        caldTmp.add(Calendar.DATE, -(caldTmp.get(Calendar.DAY_OF_WEEK) - 1));
        // 区别此处不同于西方：周日为每周的第一天，周六为每周最后一天
        // 周一为每周的第一天，周日为每周最后一天
        // 此处得到的caldTmp为周日，必须将其做相应修改
        // 此处得到的caldTmp为周日故需加一天；注意：若指定日期刚好是周日，则需减6天
        if (nDayOfWeek == 1) {
            caldTmp.add(Calendar.DATE, -6);
        } else {
            caldTmp.add(Calendar.DATE, 1);
        }
        return date2String(caldTmp.getTime(), YYYY_MM_DD);

    }

    /**
     * 取得指定日期所属week，周日的日期 注：此处返回周日，不是返回周六(按照中国工作周习惯，不同于西方将周六作为周末)
     *
     * @param strYYYYMMDD 日期字符串，格式为yyyyMMdd或yyyy-MM-dd或yyyy/MM/dd
     * @return String 处理后的日期字符串,格式为yyyy-MM-dd
     */
    public static String getSundayOfThisWeek(String strYYYYMMDD) {
        String strThisWeekFirstDate = getMondayOfThisWeek(strYYYYMMDD);
        return date2String(getOffsetDate(strThisWeekFirstDate, 6, "day"), YYYY_MM_DD);
    }

    /**
     * 取给定日期（strYYYYMMDD）所在月的第n(weekIndex)个星期的周一日期
     *
     * @param strYYYYMMDD
     * @param weekIndex
     * @return String
     */
    public static String getMondayOfWeek(String strYYYYMMDD, int weekIndex) {
        int nYear = 0;
        int nMonth = 0;
        Calendar caldTmp = Calendar.getInstance();
        nYear = getIntYearOfDate(strYYYYMMDD);
        nMonth = getIntMonthOfDate(strYYYYMMDD);
        getIntDayOfDate(strYYYYMMDD);
        caldTmp.set(nYear, nMonth - 1, 1);

        // 下面为什么这样做，我也不知道，只是这样做就能得到我想要的结果，所以就这样做了……
        // 得到这个月1日是星期几
        int dayOfWeek = caldTmp.get(Calendar.DAY_OF_WEEK);
        // 如果是周日或周一，则按正常情况处理
        if ((dayOfWeek == Calendar.SUNDAY) || (dayOfWeek == Calendar.MONDAY)) {
			caldTmp.set(Calendar.WEEK_OF_MONTH, weekIndex);
		}
            // 否则，当前周数加一
        else {
			caldTmp.set(Calendar.WEEK_OF_MONTH, weekIndex + 1);
		}

        // 设置日期为当周的第二天（即周一）
        caldTmp.set(Calendar.DAY_OF_WEEK, 2);

        String tmpDate = date2String(caldTmp.getTime(), "yyyy-MM-dd");

        // System.out.println("tmpDate: " + tmpDate + " " +
        // DateDispose.getNumWeekOfMonth(tmpDate));

        return tmpDate;
    }

    /**
     * 获得给定日期所在月共有多少个星期（判断规则：星期一的日期是几月份，则此周属于几月份）
     *
     * @param strYYYYMMDD
     * @return int
     */
    public static int getWeekCountOfMonth(String strYYYYMMDD) {
        int res = 1;
        String lastDate;
        // 取本月的最后一天日期
        lastDate = getLastDateOfMonth(strYYYYMMDD);
        // 取本月最后一天是本月的第几个星期
        res = getWeekIndexOfMonth(lastDate);
        return res;
    }

    /**
     * 根据年月取得当月的天数
     *
     * @param yyyy 年份
     * @param mm   月份
     * @return int 指定年月的当月天数
     */
    public static int getDaysOfMonth(int yyyy, int mm) {
        Calendar iCal = Calendar.getInstance();
        iCal.set(yyyy, mm, 1);
        iCal.add(Calendar.DATE, -1);
        return iCal.get(Calendar.DATE);
    }

    /**
     * @return 当前月份有多少天；
     */
    public static int getDaysOfCurMonth() {
        int curyear = new Integer(getCurrentYear()).intValue(); // 当前年份
        int curMonth = new Integer(getCurrentMonth()).intValue();// 当前月份
        int mArray[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0) || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 根据年月日计算是一个星期中的第几天，星期一为第一天
     *
     * @param yyyy
     * @param mm
     * @param dd
     * @return int 星期一返回1
     */
    public static int getDayIndexOfWeek(int yyyy, int mm, int dd) {
        Calendar iCal = Calendar.getInstance();
        iCal.set(yyyy, mm - 1, dd - 1);
        return iCal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 判断给定日期是所属星期的第几天
     *
     * @param strYYYYMMDD
     * @return int
     */
    public static int getDayIndexOfWeek(String strYYYYMMDD) {
        int res = 1;
        int nYear = 0;
        int nMonth = 0;
        int nDay = 0;
        Calendar caldTmp = Calendar.getInstance();
        nYear = getIntYearOfDate(strYYYYMMDD);
        nMonth = getIntMonthOfDate(strYYYYMMDD);
        nDay = getIntDayOfDate(strYYYYMMDD);
        caldTmp.set(nYear, nMonth - 1, nDay);

        res = caldTmp.get(Calendar.DAY_OF_WEEK) - 1;
        // 如果为0，说明当天是周日，按中国人的习惯应该是一周的第7天
        if (res == 0) {
			res = 7;
		}
        return res;
    }

    /**
     * 取得给定日期为所属月份第几周,日期格式为yyyy-MM-dd
     *
     * @param strYYYYMMDD 日期格式为yyyy-MM-dd
     * @return int 第一周返回0
     */
    public static int getWeekIndexOfMonth(String strYYYYMMDD) {
        Calendar caldTmp = Calendar.getInstance();
        // 按照西方周算出的结果，一周：周日->周六
        // 中国人习惯，一周：周一->周日
        caldTmp.setFirstDayOfWeek(Calendar.MONDAY);
        caldTmp.set(getIntYearOfDate(strYYYYMMDD), getIntMonthOfDate(strYYYYMMDD) - 1, getIntDayOfDate(strYYYYMMDD));
        int nWeekOfMonth = caldTmp.get(Calendar.WEEK_OF_MONTH);
        // 注意以下几种情况:
        // 先判断上月最后一天对应的周末
        // 1.若当前日期<=上月最后一天对应的周末，则返回0(给定日期属于上月)
        // 2.若当前日期>=上月最后一天对应的周末
        // 判断该月一号是否为周日？
        // 2.1 是周日， return nWeekOfMonth ;
        // 2.2 否则 return nWeekOfMonth -1 ;
        // 如：2004-7-1~4都是属上个月最后一周；而2004-8-1为上月最后一周sunday, 2004-8-2属于8月份第一周
        int nDateDiffNoTime = 0;
        // 本月一号
        String strFirstDayOfThisMonth = getFirstDateOfMonth(strYYYYMMDD);
        // 取得上月最后一周的sunday
        // 上月最后一天
        String strLastDateOfPreMonth = date2String(getOffsetDate(strFirstDayOfThisMonth, -1, "day"), YYYY_MM_DD);
        // 上月最后一天的sunday
        String strSundayOfLastWeekOfPreMonth = getSundayOfThisWeek(strLastDateOfPreMonth);
        // 判断给定日期是否小于上月最后一天对应的周末
        // 注：此处返回的日期字符串可能同传入参数不完全一样2004-08-01 与 2004-8-1;所以必须转为date比较
        Date dParam = string2Date(strYYYYMMDD, YYYY_MM_DD);
        Date dSundayOfLastWeekOfPreMonth = string2Date(strSundayOfLastWeekOfPreMonth);
        nDateDiffNoTime = dateDiffNoTime(dParam, dSundayOfLastWeekOfPreMonth);
        // System.out.println("--  nWeekOfMonth="+nWeekOfMonth+"; nDateDiffNoTime="+nDateDiffNoTime+"; strYYYYMMDD="+strYYYYMMDD+" ;strSundayOfLastWeekOfPreMonth="+strSundayOfLastWeekOfPreMonth);
        if (nDateDiffNoTime <= 0) {
            return 0;
        } else {
            // 判断该月一号是否为周日？
            // 本月1号对应的sunday
            String strSundayOfFirstDayOfThisMonth = getSundayOfThisWeek(strFirstDayOfThisMonth);
            // 将给定日期参数规整为标准格式 如：2004-8-1 -> 2004-08-01
            Date dTmp = string2Date(strYYYYMMDD, YYYY_MM_DD);
            String strStdYYYYMMDD = date2String(dTmp, YYYY_MM_DD);
            if (strStdYYYYMMDD.compareToIgnoreCase(strSundayOfFirstDayOfThisMonth) == 0) {
                // 本月1号为周日
                return nWeekOfMonth;
            } else {
                // 本月1号不为周日
                // 判断本月一号是否大于上月最后一天对应的周日
                Date dFistDayOfThisMonth = string2Date(strFirstDayOfThisMonth, YYYY_MM_DD);
                nDateDiffNoTime = dateDiffNoTime(dFistDayOfThisMonth, dSundayOfLastWeekOfPreMonth);
                if (nDateDiffNoTime > 0) {
                    return nWeekOfMonth;
                } else {
                    return nWeekOfMonth - 1;
                }

            }
        }

    }

    /**
     * 取得日期字符串中的年数值
     *
     * @param strYYYYMMDD
     * @return int 返回年份数值
     */
    public static int getIntYearOfDate(String strYYYYMMDD) {
        return Integer.parseInt(strYYYYMMDD.substring(0, 4));
    }

    /**
     * 取得日期字符串中月份的数值
     *
     * @param strYYYYMMDD
     * @return int
     */
    public static int getIntMonthOfDate(String strYYYYMMDD) {
        // 确定日期分割符号
        String strIntervalMark = "";
        if (strYYYYMMDD.indexOf("/") > 0) {
            strIntervalMark = "/";
        } else if (strYYYYMMDD.indexOf("-") > 0) {
            strIntervalMark = "-";
        }

        String strMonth = "";
        String strTmp = "";
        int nFirstMarkNum = 0;
        int nSecondMarkNum = 0;
        nFirstMarkNum = strYYYYMMDD.indexOf(strIntervalMark);
        strTmp = strYYYYMMDD.substring(nFirstMarkNum + 1);
        nSecondMarkNum = nFirstMarkNum + strTmp.indexOf(strIntervalMark);
        if ("".equals(strIntervalMark)) {
            // YYYYMMDD
            strMonth = strYYYYMMDD.substring(4, 6);
        } else {
            strMonth = strYYYYMMDD.substring(nFirstMarkNum + 1, nSecondMarkNum + 1);
        }
        return Integer.parseInt(strMonth);
    }

    /**
     * 取得日期字符串中的天数值
     *
     * @param strYYYYMMDD
     * @return int
     */
    public static int getIntDayOfDate(String strYYYYMMDD) {
        // 确定日期分割符号
        String strIntervalMark = "";
        if (strYYYYMMDD.indexOf(" ") > 0) {
			strYYYYMMDD = strYYYYMMDD.substring(0, strYYYYMMDD.indexOf(" "));
		}
        if (strYYYYMMDD.indexOf("/") > 0) {
            strIntervalMark = "/";
        } else if (strYYYYMMDD.indexOf("-") > 0) {
            strIntervalMark = "-";
        }

        String strDay = "";
        int nLastMarkNum = 0;
        nLastMarkNum = strYYYYMMDD.lastIndexOf(strIntervalMark);

        if (strIntervalMark.compareTo("") == 0) {
            // YYYYMMDD
            strDay = strYYYYMMDD.substring(6);
        } else {
            strDay = strYYYYMMDD.substring(nLastMarkNum + 1);
        }
        return Integer.parseInt(strDay);
    }

    /**
     * 将月份数字(从1到12)转化为英文缩写，月份的前3个字母，小写
     *
     * @param mm 月份
     * @return String
     */
    public static String getMonthName(int mm) {
        if (mm == 1) {
            return "jan";
        } else if (mm == 2) {
            return "feb";
        } else if (mm == 3) {
            return "mar";
        } else if (mm == 4) {
            return "apr";
        } else if (mm == 5) {
            return "may";
        } else if (mm == 6) {
            return "jun";
        } else if (mm == 7) {
            return "jul";
        } else if (mm == 8) {
            return "aug";
        } else if (mm == 9) {
            return "sep";
        } else if (mm == 10) {
            return "oct";
        } else if (mm == 11) {
            return "nov";
        } else if (mm == 12) {
            return "dec";
        } else {
            return null;
        }
    }

    /**
     * 按升序排序集合中的日期对象
     *
     * @param vDate
     * @return Vector
     */
    public static Vector<Date> sortDateVectorAsc(Vector<?> vDate) {
        Vector<Date> vSortedDate = new Vector<Date>();

        while (vDate.size() > 0) {
            Date dDate = getSmallestDate(vDate);
            if (dDate != null) {
                vSortedDate.addElement(dDate);
            }
        }
        return vSortedDate;
    }

    /**
     * 取得集合中所有日期类型中的最小日期
     *
     * @param vDate
     * @return Date
     */
    private static Date getSmallestDate(Vector<?> vDate) {
        int nDeleteIndex = -1;
        Date dDate = getDateObj(2999, 12, 31);
        for (int i = 0; i < vDate.size(); i++) {
            Date dPrevDate = dDate;
            Date dCurrDate = (Date) vDate.elementAt(i);
            if (dCurrDate.before(dPrevDate)) {
                dDate = dCurrDate;
                nDeleteIndex = i;
            }
        }
        if (nDeleteIndex > -1) {
            return (Date) vDate.remove(nDeleteIndex);
        }
        return null;
    }

    /**
     * 省略掉时间的毫秒，设置millisecond为0
     *
     * @param dDate
     * @return Date
     */
    public static Date trimMillis(Date dDate) {
        if (dDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 将指定日期偏移一段时间
     *
     * @param strYYYYMMDD   输入日期
     * @param nOffsetNum    前/后偏移数量
     * @param strOffsetUnit 前/后推周期单位 day,week,month,year,hour
     * @return Calendar
     */
    public static Date getOffsetDate(String strYYYYMMDD, int nOffsetNum, String strOffsetUnit) {
        int nYear = 0;
        int nMonth = 0;
        int nDay = 0;
        Calendar caldTmp = Calendar.getInstance();
        nYear = getIntYearOfDate(strYYYYMMDD);
        nMonth = getIntMonthOfDate(strYYYYMMDD);
        nDay = getIntDayOfDate(strYYYYMMDD);
        caldTmp.set(nYear, nMonth - 1, nDay);

        if ("day".equalsIgnoreCase(strOffsetUnit)) {
            caldTmp.add(Calendar.DATE, nOffsetNum);
        } else if ("week".equalsIgnoreCase(strOffsetUnit)) {
            caldTmp.add(Calendar.DATE, nOffsetNum * 7);
        } else if ("month".equalsIgnoreCase(strOffsetUnit)) {
            caldTmp.add(Calendar.MONTH, nOffsetNum);
        } else if ("year".equalsIgnoreCase(strOffsetUnit)) {
            caldTmp.add(Calendar.YEAR, nOffsetNum);

        } else if ("hour".equalsIgnoreCase(strOffsetUnit)) {
            caldTmp.add(Calendar.HOUR, nOffsetNum);

        }

        return caldTmp.getTime();
    }

    /**
     * 获得从开始日期到截止日期间所有有效日期的字符数组,参数格式为YYYYmmdd
     *
     * @param beginDate 格式为YYYYmmdd
     * @param endDate   格式为YYYYmmdd
     * @return String[] 格式为"YYYY-MM-DD"的日期字符串数组
     */
    public static String[] getDayList(String beginDate, String endDate) {
        ArrayList<String> theList = new ArrayList<String>();
        int beginYear, beginMonth, beginDay, endYear, endMonth, endDay;
        beginYear = getIntYearOfDate(beginDate);
        endYear = getIntYearOfDate(endDate);
        // java中的月份从0-11，所以正常的月份需要 - 1
        beginMonth = getIntMonthOfDate(beginDate) - 1;
        endMonth = getIntMonthOfDate(endDate) - 1;
        beginDay = getIntDayOfDate(beginDate);
        endDay = getIntDayOfDate(endDate);

        GregorianCalendar bCal = new GregorianCalendar(beginYear, beginMonth, beginDay);
        GregorianCalendar eCal = new GregorianCalendar(endYear, endMonth, endDay);
        Date eDate = eCal.getTime();
        Date bDate = bCal.getTime();
        String tmpDate;
        // 对比开始日期与截止日期的大小
        while (bDate.compareTo(eDate) <= 0) {
            tmpDate = date2String(bDate, "yyyy-MM-dd");
            // tmpDate = DateDispose.formatDate(tmpDate.substring(5));
            // System.out.println(tmpDate);
            theList.add(tmpDate);
            // 开始日期加1天
            bCal.add(Calendar.DATE, 1);
            bDate = bCal.getTime();
        }
        String[] res = new String[theList.size()];
        res = (String[]) theList.toArray(res);
        return res;
    }

    /**
     * @param beginDate YYYY-MM
     * @param endDate   YYYY-MM
     * @param beginWeek
     * @param endWeek
     * @return String[] "YYYY-MM|第几周" （年月 与 第几周之间用'|'分隔）
     */
    public static String[] getWeekList(String beginDate, String endDate, int beginWeek, int endWeek) {
        ArrayList<String> theList = new ArrayList<String>();
        int beginYear, beginMonth, beginDay, endYear, endMonth, endDay;
        int weekCntOfMonth, tmpInt;
        beginYear = getIntYearOfDate(beginDate);
        endYear = getIntYearOfDate(endDate);
        // java中的月份从0-11，所以正常的月份需要 - 1
        beginMonth = getIntMonthOfDate(beginDate) - 1;
        endMonth = getIntMonthOfDate(endDate) - 1;
        // 日期对于判断当前月有几个星期没有关系，所以日期可以为1-31的任何值，我们取一个较中间的值
        beginDay = 10;
        endDay = 10;

        GregorianCalendar bCal = new GregorianCalendar(beginYear, beginMonth, beginDay);
        GregorianCalendar eCal = new GregorianCalendar(endYear, endMonth, endDay);
        Date eDate = eCal.getTime();
        Date bDate = bCal.getTime();
        String tmpDate, tmpStr;
        // 如果开始日期比截至日期小，则不断循环
        while (bDate.compareTo(eDate) < 0) {
            tmpDate = date2String(bDate, "yyyy-MM-dd");
            // 获得当前月共有多少星期
            weekCntOfMonth = getWeekCountOfMonth(tmpDate);
            // 从本月起始星期 一直 循环到 截至星期
            for (tmpInt = beginWeek; tmpInt <= weekCntOfMonth; tmpInt++) {
                tmpStr = tmpDate.substring(0, 7) + "|" + tmpInt;
                theList.add(tmpStr);
            }

            // 起始星期回复到第一周
            beginWeek = 1;

            // 开始日期加1个月
            bCal.add(Calendar.MONTH, 1);
            bDate = bCal.getTime();
        }

        // 如果起始日期与截至日期相同
        if (bDate.compareTo(eDate) == 0) {
            tmpDate = date2String(bDate, "yyyy-MM-dd");
            // 获得当前月共有多少星期
            weekCntOfMonth = getWeekCountOfMonth(tmpDate);
            // 判断当月的星期总数与截至星期参数的大小（截至星期不能大于星期总数）
            if (endWeek > weekCntOfMonth) {
				endWeek = weekCntOfMonth;
			}
            for (tmpInt = beginWeek; tmpInt <= endWeek; tmpInt++) {
                tmpStr = tmpDate.substring(0, 7) + "|" + tmpInt;
                theList.add(tmpStr);
            }
        }

        String[] res = new String[theList.size()];
        res = (String[]) theList.toArray(res);
        return res;
    }

    /**
     * 获取给定日期区间的指定星期数的所有日期字符串数组
     *
     * @param startDate 开始日期（到天的日期值）
     * @param endDate   结束日期（到天的日期值）
     * @param week      周几（1、2、3、4、5、6、7）
     * @return 如输入(" 2016 - 05 - 01 ", " 2016 - 05 - 20 ", 7), 则返回 List<String> 20160501、20160508、20160515
     */
    public static List<String> getWeekList(String startDate, String endDate, int week) {
        List<String> result = new ArrayList<String>();
        Date end = string2Date(endDate);
        int dayOfWeek = getDayIndexOfWeek(startDate);
        int step = -1;
        if (dayOfWeek <= week) {
            step = week - dayOfWeek;
        } else {
            step = 7 + week - dayOfWeek;
        }
        Date start = getOffsetDate(startDate, step, "day");
        String startTemp = null;
        while (start.before(end)) {
            startTemp = date2String(start, "yyyyMMdd");
            result.add(startTemp);
            start = getOffsetDate(startTemp, 7, "day");
        }
        return result;
    }

    /**
     * 获取从开始月份到截至月份间所有有效月份的字符数组
     *
     * @param beginDate
     * @param endDate
     * @return String[] 格式为"YYYY-MM"
     */
    public static String[] getMonthList(String beginDate, String endDate) {
        ArrayList<String> theList = new ArrayList<String>();
        int beginYear, beginMonth, beginDay, endYear, endMonth, endDay;
        beginYear = getIntYearOfDate(beginDate);
        endYear = getIntYearOfDate(endDate);
        // java中的月份从0-11，所以正常的月份需要 - 1
        beginMonth = getIntMonthOfDate(beginDate) - 1;
        endMonth = getIntMonthOfDate(endDate) - 1;
        // 我们只关心年月的大小，但日期也能影响对比，所以我们把日期设置为相同
        beginDay = 10;
        endDay = 10;

        GregorianCalendar bCal = new GregorianCalendar(beginYear, beginMonth, beginDay);
        GregorianCalendar eCal = new GregorianCalendar(endYear, endMonth, endDay);
        Date eDate = eCal.getTime();
        Date bDate = bCal.getTime();
        String tmpDate, tmpStr;
        // 如果开始日期比截至日期小，则不断循环
        while (bDate.compareTo(eDate) <= 0) {
            tmpDate = date2String(bDate, "yyyy-MM-dd");
            tmpStr = tmpDate.substring(0, 7);
            // System.out.println(tmpStr);
            theList.add(tmpStr);

            // 开始日期加1个月
            bCal.add(Calendar.MONTH, 1);
            bDate = bCal.getTime();
        }

        String[] res = new String[theList.size()];
        res = (String[]) theList.toArray(res);
        return res;
    }

    /**
     * 获取从开始日期到截至日期间所有有效年份的字符数组
     *
     * @param beginDate
     * @param endDate
     * @return String[] 格式为"YYYY"
     */
    public static String[] getYearList(String beginDate, String endDate) {
        ArrayList<String> theList = new ArrayList<String>();
        int beginYear, beginMonth, beginDay, endYear, endMonth, endDay;
        beginYear = getIntYearOfDate(beginDate);
        endYear = getIntYearOfDate(endDate);
        // 我们只关心年的大小，但月份日期也能影响对比，所以我们把月份日期设置为相同
        beginMonth = 10;
        endMonth = 10;
        beginDay = 10;
        endDay = 10;

        GregorianCalendar bCal = new GregorianCalendar(beginYear, beginMonth, beginDay);
        GregorianCalendar eCal = new GregorianCalendar(endYear, endMonth, endDay);
        Date eDate = eCal.getTime();
        Date bDate = bCal.getTime();
        String tmpDate, tmpStr;
        // 如果开始日期比截至日期小，则不断循环
        while (bDate.compareTo(eDate) <= 0) {
            tmpDate = date2String(bDate, "yyyy-MM-dd");
            tmpStr = tmpDate.substring(0, 4);
            // System.out.println(tmpStr);
            theList.add(tmpStr);

            // 开始日期加1个年
            bCal.add(Calendar.YEAR, 1);
            bDate = bCal.getTime();
        }

        String[] res = new String[theList.size()];
        res = (String[]) theList.toArray(res);
        return res;
    }

    /**
     * 格式化"MM-dd"或"yyyy-MM"格式的字串，去除字串中月份或日期数字中的"0"
     *
     * @param date "MM-dd"或"yyyy-MM"格式的字串
     * @return String
     */
    public static String formatDate(String date) {
        String res = "";
        if (date == null) {
			return res;
		}
        int year, month, day;
        try {
            // "MM-dd"格式的字串
            if (date.length() == 5) {
                // 去除月份和日期前面的"0"
                month = Integer.parseInt(date.substring(0, 2));
                day = Integer.parseInt(date.substring(3));
                res = month + "-" + day;
            }
            // "yyyy-MM"格式的字串
            else if (date.length() == 7) {
                year = Integer.parseInt(date.substring(0, 4));
                month = Integer.parseInt(date.substring(5));
                res = year + "-" + month;
            }
        } catch (Exception e) {
            res = date;
        }
        return res;
    }

    /**
     * 把日期字符转换为中文含义的日期字符
     *
     * @param date 格式为"yyyy-MM-dd"或"MM-dd"或"yyyy-MM"格式的字串
     * @return String "yyyy年MM月dd日"或"MM月dd日"或"yyyy年MM月"格式的字串
     */
    public static String formatDateToCN(String date) {
        String res = "";
        if (date == null) {
			return res;
		}
        int year, month, day;
        try {
            // 是"MM-dd"格式的字串
            if (date.length() == 5) {
                month = Integer.parseInt(date.substring(0, 2));
                day = Integer.parseInt(date.substring(3));
                res = month + "月" + day + "日";
            }
            // "yyyy-MM"格式的字串
            else if (date.length() == 7) {
                year = Integer.parseInt(date.substring(0, 4));
                month = Integer.parseInt(date.substring(5, 7));
                res = year + "年" + month + "月";
            }
            // 是"yyyy-MM-dd"格式的字串
            else if (date.length() == 10) {
                year = Integer.parseInt(date.substring(0, 4));
                month = Integer.parseInt(date.substring(5, 7));
                day = Integer.parseInt(date.substring(8));
                res = year + "年" + month + "月" + day + "日";
            } else {
				res = date;
			}
        } catch (Exception e) {
            res = date;
        }
        return res;
    }

    /**
     * 通过年月日生成时间对象
     *
     * @param year  年份
     * @param month 月份
     * @param day   日期
     * @return Date
     */
    public static Date getDateObj(int year, int month, int day) {
        int mon = month - 1;
        int ye;
        Date db;
        Calendar rightNow = Calendar.getInstance();
        if (year >= 0 && year < 80) {
			ye = year + 2000;
		} else if (year > 100) {
			ye = year;
		} else {
			ye = year + 1900;
		}
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(ye, mon, day);
        db = rightNow.getTime();
        return db;
    }

    /**
     * 取得指定分隔符分割的年月日的日期对象.
     *
     * @param argsDate 格式为"yyyy-MM-dd"等格式
     * @param split    时间格式的间隔符，例如“-”，“/”，要和时间一致起来。
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(String argsDate, String split) {
        String[] temp = argsDate.split(split);
        int year = new Integer(temp[0]).intValue();
        int month = new Integer(temp[1]).intValue();
        int day = new Integer(temp[2]).intValue();
        return getDateObj(year, month, day);
    }

    /**
     * 原期号i个月之前或者之后的期号值,如200310后5月为200403
     *
     * @param str
     * @param i
     * @return String
     */
    public static String addMonth(String str, int i) {
        String issue = str; // 原期号格式为：200302
        int n_year = Integer.parseInt(issue) / 100;
        int n_month = Integer.parseInt(issue) % 100;
        int aY = i / 12;
        int aM = i % 12;
        n_year = n_year + aY;
        n_month = n_month + aM;
        if (n_month > 12) {
            n_year = n_year + 1;
            n_month = n_month - 12;
        }
        if (n_month < 0) {
            n_year = n_year - 1;
            n_month = 12 + n_month;
        }
        if (n_month < 10) {
            issue = ((Integer.toString(n_year).trim()) + '0' + ((Integer.toString(n_month).trim())));
        } else {
            issue = ((Integer.toString(n_year).trim()) + ((Integer.toString(n_month).trim())));
        }

        return issue;
    }

    /**
     * 根据所给年、月、日，检验是否为合法日期。
     *
     * @param yyyy
     * @param MM
     * @param dd
     * @return boolean
     */
    public static boolean verifyDate(int yyyy, int MM, int dd) {
        boolean flag = false;
        if ((MM >= 1) && (MM <= 12) && (dd >= 1) && (dd <= 31)) {
            if ((MM == 4) || (MM == 6) || (MM == 9) || (MM == 11)) {
                if (dd <= 30) {
                    flag = true;
                }
            } else if (MM == 2) {
                if (((yyyy % 100 != 0) && (yyyy % 4 == 0)) || (yyyy % 400 == 0)) {
                    if (dd <= 29) {
                        flag = true;
                    }
                } else if (dd <= 28) {
                    flag = true;
                }
            } else {
                flag = true;
            }

        }
        return flag;
    }

    /**
     * 返回当天的日期
     *
     * @return "YYYY-MM-DD"
     */
    public static String getToday() {
        return DateUtil.date2String(new Date(), DateUtil.YYYY_MM_DD);
    }

    /**
     * 返回前n天账期
     *
     * @return "YYYY-MM-DD"
     */
    public static String lastFewDay(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, n);
        return DateUtil.date2String(cal.getTime(), DateUtil.YYYY_MM_DD);
    }

    /**
     * 返回前n月账期
     *
     * @return "YYYY-MM"
     */
    public static String lastFewMon(int n) {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        cal.setTime(today);
        cal.set(Calendar.MONDAY, cal.get(Calendar.MONDAY) - n);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
        return DateUtil.date2String(cal.getTime(), DateUtil.YYYY_MM);
    }

    /**
     * 根据所给的起始时间,间隔天数来计算终止时间
     *
     * @return 终止时间
     */
    public static java.sql.Date getStepDay(java.sql.Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, step);
        return new java.sql.Date(calendar.getTime().getTime());
    }

    /**
     * 得到将date增加指定月数后的date
     *
     * @param date
     * @param intBetween
     * @return date加上intBetween月数后的日期
     */
    public static java.sql.Date getStepMonth(Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(Calendar.MONTH, intBetween);
        return new java.sql.Date(calo.getTime().getTime());
    }

    /**
     * 得到将date增加指定年数后的date
     *
     * @param date
     * @param intBetween
     * @return date加上intBetween年数后的日期
     */
    public static java.sql.Date getStepYear(Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(Calendar.YEAR, intBetween);
        return new java.sql.Date(calo.getTime().getTime());
    }

    /*
     * 获取昨天的日期
     */
    public static String getLastDate(String start) {
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = formatDay.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return formatDay.format(cal.getTime());
    }

    /*
     * 获取前一个月的同一天
     */
    private static Calendar getDateOfLastMonth(Calendar date) {
        Calendar lastDate = (Calendar) date.clone();
        lastDate.add(Calendar.MONTH, -1);
        return lastDate;
    }

    /*
     * 获取前一个月的同一天
     */
    public static String getDateOfLastMonth(String dateStr) {
        try {
            Date date = formatDay.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            Calendar day = getDateOfLastMonth(c);
            return formatDay.format(day.getTime());
            // return getDateOfLastMonth(c);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format(yyyyMMdd): " + dateStr);
        }
    }

    /*
     * 取得当前日期的上月
     */
    public static String getFrontBackStrDate(String strDate) {
        if (null == strDate) {
            return null;
        }
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(formatMon.parse(strDate));
            c.add(Calendar.MONTH, -1);
            return formatMon.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    private static SimpleDateFormat formatMon1 = new SimpleDateFormat(YYYY_MM);

    /*
     * 取得当前日期的上月
     * 入参yyyy-dd 回参yyyy-dd
     */
    public static String getFrontBackStrDate1(String strDate) {
        if (null == strDate) {
            return null;
        }

        try {
            Calendar c = Calendar.getInstance();
            c.setTime(formatMon1.parse(strDate));
            c.add(Calendar.MONTH, -1);
            return formatMon1.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * 取得当前日期的上一年同期
     */
    public static String getFrontYearStrDate(String strDate) {
        if (null == strDate) {
            return null;
        }
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(formatMon.parse(strDate));
            c.add(Calendar.YEAR, -1);
            return formatMon.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * 取得当前日期的上一年同期
     * 入参yyyy-dd 回参yyyy-dd
     */
    public static String getFrontYearStrDate1(String strDate) {
        if (null == strDate) {
            return null;
        }
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(formatMon1.parse(strDate));
            c.add(Calendar.YEAR, -1);
            return formatMon1.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取两个日期之间所有的日期
     *
     * @param date1
     * @param date2
     * @return
     */
    public static ArrayList<String> getAlldays(String date1, String date2) {
        ArrayList<String> L = new ArrayList<String>();
        L.add(formatDay.format(str2Date(date1).getTime()));
        if (date1.equals(date2)) {
            // System.out.println("两个日期相等!");
            return L;
        }

        String tmp;
        if (date1.compareTo(date2) > 0) { // 确保 date1的日期不晚于date2
            tmp = date1;
            date1 = date2;
            date2 = tmp;
        }

        tmp = formatDay.format(str2Date(date1).getTime() + 3600 * 24 * 1000);

        int num = 0;
        while (tmp.compareTo(date2) < 0) {
            L.add(tmp);
            num++;
            tmp = formatDay.format(str2Date(tmp).getTime() + 3600 * 24 * 1000);
        }

        // if (num == 0)
        // System.out.println("两个日期相邻!");
        L.add(formatDay.format(str2Date(date2).getTime()));
        return L;
    }

    /**
     * 字符串转换成Date
     *
     * @param str
     * @return
     */
    private static Date str2Date(String str) {
        if (str == null) {
			return null;
		}

        try {
            return formatDay.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 获取同期前一月集合
     */
    public static List<String> getFrontMonthList(List<String> monthList) {
        List<String> monthBeforeList = new ArrayList<String>();
        for (int i = 1; i < monthList.size(); i++) {
            monthBeforeList.add(getDateOfLastMonth(monthList.get(i)));
        }
        return monthBeforeList;
    }

    public static List<String> getFrontMonthList2(List<String> monthList) {
        List<String> monthBeforeList = new ArrayList<String>();
        for (int i = 0; i < monthList.size(); i++) {
            monthBeforeList.add(getDateOfLastMonth(monthList.get(i)));
        }
        return monthBeforeList;
    }

    /*
     * 获取两个日期之间的所有月份
     */
    public static List<String> getAllMonths(String start, String end) {
        Date date1 = null; // 开始日期
        Date date2 = null; // 结束日期
        try {
            date1 = formatMon.parse(start);
            date2 = formatMon.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        // 定义集合存放月份
        List<String> list = new ArrayList<String>();
        // 添加第一个月，即开始时间
        list.add(start);
        c1.setTime(date1);
        c2.setTime(date2);
        while (c1.compareTo(c2) < 0) {
            c1.add(Calendar.MONTH, 1);// 开始日期加一个月直到等于结束日期为止
            Date ss = c1.getTime();
            String str = formatMon.format(ss);
            list.add(str);
        }
        return list;
    }

    /*
     * 获取同期前一年集合
     */
    public static List<String> getFrontYearList(List<String> yearList) {
        List<String> yearBeforeList = new ArrayList<String>();
        for (int i = 1; i < yearList.size(); i++) {
            yearBeforeList.add(getFrontYearStrDate(yearList.get(i)));
        }
        return yearBeforeList;
    }

    public static List<String> getFrontYearList2(List<String> yearList) {
        List<String> yearBeforeList = new ArrayList<String>();
        for (int i = 0; i < yearList.size(); i++) {
            yearBeforeList.add(getFrontYearStrDate(yearList.get(i)));
        }
        return yearBeforeList;
    }
    /**
     * 获取指定日期所在的一周(周一到周日)
     * @param date
     * @return
     */
    public static List<String> getDateToWeek(Date date) {
        List<String> dateWeekList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "";
        //count 用来存取与当天日期的相差数
        int count = 0;
        for (int i = 1; i < 8; i++) {
            //新建日历
            Calendar cal = Calendar.getInstance();
            //在日历中找到当前日期
            cal.setTime(date);
            //当前日期是本周第几天，默认按照中国习惯星期一为一周的第一天(末尾的+1的由来)
            count = -cal.get(Calendar.DAY_OF_WEEK) + 1;
            //循环。当天与本周周一到周日相差的天数
            cal.add(Calendar.DATE, count + i);
            //转化格式
            time = sdf.format(cal.getTime());
            //存入list
            dateWeekList.add(time);
        }
        return dateWeekList;
    }
    /**
     *  用户可以传入startTime或endTime任意一个或两个，也可以不传入
     *  当传入的时间间隔太长时，默认返回最近的nday
     *  plus: StringUtils为org.apache.commons.lang.StringUtils，读者也可以手动判断""和null
     */

    public static List<String> getNDaysList(String startTime, String endTime, int nday) {
        int ndaycurrent = nday - 1;
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-ndaycurrent);
            Date start = calendar.getTime();
            Date end = new Date();
            if (StringUtils.isNotBlank(startTime) && StringUtils.isBlank(endTime)){
                //如果用户只选择了startTime,endTime为null,startTime + 10的日期
                start = dateFormat.parse(startTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(start);
                calendar1.add(Calendar.DATE, ndaycurrent);
                end = calendar1.getTime();
            }else if(StringUtils.isBlank(startTime) && StringUtils.isNotBlank(endTime)){
                //如果用户只选择了endTime,startTime为null,endTime - 10的日期
                end = dateFormat.parse(endTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(end);
                calendar2.add(Calendar.DATE, -ndaycurrent);
                start = calendar2.getTime();
            }else if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
                //如果用户选择了startTime和endTime，判断endTime - startTime两个日期是否超过了ndaycurrent，超过返回最近nday天记录
                Date start1 = dateFormat.parse(startTime);
                Date end1 = dateFormat.parse(endTime);
                int a = (int) ((end1.getTime() - start1.getTime()) / (1000*3600*24));
                if (a <= ndaycurrent) {
                    //如果小于等于n天
                    start = dateFormat.parse(startTime);
                    end = dateFormat.parse(endTime);
                }
            }
            //如果超过了ndaycurrent天,就是默认的start和end
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)

            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

}
