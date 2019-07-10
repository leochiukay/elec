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

    public Selector5() {
        this.id = 5;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StrBuilder sb = new StrBuilder();
        sb.append(dateTimeS.encode());
        sb.append(ms.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        DateTimeS dateTimeS = new DateTimeS();
        index += dateTimeS.decode(hexString.substring(index));
        this.dateTimeS = dateTimeS;
        MS ms = new MS();
        index += ms.decode(hexString.substring(index));
        this.ms = ms;
        return index;
    }

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return dateTimeS.getCharLength() + ms.getCharLength();
    }

}
