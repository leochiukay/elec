package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum APDUType {

    LINK("link操作"),
    CONNECTION("connection操作"),
    RELEASE("release操作"),
    GET("get操作 "),
    SET("set操作"),
    ACTION("action操作"),
    REPORT("report操作"),
    PROXY("proxy操作");

    private String msg;

}
