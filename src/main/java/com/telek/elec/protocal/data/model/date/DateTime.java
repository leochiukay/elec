package com.telek.elec.protocal.data.model.date;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.model.AbsData;

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
public class DateTime extends AbsData {

    private static final int CHAR_LENGTH = 20;

    /**
     * 10字节
     */
    private Calendar calendar;

    public DateTime() {
        this.dataType = DataType.DATE_TIME;
    }

    public DateTime(Calendar calendar) {
        this();
        this.calendar = calendar;
    }

    @Override
    protected String encodeSpecial() {
        return EncoderUtils.encodeToDateTimeHex(calendar);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        calendar = DecoderUtils.decodeDateTimeHex(hexExcludeDataType.substring(0, CHAR_LENGTH));
        return CHAR_LENGTH;
    }
}
