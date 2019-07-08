package com.telek.elec.protocal.service.response;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;

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
        /*if (apdu instanceof GetResponseNormal) {
            GetResponseNormal response = (GetResponseNormal) apdu;
            GetResultNormal resultNormal = response.getGetResultNormal();
            GetResult getResult = resultNormal.getGetResult();
            if (getResult.getGetResultType() == GetResultType.DATA) {
                AbsData data = getResult.getDataInfo().getData();
                // mbus
                if (resultNormal.getOad().equals(CommonOADFactory.mbus())) {
                    List<AbsBasicData> array = ((Array) data).getDatas();
                    for (AbsBasicData absBasicData : array) {
                        List<AbsBasicData> structure = ((Structure) absBasicData).getDatas();
                        String portDesc = ((OctString) structure.get(0)).getValue();

                    }
                }

            } else {
                log.error("通信出错,{}", getResult.getDar());
            }
        } else if (apdu instanceof GetRequestNormalList) {

        }*/
    }
}
