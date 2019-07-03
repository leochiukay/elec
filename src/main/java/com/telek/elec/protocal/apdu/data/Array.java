package com.telek.elec.protocal.apdu.data;

import java.util.List;

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
    protected String encodeSpecial() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(size), 2));
        if (datas != null && datas.size() > 0) {
            for (IData data : datas) {
                // todo
            }
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        return 0;
    }
}
