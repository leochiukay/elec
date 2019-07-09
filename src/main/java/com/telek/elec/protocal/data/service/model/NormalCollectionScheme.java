package com.telek.elec.protocal.data.service.model;

import com.telek.elec.protocal.constant.StorageTimeUnitType;
import com.telek.elec.protocal.data.model.*;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.data.model.string.OctetString;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import lombok.Data;

import java.util.List;

/**
 * @Auther: wll
 * @Date: 2019/7/9 20:40
 * @Description: 普通采集方案
 */
@Data
public class NormalCollectionScheme extends Structure {
    private int index;
    private int deep;
    private CollectWay way;
    private List<CSD> recordList;
    private MS ms;
    private StorageTimeUnitType storageTimeUnitType;

    @Override
    protected String encodeSpecial() throws EncodeException {
        Structure structure = new Structure();
        structure.addData(new Unsigned((short) this.index));
        structure.addData(new LongUnsigned(this.deep));
        structure.addData(this.way);
        Array array = new Array();
        for (CSD csd : recordList) {
            array.addData(csd);
        }
        structure.addData(array);
        structure.addData(this.ms);
        structure.addData(new Enums(((short) storageTimeUnitType.getCode())));
        return structure.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }

    @Data
    public static class CollectWay extends Structure {
        private int type;
        private AbsData data;

        @Override
        protected String encodeSpecial() throws EncodeException {
            Structure structure = new Structure();
            structure.addData(new Unsigned((short) this.type));
            structure.addData(data);
            return structure.encode();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            return 0;
        }
    }

    @Data
    public static class RetryMetering extends Structure {
        private TI ti;
        private int retry;

        @Override
        protected String encodeSpecial() throws EncodeException {
            Structure structure = new Structure();
            structure.addData(ti);
            structure.addData(new LongUnsigned(retry));
            return structure.encode();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            return 0;
        }
    }
}
