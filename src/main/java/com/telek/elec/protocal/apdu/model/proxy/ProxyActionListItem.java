package com.telek.elec.protocal.apdu.model.proxy;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.AbsDataModel;
import com.telek.elec.protocal.apdu.model.action.ActionRequestData;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxyActionListItem extends AbsDataModel {

    private TSA tsa;

    private LongUnsigned timeout;

    private List<ActionRequestData> actionRequestDataList = new ArrayList<>();

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(tsa.encode());
        sb.append(timeout.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(actionRequestDataList.size()), 2));
        for (ActionRequestData requestData : actionRequestDataList) {
            sb.append(requestData.encode());
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
        this.actionRequestDataList = new ArrayList<>(size);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String oadStr = hexString.substring(index);
                ActionRequestData oad = new ActionRequestData();
                int len = oad.decode(oadStr);
                index += len;
                this.actionRequestDataList.add(oad);
            }
        }
        return index;
    }
}
