package com.zyk.newaimdataapi.service;


import com.zyk.newaimdataapi.entity.dto.Contactor;

/**
 *  活动操作服务类
 */
public interface PIIService {

    /**
     * @return 查询结果
     */
    Contactor getContactor(String username);

}

