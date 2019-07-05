package com.telek.elec.protocal;

import com.telek.elec.protocal.service.response.ResponseService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2019/7/4 16:01
 * @Description:
 */
@Slf4j
public class ProcessingServer implements Runnable {

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
            new ResponseService(resultPacket).dealAndResponse();
        }
    }


}
