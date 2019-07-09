package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OMD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设置请求的数据信息
 * 00 10 01 00 —— OMD
 *  * 0F 00 ——参数Data， integer(0)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequestData extends AbsResult {

    private OMD omd;

    private Datas data;

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
        int omdCharLen = omd.decode(hexString.substring(index, index += 8));
        this.omd = omd;
        Datas<AbsData> data = new Datas<>();
        int charLen = data.decode(hexString.substring(index));
        this.data = data;
        return charLen + omdCharLen;
    }
}
