package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.apdu.model.selector.Selector1;
import com.telek.elec.protocal.apdu.model.selector.Selector10;
import com.telek.elec.protocal.apdu.model.selector.Selector2;
import com.telek.elec.protocal.apdu.model.selector.Selector3;
import com.telek.elec.protocal.apdu.model.selector.Selector4;
import com.telek.elec.protocal.apdu.model.selector.Selector5;
import com.telek.elec.protocal.apdu.model.selector.Selector6;
import com.telek.elec.protocal.apdu.model.selector.Selector7;
import com.telek.elec.protocal.apdu.model.selector.Selector8;
import com.telek.elec.protocal.apdu.model.selector.Selector9;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class RSD extends AbsData {

    private Selector selector;

    public RSD() {
        this.dataType = DataType.RSD;
    }

    public RSD(Selector selector) {
        this();
        this.selector = selector;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        if (selector == null) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(0), 2));
        } else {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(selector.getId()), 2));
            sb.append(selector.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        int id = Integer.parseInt(hexString.substring(index, index += 2), 16);
        switch (id) {
            case 0:
                return index;
            case 1:
                selector = new Selector1();
                break;
            case 2:
                selector = new Selector2();
                break;
            case 3:
                selector = new Selector3();
                break;
            case 4:
                selector = new Selector4();
                break;
            case 5:
                selector = new Selector5();
                break;
            case 6:
                selector = new Selector6();
                break;
            case 7:
                selector = new Selector7();
                break;
            case 8:
                selector = new Selector8();
                break;
            case 9:
                selector = new Selector9();
                break;
            case 10:
                selector = new Selector10();
                break;
        }
        index += selector.decode(hexString.substring(index));
        return index;
    }

}
