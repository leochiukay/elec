package com.telek.elec.protocal.data.service.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.constant.BAUDType;
import com.telek.elec.protocal.constant.ConnectionType;
import com.telek.elec.protocal.constant.ProtocalType;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.data.model.string.OctetString;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * @Auther: wll
 * @Date: 2019/7/9 15:30
 * @Description:
 */
@Data
public class Document extends Structure {
    /**
     * 配置序号.
     */
    private int index =  1;
    /**
     * 基本信息.
     */
    private BasicObject basicObject = new BasicObject();
    /**
     * 扩展信息.
     */
    private ExtendedObject extendedObject = new ExtendedObject();
    /**
     * 附属信息.
     */
    private List<AnnexObject> annexObject = new ArrayList<>();

    @Override
    protected String encodeSpecial() throws EncodeException {
        Structure structure = new Structure();
        structure.addData(new LongUnsigned(this.index));
        structure.addData(this.basicObject);
        structure.addData(this.extendedObject);
        Array array = new Array();
        for (AnnexObject object : annexObject) {
            array.addData(object);
        }
        structure.addData(array);
        return structure.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }

    @Data
    public static class BasicObject extends Structure {
        /**
         * 通信地址.
         */
        private String address = "010203040506";
        /**
         * 波特率.
         */
        private BAUDType baudType = BAUDType.BAUD_2400bps;
        /**
         * 协议类型.F
         */
        private ProtocalType protocalType = ProtocalType.DLT69845;
        /**
         * 端口.
         */
        private Datas<OAD> port;
        /**
         * 通信密码.
         */
        private String password = "0000000000";
        /**
         * 费率数量.
         */
        private int rateNum = 4;
        /**
         * 用户类型.
         */
        private int userType = 1;
        /**
         * 连接类型.
         */
        private ConnectionType connectionType = ConnectionType.ONE;
        /**
         * 额定电压.
         */
        private int voltage = 2200;
        /**
         * 额定电流.
         */
        private int current = 50;

        @Override
        protected String encodeSpecial() throws EncodeException {
            Structure structure = new Structure();
            structure.addData(new TSA(this.address));
            structure.addData(new Enums((short) this.baudType.getCode()));
            structure.addData(new Enums((short) this.protocalType.getCode()));
            structure.addData(port);
            structure.addData(new OctetString(this.password));
            structure.addData(new Unsigned((short) this.rateNum));
            structure.addData(new Unsigned((short) this.userType));
            structure.addData(new Enums((short) this.connectionType.getCode()));
            structure.addData(new LongUnsigned(this.voltage));
            structure.addData(new LongUnsigned(this.current));
            return structure.encode();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            return 0;
        }
    }

    @Data
    public static class ExtendedObject extends Structure {
        /**
         * 集中器地址.
         */
        private String address = "000000000003";
        /**
         * 资产号 .
         */
        private String assetNumber = "0000";
        private int pt = 1;
        private int ct = 1;

        @Override
        protected String encodeSpecial() throws EncodeException {
            Structure structure = new Structure();
            structure.addData(new TSA(this.address));
            structure.addData(new OctetString(assetNumber));
            structure.addData(new LongUnsigned(this.pt));
            structure.addData(new LongUnsigned(this.ct));
            return structure.encode();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            return 0;
        }
    }

    @Data
    public static class AnnexObject extends Structure {
        private OAD oad;
        private AbsData absData;

        @Override
        protected String encodeSpecial() throws EncodeException {
            Structure structure = new Structure();
            structure.addData(oad);
            structure.addData(absData);
            return structure.encode();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            return 0;
        }
    }
}
