package com.telek.elec.protocal.apdu.model.proxy;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.AbsDataModel;
import com.telek.elec.protocal.apdu.model.set.SetResponseData;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxySetResponseItem extends AbsDataModel {

    private TSA tsa;

    private List<SetResponseData> setResponseDataList = new ArrayList<>();

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(tsa.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(setResponseDataList.size()), 2));
        for (SetResponseData normal : setResponseDataList) {
            sb.append(normal.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        TSA tsa = new TSA();
        int tsaLen = tsa.decode(hexString);
        this.tsa = tsa;

        index += tsaLen;
        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.setResponseDataList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            SetResponseData setResponseData = new SetResponseData();
            int len = setResponseData.decode(hexString.substring(index));
            this.setResponseDataList.add(setResponseData);
            index += len;
        }
        return index;
    }

}
