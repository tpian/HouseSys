package com.github.wxiaoqi.security.modules.inspur.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static long paseDate(String stime, String etime) {
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        try {
            Date star = dft.parse(stime);//开始时间
            Date endDay = dft.parse(etime);//结束时间
            Long starTime = star.getTime();
            Long endTime = endDay.getTime();
            long num = endTime - starTime;
            day = num / 24 / 60 / 60 / 1000;
//            Date parse = dft.parse(stime);
//            Date parse1 = dft.parse(etime);
//            Instant instant=parse.toInstant();
//            Instant instant1 = parse1.toInstant();
//            ZoneId zoneId=ZoneId.systemDefault();
//            LocalDateTime time1 = instant.atZone(zoneId).toLocalDateTime();
//            LocalDateTime time2 = instant1.atZone(zoneId).toLocalDateTime();
//            Duration duration = Duration.between(time1, time2);
//             day = duration.toDays();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    public static String date(Date time) {
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = "";
        try {
            format = dft.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }


    public static void main(String[] args) {
        long l = paseDate("2022-08-19 09:07:28", "2022-08-23 10:07:28");
        System.out.println(l);
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dft.format(new Date());
        System.out.println(format);

    }
}
