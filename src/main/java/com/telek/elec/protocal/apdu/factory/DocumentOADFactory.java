package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;

/**
 * @Auther: wll 档案配置
 * @Date: 2019/7/8 20:32
 * @Description:
 */
public class DocumentOADFactory {
    public static final OI OI = new OI(0x6000);

    /**
     * 添加一个采集档案配置单元
     * 方法127
     *
     * @return OAD
     */
    public static OMD addDocument() {
        return new OMD(OI, 127, 0);
    }

    /**
     * 批量添加采集档案配置单元
     * 方法127
     *
     * @return OAD
     */
    public static OMD addBatchDocuments() {
        return new OMD(OI, 128, 0);
    }

    public static OMD clear() {
        return new OMD(OI, 134, 0);
    }

}
