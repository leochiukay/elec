package com.telek.elec.protocal.service.response;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.netty.NettyContext;
import com.telek.elec.netty.SyncWriteFuture;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.APDUFactory;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.APDUType;
import com.telek.elec.protocal.exeception.DecodeException;

import lombok.extern.slf4j.Slf4j;

/**
 * 分发处理服务端的响应信息
 */
@Slf4j
public class ResponseService implements IResponseService {

    /**
     * 请求的包
     */
    private Packet packet;

    public ResponseService(Packet packet) {
        this.packet = packet;
    }

    /**
     * 具体业务逻辑的处理
     *
     * @param
     */
    @Override
    public void dealAndResponse() {
        byte[] data = packet.getData();
        CodecAPDU apdu = APDUFactory.getAPDUHandler(data);
        try {
            if (apdu == null) {
                String s = HexBin.encode(data);
                log.error("没有可以处理该方法的apdu service" + s);
                return;
            }
            apdu.decode(HexBin.encode(data));
        } catch (DecodeException e) {
            log.error("解码失败", e);
            return;
        }
        // 获取具体处理业务的实体类
        IResponseService responseService = getResponseService(apdu);
        if (responseService != null) {
            responseService.dealAndResponse();
        }
    }

    private IResponseService getResponseService(CodecAPDU apdu) {
        APDUSequence apduSequence = apdu.getApduSequence();
        APDUType apduType = apduSequence.getApduType();
        switch (apduType) {
            case LINK:
                return new LinkService(packet, apdu);
            case RELEASE:
                return new ReleaseService(packet, apdu);
            case CONNECTION:
                return new ConnectionService(packet, apdu);
            case REPORT:
            case PROXY:
                return null;
            case ACTION:
                return new ActionService(packet, apdu);
            case SET:
                return new SetService(packet, apdu);
            case GET:
                return new GetService(packet, apdu);
        }
        return null;
    }
}
