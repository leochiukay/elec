package com.telek.elec.protocal.data.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 数组
 *  * 03 —— 数组元素个数=3
 *  12 09 6D —— 元素1：类型18：long-unsigned 241.3V A相
 *  12 09 6D —— 元素2：类型18：long-unsigned 241.3V B相
 *  12 09 6D —— 元素3：类型18：long-unsigned 241.3V C相
 */
@Data
public class Array extends IData {

    private static final int SIZE_CHAR_LENGTH = 2;

    /**
     * 数组个数,1字节
     */
    private int size;
    /**
     * 表示数组里的元素
     */
    private List<IData> datas;

    public Array() {
        super(DataType.ARRAY);
    }

    @Override
    protected int calculateSpecialCharLength() {
        int length = SIZE_CHAR_LENGTH;
        if (datas != null) {
            for (IData data : datas) {
                // 计算字符长度
                data.encode();
                length += data.getCharLength();
            }
        }
        return length;
    }

    @Override
    protected String encodeSpecial() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(size), 2));
        if (size > 0 && datas != null && datas.size() > 0) {
            for (IData data : datas) {
                sb.append(data.encode());
            }
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        int charLength = SIZE_CHAR_LENGTH;
        this.size = java.lang.Integer.parseInt(hexExcludeDataType.substring(0, SIZE_CHAR_LENGTH), 16);
        if (size > 0) {
            if (this.datas == null) {
                this.datas = new ArrayList<>(size);
            }
            for (int i = 0; i < size; i++) {
                // 获取里面的元素类型
                String hex = hexExcludeDataType.substring(charLength);
                IData data = HexToDataConvertor.hexToData(hex);
                if (data != null) {
                    this.datas.add(data);
                    int thisCharLength = data.getCharLength();
                    charLength += thisCharLength;
                }
            }
        }
        return charLength;
    }
}
