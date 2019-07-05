package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * 数组
 *  * 03 —— 数组元素个数=3
 *  12 09 6D —— 元素1：类型18：long-unsigned 241.3V A相
 *  12 09 6D —— 元素2：类型18：long-unsigned 241.3V B相
 *  12 09 6D —— 元素3：类型18：long-unsigned 241.3V C相
 */
@Data
public class Array extends AbsArraysData {

    public Array() {
        this.dataType = DataType.ARRAY;
    }
}
