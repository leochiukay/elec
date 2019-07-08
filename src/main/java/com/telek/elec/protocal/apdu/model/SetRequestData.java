package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * 设置请求的数据信息
 * 40 00 02 00 —— OAD
 * 1C —— Data：类型28：date_time_s
 * 07 E0 01 14 10 1B 0B—— 时间：2016-01-20 16：27：11
 */
@Data
public class SetRequestData extends AbsResult {

    private OAD oad;

    private DataInfo data;

    @Override
    protected String encodeSpecial() throws EncodeException {
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
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        OAD oad = new OAD();
        int oadLength = oad.decode(hexString.substring(index, index += 8));
        this.oad = oad;
        String data = hexString.substring(index);
        DataInfo resultData = new DataInfo();
        int dataLength = resultData.decode(data);
        this.data = resultData;
        return dataLength + oadLength;
    }
}
