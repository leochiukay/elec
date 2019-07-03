package com.telek.elec.protocal.apdu.action.response;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.action.CommonAction;
import com.telek.elec.protocal.apdu.model.ActionResponseData;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.ActionType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ActionResponseNormalList extends CommonAction {

    private int count;

    private List<ActionResponseData> actionResponseDatas;
    /**
     * 标识是否有上报信息-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public ActionResponseNormalList() {
        super(ActionType.NORMAL_LIST);
        this.apduSequence = APDUSequence.ACTION_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(count), 2));
        if (count > 0 && actionResponseDatas.size() > 0) {
            for (ActionResponseData responseData : actionResponseDatas) {
                sb.append(responseData.encode());
            }
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        int index = this.decodeHexExcludeCommonBeginIndex;
        int length = hexString.length();
        this.count = Integer.parseInt(hexString.substring(index, index += 2), 16);
        if (count > 0) {
            this.actionResponseDatas = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                // todo
            }
        }
        this.followReport = Integer.parseInt(hexString.substring(length - 4, length - 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(length - 2, length), 16);
    }
}
