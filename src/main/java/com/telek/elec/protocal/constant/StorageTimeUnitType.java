package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wll
 * @Date: 2019/7/9 21:36
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum StorageTimeUnitType {
    NO_DEFINED(0, "未定义"),
    TASK_START_TIME(1, "任务开始时间"),
    CURRENT_DAY_0000(2, "相对当日0点0分"),
    LAST_DAY_2359(3, "相对上日23点59分"),
    LAST_DAY_0000(4, "相对上日0点0分"),
    CURRENT_MONTH_START(5, "相对当月1日0点0分"),
    FREZZING_TIME(6, "数据冻结时标"),
    LAST_MONTH_END(7, "相对上月月末23点59分");

    private int code;
    private String msg;
}
