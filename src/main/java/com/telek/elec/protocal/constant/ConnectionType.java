package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Auther: wll
 * @Date: 2019/7/9 15:43
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ConnectionType {
    UNKNOWN(0, "未知"),
    ONE(1, "单相"),
    THREE_THREE(2, "三相三线"),
    THREE_FOUR(3, "三相四线");

    private int code;
    private String msg;
}
