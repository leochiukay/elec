package com.telek.elec.protocal;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.constant.ProtocalConstant;
import com.telek.elec.util.ByteUtils;
import com.telek.elec.util.CRC;
import com.telek.elec.util.CRC16M;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by PETER on 2015/3/13.
 */
@Getter
@NoArgsConstructor
public class Packet {
    private StringBuffer hexStr = new StringBuffer();
    /**
     * 起始帧 .
     */
    private byte head = ProtocalConstant.START_FRAM;
    /**
     * 长度域.
     */
    private int length;
    /**
     * 控制域.
     */
    private Control control;
    /**
     * 服务器地址.
     */
    private SA sa;
    /**
     * 客户机地址.
     */
    private int ca;
    /**
     * 帧头校验.
     */
    private byte[] hcs;
    /**
     * 链路用户数据.
     */
    private byte[] data;
    /**
     * 帧尾校验.
     */
    private byte[] fcs;

    public Packet(byte[] data) {
        this.data = data;
    }

    private byte end = ProtocalConstant.END_FRAM;

    public void setLength(int length) {
        this.length = length;
        byte[] lengthBytes = new byte[]{(byte) (length & 0xff), (byte) ((length >> 8) & 0x3f)};
        hexStr.append(HexBin.encode(lengthBytes));
    }

    public void setControl(Control control) {
        this.control = control;
        hexStr.append(HexBin.encode(new byte[]{(byte) (control.getFun() + (control.getBlock() << 5) + (control.getPrm() << 6) + (control.getDir() << 7))}));
    }

    public void setSa(SA sa) {
        this.sa = sa;
        hexStr.append(HexBin.encode(new byte[]{(byte) ((sa.length - 1 & 0x0F) + ((sa.logicAdd << 4) & 0x03) + ((sa.type << 6) & 0x03))}));
        hexStr.append(HexBin.encode(ByteUtils.reverse(HexBin.decode(sa.getAddress()))));
    }

    public void setCa(int ca) {
        this.ca = ca;
        hexStr.append(HexBin.encode(new byte[]{(byte) (ca)}));
    }

    public void setHcs(byte[] hcs) {
        this.hcs = hcs;
        hexStr.append(HexBin.encode(ByteUtils.reverse(hcs)));
    }

    public void setData(byte[] data) {
        this.data = data;
        hexStr.append(HexBin.encode(data));
    }

    public void setFcs(byte[] fcs) {
        this.fcs = fcs;
        hexStr.append(HexBin.encode(ByteUtils.reverse(fcs)));
    }

    @Data
    public static class SA {
        private int type;
        private int logicAdd;
        /**
         * 为地址的字节数，取值范围：0…15，对应表示1…16个字节长度.
         */
        private int length;
        private String address;
    }

    @Data
    public static class Control {
        private int dir;
        private int prm;
        private int block;
        private int fun;
    }

    /**
     * @Description: 计算帧头校验
     * @auther: wll
     * @date: 14:35 2019/7/4
     * @param: []
     * @return: byte[]
     */
    public void calculateHcs() {
        byte[] hcs = CRC.getCRC16(HexBin.decode(this.hexStr.toString()));
        setHcs(hcs);
    }

    /**
     * @Description: 计算帧尾校验
     * @auther: wll
     * @date: 14:35 2019/7/4
     * @param: []
     * @return: byte[]
     */
    public void calculateFcs() {
        byte[] fcs = CRC.getCRC16(HexBin.decode(this.hexStr.toString()));
        setFcs(fcs);
    }
}
