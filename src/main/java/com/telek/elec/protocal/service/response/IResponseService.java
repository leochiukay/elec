package com.telek.elec.protocal.service.response;

import com.telek.elec.ProtocalSendHelper;
import com.telek.elec.util.SpringBeanContext;

/**
 * 处理服务机发来的请求并进行响应
 */
public interface IResponseService {

    ProtocalSendHelper sendHelper = SpringBeanContext.getBean(ProtocalSendHelper.class);

    /**
     * 处理请求并响应
     */
    void dealAndResponse();

}
