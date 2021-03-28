package com.ruoyi.camunda.config;


import com.ruoyi.camunda.plugin.ProcessEnginePlugInTest;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class CamundaConfig {


    /**
     * spring 流程引擎配置，做如下操作：
     * 1. 关联数据源
     * 2. 关联事务管理器
     * 3. 配置启动时是否检查数据库
     * 4. 配置流程历史信息保留级别。
     *
     * @return spring 流程配置器
     */

//    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
        List<ProcessEnginePlugin> pluginList = new ArrayList<>();
//        //获取配置类
        pluginList.add(new ProcessEnginePlugInTest());
        StandaloneProcessEngineConfiguration config = new StandaloneProcessEngineConfiguration();
        config.setProcessEnginePlugins(pluginList);
        return config;
    }


}