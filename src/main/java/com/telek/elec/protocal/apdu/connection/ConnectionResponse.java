package com.telek.elec.protocal.apdu.connection;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端应用链接响应:
 * 82 00 54 4F 50 53 30 31 30 32 31 36 30 37 33 31 30 31 30 32 31 36 30 37 33 31 00 00 00 00 00 00 00 00 00 10 FF FF FF
 * FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF 04 00 04 00 01 04 00 00 00 00 64 00 00 00 00
 */
@Data
@Slf4j
public class ConnectionResponse extends CodecAPDU implements Connection {
    /**
     * 服务器厂商代码-4字节
     */
    private long code;
    /**
     * 服务器厂商版本-4字节
     */
    private long version;
    /**
     * 服务器厂商版本日期-6字节
     */
    private long versionDate;
    /**
     * 服务器厂商硬件版本-4字节
     */
    private long hardwareVersion;
    /**
     * 服务器厂商硬件版本日期-6字节
     */
    private long hardwareVersionDate;
    /**
     * 服务器厂商扩展信心-8字节
     */
    private long expandInfo;
    /**
     * 期望的应用层协议版本-2字节
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
        this.code = Long.parseLong(hexString.substring(4, 12), 16);
        this.version = Long.parseLong(hexString.substring(12, 20), 16);
        this.versionDate = Long.parseLong(hexString.substring(20, 32), 16);
        this.hardwareVersion = Long.parseLong(hexString.substring(32, 40), 16);
        this.hardwareVersionDate = Long.parseLong(hexString.substring(40, 52), 16);
        this.expandInfo = Long.parseLong(hexString.substring(52, 68), 16);
        this.expectVersion = Integer.parseInt(hexString.substring(68, 72), 16);
        this.protocolConformance = Long.parseLong(hexString.substring(72, 88), 16);
        this.functionConformance = Long.parseLong(hexString.substring(88, 120), 16);
        this.sendMaxSize = Integer.parseInt(hexString.substring(120, 124), 16);
        this.receiveMaxSize = Integer.parseInt(hexString.substring(124, 128), 16);
        this.windowMaxSize = Integer.parseInt(hexString.substring(128, 130), 16);
        this.maxApduSize = Integer.parseInt(hexString.substring(130, 134), 16);
        this.expectOverTime = Long.parseLong(hexString.substring(134, 142), 16);
        this.responsesObj = Integer.parseInt(hexString.substring(142, 144), 16);
        this.authObj = Integer.parseInt(hexString.substring(144, 146), 16);
        this.followReport = Integer.parseInt(hexString.substring(146, 148), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(148), 16);
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Long.toHexString(code), 8));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(version), 8));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(versionDate), 12));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(hardwareVersion), 8));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(hardwareVersionDate), 12));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(expandInfo), 16));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(expectVersion), 4));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(protocolConformance), 16));
        sb.append(StringUtils.subLastNumStr(Long.toHexString(functionConformance), 32));
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
}
