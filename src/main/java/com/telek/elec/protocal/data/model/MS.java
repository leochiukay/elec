package com.telek.elec.protocal.data.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class MS extends AbsData {

    /**
     * 无表计 [0]，
     * 全部用户地址 [1]，
     * 一组用户类型 [2] SEQUENCE OF unsigned，
     * 一组用户地址 [3] SEQUENCE OF TSA，
     * 一组配置序号 [4] SEQUENCE OF long-unsigned，
     * 一组用户类型区间 [5] SEQUENCE OF Region，
     * 一组用户地址区间 [6] SEQUENCE OF Region，
     * 一组配置序号区间 [7] SEQUENCE OF Region
     */
    private int value;

    private List<AbsData> data;

    public MS() {
        this.dataType = DataType.MS;
    }

    public MS(int value, List<AbsData> datas) {
        this.dataType = DataType.MS;
        this.value = value;
        this.data = datas;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        if (data == null) {
            this.data = new ArrayList<>();
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.value), 2));
        switch (value) {
            case 0:
            case 1:
                break;
            case 2:
                sb.append(StringUtils.subLastNumStr(Integer.toHexString(data.size()), 2));
                for (Object datum : data) {
                    int i = (int) datum;
                    sb.append(StringUtils.subLastNumStr(Integer.toHexString(i), 2));
                }
            case 4:
                break;
            case 3:
                sb.append(StringUtils.subLastNumStr(Integer.toHexString(data.size()), 2));
                for (Object datum : data) {
                    TSA tsa = (TSA) datum;
                    sb.append(tsa.encode());
                }
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        this.value = Integer.parseInt(hexString.substring(0, 0), 16);
        return 0;
    }
}
