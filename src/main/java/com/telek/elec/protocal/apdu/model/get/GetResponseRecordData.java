package com.telek.elec.protocal.apdu.model.get;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.AbsDataModel;
import com.telek.elec.protocal.apdu.model.DAR;
import com.telek.elec.protocal.apdu.model.constant.DataResultType;
import com.telek.elec.protocal.constant.DARType;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class GetResponseRecordData extends AbsDataModel {

    private OAD oad;

    private RCSD rcsd;
    /**
     * 响应的数据类型-1字节，如果为data则表示有数据
     */
    private DataResultType getResultType;
    /**
     * 错误数据类型
     */
    private DAR dar;
    /**
     * 正确数据
     */
    List<Datas> data = new ArrayList<>();

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(rcsd.encode());
        if (DataResultType.DAR.equals(getResultType)) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(getResultType.getCode()), 2));
        } else {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(data.size()), 2));
            for (Datas d : data) {
                sb.append(d.encode());
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
        DataResultType getResultType = DataResultType.getByCode(Integer.parseInt(hexString.substring(index, index += 2), 16));

        if (DataResultType.DAR.equals(getResultType)) {
            int darType = Integer.parseInt(hexString.substring(index, index += 2), 16);
            DARType dar = DARType.decode(darType);
            this.dar = new DAR(dar);
            return index;
        } else {
            int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
            this.data = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                AbsData absData = HexToDataConvertor.hexToData(hexString.substring(index));
                this.data.add(new Datas(absData));
                index += absData.getCharLength();
            }
        }
        return index;
    }
}
