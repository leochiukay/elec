package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

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

    private Datas<? extends AbsData> start;

    private Datas<? extends AbsData> end;

    public Region() {
        this.dataType = DataType.REGION;
    }

    public Region(Unit unit, Datas<? extends AbsData> start, Datas<? extends AbsData> end) {
        this.unit = unit;
        this.start = start;
        this.end = end;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(unit.getCode()), 2));
        sb.append(start.encode());
        sb.append(end.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.unit = Unit.decode(Integer.parseInt(hexString.substring(index, index += 2)));
        AbsData start = HexToDataConvertor.hexToData(hexString.substring(index));
        this.start = new Datas<>(start);
        index = index + start.getCharLength();
        AbsData end = HexToDataConvertor.hexToData(hexString.substring(index));
        this.end = new Datas<>(end);
        return 2 + start.getCharLength() + end.getCharLength();
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

        public static Unit decode(int code) {
            for (Unit value : values()) {
                if (value.getCode() == code) {
                    return value;
                }
            }
            return null;
        }
    }
}
