package com.telek.elec.protocal.service.response;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.action.response.ActionResponseNormal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyService implements IResponseService {

    private Packet packet;
    private CodecAPDU apdu;

    @Override
    public void dealAndResponse() {

        if (apdu instanceof ActionResponseNormal) {
            System.out.println("处理proxy response normal请求。。。");
        }

    }
}
