package com.telek.elec.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.connection.ConnectionResponse;
import com.telek.elec.protocal.apdu.link.LinkRequest;

/**
 * @Auther: wll
 * @Date: 2019/7/4 17:04
 * @Description:
 */
public class TempCache {
    /**
     * 服务器地址信息.
     */
    public static final Map<String, Packet.SA> serviceAddressInfo = new ConcurrentHashMap<>();
    /**
     * 预连接的信息
     */
    public static final Map<String, LinkRequest> linkedInfo = new ConcurrentHashMap<>();
    /**
     * 连接的信息
     */
    public static final Map<String, ConnectionResponse> connectionInfo = new ConcurrentHashMap<>();
}
