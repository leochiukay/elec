package com.telek.elec.protocal.apdu.model.selector;

import org.apache.commons.lang3.text.StrBuilder;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.model.MS;
import com.telek.elec.protocal.data.model.date.DateTimeS;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

@Data
public class Selector5 extends Selector {

    /**
     * 采集存储时间 date_time_s
     */
    private DateTimeS dateTimeS;

    private MS ms;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StrBuilder sb = new StrBuilder();
        sb.append(dateTimeS.encode());
        sb.append(ms.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        DateTimeS dateTimeS = new DateTimeS();
        dateTimeS.decode(hexString);
        this.dateTimeS = dateTimeS;

        MS ms = new MS();
        ms.decode(hexString.substring(dateTimeS.getCharLength()));
        this.ms = ms;
        return dateTimeS.getCharLength() + ms.getCharLength();
    }

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return dateTimeS.getCharLength() + ms.getCharLength();
    }

}
