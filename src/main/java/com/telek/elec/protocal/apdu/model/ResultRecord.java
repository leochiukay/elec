package com.telek.elec.protocal.apdu.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.constant.GetResultType;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ResultRecord extends AbsResult {

    private OAD oad;

    private RCSD rcsd;
    /**
     * 响应的数据类型-1字节，如果为data则表示有数据
     */
    private GetResultType getResultType;
    /**
     * 错误数据类型
     */
    private DAR dar;
    /**
     * 正确数据
     */
    int size;// 多少条数据，1字节
    List<AbsData> data;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(rcsd.encode());
        if (GetResultType.DAR.equals(getResultType)) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(getResultType.getCode()), 2));
        } else {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(size), 2));
            if (data != null) {
                for (AbsData d : data) {
                    sb.append(d.encode());
                }
            }
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        OAD oad = new OAD();
        int oadLen = oad.decode(hexString.substring(index));
        this.oad = oad;

        RCSD rcsd = new RCSD();
        int rcsdLen = rcsd.decode(hexString.substring(index += oadLen));
        this.rcsd = rcsd;
        index = index + rcsdLen;
        GetResultType getResultType = GetResultType.getByCode(Integer.parseInt(hexString.substring(index, index += 1), 16));

        if (GetResultType.DAR.equals(getResultType)) {
            return index;
        } else {
            this.size = Integer.parseInt(hexString.substring(index, index += 1), 16);
            if (size > 0) {
                this.data = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    AbsData absData = HexToDataConvertor.hexToData(hexString.substring(index));
                    this.data.add(absData);
                    index += absData.getCharLength();
                }
            }
        }
        return index;
    }
}
