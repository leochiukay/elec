package com.telek.elec.protocal.apdu.action.request;

import com.telek.elec.protocal.apdu.action.AbsAction;
import com.telek.elec.protocal.apdu.model.ActionRequestData;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.ActionType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 操作一个对象方法请求
 * 执行电能量复位方法
 * 发送：07 01 05 00 10 01 00 0F 00 00
 * 07 ——  [7] ACTION-Request
 * 01 ——  [1] ActionRequest
 * 05 —— PIID
 * 00 10 01 00 —— OMD
 * 0F 00 ——参数Data， integer(0)
 * 00 —— 没有时间标签
 */
@Data
public class ActionRequestNormal extends AbsAction {
    /**
     * 设置数据
     */
    private ActionRequestData actionRequestData;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public ActionRequestNormal() {
        super(ActionType.NORMAL);
        this.apduSequence = APDUSequence.ACTION_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(actionRequestData.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        ActionRequestData actionRequestData = new ActionRequestData();
        actionRequestData.decode(hexString.substring(index, hexString.length() - 2));
        this.actionRequestData = actionRequestData;
        this.timeStamp = Integer.parseInt(hexString.substring(hexString.length() - 2), 16);
    }
}
