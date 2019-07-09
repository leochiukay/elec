package com.telek.elec.protocal.data.model.basic.string;

import java.io.UnsupportedEncodingException;

public class UTF8String extends AbsString {

    public UTF8String(String value) {
        try {
            this.value = value.getBytes("utf-8");
            this.size = this.value.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
