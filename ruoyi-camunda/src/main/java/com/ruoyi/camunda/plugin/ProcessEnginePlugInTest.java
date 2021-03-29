package com.ruoyi.camunda.plugin;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessEnginePlugInTest implements ProcessEnginePlugin {
    @Override
    //获取配置插件
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        System.out.println("\n\npreInit ..... . ... .. plugin..\thistoryLevel");
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        System.out.println("\n\npostInit ..... . ... ..plugin ..\n ");
    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {
        System.out.println("\n\npostProcessEngineBuild ..... . ... ..plugin ..\n ");

    }
}
