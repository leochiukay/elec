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
public class SetRequestData implements IResult {

    private OAD oad;

    private SetData data;

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        if (oad != null) {
            sb.append(oad.encode());
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
        OAD oad = new OAD();
        oad.decode(onlyThisHexStr.substring(index, index += 8));
        this.oad = oad;
        String data = onlyThisHexStr.substring(index);
        SetData resultData = new SetData();
        resultData.decode(data);
        this.data = resultData;
    }

    /**
     * 1C —— Data：类型28：date_time_s
     * 07 E0 01 14 10 1B 0B—— 时间：2016-01-20 16：27：11
     */
    @Data
    public static class SetData implements IResult {
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
