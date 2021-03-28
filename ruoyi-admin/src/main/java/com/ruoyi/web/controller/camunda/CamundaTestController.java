package com.ruoyi.web.controller.camunda;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Api(tags = "camunda测试接口")
public class CamundaTestController {


    @GetMapping("/getServices/{pname}")
    public AjaxResult testGetService(@PathVariable("pname")String pname) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        ProcessEngine engine = ProcessEngines.getProcessEngine("default");
//        ProcessEngineConfigurationImpl config = (ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration();
//        config = config.setProcessEngineName("test");
        String name = engine.getName();
        AuthorizationService authorizationService = engine.getAuthorizationService();
        DecisionService decisionService = engine.getDecisionService();
        System.out.println("name = " + name+"\t");
        System.out.println("decisionService = " + decisionService);
        System.out.println("authorizationService = " + authorizationService);

        return AjaxResult.success(objectObjectHashMap);

    }
}
