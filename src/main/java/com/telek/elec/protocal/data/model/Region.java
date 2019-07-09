package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:48
 * @Description:
 */
@Data
public class Region extends AbsData {
    private Unit unit;

    private AbsData start;

    private AbsData end;

    public Region() {
        this.dataType = DataType.REGION;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.toHexString(unit.getCode()));
        sb.append(new Datas(start).encode());
        sb.append(new Datas(end).encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }

    @Getter
    @AllArgsConstructor
    public enum Unit {
        CLOSE_OPEN(0, "前闭后开"),
        OPEN_CLOSE(1, "前开后闭"),
        CLOSE_CLOSE(2, "前闭后闭"),
        OPEN_OPEN(3, "前开后开");

        private int code;
        private String msg;
    }
}
