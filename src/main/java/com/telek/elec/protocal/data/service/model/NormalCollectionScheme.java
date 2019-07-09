package com.telek.elec.protocal.data.service.model;

import com.telek.elec.protocal.data.model.*;

import java.util.List;

/**
 * @Auther: wll
 * @Date: 2019/7/9 20:40
 * @Description: 普通采集方案
 */
public class NormalCollectionScheme extends Structure {
    private int index;
    private int deep;
    private CollectWay way;
    private List<CSD> recordList;
    private MS ms;

    public static class CollectWay {
        private int type;
        private AbsData data;
    }

    public static class RetryMetering {
        private TI ti;
        private int retry;
    }


}
