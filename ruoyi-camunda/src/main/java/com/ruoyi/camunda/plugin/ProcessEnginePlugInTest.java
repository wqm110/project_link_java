package com.ruoyi.camunda.plugin;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProcessEnginePlugInTest implements ProcessEnginePlugin {
    @Override
    //获取配置类
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
