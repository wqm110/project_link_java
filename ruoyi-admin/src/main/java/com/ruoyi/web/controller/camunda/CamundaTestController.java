package com.ruoyi.web.controller.camunda;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.spring.SpringConfigurationHelper;
import org.camunda.bpm.spring.boot.starter.property.Defaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "camunda测试接口")
public class CamundaTestController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/getServices")
    public AjaxResult testGetService() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        Defaults defaults = (Defaults) engine.getProcessEngineConfiguration();
        Map<Object, Object> beans = defaults.getBeans();
        Object taskService1 = beans.get("taskService");
        System.out.println("beans = " + beans);
        System.out.println("taskService1 = " + taskService1);
        return AjaxResult.success(objectObjectHashMap);

    }
}
