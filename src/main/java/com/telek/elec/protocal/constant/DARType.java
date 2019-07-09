package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * dar类型
 */
@AllArgsConstructor
@Getter
public enum DARType {

    成功(0),
    硬件失效(1),
    暂时失效(2),
    拒绝读写(3),
    对象未定义(4),
    对象接口类不符合(5),
    对象不存在(6),
    类型不匹配(7),
    越界(8),
    数据块不可用(9),
    分帧传输已取消(10),
    不处于分帧传输状态(11),
    块写取消(12),
    不存在块写状态(13),
    数据块序号无效(14),
    密码错(15),
    通信速率不能更改(16),
    年时区数超(17),
    日时段数超(18),
    费率数超(19),
    安全认证不匹配(20),
    重复充值(21),
    ESAM验证失败(22),
    安全认证失败(23),
    客户编号不匹配(24),
    充值次数错误(25),
    购电超囤积(26),
    地址异常(27),
    对称解密错误(28),
    非对称解密错误(29),
    签名错误(30),
    电能表挂起(31),
    时间标签无效(32),
    请求超时(33),
    ESAM的P1P2不正确(34),
    ESAM的LC错误(35),
    其它(255);

    private int code;

    public static DARType decode(int code) {
        for (DARType value : DARType.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return null;
    }
}
