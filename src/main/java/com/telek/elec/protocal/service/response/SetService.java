package com.telek.elec.protocal.service.response;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.set.response.SetResponseNormal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetService implements IResponseService {

    private Packet packet;
    private CodecAPDU apdu;

    @Override
    public void dealAndResponse() {

        if (apdu instanceof SetResponseNormal) {
            System.out.println("处理set response normal请求。。。");
        }

    }
}
