package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 对象标识,4字节，如00100200。
 */
@Data
public class OAD extends AbsData {

    private static final int OAD_CHAR_LENGTH = 8;

    private OI oi;
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

    public OAD() {
        this.dataType = DataType.OAD;
    }

    public OAD(OI oi, int attr, int index) {
        this();
        this.oi = oi;
        this.attr = attr;
        this.index = index;
    }

    /**
     * 将该对象编码为hex
     *
     * @return
     */
    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oi.encode());
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(attr), 2));
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(index), 2));
        return sb.toString();
    }

    /**
     * 将十六进制数据解码
     *
     * @param hexString
     * @return
     */
    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        OI oi = new OI();
        int oiCharLen = oi.decode(hexString);
        this.oi = oi;
        this.attr = java.lang.Integer.parseInt(hexString.substring(oiCharLen, oiCharLen += 2), 16);
        this.index = java.lang.Integer.parseInt(hexString.substring(oiCharLen, oiCharLen += 2), 16);
        return OAD_CHAR_LENGTH;
    }

    @Override
    protected int calculateSpecialCharLength() {
        return OAD_CHAR_LENGTH;
    }
}
