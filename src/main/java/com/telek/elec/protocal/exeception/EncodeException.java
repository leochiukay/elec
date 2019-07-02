package com.telek.elec.protocal.exeception;

import lombok.Data;

/**
 * 解码异常
 */
@Data
public class EncodeException extends Exception {

    private int code;

    public EncodeException() {
    }

    public EncodeException(String message) {
        super(message);
    }

    public EncodeException(String message, int code) {
        super(message);
        this.code = code;
    }
}
