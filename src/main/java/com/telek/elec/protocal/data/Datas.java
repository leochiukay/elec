package com.telek.elec.protocal.data;

import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * data数据类型，编解码头部都含有data_type
 * @param <T>
 */
@Data
public class Datas<T extends AbsData> {

    /**
     * 具体的数据
     */
    private T data;

    public Datas() {
    }

    public Datas(T data) {
        this.data = data;
        data.setEncodeDataType(true);
    }

    public void setData(T data) {
        this.data = data;
        this.data.setEncodeDataType(true);
    }

    public String encode() throws EncodeException {
        return data.encode();
    }

    public int decode(String hex) throws DecodeException {
        AbsData absData = HexToDataConvertor.hexToData(hex);
        this.data = (T) HexToDataConvertor.hexToData(hex);
        return absData.getCharLength();
    }
}
