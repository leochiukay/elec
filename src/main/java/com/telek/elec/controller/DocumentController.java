package com.telek.elec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.CodecAPDU;
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
        Document document = new Document();
        document.setIndex(12);
        CodecAPDU apdu = DocumentOps.addDocument(document);
        requestService.sendRequest(apdu, address);
        return "OK";
    }

    @PostMapping("/addBatch")
    public Object addBatch() {
        Document document = new Document();
        document.setIndex(12);
        Document.BasicObject basicObject = new Document.BasicObject();
        DocumentOps.addDocument(document);
        return "OK";
    }

    @PostMapping("/clear")
    public Object delete(String address) {
        CodecAPDU apdu = DocumentOps.clear();
        requestService.sendRequest(apdu, address);
        return "OK";
    }
}
