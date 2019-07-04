package com.telek.elec.protocal.data.model;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * year          long-unsigned，
 *   month         unsigned，
 *   day           unsigned，
 *   hour          unsigned，
 *   minute        unsigned，
 *   second        unsigned
 */
@Data
public class DateTimeS extends AbsBasicData {

    private static final int CHAR_LENGTH = 14;

    /**
     * 7字节
     */
    private Calendar calendar;

    public DateTimeS() {
        super(DataType.DATE_TIME_S);
    }

    @Override
    protected int calculateSpecialCharLength() {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() {
        return EncoderUtils.encodeToDateTimeSHex(calendar);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        calendar = DecoderUtils.decodeDateTimeSHex(hexExcludeDataType);
        return CHAR_LENGTH;
    }
}
