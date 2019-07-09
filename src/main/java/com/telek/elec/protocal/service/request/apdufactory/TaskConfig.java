package com.telek.elec.protocal.service.request.apdufactory;
import java.util.List;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.TaskConfigFactory;
import com.telek.elec.protocal.apdu.model.ActionRequestData;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.data.service.model.TaskConfigModel;

/**
 * 任务配置
 */
public class TaskConfig {

    /**
     * 方法127：Add（array 任务配置单元）
     * 添加或更新一组任务配置单元。
     *
     * @return
     */
    public static CodecAPDU addArrayTask(List<TaskConfigModel> taskConfigs) {
        Datas<Array> arrayDatas = new Datas<>(new Array());
        Array array = arrayDatas.getData();
        for (TaskConfigModel taskConfig : taskConfigs) {
            array.addData(taskConfig.getData().getData());
        }

        ActionRequestData actionRequestData = new ActionRequestData();
        actionRequestData.setOmd(TaskConfigFactory.add());
        actionRequestData.setData(arrayDatas);

        ActionRequestNormal actionRequestNormal = new ActionRequestNormal();
        actionRequestNormal.setPiid(05);
        actionRequestNormal.setActionRequestData(actionRequestData);

        return actionRequestNormal;
    }

    public static CodecAPDU deleteArrayTask(List<Integer> taskIds) {
        Datas<Array> arrayDatas = new Datas<>(new Array());
        Array data = arrayDatas.getData();
        for (Integer taskId : taskIds) {
            data.addData(new Datas<>(new Unsigned((short) taskId.intValue())).getData());
        }

        ActionRequestData actionRequestData = new ActionRequestData();
        actionRequestData.setOmd(TaskConfigFactory.delete());
        actionRequestData.setData(arrayDatas);

        ActionRequestNormal actionRequestNormal = new ActionRequestNormal();
        actionRequestNormal.setPiid(05);
        actionRequestNormal.setActionRequestData(actionRequestData);
        return actionRequestNormal;
    }

    public static CodecAPDU clearTask() {

        ActionRequestData actionRequestData = new ActionRequestData();
        actionRequestData.setOmd(TaskConfigFactory.clear());
        actionRequestData.setData(null);

        ActionRequestNormal actionRequestNormal = new ActionRequestNormal();
        actionRequestNormal.setPiid(05);
        actionRequestNormal.setActionRequestData(actionRequestData);
        return actionRequestNormal;
    }

}
