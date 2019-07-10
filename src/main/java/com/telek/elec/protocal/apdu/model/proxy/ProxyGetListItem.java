package com.telek.elec.protocal.apdu.model.proxy;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.AbsDataModel;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxyGetListItem extends AbsDataModel {

    private TSA tsa;

    private LongUnsigned timeout;

    private List<OAD> oads = new ArrayList<>();

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(tsa.encode());
        sb.append(timeout.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(oads.size()), 2));
        for (OAD oad : oads) {
            sb.append(oad.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        TSA tsa = new TSA();
        int index = tsa.decode(hexString);
        this.tsa = tsa;
        this.timeout = new LongUnsigned(Integer.parseInt(hexString.substring(index, index += 4), 16));

        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.oads = new ArrayList<>(size);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String oadStr = hexString.substring(index, index += 8);
                OAD oad = new OAD();
                oad.decode(oadStr);
                this.oads.add(oad);
            }
        }
        return index;
    }
}
