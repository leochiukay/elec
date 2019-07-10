package com.telek.elec.protocal.apdu.model.proxy;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.AbsDataModel;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxySetListItem extends AbsDataModel {

    private TSA tsa;

    private LongUnsigned timeout;

    private List<ItemInfo> infos = new ArrayList<>();

    @Data
    public static class ItemInfo extends AbsDataModel {
        private OAD oad;
        private Datas datas;

        @Override
        protected String encodeSpecial() throws EncodeException {
            StringBuilder sb = new StringBuilder();
            sb.append(oad.encode());
            sb.append(datas.encode());
            return sb.toString();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            int index = 0;
            this.oad = new OAD();
            index += oad.decode(hexString);
            AbsData absData = HexToDataConvertor.hexToData(hexString.substring(index));
            this.datas = new Datas(absData);
            return index + absData.getCharLength();
        }
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(tsa.encode());
        sb.append(timeout.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(infos.size()), 2));
        for (ItemInfo info : infos) {
            sb.append(info.encode());
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
        this.infos = new ArrayList<>(size);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ItemInfo info = new ItemInfo();
                int len = info.decode(hexString.substring(index));
                index += len;
                this.infos.add(info);
            }
        }
        return index;
    }
}
