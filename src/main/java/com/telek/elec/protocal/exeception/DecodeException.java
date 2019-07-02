package com.telek.elec.protocal.exeception;

import lombok.Data;

/**
 * 编码异常
 */
@Data
public class DecodeException extends Exception {

    private int code;

    public DecodeException() {
    }

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(String message, int code) {
        super(message);
        this.code = code;
    }
}
