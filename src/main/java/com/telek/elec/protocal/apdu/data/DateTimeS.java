package com.telek.elec.protocal.apdu.data;

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
public class DateTimeS extends IData {

    /**
     * 7字节
     */
    private Calendar calendar;

    public DateTimeS() {
        super(DataType.DATE_TIME_S);
    }

    @Override
    protected String encodeSpecial() {
        return EncoderUtils.encodeToDateTimeSHex(calendar);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        calendar = DecoderUtils.decodeDateTimeSHex(hexExcludeDataType);
        return 14;
    }
}
