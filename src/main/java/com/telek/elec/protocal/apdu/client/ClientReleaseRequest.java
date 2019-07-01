package com.telek.elec.protocal.apdu.client;

import com.telek.elec.protocal.constant.ProtocalSequence;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端断开链接请求
 */
@Data
@Slf4j
public class ClientReleaseRequest extends Client {

    public ClientReleaseRequest() {
        this.id = ProtocalSequence.RELEASE_REQUEST;
    }

    @Override
    public String assembleByteStr() {
        return assembleSpecial();
    }

    @Override
    protected String assembleSpecial() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.id), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), 2));

        log.info(this.getClass().getSimpleName() + "--客户端应用层断开连接APDU--" + sb.toString());

        return sb.toString();
    }
}
