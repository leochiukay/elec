package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.apdu.model.selector.Selector5;
import com.telek.elec.protocal.apdu.model.selector.Selector7;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

@Data
public class RSD extends AbsData {

    /**
     * 1字节， selector的id,0为null
     */
    private byte selectorId;

    private Selector selector;

    public RSD() {
        this.dataType = DataType.RSD;
    }

    public RSD(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public RSD(byte selectorId, Selector selector, boolean isEncodeDataType) {
        this(isEncodeDataType);
        this.selectorId = selectorId;
        this.selector = selector;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        if (selectorId == 0) {
            return 0;
        }
        return selector.getCharLength();
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        if (selector != null) {
            return "";
        }
        return selector.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        Selector selector = null;
        int len;
        switch (selectorId) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                selector = new Selector5();
                break;
            case 6:
                break;
            case 7:
                selector = new Selector7();
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
        }
        if (selector == null) {
            return 0;
        }
        return selector.decode(hexString);
    }
}
