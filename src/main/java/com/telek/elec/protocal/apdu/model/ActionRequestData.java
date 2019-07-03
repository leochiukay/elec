package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 设置请求的数据信息
 * 40 00 02 00 —— OAD
 * 1C —— Data：类型28：date_time_s
 * 07 E0 01 14 10 1B 0B—— 时间：2016-01-20 16：27：11
 */
@Data
public class ActionRequestData implements IResult {

    private OMD omd;

    private ActionData data;

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        if (omd != null) {
            sb.append(omd.encode());
        }
        if (data != null) {
            sb.append(data.encode());
        }
        return sb.toString();
    }

    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null) {
            return;
        }
        int index = 0;
        OMD omd = new OMD();
        omd.decode(onlyThisHexStr.substring(index, index += 8));
        this.omd = omd;
        String data = onlyThisHexStr.substring(index);
        ActionData actionData = new ActionData();
        actionData.decode(data);
        this.data = actionData;
    }

    /**
     * 0F 00
     * ——参数Data， integer(0)
     */
    @Data
    public static class ActionData implements IResult {
        /**
         * 数据类型
         */
        private DataType dataType;
        /**
         * 具体数据
         */
        private Object obj;

        @Override
        public String encode() {
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataType.getCode()), 16));
            // todo
            return sb.toString();
        }

        @Override
        public void decode(String onlyThisHexStr) {
            if (onlyThisHexStr == null) {
                return;
            }
            int index = 0;
            int dataType = Integer.parseInt(onlyThisHexStr.substring(index, index += 2), 16);
            for (DataType value : DataType.values()) {
                if (value.getCode() == dataType) {
                    this.dataType = value;
                    break;
                }
            }

            // todo
            String data = onlyThisHexStr.substring(index);
        }

    }
}
