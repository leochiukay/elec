package com.telek.elec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wll
 * @Date: 2019/7/7 22:41
 * @Description:
 */
@RestController
@RequestMapping("/test/mbus")
public class MbusController {
    @GetMapping("/rp")
    public Object readProperties() {
        return null;
    }
}
