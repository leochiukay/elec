package com.telek.elec.protocal.data.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 数组类型的数据类型eg：数组，structure
 */
@Data
public abstract class AbsArraysData extends AbsData {

    private static final int SIZE_CHAR_LENGTH = 2;

    /**
     * 个数,1字节
     */
    protected int size;
    /**
     * 表示里面的元素
     */
    protected List<Datas> datas = new ArrayList<>();

    public void addData(AbsData data) {
        Datas datas = new Datas(data);
        this.datas.add(datas);
        this.size = this.size + 1;
    }

    public void addData(Datas<? extends AbsData> datas) {
        this.datas.add(datas);
        this.size = this.size + 1;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(size), 2));
        if (size > 0 && datas != null && datas.size() > 0) {
            for (Datas data : datas) {
                sb.append(data.encode());
            }
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) throws DecodeException {
        int charLength = SIZE_CHAR_LENGTH;
        this.size = java.lang.Integer.parseInt(hexExcludeDataType.substring(0, SIZE_CHAR_LENGTH), 16);
        if (size > 0) {
            if (this.datas == null) {
                this.datas = new ArrayList<>(size);
            }
            for (int i = 0; i < size; i++) {
                // 获取里面的元素类型
                String hex = hexExcludeDataType.substring(charLength);
                AbsData data = HexToDataConvertor.hexToData(hex);
                if (data != null) {
                    this.datas.add(new Datas(data));
                    int thisCharLength = data.getCharLength();
                    charLength += thisCharLength;
                }
            }
        }
        return charLength;
    }

}
