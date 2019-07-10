package com.telek.elec.protocal.apdu.model.set;

import com.telek.elec.protocal.apdu.model.AbsDataModel;
import com.telek.elec.protocal.constant.DARType;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 设置响应请求
 * 40 01 02 00 —— OAD1
 * 00 —— DAR1，0成功
 */
@Data
public class SetResponseData extends AbsDataModel {

    private OAD oad;
    /**
     * 结果-1字节
     */
    private DARType dar;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dar.getCode()), 2));
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        OAD oad = new OAD();
        int oadCharLen = oad.decode(hexString.substring(index, index += 8));
        this.oad = oad;
        int dar = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.dar = DARType.decode(dar);
        return oadCharLen + 2;
    }
}
