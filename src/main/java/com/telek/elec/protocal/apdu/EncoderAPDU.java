package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 编码apdu
 */
@Slf4j
public abstract class EncoderAPDU extends AbsAPDU {
    /**
     * 将当前对象编码成十六进制字符串
     * @return
     * @throws EncodeException
     */
    protected String encodeThisToHex() throws EncodeException {
        // 编码校验
        encodeValidate();
        // 编码通用属性
        StringBuilder sb = encodeCommonFieldToHex();
        // 子类处理其它逻辑
        subclassEncodeProcessing();
        String specialHex = encodeThisSpecialToHex();
        if (specialHex != null) {
            sb.append(specialHex);
        }
        log.info(this.getClass().getSimpleName() + "--请求APDU--" + sb.toString());
        return sb.toString();
    }

    protected Object subclassEncodeProcessing() {
        return null;
    }

    /**
     * 编码校验
     * @throws EncodeException
     */
    private void encodeValidate() throws EncodeException {
        if (this.hasPiidFied() && this.piid > 0xff) {
            log.error("ppid不能大于255");
            throw new EncodeException("ppid不能大于255");
        }
        // 子类的校验
        encodeValidateSpecial();
    }

    /**
     * 留给子类自己校验数据
     * @throws EncodeException
     */
    protected void encodeValidateSpecial() throws EncodeException{

    }

    /**
     * 子类编码自己特有的属性
     * @return
     */
    protected abstract String encodeThisSpecialToHex() throws EncodeException;

    /**
     * 编码通用属性
     * @return
     */
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), APDU_SEQUENCE_CHAR_LENGTH));
        // ppid
        if (hasPiidFied()) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), PIID_CHAR_LENGTH));
        }
        return sb;
    }
}
