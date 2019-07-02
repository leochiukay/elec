package com.telek.elec.protocal.apdu.codec;

import java.util.Calendar;

import com.telek.elec.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 编码工具类
 */
public class EncoderUtils {

    /**
     * 将java日期对象编码为十六进制time
     * @param cal
     * @return
     */
    public static String encodeToTimeHex(final Calendar cal) {
        DateTimeField dateTimeField = calToDateTimeField(cal);
        StringBuilder sb = appendTimeWithoutMillisecond(dateTimeField);
        return sb.toString();
    }

    /**
     * 将java日期对象编码为十六进制date
     * @param cal
     * @return
     */
    public static String encodeToDateHex(final Calendar cal) {
        DateTimeField dateTimeField = calToDateTimeField(cal);
        StringBuilder sb = appendDateAll(dateTimeField);
        return sb.toString();
    }

    /**
     * 将java日期对象编码为十六进制date_time_s
     * @param cal
     * @return
     */
    public static String encodeToDateTimeSHex(final Calendar cal) {
        DateTimeField dateTimeField = calToDateTimeField(cal);
        StringBuilder dateWithoutWeek = appendDateWithoutWeek(dateTimeField);
        StringBuilder timeWithoutMillisecond = appendTimeWithoutMillisecond(dateTimeField);
        dateWithoutWeek.append(timeWithoutMillisecond);
        return dateWithoutWeek.toString();
    }

    /**
     * 将java日期对象编码为十六进制date_time
     * @param cal
     * @return
     */
    public static String encodeToDateTimeHex(final Calendar cal) {
        DateTimeField dateTimeField = calToDateTimeField(cal);
        StringBuilder dateAll = appendDateAll(dateTimeField);
        StringBuilder timeAll = appendTimeAll(dateTimeField);
        dateAll.append(timeAll);
        return dateAll.toString();
    }

    /**
     * 小时+分+秒
     * @param dateTimeField
     * @return
     */
    private static StringBuilder appendTimeWithoutMillisecond(DateTimeField dateTimeField) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getHour()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getMinute()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getSecond()), 2));
        return sb;
    }

    /**
     * 年+月+日
     * @param dateTimeField
     * @return
     */
    private static StringBuilder appendDateWithoutWeek(DateTimeField dateTimeField) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getYear()), 4));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getMonth()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getDay()), 2));
        return sb;
    }

    /**
     * 年+月+日+星期
     * @param dateTimeField
     * @return
     */
    private static StringBuilder appendDateAll(DateTimeField dateTimeField) {
        StringBuilder sb = appendDateWithoutWeek(dateTimeField);
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getWeek()), 2));
        return sb;
    }

    /**
     * 小时+分+秒+毫秒
     * @param dateTimeField
     * @return
     */
    private static StringBuilder appendTimeAll(DateTimeField dateTimeField) {
        StringBuilder sb = appendTimeWithoutMillisecond(dateTimeField);
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dateTimeField.getMillisecond()), 4));
        return sb;
    }

    /**
     * 更具日期分解各个字段
     * @param cal
     * @return
     */
    private static DateTimeField calToDateTimeField(final Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int millis = cal.get(Calendar.MILLISECOND);
        DateTimeField dateTimeField = new DateTimeField(year, month, day, week, hour, minutes, second, millis);
        return dateTimeField;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class DateTimeField {
        private int year;
        private int month;
        private int day;
        /**
         * 0-7 为周日-周六
         */
        private int week;
        private int hour;
        private int minute;
        private int second;
        private int millisecond;
    }

}
