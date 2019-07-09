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
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.service.model.TaskConfigModel;
import com.telek.elec.protocal.service.request.apdufactory.TaskConfig;

/**
 * 任务配置
 */
@Controller
@RequestMapping("/test/task")
public class TaskController {

    @PostMapping("/add")
    public Object taskConfig(String address) throws Exception {
        List<TaskConfigModel> list = new ArrayList<>();
        TaskConfigModel taskConfigModel = new TaskConfigModel();
        taskConfigModel.setTaskId(1);
        taskConfigModel.setFrequency(new Datas<>(new TI((byte) 1, 1)));
        taskConfigModel.setTaskType(1);
        taskConfigModel.setTaskNum(1);
        taskConfigModel.setBeginTime(Calendar.getInstance());
        taskConfigModel.setEndTime(Calendar.getInstance());
        taskConfigModel.setDelay(new Datas<>(new TI((byte) 1, 1)));
        taskConfigModel.setPriority(1);
        taskConfigModel.setState(1);
        taskConfigModel.setBeginScriptId(1);
        taskConfigModel.setEndScriptId(1);
        taskConfigModel.setType(1);

        List<TaskConfigModel.TaskConfigModelTime> modelTimeList = new ArrayList<>();
        TaskConfigModel.TaskConfigModelTime modelTime = new TaskConfigModel.TaskConfigModelTime();
        modelTime.setBeginHour(1);
        modelTime.setBeginMinute(1);
        modelTime.setEndHour(2);
        modelTime.setEndMinute(2);

        modelTimeList.add(modelTime);

        taskConfigModel.setTimes(modelTimeList);


        list.add(taskConfigModel);
        CodecAPDU codecAPDU = TaskConfig.addArrayTask(list);

        ProtocalSendHelper.send2Service(address, codecAPDU, false);
        return "0k";
    }
}
