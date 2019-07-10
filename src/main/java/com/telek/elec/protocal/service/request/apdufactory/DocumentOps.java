package com.telek.elec.protocal.service.request.apdufactory;

import java.util.List;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.DocumentFactory;
import com.telek.elec.protocal.apdu.model.action.ActionRequestData;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.*;
import com.telek.elec.protocal.data.service.model.Document;

/**
 * 档案
 */
public class DocumentOps {
    /**
     * @Description: 添加一个采集档案配置单元
     * @auther: wll
     * @date: 20:38 2019/7/8
     * @param: []
     * @return: com.telek.elec.protocal.apdu.CodecAPDU
     */
    public static CodecAPDU addDocument(Document document) {
        ActionRequestNormal actionRequest = new ActionRequestNormal();
        actionRequest.setActionRequestData(new ActionRequestData(DocumentFactory.addDocument(), new Datas(document)));
        actionRequest.setTimeStamp(0);
        return actionRequest;
    }

    /**
     * @Description: 添加多个采集档案配置单元
     * @auther: wll
     * @date: 20:29 2019/7/9
     * @param: [documents]
     * @return: com.telek.elec.protocal.apdu.CodecAPDU
     */
    public static CodecAPDU addBatchDocument(List<Document> documents) {
        ActionRequestNormal actionRequest = new ActionRequestNormal();
        Array array = new Array();
        for (Document document : documents) {
            array.addData(document);
        }
        actionRequest.setActionRequestData(new ActionRequestData(DocumentFactory.addBatchDocuments(), new Datas(array)));
        return actionRequest;
    }

    public static CodecAPDU clear() {
        ActionRequestNormal actionRequest = new ActionRequestNormal();
        actionRequest.setActionRequestData(new ActionRequestData(DocumentFactory.clear(), new Datas(new Null())));
        return actionRequest;
    }
}
