package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;

/**
 * @Auther: wll 普通采集方案
 * @Date: 2019/7/8 20:32
 * @Description:
 */
public class NormalCollectionSchemeOADFactory {
    public static final OI OI = new OI(0x6014);

    /**
     * 添加或更新一组普通采集方案
     * 方法127
     * @return OAD
     */
    public static OMD addScheme() {
        return new OMD(OI, 127, 0);
    }
    /**
     * 删除一组普通采集方案
     * 方法128
     * @return OAD
     */
    public static OMD clear() {
        return new OMD(OI, 129, 0);
    }

}
