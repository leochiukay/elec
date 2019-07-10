package com.telek.elec.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.service.model.Document;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.DocumentOps;

/**
 * 档案配置
 * @Auther: wll
 * @Date: 2019/7/8 20:26
 * @Description:
 */
@RestController
@RequestMapping("/test/document")
public class DocumentController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/add")
    public Object addDocument(String address) {
        Document document = getDocument();

        CodecAPDU apdu = DocumentOps.addDocument(document);
        requestService.sendRequest(apdu, address);
        return "OK";
    }

    private Document getDocument() {
        Document document = new Document();
        document.setIndex(1);
        // 基本信息
        Document.BasicObject basicObject = new Document.BasicObject();
        basicObject.setPort(new Datas<>(new OAD(new OI(0xf220), 02, 00)));
        basicObject.setRateNum(0);
        basicObject.setUserType(0);
        document.setBasicObject(basicObject);

        // 扩展信息
        Document.ExtendedObject extendedObject = new Document.ExtendedObject();
        extendedObject.setAddress("000000000000");
        extendedObject.setPt(0);
        extendedObject.setCt(0);
        document.setExtendedObject(extendedObject);

        // 附属信息
        /*Document.AnnexObject annexObject = new Document.AnnexObject();
        List<Document.AnnexObject> annexObjects = new ArrayList<>();
        annexObjects.add(annexObject);
        document.setAnnexObject(annexObjects);*/
        return document;
    }

    @PostMapping("/addBatch")
    public Object addBatch(String address) {
        Document document = getDocument();
        List<Document> list = new ArrayList<>();
        list.add(document);
        CodecAPDU codecAPDU = DocumentOps.addBatchDocument(list);
        requestService.sendRequest(codecAPDU, address);
        return "OK";
    }

    @PostMapping("/clear")
    public Object delete(String address) {
        CodecAPDU apdu = DocumentOps.clear();
        requestService.sendRequest(apdu, address);
        return "OK";
    }
}
