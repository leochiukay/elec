package com.telek.elec.protocal.apdu.codec;

import java.util.Calendar;

/**
 * 解码工具类
 */
public class DecoderUtils {
    /**
     * date_time 类型转java对象
     * @param dateTimeHexString
     * @return
     */
    public static Calendar decodeDateTimeHex(String dateTimeHexString) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateTimeHexString.substring(0, 4), 16));
        cal.set(Calendar.MONTH, Integer.parseInt(dateTimeHexString.substring(4, 6), 16) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateTimeHexString.substring(6, 8), 16));
        int week = Integer.parseInt(dateTimeHexString.substring(8, 10), 16) + 1;
        cal.set(Calendar.DAY_OF_WEEK, week);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateTimeHexString.substring(10, 12), 16));
        cal.set(Calendar.MINUTE, Integer.parseInt(dateTimeHexString.substring(12, 14), 16));
        cal.set(Calendar.SECOND, Integer.parseInt(dateTimeHexString.substring(14, 16), 16));
        cal.set(Calendar.MILLISECOND, Integer.parseInt(dateTimeHexString.substring(16, 20), 16));
        return cal;
    }

    /**
     * date_time 类型转java对象
     * @param dateTimeHexString
     * @return
     */
    public static Calendar decodeDateTimeSHex(String dateTimeHexString) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateTimeHexString.substring(0, 4), 16));
        cal.set(Calendar.MONTH, Integer.parseInt(dateTimeHexString.substring(4, 6), 16) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateTimeHexString.substring(6, 8), 16));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateTimeHexString.substring(8, 10), 16));
        cal.set(Calendar.MINUTE, Integer.parseInt(dateTimeHexString.substring(10, 12), 16));
        cal.set(Calendar.SECOND, Integer.parseInt(dateTimeHexString.substring(12, 14), 16));
        return cal;
    }

}
