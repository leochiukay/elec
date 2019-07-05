package com.telek.elec.protocal.service.response;

import com.telek.elec.cache.TempCache;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.connection.ConnectionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionService implements IResponseService {

    private Packet packet;
    private CodecAPDU apdu;

    @Override
    public void dealAndResponse() {
        ConnectionResponse connectionResponse = (ConnectionResponse) apdu;
        TempCache.connectionInfo.put(packet.getSa().getAddress(), connectionResponse);
    }
}
