package com.telek.elec.protocal.data.service.model;

import java.util.Calendar;
import java.util.List;

import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.model.date.DateTimeS;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.data.model.number.Unsigned;

import lombok.Data;

/**
 * 任务配置单元
 *
 * 属性2（任务配置单元）∷=structure
 * {
 * 任务ID    unsigned，
 * 执行频率   TI，
 * 方案类型   enum
 * {
 * 普通采集方案 （1），   事件采集方案 （2），
 * 透明方案     （3），   上报方案     （4），
 * 脚本方案     （5）
 * }，
 * 方案编号    unsigned，
 * 开始时间    date_time_s，
 * 结束时间    date_time_s，
 * 延时        TI，
 * 执行优先级  unsigned，
 * 状态        enum{正常（1），停用（2）}，
 * 任务开始前脚本id   long-unsigned，
 * 任务完成后脚本id   long-unsigned，
 * 任务运行时段       structure，
 * }
 * 当方案类型为脚本时，方案编号为脚本id
 * 任务运行时段∷=structrue
 * {
 *    类型  enum
 * {
 *        前闭后开    （0），
 *        前开后闭    （1），
 *        前闭后闭    （2），
 *        前开后开    （3）
 * }，
 * 时段表  array 时段
 * }
 * 时段∷=structure
 * {
 * 起始小时  unsigned，
 * 起始分钟  unsigned，
 * 结束小时  unsigned，
 * 结束分钟  unsigned
 * }
 */
@Data
public class TaskConfigModel {
    /**
     * 任务id
     */
    private int taskId;
    /**
     * 执行频率
     */
    private Datas<TI> frequency;
    /**
     * 方案类型   enum
     * {
     * 普通采集方案 （1），   事件采集方案 （2），
     * 透明方案     （3），   上报方案     （4），
     * 脚本方案     （5）
     * }，
     */
    private int taskType;
    /**
     * 方案编号 unsigned
     */
    private int taskNum;
    /**
     * 开始时间    date_time_s，
     */
    private Calendar beginTime;
    /**
     * 结束时间    date_time_s，
     */
    private Calendar endTime;
    /**
     * 延时        TI，
     */
    private Datas<TI> delay;
    /**
     * 执行优先级  unsigned，
     */
    private int priority;
    /**
     * 状态        enum{正常（1），停用（2）}
     */
    private int state;
    /**
     * 任务开始前脚本id   long-unsigned，
     *
     */
    private int beginScriptId;
    /**
     * 任务完成后脚本id   long-unsigned，
     */
    private int endScriptId;
    /**
     * 任务运行时段       structure，
     * 任务运行时段∷=structrue
     * {
     *    类型  enum
     * {
     *        前闭后开    （0），
     *        前开后闭    （1），
     *        前闭后闭    （2），
     *        前开后开    （3）
     * }，
     *时段表  array 时段
     * }
     */
    private int type;
    /**
     * 时段表  array 时段
     */
    private List<time> times;

    /**
     * 时段
     */
    @Data
    private static class time {
        /**
         * 起始小时  unsigned，
         * 起始分钟  unsigned，
         * 结束小时  unsigned，
         * 结束分钟  unsigned
         */
        private int beginHour;
        private int beginMinute;
        private int endHour;
        private int endMinute;

        private Datas<Structure> getData() {
            Datas<Structure> structureDatas = new Datas<>(new Structure());

            Datas<Unsigned> beginHour = new Datas<>(new Unsigned((short) this.beginHour));
            Datas<Unsigned> beginMinute = new Datas<>(new Unsigned((short) this.beginMinute));
            Datas<Unsigned> endHour = new Datas<>(new Unsigned((short) this.endHour));
            Datas<Unsigned> endMinute = new Datas<>(new Unsigned((short) this.endMinute));

            Structure data = structureDatas.getData();
            data.addData(beginHour.getData());
            data.addData(beginMinute.getData());
            data.addData(endHour.getData());
            data.addData(endMinute.getData());

            return structureDatas;
        }
    }

    /**
     * 编码
     * @return
     */
    public Datas<Structure> getData() {
        Datas<Structure> task = new Datas<>(new Structure());

        Datas<Unsigned> taskId = new Datas<>(new Unsigned((short) this.taskId));
        Datas<Enums> taskType = new Datas<>(new Enums((short) this.taskType));
        Datas<Unsigned> taskNum = new Datas<>(new Unsigned((short) this.taskNum));
        Datas<DateTimeS> beginTime = new Datas<>(new DateTimeS(this.beginTime));
        Datas<DateTimeS> endTime = new Datas<>(new DateTimeS(this.endTime));
        Datas<Unsigned> priority = new Datas<>(new Unsigned((short) this.priority));
        Datas<Enums> state = new Datas<>(new Enums((short) this.state));
        Datas<LongUnsigned> beginScriptId = new Datas<>(new LongUnsigned(this.beginScriptId));
        Datas<LongUnsigned> endScriptId = new Datas<>(new LongUnsigned(this.endScriptId));

        Datas<Structure> period = new Datas<>(new Structure());
        Structure periodData = period.getData();
        Datas<Enums> type = new Datas<>(new Enums((short) this.type));
        Datas<Array> arrayPeriod = new Datas<>(new Array());
        Array data = arrayPeriod.getData();
        for (time time : times) {
            data.addData(time.getData().getData());
        }
        periodData.addData(type.getData());
        periodData.addData(arrayPeriod.getData());

        Structure structure = task.getData();
        structure.addData(taskId.getData());
        structure.addData(frequency.getData());
        structure.addData(taskType.getData());
        structure.addData(taskNum.getData());
        structure.addData(beginTime.getData());
        structure.addData(endTime.getData());
        structure.addData(delay.getData());
        structure.addData(priority.getData());
        structure.addData(state.getData());
        structure.addData(beginScriptId.getData());
        structure.addData(endScriptId.getData());
        structure.addData(periodData);

        return task;
    }

}
