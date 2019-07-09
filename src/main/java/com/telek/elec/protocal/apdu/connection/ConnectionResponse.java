package com.telek.elec.protocal.apdu.connection;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.basic.string.VisibleString;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端应用链接响应:
 * 8响应：82 00 54 4F 50 53 30 31 30 32 31 36 30 37 33 31 30 31 30 32 31 36 30 37 33 31 00 00 00 00 00 00 00 00 00 10 FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF 04 00 04 00 01 04 00 00 00 00 64 00 00 00 00
 * 82 —— [130] CONNECT-Response
 * 00 —— PIID-ACD
 * 54 4F 50 53 30 31 30 32 31 36 30 37 33 31 30 31 30 32 31 36 30 37 33 31 00 00 00 00 00 00 00 00 —— 厂商版本信息：厂商代码（size(4)）+ 软件版本号（size(4)）+软件版本日期（size(6)）+硬件版本号（size(4)）+硬件版本日期（size(6)）+厂家扩展信息（size(8)）
 * 00 10 —— 期望的应用层协议版本号
 * FF FF FF FF FF FF FF FF —— ProtocolConformance
 * FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF —— FunctionConformance
 * 04 00 —— 服务器发送帧最大尺寸
 * 04 00 —— 服务器接收帧最大尺寸
 * 01    —— 服务器接收帧最大窗口尺寸
 * 04 00 —— 服务器最大可处理APDU尺寸
 * 00 00 00 64 —— 期望的应用连接超时时间
 * 00 —— 连接响应对象   允许建立应用连接     （0）
 * 00 —— 认证附加信息   OPTIONAL=0 表示没有
 * 00 —— FollowReport  OPTIONAL=0表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
@Slf4j
public class ConnectionResponse extends CodecAPDU implements Connection {
    /**
     * 服务器厂商代码-4字节
     */
    private String code;
    /**
     * 服务器厂商版本-4字节
     */
    private String version;
    /**
     * 服务器厂商版本日期-6字节
     */
    private String versionDate;
    /**
     * 服务器厂商硬件版本-4字节
     */
    private String hardwareVersion;
    /**
     * 服务器厂商硬件版本日期-6字节
     */
    private String hardwareVersionDate;
    /**
     * 服务器厂商扩展信心-8字节
     */
    private String expandInfo;
    /**
     * 期望的应用层协议版本-2字节
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
     * 服务器发送帧最大尺寸-2字节
     */
    private int sendMaxSize;
    /**
     * 服务器接收帧最大尺寸-2字节
     */
    private int receiveMaxSize;
    /**
     * 服务器接收帧最大窗口尺寸-1字节
     */
    private int windowMaxSize;
    /**
     * 服务器最大可处理APDU尺寸-2字节
     */
    private int maxApduSize;
    /**
     * 期望的应用连接超时时间-4字节
     */
    private long expectOverTime;
    /**
     * 连接响应对象-1字节
     */
    private int responsesObj;
    /**
     * 认证附加信息-1字节
     */
    private int authObj;
    /**
     * FollowReport-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    protected int timeStamp;


    public ConnectionResponse() {
        this.apduSequence = APDUSequence.CONNECTION_RESPONSE;
    }

    @Override
    public void decodeSpecialHexToThis(String hexString) {
        // TODO
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.code = hexString.substring(index, index += 8);
        this.version = hexString.substring(index, index += 8);
        this.versionDate = hexString.substring(index, index += 12);
        this.hardwareVersion = hexString.substring(index, index += 8);
        this.hardwareVersionDate = hexString.substring(index, index += 12);
        this.expandInfo = hexString.substring(index, index += 16);
        this.expectVersion = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.protocolConformance = hexString.substring(index, index += 16);
        this.functionConformance = hexString.substring(index, index += 32);
        this.sendMaxSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.receiveMaxSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.windowMaxSize = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.maxApduSize = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.expectOverTime = Long.parseLong(hexString.substring(index, index += 8), 16);
        this.responsesObj = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.authObj = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.followReport = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(code, 8));
        sb.append(StringUtils.subLastNumStr(version, 8));
        sb.append(StringUtils.subLastNumStr(versionDate, 12));
        sb.append(StringUtils.subLastNumStr(hardwareVersion, 8));
        sb.append(StringUtils.subLastNumStr(hardwareVersionDate, 12));
        sb.append(StringUtils.subLastNumStr(expandInfo, 16));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(expectVersion), 4));
        sb.append(StringUtils.subLastNumStr(protocolConformance, 16));
        sb.append(StringUtils.subLastNumStr(functionConformance, 32));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(sendMaxSize), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(receiveMaxSize), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(windowMaxSize), 2));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(maxApduSize), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(expectOverTime), 8));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(responsesObj), 2));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(authObj), 2));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(timeStamp), 2));
        return sb.toString();
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
