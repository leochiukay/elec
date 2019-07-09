package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.DocumentFactory;
import com.telek.elec.protocal.apdu.model.ActionRequestData;
import com.telek.elec.protocal.data.model.basic.Array;
import com.telek.elec.protocal.data.model.basic.Enums;
import com.telek.elec.protocal.data.model.basic.Null;
import com.telek.elec.protocal.data.model.basic.Structure;
import com.telek.elec.protocal.data.model.basic.number.LongUnsigned;
import com.telek.elec.protocal.data.model.basic.number.Unsigned;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: wll
 * @Date: 2019/7/8 21:00
 * @Description:
 */
public class Scheme {
    public static volatile AtomicInteger index_num = new AtomicInteger(0);

    /**
     * @Description: 添加一个采集档案配置单元
     * @auther: wll
     * @date: 20:38 2019/7/8
     * @param: []
     * @return: com.telek.elec.protocal.apdu.CodecAPDU
     */
    public static CodecAPDU addDocument() {
        Structure scheme = new Structure();
        // 1.方案编号
        scheme.addData(new Unsigned((short) index_num.incrementAndGet()));
        // 2.存储深度
        scheme.addData(new LongUnsigned(32));
        // 3.采集方式
        Structure collectMethod = new Structure();
        // 3.1 采集类型
        collectMethod.addData(new Unsigned((short) 0));
        // 3.2 采集内容
        collectMethod.addData(new Null());
        // 4.记录列选择
        Array CSDs = new Array();
//        CSDs.addData();
        // 5.电能表集合
//        scheme.addData(new );
        // 6.存储时标选择

        return null;
    }
}
