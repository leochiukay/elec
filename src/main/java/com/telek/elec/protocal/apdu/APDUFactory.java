package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.apdu.action.response.ActionResponseNormal;
import com.telek.elec.protocal.apdu.action.response.ActionResponseNormalList;
import com.telek.elec.protocal.apdu.connection.ConnectionResponse;
import com.telek.elec.protocal.apdu.connection.ReleaseNotification;
import com.telek.elec.protocal.apdu.connection.ReleaseResponse;
import com.telek.elec.protocal.apdu.link.LinkRequest;
import com.telek.elec.protocal.apdu.read.response.GetResponseNormal;
import com.telek.elec.protocal.apdu.read.response.GetResponseNormalList;
import com.telek.elec.protocal.apdu.set.response.SetResponseNormal;
import com.telek.elec.protocal.apdu.set.response.SetResponseNormalList;
import com.telek.elec.protocal.constant.APDUSequence;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * @Auther: wll
 * @Date: 2019/7/4 12:50
 * @Description:
 */
public class APDUFactory {
    public static CodecAPDU getAPDU(byte[] apduData) {
        // 预连接请求
        if (apduData[0] == APDUSequence.LINK_REQUEST.getId()) {
            return new LinkRequest();
        } else if (apduData[0] == APDUSequence.CONNECTION_RESPONSE.getId()) {
            return new ConnectionResponse();
        } else if (apduData[0] == APDUSequence.RELEASE_RESPONSE.getId()) {
            return new ReleaseResponse();
        } else if (apduData[0] == APDUSequence.RELEASE_NOTIFICATION.getId()) {
            return new ReleaseNotification();
        } else if (apduData[0] == APDUSequence.GET_RESPONSE.getId()) {
            int getResponse = apduData[1];
            if (getResponse == 1) {
                return new GetResponseNormal();
            } else if (getResponse == 2) {
                return new GetResponseNormalList();
            }
        } else if (apduData[0] == APDUSequence.SET_RESPONSE.getId()) {
            int setResponse = apduData[1];
            if (setResponse == 1) {
                return new SetResponseNormal();
            } else if (setResponse == 2) {
                return new SetResponseNormalList();
            }
        } else if (apduData[0] == APDUSequence.ACTION_RESPONSE.getId()) {
            int actionResponse = apduData[1];
            if (actionResponse == 1) {
                return new ActionResponseNormal();
            } else if (actionResponse == 2) {
                return new ActionResponseNormalList();
            }
        } else if (apduData[0] == APDUSequence.REPORT_NOTIFICATION.getId()) {

        } else if (apduData[0] == APDUSequence.PROXY_RESPONSE.getId()) {

        }
        return null;
    }
}
