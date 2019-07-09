package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:48
 * @Description:
 */
public class Region extends AbsData {
    private int unit;

    private AbsData start;

    private AbsData end;

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return 0;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return null;
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }
}
