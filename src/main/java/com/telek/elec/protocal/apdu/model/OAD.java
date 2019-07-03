package com.telek.elec.protocal.apdu.model;

import com.telek.elec.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对象标识,4字节，如00100200。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAD implements IResult {

    /**
     * 对象标识OI，标识终端中对象唯一名称的编码，2字节。如0010-正向有功电能
     *
     */
    private int oi;
    /**
     * 属性:1字节
     * 对象属性标识及其特征——用bit0…bit7表示八位位组的最低位到最高位，其中：
     * 1)	bit0…bit4编码表示对象属性编号，取值0…31，其中0表示整个对象属性，即对象的所有属性；
     * 2)	bit5…bit7编码表示属性特征，属性特征是对象同一个属性在不同快照环境下取值模式，取值0…7，特征含义在具体类属性中描述。
     */
    private int attr;
    /**
     * 下标：1字节
     * 属性内元素索引——00H表示整个属性全部内容。如果属性是一个结构或数组，01H指向对象属性的第一个元素；
     * 如果属性是一个记录型的存储区，非零值n表示最近第n次的记录。
     */
    private int index;

    /**
     * 将该对象编码为hex
     * @return
     */
    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(oi), 4));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(attr), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(index), 2));
        return sb.toString();
    }

    /**
     * 将十六进制数据解码
     * @param onlyThisHexStr
     * @return
     */
    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null || onlyThisHexStr.length() != 8) {
            return;
        }
        this.oi = Integer.parseInt(onlyThisHexStr.substring(0, 4), 16);
        this.attr = Integer.parseInt(onlyThisHexStr.substring(4, 6), 16);
        this.index = Integer.parseInt(onlyThisHexStr.substring(6), 16);
    }

}
