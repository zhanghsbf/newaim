package com.zyk.newaimdataapi.controller;

import com.zyk.newaimdataapi.entity.dto.Contactor;
import com.zyk.newaimdataapi.entity.vo.result.Result;
import com.zyk.newaimdataapi.service.PIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pii")
public class PIIController {

    @Autowired
    private PIIService piiService;

    @GetMapping("contactor")
    public Result<Contactor> getContactor(@RequestParam("username") String username) {
        return Result.ok(piiService.getContactor(username));
    }
}

