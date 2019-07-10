package com.telek.elec.protocal.apdu.action.request;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.action.Action;
import com.telek.elec.protocal.apdu.model.action.ActionRequestData;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ActionRequestNormalList extends ResTypeCodecAPDU implements Action {

    /**
     * 设置数据
     */
    private List<ActionRequestData> actionRequestDatas = new ArrayList<>();
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public ActionRequestNormalList() {
        super(APDUResType.ACTION_NORMAL_LIST);
        this.apduSequence = APDUSequence.ACTION_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(actionRequestDatas.size()), 2));
        for (ActionRequestData requestData : actionRequestDatas) {
            sb.append(requestData.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.actionRequestDatas = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ActionRequestData requestData = new ActionRequestData();
            index += requestData.decode(hexString.substring(index));
            actionRequestDatas.add(requestData);
        }
        this.timeStamp = Integer.parseInt(hexString.substring(hexString.length() - 2), 16);
    }
}
