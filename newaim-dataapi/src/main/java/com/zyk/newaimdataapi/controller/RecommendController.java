package com.zyk.newaimdataapi.controller;

import com.zyk.newaimdataapi.entity.dto.RecommendData;
import com.zyk.newaimdataapi.entity.vo.result.Result;
import com.zyk.newaimdataapi.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("hotcatagory")
    public Result<List<RecommendData>> getHotCategory() {
        return Result.ok(recommendService.getHotCategory());
    }
}

