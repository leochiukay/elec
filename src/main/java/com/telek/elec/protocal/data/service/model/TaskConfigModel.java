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
     * 任务id unsigned
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
    private Datas<Enums> taskType;
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
    private Datas<Enums> state;
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
     * 任务运行时段
     */
    private TaskRunPeriod taskRunPeriod;


    /**
     任务运行时段       structure，
     * 任务运行时段∷=structrue
     * {
     *    类型  enum
     * {
     *        前闭后开    （0），
     *        前开后闭    （1），
     *        前闭后闭    （2），
     *        前开后开    （3）
     * }，
     *  时段表  array 时段
     * }
     */
    @Data
    public static class TaskRunPeriod {
        /**
         * 类型 enum
         * {
         * 前闭后开 （0），
         * 前开后闭 （1），
         * 前闭后闭 （2），
         * 前开后开 （3）
         * }，
         */
        private Datas<Enums> type;
        /**
         * 时段表  array
         */
        private List<TaskConfigModelTime> modelTimes;

        public Datas<Structure> getData() {
            Datas<Structure> structureDatas = new Datas<>(new Structure());
            Structure structure = structureDatas.getData();
            if (type == null) {
                return structureDatas;
            }
            structure.addData(this.type);

            // 时段表
            Datas<Array> arrayDatas = new Datas<>(new Array());
            structure.addData(arrayDatas);

            // 组装时段表array
            if (this.modelTimes != null) {
                Array array = arrayDatas.getData();
                for (TaskConfigModelTime modelTime : this.modelTimes) {
                    array.addData(modelTime.getData());
                }
            }
            return structureDatas;
        }
    }

    /**
     * 时段
     */
    @Data
    public static class TaskConfigModelTime {
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
            Structure structure = structureDatas.getData();
            structure.addData(new Unsigned((short) beginHour));
            structure.addData(new Unsigned((short) beginMinute));
            structure.addData(new Unsigned((short) endHour));
            structure.addData(new Unsigned((short) endMinute));
            return structureDatas;
        }
    }

    /**
     * 编码
     * @return
     */
    public Datas<Structure> getData() {
        Datas<Structure> task = new Datas<>(new Structure());
        Structure structure = task.getData();

        structure.addData(new Unsigned((short) taskId));
        structure.addData(frequency);
        structure.addData(taskType);
        short value = taskType.getData().getValue();
        //当方案类型为脚本时，方案编号为脚本 id
        if (value == 5) {
            this.taskNum = value;
        }

        structure.addData(new Unsigned((short) taskNum));
        structure.addData(new DateTimeS(beginTime));
        structure.addData(new DateTimeS(endTime));
        structure.addData(delay);
        structure.addData(new Unsigned((short) priority));
        structure.addData(state);
        structure.addData(new LongUnsigned(beginScriptId));
        structure.addData(new LongUnsigned(endScriptId));

        Datas<Structure> taskRunPeriod;
        if (this.taskRunPeriod == null) {
            taskRunPeriod = new Datas<>(new Structure());
        } else {
            taskRunPeriod = this.taskRunPeriod.getData();
        }
        structure.addData(taskRunPeriod);

        return task;
    }

}
