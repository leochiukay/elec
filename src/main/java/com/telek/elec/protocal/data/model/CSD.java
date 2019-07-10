package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import com.telek.elec.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2019/7/8 21:11
 * @Description:
 */
@Data
@Slf4j
public class CSD extends AbsData {

    private OAD oad;

    private ROAD road;

    public CSD() {
        this.dataType = DataType.CSD;
    }

    public CSD(OAD oad) {
        this();
        this.oad = oad;
    }

    public CSD(ROAD road) {
        this();
        this.road = road;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        if (oad != null) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(0), 2));
            sb.append(oad.encode());
        } else if (road != null) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(1), 2));
            sb.append(road.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int charLength = 0;
        int choice = Integer.parseInt(hexString.substring(0, 2), 16);
        charLength += 2;
        if (choice == 0) {
            OAD oad = new OAD();
            oad.decode(hexString.substring(2));
            this.oad = oad;
            charLength += oad.getCharLength();
        } else if (choice == 1) {
            ROAD road = new ROAD();
            road.decode(hexString.substring(2));
            this.road = road;
            charLength += road.getCharLength();
        } else {
            log.error("CSD解码出错，choice={}", choice);
            throw new DecodeException();
        }
        return charLength;
    }
}
