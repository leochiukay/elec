package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataType {
    NULL(0),
    ARRAY(1),
    STRUCTURE(2),
    BOOL(3),
    BIT_STRING(4),
    DOUBLE_LONG(5),
    DOUBLE_LONG_UNSIGNED(6),
    OCTET_STRING(9),
    VISIBLE_STRING(10),
    UTF8_STRING(12),
    INTEGER(15),
    LONG(16),
    UNSIGNED(17),
    LONG_UNSIGNED(18),
    LONG64(20),
    LONG64_UNSIGNED(21),
    ENUM(22),
    FLOAT32(23),
    FLOAT64(24),
    DATE_TIME(25),
    DATE(26),
    TIME(27),
    DATE_TIME_S(28),
    OI(80),
    OAD(81),
    ROAD(82),
    OMD(83),
    TI(84),
    TSA(85),
    MAC(86),
    RN(87),
    REGION(88),
    SCALER_UNIT(89),
    RSD(90),
    CSD(91),
    MS(92),
    SID(93),
    SID_MAC(94),
    COMDCB(95),
    RCSD(96);

    private int code;

}
