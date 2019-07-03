package com.telek.elec.protocal.apdu.data;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 数据类型的顶层
 */
@Data
public abstract class IData {

    /**
     * 数据类型
     */
    private DataType dataType;
    /**
     * 总共占用字符串长度（包括datatype）
     */
    private int length;

    public IData(DataType dataType) {
        this.dataType = dataType;
    }



    /**
     * 编码
     * @return
     */
    public String encode(){
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataType.getCode()), 2));
        String special = encodeSpecial();
        if (special != null) {
            sb.append(special);
        }
        return sb.toString();
    }

    protected abstract String encodeSpecial();

    /**
     * 解码
     * @param hex 必须从第一位开始
     * @return 返回该类型总共占用多少字符串数目
     */
    public int decode(String hex){
        int res = 0;
        if (hex == null) {
            return res;
        }
        int length = hex.length();
        if (length >= 2) {
            int type = Integer.parseInt(hex.substring(0, 2), 16);
            this.dataType = DataUtil.getDataType(type);
        }
        res = 2;
        return res + decodeSpecial(hex.substring(2));
    }

    protected abstract int decodeSpecial(String hexExcludeDataType);
}
