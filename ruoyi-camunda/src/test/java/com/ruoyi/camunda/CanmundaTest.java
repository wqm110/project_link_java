package com.ruoyi.camunda;


import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;


public class CanmundaTest {
    public static void main(String[] args) {

        ProcessEngineConfiguration configuration1 = ProcessEngines.getDefaultProcessEngine().getProcessEngineConfiguration();
        ProcessEngine engine = configuration1.buildProcessEngine();
        String name = engine.getName();
        AuthorizationService authorizationService = engine.getAuthorizationService();
        ProcessEngineConfiguration configuration = engine.getProcessEngineConfiguration();

    }
}