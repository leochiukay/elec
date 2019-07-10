package com.telek.elec.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.ProtocalSendHelper;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.service.model.TaskConfigModel;
import com.telek.elec.protocal.service.request.apdufactory.TaskConfig;

/**
 * 任务配置
 */
@Controller
@RequestMapping("/test/task")
public class TaskController {

    @PostMapping("/clear")
    public Object clearTaskConfig(String address) throws Exception {
        CodecAPDU codecAPDU = TaskConfig.clearTask();
        ProtocalSendHelper.send2Service(address, codecAPDU, false);
        return "0k";
    }

    @PostMapping("/add")
    public Object taskConfig(String address) throws Exception {
        List<TaskConfigModel> list = new ArrayList<>();
        TaskConfigModel taskConfigModel = getTaskConfigModel();
        list.add(taskConfigModel);
        CodecAPDU codecAPDU = TaskConfig.addArrayTask(list);
        ProtocalSendHelper.send2Service(address, codecAPDU, false);
        return "0k";
    }

    /**
     * 获取taskconfig
     * @return
     */
    private TaskConfigModel getTaskConfigModel() {
        TaskConfigModel taskConfigModel = new TaskConfigModel();
        taskConfigModel.setTaskId(1);
        taskConfigModel.setFrequency(new Datas<>(new TI((byte) 1, 1)));
        taskConfigModel.setTaskType(new Datas<>(new Enums((short) 1)));
        taskConfigModel.setTaskNum(1);
        taskConfigModel.setBeginTime(Calendar.getInstance());
        taskConfigModel.setEndTime(Calendar.getInstance());
        taskConfigModel.setDelay(new Datas<>(new TI((byte) 1, 1)));
        taskConfigModel.setPriority(1);
        taskConfigModel.setState(new Datas<>(new Enums((short) 1)));
        taskConfigModel.setBeginScriptId(1);
        taskConfigModel.setEndScriptId(1);

        TaskConfigModel.TaskRunPeriod taskRunPeriod = new TaskConfigModel.TaskRunPeriod();
        taskRunPeriod.setType(new Datas<>(new Enums((short) 1)));
        List<TaskConfigModel.TaskConfigModelTime> lists = new ArrayList<>();
        TaskConfigModel.TaskConfigModelTime taskConfigModelTime = new TaskConfigModel.TaskConfigModelTime();
        taskConfigModelTime.setBeginHour(1);
        taskConfigModelTime.setBeginMinute(1);
        taskConfigModelTime.setEndHour(2);
        taskConfigModelTime.setEndMinute(2);

        lists.add(taskConfigModelTime);
        taskRunPeriod.setModelTimes(lists);
        taskConfigModel.setTaskRunPeriod(taskRunPeriod);
        return taskConfigModel;
    }
}
