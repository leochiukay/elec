package com.telek.elec.protocal.service.request;

import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.Unsigned;

public class Utils {

    /**
     * 自动控制时段
     * @return
     */
    public static Datas<Array> autoControlPeriod(short beginHour, short beginMinute,
                                                 short endHour, short endMinute) {
        Structure structure = new Structure();
        structure.addData(new Unsigned(beginHour));
        structure.addData(new Unsigned(beginMinute));
        structure.addData(new Unsigned(endHour));
        structure.addData(new Unsigned(endMinute));

        Datas<Array> datas = new Datas<>(new Array());
        Array array = datas.getData();
        array.addData(structure);
        return datas;
    }

}
