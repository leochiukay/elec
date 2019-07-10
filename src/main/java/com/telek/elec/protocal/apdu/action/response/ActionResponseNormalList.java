package com.telek.elec.protocal.apdu.action.response;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.action.Action;
import com.telek.elec.protocal.apdu.model.action.ActionResponseData;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ActionResponseNormalList extends ResTypeCodecAPDU implements Action {

    private List<ActionResponseData> actionResponseDatas = new ArrayList<>();
    /**
     * 标识是否有上报信息-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public ActionResponseNormalList() {
        super(APDUResType.ACTION_NORMAL_LIST);
        this.apduSequence = APDUSequence.ACTION_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(actionResponseDatas.size()), 2));
        for (ActionResponseData responseData : actionResponseDatas) {
            sb.append(responseData.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        int length = hexString.length();
        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.actionResponseDatas = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String s = hexString.substring(index);
            ActionResponseData responseData = new ActionResponseData();
            int charLen = responseData.decode(s);
            this.actionResponseDatas.add(responseData);
            index += charLen;
        }
        this.followReport = Integer.parseInt(hexString.substring(length - 4, length - 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(length - 2, length), 16);
    }
}
