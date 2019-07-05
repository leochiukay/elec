package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.MessageAPDU;
import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.action.request.ActionRequestNormalList;
import com.telek.elec.protocal.apdu.action.response.ActionResponseNormal;
import com.telek.elec.protocal.apdu.action.response.ActionResponseNormalList;
import com.telek.elec.protocal.apdu.connection.ConnectionRequest;
import com.telek.elec.protocal.apdu.connection.ConnectionResponse;
import com.telek.elec.protocal.apdu.connection.ReleaseNotification;
import com.telek.elec.protocal.apdu.connection.ReleaseRequest;
import com.telek.elec.protocal.apdu.connection.ReleaseResponse;
import com.telek.elec.protocal.apdu.link.LinkRequest;
import com.telek.elec.protocal.apdu.link.LinkResponse;
import com.telek.elec.protocal.apdu.read.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.read.response.GetResponseNormal;
import com.telek.elec.protocal.apdu.read.response.GetResponseNormalList;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormalList;
import com.telek.elec.protocal.apdu.set.response.SetResponseNormal;
import com.telek.elec.protocal.apdu.set.response.SetResponseNormalList;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.APDUType;

/**
 * 根据apdu_sequence获取处理的类
 */
public class APDUFactory {

    /**
     * 获取处理不同类型apdu的工厂类
     *
     * @param data
     * @return
     */
    public static CodecAPDU getAPDUHandler(byte[] data) {
        int length = data.length;
        int apdeSequenceId = data[0] & 0xff;
        APDUSequence apduSequence = APDUSequence.getByIdSequence(apdeSequenceId);
        APDUResType apduResType = null;
        if (length > 1) {
            apduResType = APDUResType.getResByType(data[1] & 0xff, apduSequence.getApduType());
        }
        return getCodecAPDU(apduSequence, apduResType);
    }

    /**
     * 获取处理不同类型apdu的工厂类
     *
     * @param messageAPDU
     * @return
     */
    public static CodecAPDU getAPDUHandler(MessageAPDU messageAPDU) {
        APDUSequence apduSequence = messageAPDU.getApduSequence();
        APDUResType apduResType = messageAPDU.getApduResType();
        if (apduSequence != null) {
            return getCodecAPDU(apduSequence, apduResType);
        }
        return null;
    }

    private static CodecAPDU getCodecAPDU(APDUSequence apduSequence, APDUResType apduResType) {
        APDUType apduType = apduSequence.getApduType();
        switch (apduType) {
            case LINK:
                switch (apduSequence) {
                    case LINK_REQUEST:
                        return new LinkRequest();
                    case LINK_RESPONSE:
                        return new LinkResponse();
                    default:
                        return null;
                }
            case CONNECTION:
                switch (apduSequence) {
                    case CONNECTION_REQUEST:
                        return new ConnectionRequest();
                    case CONNECTION_RESPONSE:
                        return new ConnectionResponse();
                    default:
                        return null;
                }
            case RELEASE:
                switch (apduSequence) {
                    case RELEASE_REQUEST:
                        return new ReleaseRequest();
                    case RELEASE_RESPONSE:
                        return new ReleaseResponse();
                    case RELEASE_NOTIFICATION:
                        return new ReleaseNotification();
                    default:
                        return null;
                }
            case GET:
                switch (apduSequence) {
                    case GET_REQUEST:
                        switch (apduResType) {
                            case GET_NORMAL:
                                return new GetRequestNormal();
                            case GET_NORMAL_LIST:
                                return new GetResponseNormal();
                            default:
                                return null;
                        }
                    case GET_RESPONSE:
                        switch (apduResType) {
                            case GET_NORMAL:
                                return new GetResponseNormal();
                            case GET_NORMAL_LIST:
                                return new GetResponseNormalList();
                            default:
                                return null;
                        }
                }
            case SET:
                switch (apduSequence) {
                    case SET_REQUEST:
                        switch (apduResType) {
                            case SET_NORMAL:
                                return new SetRequestNormal();
                            case SET_NORMAL_LIST:
                                return new SetRequestNormalList();
                            default:
                                return null;
                        }
                    case SET_RESPONSE:
                        switch (apduResType) {
                            case SET_NORMAL:
                                return new SetResponseNormal();
                            case SET_NORMAL_LIST:
                                return new SetResponseNormalList();
                            default:
                                return null;
                        }
                }
            case ACTION:
                switch (apduSequence) {
                    case ACTION_REQUEST:
                        switch (apduResType) {
                            case ACTION_NORMAL:
                                return new ActionRequestNormal();
                            case ACTION_NORMAL_LIST:
                                return new ActionRequestNormalList();
                            default:
                                return null;
                        }
                    case ACTION_RESPONSE:
                        switch (apduResType) {
                            case ACTION_NORMAL:
                                return new ActionResponseNormal();
                            case ACTION_NORMAL_LIST:
                                return new ActionResponseNormalList();
                            default:
                                return null;
                        }
                }
            case PROXY:
            case REPORT:
            default:
                return null;
        }
    }
}
