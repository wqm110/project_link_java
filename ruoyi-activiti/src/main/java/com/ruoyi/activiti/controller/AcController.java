package com.ruoyi.activiti.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ac")
@Api(tags = "activiti 控制器测试")
public class AcController {
    @GetMapping("/test")
    AjaxResult test() {
        System.out.println("测试联通");
        return AjaxResult.success("测试联通", 200);
    }
}
