package com.telek.elec.netty;

import io.netty.channel.Channel;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: wll
 * @Date: 2018/6/21 13:55
 * @Description: Netty相关上下文读取
 */
public class NettyContext {
    /**
     * 集中器对应的nettychannelId，map<集中器地址，channelid>.
     */
    public static Map<String, String> concentratorCache = new ConcurrentHashMap<>();
    /**
     * 客户端连接map<ip:port,channel>.
     */
    public static Map<String, Channel> clientChannel = new ConcurrentHashMap<String, Channel>();
    /**
     * 每次电表请求用于同步的回调缓存map<address-command,SyncWriteFuture>.
     */
    public static Map<String, SyncWriteFuture> syncKey = new ConcurrentHashMap<String, SyncWriteFuture>();
    /**
     * 帧序号缓存map<address,seq></>.
     */
    public static Map<String, Integer> seqMap = new ConcurrentHashMap<>();

    public static String getConcentratorByChannel(String channelId) {
        Iterator<Map.Entry<String, String>> iter = concentratorCache.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            if (entry.getValue().equals(channelId)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
