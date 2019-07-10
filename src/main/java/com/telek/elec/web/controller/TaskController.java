package com.telek.elec.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.service.model.TaskConfigModel;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.TaskConfig;

/**
 * 任务配置
 */
@Controller
@RequestMapping("/test/task")
public class TaskController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/clear")
    public Object clearTaskConfig(String address) {
        CodecAPDU codecAPDU = TaskConfig.clearTask();
        requestService.sendRequest(codecAPDU, address);
        return "0k";
    }

    @PostMapping("/add")
    public Object taskConfig(String address) {
        List<TaskConfigModel> list = new ArrayList<>();
        TaskConfigModel taskConfigModel = getTaskConfigModel();
        list.add(taskConfigModel);
        CodecAPDU codecAPDU = TaskConfig.addArrayTask(list);
        requestService.sendRequest(codecAPDU, address);
        return "0k";
    }

    /**
     * 获取taskconfig
     * @return
     */
    private TaskConfigModel getTaskConfigModel() {
        TaskConfigModel taskConfigModel = new TaskConfigModel();
        taskConfigModel.setTaskId(1);
        taskConfigModel.setFrequency(new Datas<>(new TI((byte) 1, 15)));
        taskConfigModel.setTaskType(new Datas<>(new Enums((short) 1)));
        taskConfigModel.setTaskNum(1);
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), 0, 0);
        taskConfigModel.setBeginTime(cal);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        taskConfigModel.setEndTime(cal);
        taskConfigModel.setDelay(new Datas<>(new TI((byte) 0, 15)));
        taskConfigModel.setPriority(0);
        taskConfigModel.setState(new Datas<>(new Enums((short) 1)));
        taskConfigModel.setBeginScriptId(0);
        taskConfigModel.setEndScriptId(0);

        return taskConfigModel;
    }
}
