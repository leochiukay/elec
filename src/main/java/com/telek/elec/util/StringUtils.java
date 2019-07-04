package com.telek.elec.util;

import org.apache.commons.lang3.text.StrBuilder;

public class StringUtils {

    /**
     * 截取字符串最后几位,不足补0
     * @param s
     * @param lastNum
     * @return
     */
    public static String subLastNumStr(String s, int lastNum) {
        if (s == null) {
            s = "";
        }
        if (lastNum < 0) {
            lastNum = 0;
        }
        int length = s.length();
        int minus = length - lastNum;
        if (minus == 0) {
            return s;
        } else if (minus < 0) {
            StrBuilder sb = new StrBuilder();
            int max = -minus;
            for (int i = 0; i < max; i++) {
                sb.append("0");
            }
            return sb.toString() + s;
        } else {
            return s.substring(minus);
        }
    }
}
