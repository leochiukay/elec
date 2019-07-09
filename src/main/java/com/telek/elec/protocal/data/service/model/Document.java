package com.telek.elec.protocal.data.service.model;

import com.telek.elec.protocal.constant.BAUDType;
import com.telek.elec.protocal.constant.ConnectionType;
import com.telek.elec.protocal.constant.ProtocalType;
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
 * @Date: 2019/7/9 15:30
 * @Description:
 */
@Data
public class Document extends AbsData {
    /**
     * 配置序号.
     */
    private int index;
    /**
     * 基本信息.
     */
    private BasicObject basicObject;
    /**
     * 扩展信息.
     */
    private ExtendedObject extendedObject;
    /**
     * 附属信息.
     */
    private List<AnnexObject> annexObject;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sbf = new StringBuffer();
        sbf.append(new LongUnsigned(this.index));
        sbf.append(this.basicObject.encode());
        sbf.append(this.extendedObject.encode());
        Array array = new Array();
        for (AnnexObject object : annexObject) {
            array.addData(object);
        }
        sbf.append(array.encode());
        return sbf.toString();
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
        private String address;
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
        private int port = 4;
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
        protected int calculateSpecialCharLength() throws EncodeException {
            return 0;
        }

        @Override
        protected String encodeSpecial() throws EncodeException {
            super.addData(new TSA(this.address));
            super.addData(new Enums((short) this.baudType.getCode()));
            super.addData(new Enums((short) this.protocalType.getCode()));
            super.addData(new OAD());
            super.addData(new OctetString(this.password));
            super.addData(new Unsigned((short) this.rateNum));
            super.addData(new Unsigned((short) this.userType));
            super.addData(new Enums((short) this.connectionType.getCode()));
            super.addData(new LongUnsigned(this.voltage));
            super.addData(new LongUnsigned(this.current));
            return super.encode();
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
        private String address;
        /**
         * 资产号 .
         */
        private String assetNumber = "0000";
        private int pt;
        private int ct;

        @Override
        protected int calculateSpecialCharLength() throws EncodeException {
            return 0;
        }

        @Override
        protected String encodeSpecial() throws EncodeException {
            super.addData(new TSA(this.address));
            super.addData(new OctetString(assetNumber));
            super.addData(new LongUnsigned(this.pt));
            super.addData(new LongUnsigned(this.ct));
            return super.encode();
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
        protected int calculateSpecialCharLength() throws EncodeException {
            return 0;
        }

        @Override
        protected String encodeSpecial() throws EncodeException {
            super.addData(oad);
            super.addData(absData);
            return super.encode();
        }

        @Override
        protected int decodeSpecial(String hexString) throws DecodeException {
            return 0;
        }
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return 0;
    }
}
