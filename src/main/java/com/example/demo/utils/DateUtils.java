package com.example.demo.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

        private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
                "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

        /**
         * 得到当前日期字符串 格式（yyyy-MM-dd）
         */
        public static String getDate() {
            return getDate("yyyy-MM-dd");
        }

        /**
         * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
         */
        public static String getDate(String pattern) {
            return DateFormatUtils.format(new Date(), pattern);
        }

        /**
         * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
         */
        public static String formatDate(Date date, Object... pattern) {
            String formatDate = null;
            if (pattern != null && pattern.length > 0) {
                formatDate = DateFormatUtils.format(date, pattern[0].toString());
            } else {
                formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
            }
            return formatDate;
        }

        /**
         * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
         */
        public static String formatDateTime(Date date) {
            return formatDate(date, "yyyy-MM-dd HH:mm:ss");
        }

        /**
         * 得到当前时间字符串 格式（HH:mm:ss）
         */
        public static String getTime() {
            return formatDate(new Date(), "HH:mm:ss");
        }

        /**
         * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
         */
        public static String getDateTime() {
            return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        }

        /**
         * 得到当前年份字符串 格式（yyyy）
         */
        public static String getYear() {
            return formatDate(new Date(), "yyyy");
        }

        /**
         * 得到当前月份字符串 格式（MM）
         */
        public static String getMonth() {
            return formatDate(new Date(), "MM");
        }

        /**
         * 得到当天字符串 格式（dd）
         */
        public static String getDay() {
            return formatDate(new Date(), "dd");
        }

        /**
         * 得到当前星期字符串 格式（E）星期几
         */
        public static String getWeek() {
            return formatDate(new Date(), "E");
        }

        /**
         * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
         */
        public static Date parseDate(Object str) {
            if (str == null) {
                return null;
            }
            try {
                return parseDate(str.toString(), parsePatterns);
            } catch (ParseException e) {
                return null;
            }
        }

        public static Date parseStrDate(String str, String parter) {
            if (StringUtils.isEmpty(str) || StringUtils.isEmpty(parter)) {
                return null;
            }
            try {
                return parseDate(str, parter);
            } catch (ParseException e) {
                return null;
            }
        }

        /**
         * 获取过去的天数
         *
         * @param date
         * @return
         */
        public static long pastDays(Date date) {
            long t = new Date().getTime() - date.getTime();
            return t / (24 * 60 * 60 * 1000);
        }

        /**
         * 获取过去的小时
         *
         * @param date
         * @return
         */
        public static long pastHour(Date date) {
            long t = new Date().getTime() - date.getTime();
            return t / (60 * 60 * 1000);
        }

        /**
         * 获取过去的分钟
         *
         * @param date
         * @return
         */
        public static long pastMinutes(Date date) {
            long t = new Date().getTime() - date.getTime();
            return t / (60 * 1000);
        }

        /**
         * 转换为时间（天,时:分:秒.毫秒）
         *
         * @param timeMillis
         * @return
         */
        public static String formatDateTime(long timeMillis) {
            long day = timeMillis / (24 * 60 * 60 * 1000);
            long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
            long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
            return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
        }

        /**
         * 获取两个日期之间的天数
         *
         * @param before
         * @param after
         * @return
         */
        public static int getDistanceOfTwoDate(Date before, Date after) {
            long beforeTime = before.getTime();
            long afterTime = after.getTime();
            return (int)((afterTime - beforeTime) / (1000 * 60 * 60 * 24));
        }

        /**
         * 获取两个日期之间的分钟数
         *
         * @param before
         * @param after
         * @return
         */
        public static long getDistanceMinOfTwoDate(Date before, Date after) {
            Long time = after.getTime() - before.getTime();
            return time / (60 * 1000);
        }

        /**
         * 获取两个日期之间的分钟数
         */
        public static long getMiniteOfTwoDate(Date before, Date after) {
            Long time = after.getTime() - before.getTime();
            return (time / (60 * 1000));
        }

        public static Date strParseDate(String str, String parter) {
            if (str == null) {
                return null;
            }

            try {
                return parseDate(str.toString(), parter);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 将小时转换成分钟，如 08:00 ---> 480,flag为true时，将00:22 转换成24:22
         */
        public static long convertTime(String date, boolean flag) {
            String[] start = date.split(":");
            String str1 = start[0];// 小时
            String str2 = start[1];// 分钟
            if (flag) {
                if ("24".equals(str1)) {
                    str1 = "24";
                }
            }
            Long totalMinute = Long.valueOf(str1) * 60 + Long.valueOf(str2);
            return totalMinute;
        }

        /**
         * 小时转换
         */
        public static long convertRateTime(String date) {
            String[] start = date.split(":");
            String str1 = start[0];// 小时
            return Long.valueOf(str1);
        }

        /**
         * @param args
         * @throws ParseException
         */
        public static void main(String[] args) throws ParseException {
            // System.out.println(formatDate(parseDate("2010/3/6")));
            // System.out.println(getDate("yyyy年MM月dd日 E"));
            // long time = new Date().getTime()-parseDate("2012-11-19").getTime();
            // System.out.println(time/(24*60*60*1000));
            /*
             * String str = "00:00"; str == "2" ? "24:00" : str;
             */
            String str = "Type";
            String test = "firstType";
            String temp1 = str.toUpperCase();
            String temp2 = test.toUpperCase();
            if (temp2.contains(temp1)) {
                System.out.println("存在此字符串");
            }
        }

        /**
         * 判断时间是否在时间段内
         *
         * @param start
         *            开始时间段
         * @param end
         *            结束时间段
         * @param date
         *            时间
         */
        public static boolean compareDate(Date date, String start, String end) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String now = new SimpleDateFormat("HH:mm").format(date);
            try {
                Date date2 = sdf.parse(now);
                if (date2.getTime() >= sdf.parse(start).getTime() && date2.getTime() <= sdf.parse(end).getTime()) {
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static boolean isToday(Date date) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date);
            int year1 = c1.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH) + 1;
            int day1 = c1.get(Calendar.DAY_OF_MONTH);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            int year2 = c2.get(Calendar.YEAR);
            int month2 = c2.get(Calendar.MONTH) + 1;
            int day2 = c2.get(Calendar.DAY_OF_MONTH);
            if (year1 == year2 && month1 == month2 && day1 == day2) {
                return true;
            }
            return false;
        }

        /**
         * java获取某天起始时间
         *
         * @return
         */
        public static Date getStartTime(Date startDate) {
            Calendar currentDate = new GregorianCalendar();
            currentDate.setTime(startDate);
            currentDate.set(Calendar.HOUR_OF_DAY, 0);
            currentDate.set(Calendar.MINUTE, 0);
            currentDate.set(Calendar.SECOND, 0);
            return (Date) currentDate.getTime().clone();
        }

        /**
         * java获取某天结束时间
         *
         * @return
         */
        public static Date getEndTime(Date endDate) {
            Calendar currentDate = new GregorianCalendar();
            currentDate.setTime(endDate);
            currentDate.set(Calendar.HOUR_OF_DAY, 23);
            currentDate.set(Calendar.MINUTE, 59);
            currentDate.set(Calendar.SECOND, 59);
            return (Date) currentDate.getTime().clone();
        }

        /**
         * 获取某月天数
         */
        public static int getDayOfMonth(Date date) {
            Calendar currentDate = new GregorianCalendar();
            currentDate.setTime(date);
            int day = currentDate.getActualMaximum(Calendar.DATE);
            return day;
        }

        /**
         * 得到某月第一天
         */
        public static Date getFirstDay(Date date) {
            Calendar currentDate = new GregorianCalendar();
            currentDate.setTime(date);
            currentDate.set(Calendar.DAY_OF_MONTH, 1);
            currentDate.set(Calendar.HOUR_OF_DAY, 00);
            currentDate.set(Calendar.MINUTE, 00);
            currentDate.set(Calendar.SECOND, 00);
            return (Date) currentDate.getTime().clone();
        }

        /**
         * 得到某月最后一天
         */
        public static Date getLastDay(Date date) {
            Calendar currentDate = new GregorianCalendar();
            currentDate.setTime(date);
            int days = getDayOfMonth(date);
            currentDate.set(Calendar.DAY_OF_MONTH, days);
            currentDate.set(Calendar.HOUR_OF_DAY, 23);
            currentDate.set(Calendar.MINUTE, 59);
            currentDate.set(Calendar.SECOND, 59);
            return (Date) currentDate.getTime().clone();
        }

        /**
         * 天数减n
         */
        public static Date beforeOneDay(Date date,int n) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - n);
            return calendar.getTime();
        }

        /**
         * 年数减n
         */
        public static Date beforeOneYear(Date date,int n) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - n);
            return calendar.getTime();
        }

        /**
         * 月数减n
         */
        public static Date beforeOneMonth(Date date,int n) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - n);
            return calendar.getTime();
        }

        /**
         * 获取当前时间 yyyyMMddHHmmssSSS
         */
        public static String getCurrLongTime() {
            Date now = new Date();
            SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String s = outFormat.format(now);
            return s;
        }

        /**
         * 得到当前时间至当天23:59:59时刻的秒数
         */
        public static long getTodayLastSeconds(Date date1) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            long time1 = calendar1.getTimeInMillis();

            String str2 = DateUtils.formatDate(date1, "yyyy-MM-dd 23:59:59");
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(DateUtils.parseStrDate(str2, "yyyy-MM-dd HH:mm:ss"));
            long time2 = calendar2.getTimeInMillis();

            return (time2 - time1) / 1000;
        }

        /**
         * 得到当前时间至当月最后一天23:59:59时刻的秒数
         */
        public static long getMonthLastSeconds(Date date1) {
            Date lastDay = getLastDay(date1);
            String str2 = DateUtils.formatDate(lastDay, "yyyy-MM-dd 23:59:59");

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            long time1 = calendar1.getTimeInMillis();

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(DateUtils.parseStrDate(str2, "yyyy-MM-dd HH:mm:ss"));
            long time2 = calendar2.getTimeInMillis();

            return (time2 - time1) / 1000;
        }

        /**
         * 时间校验(校验两个日期不能超过相隔天数)
         */
        public static boolean checkDateFromTwoDate(String starteDate, String endDate, int dayNum) {
            try {
                if (StringUtils.isEmpty(starteDate) || StringUtils.isEmpty(endDate))
                    return false;

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date begin = df.parse(starteDate);
                Date end = df.parse(endDate);
                if (DateUtils.addDays(begin, dayNum).getTime() < end.getTime())
                    return false;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
}
