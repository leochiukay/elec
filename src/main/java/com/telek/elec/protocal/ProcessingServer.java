package com.telek.elec.protocal;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.ProtocalSendHelper;
import com.telek.elec.netty.NettyContext;
import com.telek.elec.netty.SyncWriteFuture;
import com.telek.elec.protocal.apdu.APDUFactory;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.link.LinkRequest;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.util.SpringBeanContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2019/7/4 16:01
 * @Description:
 */
@Slf4j
public class ProcessingServer implements Runnable {
    private ProtocalSendHelper sendHelper = SpringBeanContext.getBean(ProtocalSendHelper.class);
    private Packet resultPacket;

    public ProcessingServer(Packet packet) {
        super();
        this.resultPacket = packet;
    }

    @Override
    public void run() {
        // 1.处理分包问题 TODO
        if (resultPacket.getControl().getBlock() == 1) {
            String address = resultPacket.getSa().getAddress();
            byte[] data = resultPacket.getData();
            // 分帧传输的确认帧仅包含分帧格式域，不含APDU片段
            if (data.length == 1) {

            } else {
                byte blockFlag = data[0];
            }


        } else {
            byte[] data = resultPacket.getData();
            CodecAPDU apdu = APDUFactory.getAPDU(data);
            try {
                apdu.decode(HexBin.encode(data));
            } catch (DecodeException e) {
                log.error("解码失败", e);
            }
            if (apdu instanceof LinkRequest) {
                //TODO 处理预连接的逻辑
                try {
                    sendHelper.sendLinkResponse(resultPacket.getSa().getAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String syncKey = resultPacket.getSa().getAddress();
                SyncWriteFuture future = NettyContext.syncKey.get(syncKey);
                if (future != null) {
                    future.setResult(resultPacket);
                    return;
                } else {
                    log.error("接收包超时，{}", resultPacket);
                }
            }
        }
    }
}
