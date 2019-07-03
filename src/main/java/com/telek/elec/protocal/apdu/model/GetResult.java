package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.apdu.model.constant.GetResultType;
import com.telek.elec.protocal.constant.DARType;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * get得到的结果
 * 01 —— Data/dar
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12 —— 通信地址：123456789012
 */
@Data
public class GetResult implements IResult {
    /**
     * 响应的数据类型-1字节，如果为data则表示有数据
     */
    private GetResultType getResultType;

    private GetResultData resultData;

    private GetResultDAR resultDAR;


    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(getResultType.getCode()), 2));
        if (GetResultType.DAR.equals(getResultType)) {
            sb.append(getResultDAR().encode());
        } else if (GetResultType.DATA.equals(getResultType)) {
            sb.append(getResultData().encode());
        }
        return sb.toString();
    }

    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null) {
            return;
        }
        int length = onlyThisHexStr.length();
        // 数据类型
        if (length >= 2) {
            int resultType = Integer.parseInt(onlyThisHexStr.substring(0, 2), 16);
            for (GetResultType value : GetResultType.values()) {
                if (resultType == value.getCode()) {
                    this.getResultType = value;
                    break;
                }
            }
        }

        // 解码数据
        if (length > 2) {
            String hex = onlyThisHexStr.substring(2);
            if (GetResultType.DATA.equals(this.getResultType)) {
                GetResultData resultData = new GetResultData();
                resultData.decode(hex);
                this.resultData = resultData;
            } else if (GetResultType.DAR.equals(this.getResultType)) {
                GetResultDAR resultDAR = new GetResultDAR();
                resultDAR.decode(hex);
                this.resultDAR = resultDAR;
            }
        }
    }

    /**
     * 正确数据类型
     * 09 —— octet-string
     * 06 —— SIZE(6)
     * 12 34 56 78 90 12 —— 通信地址：123456789012
     */
    @Data
    public static class GetResultData implements IResult {
        /**
         * 表示数据类型-1字节
         */
        private DataType dataType;
        /**
         * 标识数据类型的属性：eg 字符串该字段为长度，数组该字段为数组长度。。。
         */
        private int dataAttr;
        /**
         * 具体数据
         */
        private Object obj;

        @Override
        public String encode() {
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataType.getCode()), 2));
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataAttr), 2));
            // todo
            return sb.toString();
        }

        @Override
        public void decode(String onlyThisHexStr) {
            if (onlyThisHexStr == null) {
                return;
            }
            int length = onlyThisHexStr.length();
            if (length >= 2) {
                // 数据类型
                int dataType = Integer.parseInt(onlyThisHexStr.substring(0, 2), 16);
                for (DataType value : DataType.values()) {
                    if (dataType == value.getCode()) {
                        this.dataType = value;
                        break;
                    }
                }
            }

            // 具体数据
            if (length >= 4) {
                this.dataAttr = Integer.parseInt(onlyThisHexStr.substring(2, 4), 16);
            }
            if (length > 4) {
                // todo
            }
        }
    }

    /**
     * 错误的响应
     *  01
     */
    @Data
    public static class GetResultDAR implements IResult {

        private DARType dar;

        @Override
        public String encode() {
            return StringUtils.subLastNumStr(Integer.toHexString(dar.getCode()), 2);
        }

        @Override
        public void decode(String onlyThisHexStr) {
            if (onlyThisHexStr == null) {
                return;
            }
            int length = onlyThisHexStr.length();
            if (length >= 2) {
                int dar = Integer.parseInt(onlyThisHexStr.substring(0, 2), 16);
                for (DARType value : DARType.values()) {
                    if (dar == value.getCode()) {
                        this.dar = value;
                        break;
                    }
                }
            }
        }
    }
}
