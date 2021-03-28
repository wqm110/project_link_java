package com.ruoyi.web.controller.camunda;

import com.ruoyi.camunda.plugin.ProcessEnginePlugInTest;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.spring.SpringConfigurationHelper;
import org.camunda.bpm.spring.boot.starter.property.Defaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "camunda测试接口")
public class CamundaTestController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/getServices/{pname}")
    public AjaxResult testGetService(@PathVariable("pname")String pname) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        ProcessEngine engine = ProcessEngines.getProcessEngine("default");
        ProcessEngineConfigurationImpl config = (ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration();
        config = config.setProcessEngineName("test");
        String name = engine.getName();
        AuthorizationService authorizationService = engine.getAuthorizationService();
        DecisionService decisionService = engine.getDecisionService();
        System.out.println("name = " + name+"\t");
        System.out.println("decisionService = " + decisionService);
        System.out.println("authorizationService = " + authorizationService);

        return AjaxResult.success(objectObjectHashMap);

    }
}
