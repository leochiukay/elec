package com.telek.elec.cache;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.connection.ConnectionResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: wll
 * @Date: 2019/7/4 17:04
 * @Description:
 */
public class TempCache {
    /**
     * 服务器应用层信息.
     */
    public static final Map<String, ConnectionResponse> connectionInfo = new ConcurrentHashMap<>();
    /**
     * 服务器地址信息.
     */
    public static final Map<String, Packet.SA> serviceAddressInfo = new ConcurrentHashMap<>();
}
