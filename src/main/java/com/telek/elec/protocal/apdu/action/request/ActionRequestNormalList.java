package com.telek.elec.protocal.apdu.action.request;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.action.AbsAction;
import com.telek.elec.protocal.apdu.model.ActionRequestData;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.ActionType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ActionRequestNormalList extends AbsAction {
    /**
     * 操作个数
     */
    private int count;
    /**
     * 设置数据
     */
    private List<ActionRequestData> actionRequestDatas;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public ActionRequestNormalList() {
        super(ActionType.NORMAL_LIST);
        this.apduSequence = APDUSequence.ACTION_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(count), 2));
        if (count > 0 && actionRequestDatas.size() > 0) {
            for (ActionRequestData requestData : actionRequestDatas) {
                sb.append(requestData.encode());
            }
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.count = Integer.parseInt(hexString.substring(index, index += 2), 16);
        if (count > 0) {
            this.actionRequestDatas = new ArrayList<>(count);
            ActionRequestData requestData = new ActionRequestData();
            requestData.decode(hexString.substring(index, index += 12));
            actionRequestDatas.add(requestData);
        }
        this.timeStamp = Integer.parseInt(hexString.substring(hexString.length() - 2), 16);
    }
}
