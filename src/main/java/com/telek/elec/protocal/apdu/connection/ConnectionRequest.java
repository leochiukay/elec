package com.telek.elec.protocal.apdu.connection;

import com.telek.elec.protocal.apdu.CommonCodecAPDU;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 客户端应用链接请求
 */
@Data
public class ConnectionRequest extends CommonCodecAPDU implements Connection {
    /**
     * 期望的应用层协议版本号-2字节
     */
    private int expectVersion;
    /**
     * 期望的协议一致性块-8字节
     */
    private long protocolConformance;
    /**
     * 期望的功能一致性块-16字节
     */
    private long functionConformance;
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
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.protocolConformance), 16));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(this.functionConformance), 32));
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
        this.protocolConformance = Integer.parseInt(hexString.substring(index, index += 16), 16);
        this.functionConformance = Integer.parseInt(hexString.substring(index, index += 32), 16);
        this.sendMaxSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.receiveMaxSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.windowMaxSize = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.maxApduSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.expectOverTime = Integer.parseInt(hexString.substring(index, index += 8), 16);
        this.authObject = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected void decodeValidateSpecial(String hexString) throws DecodeException {

    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {

    }
}
