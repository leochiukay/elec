package com.telek.elec.protocal.block;

import lombok.Data;

/**
 * @Auther: wll
 * @Date: 2019/7/4 12:31
 * @Description:
 */
@Data
public class Seq {
    private int seqNum;
    private boolean start;
    private boolean end;
    private byte[] data;
}
