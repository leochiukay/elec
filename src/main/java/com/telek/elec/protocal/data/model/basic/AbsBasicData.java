package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.data.model.AbsData;

import lombok.Data;

/**
 * 基本数据类型: 头两个字符串表示该数据类型
 * eg：
 *  * 字符串
 *  * 09 —— octet-string
 *  * 06 —— SIZE(6)
 *  * 12 34 56 78 90 12 —— 通信地址：123456789012
 **/
@Data
public abstract class AbsBasicData extends AbsData {

    protected boolean isEncodeDataType = true;

}
