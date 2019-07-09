package com.telek.elec.protocal.data.model.date;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.model.AbsData;

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
public class DateTimeS extends AbsData {

    private static final int CHAR_LENGTH = 14;

    /**
     * 7字节
     */
    private Calendar calendar;

    public DateTimeS(Calendar calendar, boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
        this.calendar = calendar;
    }

    public DateTimeS() {
        this.dataType = DataType.DATE_TIME_S;
    }

    public DateTimeS(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
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
        calendar = DecoderUtils.decodeDateTimeSHex(hexExcludeDataType.substring(0, CHAR_LENGTH));
        return CHAR_LENGTH;
    }
}
