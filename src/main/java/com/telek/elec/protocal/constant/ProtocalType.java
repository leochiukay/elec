package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wll
 * @Date: 2019/7/9 15:39
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ProtocalType {
    UNKNOWN(0,"未知"),
    DLT645_1997(1,"DL/T645-1997"),
    DLT645_2007(2,"DL/T645-2007"),
    DLT69845(3,"DL/T698.45"),
    CJT188_2004(4,"CJ/T 188-2004");

    private int code;
    private String msg;
}
