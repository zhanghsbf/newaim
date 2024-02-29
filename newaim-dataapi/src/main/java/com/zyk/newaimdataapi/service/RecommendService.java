package com.zyk.newaimdataapi.service;


import com.zyk.newaimdataapi.entity.dto.RecommendData;

import java.util.List;

/**
 *  活动操作服务类
 */
public interface RecommendService {

    /**
     * @return 查询结果
     */
    List<RecommendData> getHotCategory();

}

