package com.telek.elec.protocal.block;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: wll
 * @Date: 2019/7/4 12:30
 * @Description:
 */
public class SeqCache {
    public static Map<String,Seq> seqCache = new ConcurrentHashMap<>();
}
