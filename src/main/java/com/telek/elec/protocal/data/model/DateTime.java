package com.telek.elec.protocal.data.model;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 *   year          long-unsigned，
 *   month         unsigned，
 *   day_of_month  unsigned，
 *   day_of_week   unsigned，
 *   hour          unsigned，
 *   minute        unsigned，
 *   second        unsigned，
 *   milliseconds  long-unsigned
 */
@Data
public class DateTime extends IData {

    private static final int CHAR_LENGTH = 20;

    /**
     * 10字节
     */
    private Calendar calendar;

    public DateTime() {
        super(DataType.DATE_TIME);
    }

    @Override
    protected int calculateSpecialCharLength() {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() {
        return EncoderUtils.encodeToDateTimeHex(calendar);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        calendar = DecoderUtils.decodeDateTimeHex(hexExcludeDataType);
        return CHAR_LENGTH;
    }
}
