package com.telek.elec.protocal.apdu.model.selector;

import org.apache.commons.lang3.text.StrBuilder;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.model.MS;
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.model.date.DateTimeS;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

@Data
public class Selector7 extends Selector {

    private DateTimeS timeBegin;

    private DateTimeS timeEnd;

    private TI ti;

    private MS ms;

    public Selector7() {
        this.id = 7;
    }

    public Selector7(DateTimeS timeBegin, DateTimeS timeEnd, TI ti, MS ms) {
        this();
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.ti = ti;
        this.ms = ms;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StrBuilder sb = new StrBuilder();
        sb.append(timeBegin.encode());
        sb.append(timeEnd.encode());
        sb.append(ti.encode());
        sb.append(ms.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;

        DateTimeS begin = new DateTimeS();
        index += begin.decode(hexString.substring(index));
        this.timeBegin = begin;

        DateTimeS end = new DateTimeS();
        index += end.decode(hexString.substring(index));
        this.timeEnd = end;

        TI ti = new TI();
        index += ti.decode(hexString.substring(index));
        this.ti = ti;

        MS ms = new MS();
        index += ms.decode(hexString.substring(index));
        this.ms = ms;

        return index;
    }

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return timeBegin.getCharLength() + timeEnd.getCharLength() + ti.getCharLength() + ms.getCharLength();
    }

}
