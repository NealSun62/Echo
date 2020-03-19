package cn.sits.rjb.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class DateHelper {

    public static SimpleDateFormat formatteryear = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
    //获取当天
    public static Date GetCurrentDate(){
        return new Date();
    }

    public static Date GetTodayDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取第二天
    public static Date GetTomorrowDate(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);
        date=calendar.getTime();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter.format(date);
        return date;
    }
    //获取传入时间的前一天
    public static String GetYesterdayDate(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        date=calendar.getTime();
        String timeString = formatter.format(date);
        return timeString;
    }

    //获取传入时间的前一天
    public static String GetTommorrowTime(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        date=calendar.getTime();
        String timeString = formatter.format(date);
        return timeString;
    }

    //获取传入时间的前七天（当天）
    public static String GetLastweekDate(String time){
       try {
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           Date date = formatter.parse(time);
           Calendar calendar = new GregorianCalendar();
           calendar.setTime(date);
           calendar.add(Calendar.DATE,-6);
           date=calendar.getTime();
           String timeString = formatter.format(date);
           return timeString;
       } catch (Exception e){
           return null;
       }
    }

    public static Date getLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.MONTH, 1);
        date = cal.getTime();
        return date;
    }

    public static String GetLastYearDate(String time){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            Date date = formatter.parse(time);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH,-11);
            date=calendar.getTime();
            String timeString = formatter.format(date);
            return timeString + "-01 00:00:00";
        } catch (Exception e){
            return null;
        }
    }

    //获取一个时间段内的日期
    public static List<String> getDates(String start,String end){ //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                list.add(sdf.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


    //获取一个时间段内的日期
    public static List<String> getDateList(String start,String end){ //
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf1.parse(start);
            Date date_end = sdf1.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                list.add(sdf.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    //获取一个时间段内的日期倒序
    public static List<String> getDatesReversed(String start,String end){ //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                list.add(0,sdf.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    //获取一个时间段内的日期
    public static List<String> getBetweenDate(String start,String end){ //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                list.add(sdf1.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取一个时间段内的日期
    public static List<String> getBetweenMonth(String time,int num){ //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date = sdf.parse(time);
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (num == 0){
                cd.setTime(date);
                cd.add(Calendar.MONTH, -1);//增加一天 放入集合
                num = num - 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getDatesAndHours(String start,String end){ //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayTime = sdf.format(new Date());
        String endTime = end;
        if (todayTime.equals(end)){
            end = GetYesterdayDate(end);
        }
        String[] hours = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                String day = sdf.format(date);
                for (int i=0;i < hours.length;i++){
                    list.add(day + " " + hours[i]);
                }
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
            if (todayTime.equals(endTime)){
                int a = Integer.parseInt(getHour());
                for (int i = 0;i <= a;i++){
                    list.add(endTime + " " + hours[i]);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getDateString(String start,String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                String day = sdf.format(date);
                list.add(day);
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
//            if (todayTime.equals(endTime)){
//                int a = Integer.parseInt(getHour());
//                for (int i = 0;i <= a;i++){
//                    list.add(endTime);
//                }
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    //获取本周周一
    public static Date GetMondayForCurWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();

    }

    //当月1日
    public static Date GetStartMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        return firstDayOfMonth;
    }

    public static Map<String,Date> GetOneDayBeginAndEndDate(String time) throws ParseException {
        Map<String,Date> map = new HashMap<String,Date>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginTime = time + " 00:00:00";
        String endTime = time + " 23:59:59";
        Date beginDate = formatter.parse(beginTime);
        Date endDate = formatter.parse(endTime);
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        return map;
    }
    public static Map<String,String> GetOneDayBeginAndEndTime(String time) throws ParseException {
        Map<String,String> map = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("beginTime",time + " 00:00:00");
        map.put("endTime",time + " 23:59:59");
        return map;
    }

    public static Map<String,String> getBeginTimeAndEndTime(String beginTime,String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> map = new HashMap<>();
        if (StringUtils.isEmpty(beginTime)){
            beginTime = "1970-01-01 00:00:00";
        } else {
            beginTime = beginTime + " 00:00:00";
        }
        if (StringUtils.isEmpty(endTime)){
            endTime = formatter.format(new Date());
        } else {
            endTime = endTime + " 23:59:59";
        }
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        return map;
    }

    public static Map<String,Date> getBeginAndEndDate(String beginTime,String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Date> map = new HashMap<>();
        if (StringUtils.isEmpty(beginTime)){
            beginTime = "1970-01-01 00:00:00";
        } else {
            beginTime = beginTime + " 00:00:00";
        }
        if (StringUtils.isEmpty(endTime)){
            endTime = formatter.format(new Date());
        } else {
            endTime = endTime + " 23:59:59";
        }
        try {
            map.put("beginTime",formatter.parse(beginTime));
            map.put("endTime",formatter.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String,Date> GetBeginDateAndEndDate(String beginTime,String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Date> map = new HashMap<>();
        Date beginDate = new Date();
        Date endDate = new Date();
        try {
            beginDate = formatter.parse("1970-01-01");
            if (StringUtils.isNotEmpty(beginTime)){
                beginDate = formatter.parse(beginTime);
            }
            if (StringUtils.isNotEmpty(endTime)){
                endDate = formatter.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        return map;
    }
    public static Map<String,String> getBeginDateAndEndDate(String beginTime,String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> map = new HashMap<>();
        if (StringUtils.isEmpty(beginTime)){
            beginTime = "1970-01-01";
        }
        if (StringUtils.isEmpty(endTime)){
            endTime = formatter.format(new Date());
        }
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        return map;
    }
    //当月最后一天
    public static Date GetEndMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();
        return lastDayOfMonth;
    }
    /**
     *
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getPerFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取下一个月.
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }
    //获取指定年月开始时间
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }
    //获取指定年月结束时间
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    public static Map<String,String> getOneMonthBetweenAndEndDate(int year,int month){
        Map map = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime begin = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));
        map.put("beginTime",formatter.format(Date.from(begin.toInstant())));
        if(format.format(new Date()).equals(format.format(Date.from(begin.toInstant())))){
            map.put("endTime",formatter.format(new Date()));
        } else {
            LocalDate endOfMonth = yearMonth.atEndOfMonth();
            LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
            ZonedDateTime end = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
            map.put("endTime",formatter.format(Date.from(end.toInstant())));
        }
        return map;
    }
    public static Date lastDate(Date date){
        Calendar calendar = Calendar.getInstance();
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(date); //设置时间为当前时间
        ca.add(Calendar.DATE, -1); //年份减1
        Date lastDATE = ca.getTime(); //结果
        return lastDATE;
    }

    //本年年初
    public static Date GetStartYearDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    //本年年末
    public static Date GetEndYearDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }
    //获取传入时间的前一天
    public static Date GetYesterdayDate(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        date=calendar.getTime();
        return date;
    }


    //获取传入时间的前一天
    public static String GetYesterdayString(String date){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(formatter.parse(date));
            calendar.add(Calendar.DATE,-1);
            date=formatter.format(calendar.getTime());
            return date;
        } catch (Exception e) {
            return null;
        }

    }

    /// <summary>
    /// 计算周期起始结束时间，公共方法
    /// </summary>
    /// <param name="method"></param>
    /// <param name="number"></param>
    /// <param name="subnumber"></param>
    /// <param name="StartDate"></param>
    /// <param name="EndDate"></param>


    public static Map<String,Date> CalculateStartTime(int year, int method, int number, int subnumber){
        Date startDate = new Date();;
        Date endDate = new Date();;
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(method == 1){//年
            startDate = GetStartYearDate();
            endDate =GetTomorrowDate();
        }else if(method == 2){//季
            date = year + "-" + (1 + (number - 1) * 3) + "-1";
            try {
                startDate = dateFormat.parse(date);
                date = year + "-" + (1 + (number - 1) * 3 + 3) + "-1";
                endDate = dateFormat.parse(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

        }else if(method == 3){//月
            date = year + "-" + (1 + (number - 1) * 1) + "-1";
            try {
                startDate = dateFormat.parse(date);
                date = year + "-" + (1 + (number - 1) * 1 + 1) + "-1";
                endDate = dateFormat.parse(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }else if(method == 4){//旬
            date = year + "-" + (1 + (number - 1) * 1) + "-" + (1 + (subnumber - 1) * 10);
            try {
                startDate = dateFormat.parse(date);
                if(subnumber == 3){
                    date = year + "-" + (1 + (number - 1) * 1 + 1) + "-1";
                }else{
                    date = year + "-" + (1 + (number - 1) * 1 + 10) + "-" + (1 + (subnumber - 1) * 10);
                }
                endDate = dateFormat.parse(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }else if(method == 5){
            date = year + "-" + (1 + (number - 1) * 1) + "-" + (1 + (subnumber - 1) * 7);
            try {
                startDate = dateFormat.parse(date);
                date = year + "-" + (1 + (number - 1) * 1 + 7) + "-" + (1 + (subnumber - 1) * 7);
                endDate = dateFormat.parse(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        Map<String,Date> CalculateTime = new HashMap<String,Date>();
        CalculateTime.put("startDate",startDate);
        CalculateTime.put("endDate",endDate);
        return CalculateTime;
    }

    //1:当日，2：本周，3：本月，4：本年
    public static Date  GetEndDateForQueryType(int type){
        if (type == 1)
        {
            return GetTomorrowDate();
        }
        else if (type == 2)
        {
            return GetTomorrowDate();
        }
        else if (type == 3)
        {
            return GetTomorrowDate();
        }
        else if (type == 4)
        {
            return GetTomorrowDate();
        }
        else
            return new Date();
    }

    //1:当日，2：本周，3：本月，4：本年
    public static Date  GetStartDateForQueryType(int type){
        if (type == 1)
        {
            return GetCurrentDate();
        }
        else if (type == 2)
        {
            return GetMondayForCurWeek();
        }
        else if (type == 3)
        {
            return GetStartMonthDate();
        }
        else if (type == 4)
        {
            return GetStartYearDate();
        }
        else
            return new Date();
    }

    /// <summary>
    /// 获取指定日期，在为一年中为第几周
    /// </summary>
    /// <param name="dt">指定时间</param>
    /// <reutrn>返回第几周</reutrn>
    public static int GetWeekOfYear(Date  dt) {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
    //获取开始时间，结束时间
    public static  Map<String,Date> GetStartAndEndTime(Date startDate,Date endDate){
        Map<String,Date> date = new HashMap<String,Date>();
        Date tempDate = startDate;

        if(null != startDate){
            date.put("startDate",tempDate);
        }else{
            tempDate = new Date(70,01,01);
            date.put("startDate",tempDate);
        }
        if(null != endDate){
            tempDate = endDate;
            date.put("endDate",tempDate);
        }else{
            tempDate = GetTomorrowDate();
            date.put("endDate",tempDate);
        }

        return date;
    }


    //获取开始时间，结束时间
    public static  Map<String,Date> GetStartAndEndTimeString(String startDate,String endDate) throws ParseException {
        Map<String,Date> date = new HashMap<String,Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date tempDate = new Date();
        if(StringUtils.isNotEmpty(startDate)){
            startDate =startDate.replace("Z"," UTC");
            date.put("startDate",sdf.parse(startDate));
        }else{
            tempDate = new Date(70,01,01);
            date.put("startDate",tempDate);
        }
        if(StringUtils.isNotEmpty(endDate)){
            endDate =endDate.replace("Z"," UTC");
            date.put("endDate",sdf.parse(endDate));
        }else{
            tempDate = GetTomorrowDate();
            date.put("endDate",tempDate);
        }

        return date;
    }
    /**
     * 获取指定日期的起始时间
     */
    public static Date getCurrentDateStartTime(Date date) {
        Calendar calendar = Calendar. getInstance();
        calendar.setTime(date);
        calendar.set(Calendar. HOUR_OF_DAY, 0);
        calendar.set(Calendar. MINUTE, 0);
        calendar.set(Calendar. SECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取指定日期的结束时间
     */
    public static Date getCurrentDateEndTime(Date date) {
        Calendar calendar = Calendar. getInstance();
        calendar.setTime(date);
        calendar.set(Calendar. HOUR_OF_DAY, 23);
        calendar.set(Calendar. MINUTE, 59);
        calendar.set(Calendar. SECOND, 59);
        return calendar.getTime();
    }
    public static Map<String,String> getTodayBeginTimeAndEndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> times = new HashMap<String,String>();
        String nowTime = formatter.format(new Date());
        times.put("beginTime",nowTime + " 00:00:00");
        times.put("endTime",nowTime + " 23:59:59");
        return times;
    }

    public static Map<String,String> getbegAndEnddayHour(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatHour = new SimpleDateFormat("yyyy-MM-dd HH");
        Map<String,String> times = new HashMap<String,String>();
        String btime = time +" 00";
        String etime = time + " 23";
        if (formatter.format(new Date()).equals(time)){
            etime = formatHour.format(new Date());
        }
        times.put("btime",btime);
        times.put("etime",etime);
        return times;
    }

    //格式转换方法
    public static String formatterStringH(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        return  formatter.format(ftDate);
    }

    public static Date formatterTDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSXXX");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String formatterString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  formatter.format(ftDate);
    }

    public static String forString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        return  formatter.format(ftDate);
    }

    public static String formatrString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return  formatter.format(ftDate);
    }
    public static String formatHourString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return  formatter.format(ftDate);
    }
    public static String fmtString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return  formatter.format(ftDate);
    }

    public static String getThisMonthBeg(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String format = formatter.format(new Date());
        return format + "-01";
    }
    public static String fmtYearAndMon(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        return  formatter.format(ftDate);
    }
    public static String fmtMonAndDay(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        return  formatter.format(ftDate);
    }
    public static String fmtYear(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return  formatter.format(ftDate);
    }
    public static String fmtmnth(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return  formatter.format(ftDate);
    }
    public static String fmtDay(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return  formatter.format(ftDate);
    }

    public static String fmtZWString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日");
        return  formatter.format(ftDate);
    }
    public static String fmtZWddString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日H时");
        return  formatter.format(ftDate);
    }
    public static String fmtZWSFMString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日H时m分s秒");
        return  formatter.format(ftDate);
    }

    public static String fmtSJString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
        return  formatter.format(ftDate);
    }

    public static String fmtTmCodeString(Date ftDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return  formatter.format(ftDate);
    }

    public static Date formatDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static Date formatterDate(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return  formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date formatMonth(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        try {
            return  formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date formatterMonth(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        try {
            return  formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date formatterHour(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            return  formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String forTime(Date date){
        String[] CN_NUMBER_NAME = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(date).toString().split("-");
        String year = String.valueOf(Integer.valueOf(strNow[0]));
        String month = String.valueOf(Integer.valueOf(strNow[1]));
        String day = String.valueOf(Integer.valueOf(strNow[2]));
        StringBuilder cnDate = new StringBuilder();
        for (int i = 0; i < year.length(); i++) {
            cnDate.append(CN_NUMBER_NAME[Integer.valueOf(String.valueOf(year.charAt(i)))]);
        }
        cnDate.append("年");
        for (int i = 0; i < month.length(); i++) {
            cnDate.append(CN_NUMBER_NAME[Integer.valueOf(String.valueOf(month.charAt(i)))]);
        }
        cnDate.append("月");
        if (day.length() == 2) {
            cnDate.append(CN_NUMBER_NAME[Integer.valueOf(String.valueOf(day.charAt(0)))]);
            cnDate.append("十");
            if (!String.valueOf(day.charAt(1)).equals("0")) {
                cnDate.append(CN_NUMBER_NAME[Integer.valueOf(String.valueOf(day.charAt(1)))]);
            }
        } else {
            cnDate.append(CN_NUMBER_NAME[Integer.valueOf(String.valueOf(day.charAt(0)))]);
        }
        cnDate.append("日");
        return cnDate.toString();
    }

    /**
     * 返回示例（‘2018-09-12 12:00:00’，2018-09-12 12:00:00’）
     * @param timeNum 需要的时间数量
     * @param interval 时间间隔
     * @return
     */
    public static String getTimeString(int timeNum,int interval){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar calendar = Calendar.getInstance();
        List<String> timeList = new ArrayList<>();
        timeList.add(df.format(calendar.getTime())+":00");
        for (int i = 0;i < timeNum;i++){
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.get(Calendar.HOUR_OF_DAY) + interval);
            timeList.add(df.format(calendar.getTime())+":00");
        }
        return SplitUtil.splitString(timeList);
    }
    public static Date formatNowTime(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            return  formatter.parse(time + " "+format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date formatTime(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            return  formatter.parse(time + " "+formatter.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getTodayTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return  formatter.format(new Date());
    }

    public static  String getTodayTimeString(){

        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return  dateString;
    }
    public static String getTodayTimeHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        return  formatter.format(new Date());
    }
    public static String getHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        return  formatter.format(new Date());
    }
    /**
     * 获取某个时间段内的工作日、周末、节假日
     */
 /*   public static DateList DayList(Date startDate, Date endDate){//0工作日、1周末、2节假日
        DateList dateList = new DateList();
        if(null == startDate){
            startDate = new Date(70,01,01);
        }
        if(null == endDate){
            endDate = GetTomorrowDate();
        }
        List<String> valueList = new ArrayList<>();
        Date dateJudge = startDate;
         List<Date> weekDayList = new ArrayList<>();
         List<Date> satDayList = new ArrayList<>();
         List<Date> sunDayList = new ArrayList<>();
         List<Date> dayList = new ArrayList<>();
        int i = 0;
        while (!dateJudge.after(endDate)){
            BufferedReader in = null;
            StringBuffer sb = new StringBuffer();
            try{
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateJudge);
                calendar.add(Calendar.DAY_OF_MONTH, + 1);
                dateJudge = calendar.getTime();
                i++;
                String dateJudgeString = sdf.format(calendar.getTime());
                URL url = new URL("http://api.goseek.cn/Tools/holiday?date="+dateJudgeString);
                in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
                String str = null;
                while((str = in.readLine()) != null) {
                    sb.append( str );
                }
                //json串转化为json对象
                JSONObject json = JSON.parseObject(sb.toString());
                String value = json.get("data").toString();
                valueList.add(value);
                if (value.equals("0")) {
                    weekDayList.add(dateJudge);
                }else if(value.equals("1")){
                    if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {
                        satDayList.add(dateJudge);
                    }else if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                        sunDayList.add(dateJudge);
                    }
                }else if(value.equals("2")){
                    dayList.add(dateJudge);
                }

            } catch (Exception ex) {
            } finally{
                try{
                    if(in!=null) {
                        in.close();
                    }
                }catch(IOException ex) {
                }
            }
        }
        dateList.setDayList(dayList);
        dateList.setWeekDayList(weekDayList);
        dateList.setSunDayList(sunDayList);
        dateList.setSatDayList(satDayList);

        return dateList;
    }*/



    /**
     * 返回日时分秒
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        StringBuffer result = new StringBuffer("");
        if (0 < days){
            result.append(days + "天");
        }
        if (0 < hours){
            result.append(hours + "时");
        }
        if (0 < minutes){
            result.append(minutes + "分");
        }
//        if (0 < second){
//            result.append(second + "秒");
//        }
        return result.toString();
    }
    public static String getYear() {
        Calendar  cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        return String.valueOf(year);
    }
    public static String getFormat(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(time);
        return format.format(parse);
    }

    //获取上个月日期
    public static String getLastMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
//        System.out.println("过去一个月："+mon);
        return mon;
    }
    /**
     * 获取某一天某一时间段的所有的小时数
     * @param endTime
     * @param interval
     * @return
     */
    public static List<String> getOneDayHour(String endTime,int interval){
        List<String> hourList = new ArrayList<>();
        int hours = DateHelper.formatDate(endTime).getHours();
        double ceil = Math.ceil(Double.valueOf(hours / interval));
        for (int i = 0,a = 0;i <= ceil+1 ; i++){
            hourList.add(a+"");
            a+=interval;
        }
        return hourList;
    }
    //获取时间段内的所有日期  不包含年份
    public static List<String> findDates(String btm, String etm) throws ParseException {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat day = new SimpleDateFormat("MM-dd");
        Date dBegin = sd.parse(btm);
        Date dEnd = sd.parse(etm);
        lDate.add(day.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(day.format(calBegin.getTime()));
        }
        return lDate;
    }

    //获取时间段内的所有日期  包含年份
    public static List<String> findYearDates(String btm, String etm) throws ParseException {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat day = new SimpleDateFormat("MM-dd");
        Date dBegin = sd.parse(btm);
        Date dEnd = sd.parse(etm);
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

    public static List<Integer> orderList(List<Integer> beginList, List<Integer> endList, int beginIndex, int endIndex){
        endList = new ArrayList<>();
        if (beginIndex >= endIndex){//开始的索引小于结束的索引
            for (int i = 0; i <= beginList.size() -1 - beginIndex;i++){
                endList.add(beginList.get(beginIndex+i));
            }
            for (int i = 0;i < endIndex;i++){
                endList.add(beginList.get(i));
            }

            for (int i = 1; i<beginIndex - endIndex;i++){
                endList.add(beginList.get(endIndex+i));
            }
            if (beginIndex != endIndex){
                endList.add(beginList.get(endIndex));
            }

        } else {
            for (int i = 0; i < endIndex - beginIndex;i++){
                endList.add(beginList.get(beginIndex + i));
            }
            for (int i = 1;i <= beginList.size()-1 - endIndex;i++){
                endList.add(beginList.get(endIndex + i));
            }
            for (int i = 0;i < beginIndex;i++){
                endList.add(beginList.get(i));
            }
            endList.add(beginList.get(endIndex));
        }


        return endList;
    }
    //获取某个时间减去i个小时得时间
    public static  String  getDateinterval( Date date ,int i){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, i);// before 8 hour
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(cal.getTime());
    }

    //
    public static  List<String>  getClockDate( Date date ){
        List<String> clockDate = new ArrayList<>();
        clockDate.add(DateHelper.formatterString(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        for(int i = 0;i<23;i++){
            cal.add(Calendar.HOUR_OF_DAY, 1);// before 8 hour
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String res =  format.format(cal.getTime());
            clockDate.add(res);
        }
            return clockDate;
    }

    public static  List<String>  getFlowDate( String date,int clock,int p ){
        List<String> clockDate = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateHelper.formatDate(date));
        for(int i = 0;i<clock;i++){
            if(i == p){
                break;
            }
            cal.add(Calendar.HOUR_OF_DAY, 1);// before 8 hour
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String res =  format.format(cal.getTime());
            clockDate.add(res);
        }
        return clockDate;
    }


    public static  String  getDateintervalday( Date date ,int i){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, i);// before 8 hour
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(cal.getTime());
    }
    /**
     * 获取指定日期所在月份开始的时间
     * @param date 指定日期
     * @return
     */
    public static Date getMonthBegin(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return  c.getTime();
    }
    /**
     * 获取指定日期所在月份结束的时间戳
     * @param date 指定日期
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND,59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTime();
    }


    public static  String getintTimeString(int i){

        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return  dateString;
    }

    public static  String getTimeStringSFM(int i){

        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return  dateString;
    }

    public static  List<String> getLatestNumMonth(int Num){
        String[] latest12Months = new String[12];
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); //要先+1,才能把本月的算进去
        for(int i=0; i<Num; i++){
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月
            latest12Months[11-i] = cal.get(Calendar.YEAR)+ "-" +fillZero(cal.get(Calendar.MONTH)+1);
        }
        List<String> resultList= new ArrayList<>(Arrays.asList(latest12Months));
        return resultList;
    }
    public static String fillZero(int i){
        String month = "";
        if(i<10){
            month = "0" + i;
        }else{
            month = String.valueOf(i);
        }
        return month;
    }
    public static String getSFM(int second ){
        int hour = second/3600;
        int minute = second%3600/60;
        int sec = second%60;
        return  hour+"时"+minute+"分"+sec+"秒";
    }
    //获取指定月份的年月日日期 yyyy-MM-dd
    public static List<String> getMonthFullDay(int year , int month){
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        List<String> fullDayList = new ArrayList<>(32);

        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, year);
        // 1月从0开始
        cal.set(Calendar.MONTH, month-1 );
        // 当月1号
        cal.set(Calendar.DAY_OF_MONTH,1);
        int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int j = 1; j <= count ; j++) {
            fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        return fullDayList;
    }

    //获取指定日期的第二天
    public static String getSpecifiedDayAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);
        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

   public static String getformatDate(String str){
       SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
       SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy年MM月dd日"); //加上时间
       //必须捕获异常
       try {
           Date date=simpleDateFormat.parse(str);
           String result = sDateFormat.format(date);
            return result;
       } catch(Exception e) {
           e.printStackTrace();
       }
       return null;
   }

    public static List<Double> findClockFlow(List<String> clockDate,List<String> flowDateList,double flow){
        List<Double> flowList = new ArrayList<>();
        for(int i = 0;i<clockDate.size();i++){
            double f = 0.0;
            if(null != flowDateList && flowDateList.size()>0){
                for(int j = 0;j<flowDateList.size();j++){
                    if(clockDate.get(i).equals(flowDateList.get(j))){
                        f = flow;
                        break;
                    }
                }
            }
            flowList.add(f);
        }
        return flowList;
    }
    public static Map<String,Date> GetThisMonthStartAndEndDate(){
        Map<String,Date> dateMap = new HashMap<String,Date>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = DateHelper.GetStartMonthDate();
        try {
            Date startDate = format.parse(format.format(startTime));
            dateMap.put("startDate",startDate);
            Calendar date = Calendar.getInstance();
            int year = date.get(Calendar.YEAR);
            int month = date.get(Calendar.MONTH)+1;
            Date endDate = DateHelper.getEndTime(year, month);
            dateMap.put("endDate",endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateMap;
    }

    //本年年初
    public static Date GetThisYearStartDate() {
        String year = formatteryear.format(new Date());
        String time = year+"-01" + "-01";
        Date currYearFirst = null;
        try {
            currYearFirst = smd.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currYearFirst;
    }

    public static void main(String args[]) throws ParseException {
//        System.out.println(getMonthFullDay(2019,04));
//        System.out.println(getformatDate("2019-05-31"));
//        System.out.println(getDates("2019-05-25","2019-06-22"));
        //System.err.println(getTodayTimeString());
//    List<String> dates = DateHelper.getDatesAndHours("2018-09-17", "2018-09-18");
////    System.out.println(dates);
////    String s = DateHelper.secondToTime(343434);
////    System.out.println(s);
////    System.err.println(DateHelper.formatterString(getCurrentDateStartTime(new Date())));
////    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
////
////    System.out.println(formatter.parse("2018-09-08 2" ));
////    System.out.println(getFormat("201802028"));
////    System.out.println(getTodayTime());
//    List<String> dateString = getDateString("2018-12-01", "2019-03-03");
//    System.out.println(dateString);
//    String s = MathUtil.DealGetRate(1, 3, 2);
//    String s1 = MathUtil.DealMsgRate(1, 3);
//    System.out.println(s +"---"+s1);
//    Map<String, String> map = getOneMonthBetweenAndEndDate(2019, 3);
//    System.out.println(map.get("beginTime"));
//    System.out.println(map.get("endTime"));
//    List<Integer> beginList = new ArrayList<>();
//    for (int i = 0 ; i< 10;i++){
//        beginList.add(i);
//    }
//    List<Integer> integers = orderList(beginList, new ArrayList<>(), 1, 5);
//    for (int i = 0;i<integers.size() ;i++){
//        System.out.println(integers.get(i));
//    }
        //System.err.println(DateHelper.fmtString(DateHelper.GetYesterdayDate(new Date())));
//    Calendar ca = Calendar.getInstance();
//    System.err.println(fmtZWddString(new Date(new Date().getYear(),0,1)));
//    System.out.println(forTime(new Date()));
//    // Calendar ca = Calendar.getInstance();
//    //  System.err.println(fmtZWddString(new Date(new Date().getYear(),0,1)));
//    System.out.println(getTimeString(5,4));
//  String s= "123456";
//   System.err.println(s.substring(0,4));
      //  System.out.println(DateHelper.getTimeStringSFM(-1));
        //System.out.println(MessageFormat.format("该域名{0}被访问了 {1} 次.", "sadsa" , "十"));
//        List<String > ddd = findYearDates("2019-01-01","2019-03-24");
//        for (String s : ddd){
//            System.err.println(s);
//        }
//        System.out.println(getOneDayHour("2018-12-01 21:34:56",3));
//        System.out.println(GetLastweekDate("2018-12-03 00:00:00"));;
      //  System.out.println(getLatestNumMonth(12));
//        System.out.println(formatterString(getLastMonth(formatterDate("2019-07-01"))));
//        System.out.println(new BigDecimal((new Date().getTime() - DateHelper.formatTime("2019-07-19 18:50:15").getTime())/1000/60));

//        BigDecimal xs = (new BigDecimal(2095).divide(new BigDecimal(3600),5,ROUND_HALF_UP));//小时
//        System.out.println(xs);
//        BigDecimal pow = new BigDecimal(160).multiply((xs).setScale(5, ROUND_HALF_UP));
//        System.out.println(pow);
//        System.out.println(new BigDecimal(2095).intValue()/3660);
//        System.out.println(DateHelper.getSFM(2095));
//        System.out.println(getClockDate(new Date()));
//        double d = Math.ceil(232.1453/new BigDecimal(80).doubleValue());
//        System.out.println(d);
//System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
        System.err.println( DateHelper.getDateinterval(DateHelper.formatDate("2019-09-11 19:00:00"), -2)  )  ;
    }


}
