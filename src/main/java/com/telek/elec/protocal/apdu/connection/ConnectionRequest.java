package com.telek.elec.protocal.apdu.connection;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 客户端应用链接请求
 * 发送：02 00 00 10 FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF 04 00 04 00 01 04 00 00 00 00 64 00 00
 * 02 —— [2] CONNECT-Request
 * 00 —— PIID
 * 00 10 —— 期望的应用层协议版本号
 * FF FF FF FF FF FF FF FF —— ProtocolConformance
 * FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF —— FunctionConformance
 * 04 00 —— 客户机发送帧最大尺寸
 * 04 00 —— 客户机接收帧最大尺寸
 * 01    —— 客户机接收帧最大窗口尺寸
 * 04 00 —— 客户机最大可处理APDU尺寸
 * 00 00 00 64 —— 期望的应用连接超时时间
 * 00 —— 认证请求对象 [0] NullSecurity，
 * 00 —— 没有时间标签
 */
@Data
public class ConnectionRequest extends CodecAPDU implements Connection {
    /**
     * 期望的应用层协议版本号-2字节
     */
    private int expectVersion;
    /**
     * 期望的协议一致性块-8字节
     */
    private String protocolConformance;
    /**
     * 期望的功能一致性块-16字节
     */
    private String functionConformance;
    /**
     * 客户机发送帧最大尺寸-2字节
     */
    private int sendMaxSize;
    /**
     * 客户机接收帧最大尺寸-2字节
     */
    private int receiveMaxSize;
    /**
     * 客户机接收帧最大窗口尺寸-1字节
     */
    private int windowMaxSize;
    /**
     * 客户机最大可处理APDU尺寸-2字节
     */
    private int maxApduSize;
    /**
     * 期望的应用连接超时时间-4字节
     */
    private long expectOverTime;
    /**
     * 认证请求对象-1字节
     */
    private int authObject;
    /**
     * 时间标签-1字节
     */
    protected int timeStamp;

    public ConnectionRequest() {
        this.apduSequence = APDUSequence.CONNECTION_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.expectVersion), 4));
        sb.append(StringUtils.subLastNumStr(this.protocolConformance, 16));
        sb.append(StringUtils.subLastNumStr(this.functionConformance, 32));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.sendMaxSize), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.receiveMaxSize), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.windowMaxSize), 2));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.maxApduSize), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.expectOverTime), 8));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.authObject), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.expectVersion = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.protocolConformance = hexString.substring(index, index += 16);
        this.functionConformance = hexString.substring(index, index += 32);
        this.sendMaxSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.receiveMaxSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.windowMaxSize = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.maxApduSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.expectOverTime = Integer.parseInt(hexString.substring(index, index += 8), 16);
        this.authObject = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (this.functionConformance == null) {
            this.functionConformance = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        }
        if (this.protocolConformance == null) {
            this.protocolConformance = "FFFFFFFFFFFFFFFF";
        }
    }
}
