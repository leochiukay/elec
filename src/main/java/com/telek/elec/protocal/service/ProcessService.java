package com.telek.elec.protocal.service;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * @Auther: wll
 * @Date: 2019/7/4 15:41
 * @Description:
 */
public class ProcessService {
    public void sendAPDU2Service(String address, CodecAPDU apdu) throws EncodeException {
        String encodeStr = apdu.encode();

    }

    public void decodeAPDU() {

    }


}
