package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OMD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * 设置请求的数据信息
 * 00 10 01 00 —— OMD
 *  * 0F 00 ——参数Data， integer(0)
 */
@Data
public class ActionRequestData extends AbsResult {

    private OMD omd;

    private AbsData data;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        if (omd != null) {
            sb.append(omd.encode());
        }
        if (data != null) {
            sb.append(data.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        OMD omd = new OMD();
        int modCharLen = omd.decode(hexString.substring(index, index += 8));
        this.omd = omd;
        String dataStr = hexString.substring(index);
        AbsData absData = HexToDataConvertor.hexToData(dataStr);
        this.data = absData;
        return absData.getCharLength() + modCharLen;
    }
}
