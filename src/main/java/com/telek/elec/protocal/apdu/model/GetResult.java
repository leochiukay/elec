package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.apdu.model.constant.GetResultType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
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
public class GetResult extends IResult {
    /**
     * 响应的数据类型-1字节，如果为data则表示有数据
     */
    private GetResultType getResultType;
    /**
     * 正确返回数据
     */
    private DataInfo dataInfo;
    /**
     * 错误信息
     */
    private DAR dar;


    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(getResultType.getCode()), 2));
        if (GetResultType.DAR.equals(getResultType)) {
            sb.append(dar.encode());
        } else if (GetResultType.DATA.equals(getResultType)) {
            sb.append(dataInfo.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        // 数据类型
        int resultType = Integer.parseInt(hexString.substring(0, 2), 16);
        for (GetResultType value : GetResultType.values()) {
            if (resultType == value.getCode()) {
                this.getResultType = value;
                break;
            }
        }

        // 解码数据
        String hex = hexString.substring(2);
        if (GetResultType.DATA.equals(this.getResultType)) {
            DataInfo resultData = new DataInfo();
            int charLength = resultData.decode(hex);
            this.dataInfo = resultData;
            return charLength + 2;
        } else if (GetResultType.DAR.equals(this.getResultType)) {
            DAR resultDAR = new DAR();
            int charLength = resultDAR.decode(hex);
            this.dar = resultDAR;
            return charLength + 2;
        } else {
            return 0;
        }
    }
}
