package cn.sits.rjb.common.utils;

import cn.sits.rjb.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间处理辅助类
 */
public class DateUtil {

    public static final SimpleDateFormat formatNY = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat formatNYR = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat formatNYRSFM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat formatNYRSF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final SimpleDateFormat format_NYR = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat format_NY = new SimpleDateFormat("yyyy年MM月");

    public static String formatToStringNYR(Date date){
        return  formatNYR.format(date);
    }
    public static String formatToStringNYRSFM(Date date){
        return  formatNYRSFM.format(date);
    }


    public static String formatToString_NYR(Date date){
        return  format_NYR.format(date);
    }
    public static String formatToString_NY(Date date){
        return  format_NY.format(date);
    }
    public static String formatToStringNYRSF(Date date){
        return  formatNYRSF.format(date);
    }
    public static SimpleDateFormat formatter19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 获得两个时间的毫秒时间差
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
         * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
         * @param strDate
         * @return
         */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }


    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }
    /**
     * 给时间加上几个小时
     * @param date  当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    public static Date addDateMinut(Date date, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return  date ;
    }

    /**
     * 给时间减上分钟
     * @param date  当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param min 需要加的时间
     * @return
     */
    public static String beforeDateMinut(String date, int min) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime =format.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.MINUTE, - min);// 5分钟之前的时间
        Date beforeD = calendar.getTime();
        String beforeTime = formatter19.format(beforeD);
        return  beforeTime ;
    }

    /**
     * 获取当天的开始时间
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }
    public static Date parseToDateNYRSFM(String tm){
        try {
            return formatNYRSFM.parse(tm);
        } catch (Exception e){
            e.printStackTrace();
            return new Date();
        }
    }
    /**
     * 距离当前时间相差的天数
     * @param tm
     * @return
     */
    public static Long getDay(String tm){
        Long days = null;
        try {
            Date currentTime = formatNYR.parse(formatNYR.format(new Date()));//现在系统当前时间
            Date pastTime = formatNYR.parse(tm);
            long diff = Math.abs(pastTime.getTime() - currentTime.getTime());
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }



    /**
     * 查询 1本日，2本周，3本月 的开始结束时间
     * @param num
     * @return
     */
    public static Map<String,Date> findBeginDateAndEndDateByType(int num){
        Map<String,Date> dateMap = new HashMap<>();
        Date beginDate = null;
        Date endDate = null;
        String type = "";
        try {
            switch (num){
                case Constants.oneNum ://日
                    String time = formatToStringNYR(new Date());
                    beginDate = formatNYRSFM.parse(time + " 00:00:00");
                    endDate = formatNYRSFM.parse(time + " 23:59:59");
                    break;
                case Constants.twoNum ://周
                    beginDate = getBeginDayOfWeek();
                    endDate = getEndDayOfWeek();
                    break;
                case Constants.threeNum ://月
                    beginDate = GetStartMonthDate();
                    endDate = GetEndMonthDate();
                    break;
            }
            dateMap.put("beginDate",beginDate);
            dateMap.put("endDate",endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateMap;
    }


    public static boolean isInTimeRange(Date date,Date startDate,Date endDate){
        long time = date.getTime();
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        return time >= startTime && time <= endTime;
    }
    /**
     * 获取本周的开始时间
     * @return
     * @throws Exception
     */
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        Date time = cal.getTime();
        String format = formatNYR.format(time) + " 00:00:00";
        try {
            return formatNYRSFM.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本周的结束时间
     * @return
     */
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        String format = formatNYR.format(weekEndSta) + " 23:59:59";
        try {
            return formatNYRSFM.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //当月1日
    public static Date GetStartMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        Date date = parseToDateNYRSFM(formatToStringNYR(firstDayOfMonth) + " 00:00:00");
        return date;
    }
    //当月最后一天
    public static Date GetEndMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date date = parseToDateNYRSFM(formatToStringNYR(calendar.getTime()) + " 23:59:59");
        return date;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 获取两个日期之间的所有月份
     * @param minDate
     * @param maxDate
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 查询N月之前到现在的所有月份
     * @param monthNum
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(int monthNum) throws ParseException {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        String maxDate = sdf.format(new Date());

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -monthNum);
        String minDate = sdf.format(c.getTime());

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    //获取当前时间的前dayNum天的日期
    public static String GetLastDateByDay(int dayNum){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,- dayNum);
            date=calendar.getTime();
            String timeString = formatter.format(date);
            return timeString;
        } catch (Exception e){
            return null;
        }
    }
    //获取当前时间的前monthNum月日期
    public static String GetLastDateByMonth(int monthNum){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH,- monthNum);
            date=calendar.getTime();
            String timeString = formatter.format(date) + "-01 00:00:00";
            return timeString;
        } catch (Exception e){
            return null;
        }
    }

    public static Map<String,Date> getBeginAndEndDate(String beginTime,String endTime){
        Map<String,Date> map = new HashMap<>();
        if (StringUtils.isEmpty(beginTime)){
            beginTime = "1970-01-01 00:00:00";
        } else {
            beginTime = beginTime + " 00:00:00";
        }
        if (StringUtils.isEmpty(endTime)){
            endTime = formatNYRSFM.format(new Date());
        } else {
            endTime = endTime + " 23:59:59";
        }
        try {
            map.put("beginTime",formatNYRSFM.parse(beginTime));
            map.put("endTime",formatNYRSFM.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }


    public static List<String> getMonthListByYear(String year){
        List<String> monthList = new ArrayList<>();
        for (int i = 1;i <= 12;i++){
            String month = "";
            if (i<10){
                month = "-0"+i;
            } else {
                month = "-"+i;
            }
            monthList.add(year + month);
        }
        return monthList;
    }

    /**
     * 获得某个月最大天数
     * @param year 年份
     * @param month 月份 (1-12)
     * @return 某个月最大天数
     */
    public static int getMaxDayByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    public static List<String> getDayListByMonth(String tm) throws ParseException {
        List<String> dayList = new ArrayList<>();
        Date date = formatNY.parse(tm);
        int year = date.getYear();
        int month = date.getMonth() + 1;
        int dayNum = getMaxDayByYearMonth(year, month);
        String day = "";
        for (int i=1;i<=dayNum;i++){
            if (i<10){
                day = "-0"+i;
            } else {
                day = "-"+i;
            }
            dayList.add(tm+day);
        }
        return dayList;
    }

}
