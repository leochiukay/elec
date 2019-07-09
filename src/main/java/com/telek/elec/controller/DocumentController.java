package com.telek.elec.controller;

import com.telek.elec.protocal.data.service.model.Document;
import com.telek.elec.protocal.service.request.apdufactory.DocumentOps;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wll
 * @Date: 2019/7/8 20:26
 * @Description:
 */
@RestController
@RequestMapping("/test/document")
public class DocumentController {
    @PostMapping("/add")
    public Object addDocument() {
        Document document = new Document();
        document.setIndex(12);
        Document.BasicObject basicObject = new Document.BasicObject();
        DocumentOps.addDocument(document);
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

    @PostMapping("/delete")
    public Object delete() {
        return null;
    }
}
