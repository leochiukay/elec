package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * 结构体
 *  02       —— 结构体
 * 06       —— 成员数量
 * 11 01    —— 方案编号 1
 * 12 00 01 —— 存储深度 1
 */
@Data
public class Structure extends AbsArraysData {

    public Structure() {
        this.dataType = DataType.STRUCTURE;
    }
}
