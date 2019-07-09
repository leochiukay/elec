package com.telek.elec.protocal.data.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:15
 * @Description:
 */
@Slf4j
@Data
public class RCSD extends AbsData {

    private List<CSD> csds = new ArrayList<>();

    public RCSD() {
        this.dataType = DataType.RCSD;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.toHexString(csds.size()));
        for (CSD csd : csds) {
            sb.append(csd.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int charLength = 0;
        int size = Integer.parseInt(hexString.substring(0, 2), 16);
        charLength += 2;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                CSD csd = new CSD();
                csd.decode(hexString.substring(charLength));
                this.csds.add(csd);
                charLength += csd.getCharLength();
            }
        }
        return charLength;
    }
}
