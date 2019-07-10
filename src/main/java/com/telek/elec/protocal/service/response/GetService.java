package com.telek.elec.protocal.service.response;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormalList;
import com.telek.elec.protocal.apdu.get.response.GetResponseNormal;
import com.telek.elec.protocal.apdu.get.response.GetResponseRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class GetService implements IResponseService {

    private Packet packet;
    private CodecAPDU apdu;


    @Override
    public void dealAndResponse() {
        if (apdu instanceof GetResponseNormal) {
            System.out.println("处理get response normal请求。。。");
        } else if (apdu instanceof GetRequestNormalList) {
            System.out.println("处理get response normal list请求。。。");
        } else if (apdu instanceof GetResponseRecord) {
            System.out.println("处理get response record请求。。。");
        }
    }
}
