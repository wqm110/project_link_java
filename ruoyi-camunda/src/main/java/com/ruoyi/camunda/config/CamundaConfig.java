//package com.ruoyi.camunda.config;
//
//
//import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class CamundaConfig {
//
//
//    /**
//     * spring 流程引擎配置，做如下操作：
//     * 1. 关联数据源
//     * 2. 关联事务管理器
//     * 3. 配置启动时是否检查数据库
//     * 4. 配置流程历史信息保留级别。
//     *
//     * @param dataSource 数据源
//     * @return spring 流程配置器
//     */
//    @Bean
//    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource) {
//
//        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
//
//        config.setTransactionManager(new DataSourceTransactionManager(dataSource));
//        config.setJdbcDriver("com.mysql.jdbc.Driver");
////        config.setDatabaseSchemaUpdate("true");
//        config.setHistory("full");
////        config.setJobExecutorActivate(true);
//        config.setDatabaseType("mysql");
////
////        List<ProcessEnginePlugin> list = new ArrayList<>();
////        list.add(new ProcessEnginePlugInTest());
////        config.setProcessEnginePlugins(list);
//        return config;
//    }
//
//
//}