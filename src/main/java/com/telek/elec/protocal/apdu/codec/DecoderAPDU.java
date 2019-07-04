package com.telek.elec.protocal.apdu.codec;

import com.telek.elec.protocal.exeception.DecodeException;

import lombok.extern.slf4j.Slf4j;

/**
 * 解码apdu
 */
@Slf4j
public abstract class DecoderAPDU extends EncoderAPDU {

    /**
     * 将十六进制解码成当前对象
     * @param hexString
     * @throws DecodeException
     */
    protected void decodeHexToThis(String hexString) throws DecodeException {
        log.info(this.getClass().getSimpleName() + "--APDU--" + hexString);
        // 校验
        validate(hexString);
        // 解码通用属性
        decodeCommonHexToThis(hexString);
        // 子类其它处理逻辑
        subclassDecodeProcessing(hexString);
        // 子类自己解码
        decodeSpecialHexToThis(hexString);
    }

    /**
     * 留给子类自己处理的逻辑
     * @param hexString
     * @return
     */
    protected Object subclassDecodeProcessing(String hexString){
        return null;
    }

    /**
     * 解码通用属性
     * @param hexString
     * @return
     */
    protected void decodeCommonHexToThis(String hexString) {
        if (hasPiidFied()) {
            String piid = hexString.substring(2, APDU_SEQUENCE_CHAR_LENGTH + PIID_CHAR_LENGTH);
            this.piid = Integer.parseInt(piid, 16);
        }
    }

    /**
     * 通用校验
     * @param hexString
     * @throws DecodeException
     */
    private void validate(String hexString) throws DecodeException {
        if (hexString == null) {
            log.error("apdu不能为null");
            throw new DecodeException("apdu不能为null");
        }
        int length = hexString.length();
        if (length < 2 || (hasPiidFied() && length < 4)) {
            log.error("apdu长度不符合要求");
            throw new DecodeException("apdu长度不符合要求");
        }

        int code = Integer.parseInt(hexString.substring(0, 2), 16);
        if (code != apduSequence.getId()) {
            log.error(this.getClass().getSimpleName() + "--ApduSequence ID错误, 正确值：" + apduSequence.getId());
            throw new DecodeException("ApduSequence ID错误");
        }
        // 子类的校验
        decodeValidateSpecial(hexString);
    }

    /**
     * 留给子类自己校验数据
     * @throws DecodeException
     */
    protected void decodeValidateSpecial(String hexString) throws DecodeException{

    }

    /**
     * 子类解码自己特有属性
     */
    protected abstract void decodeSpecialHexToThis(String hexString) throws DecodeException;

}
