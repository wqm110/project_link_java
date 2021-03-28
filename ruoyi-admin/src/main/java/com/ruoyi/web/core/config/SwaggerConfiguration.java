//package com.ruoyi.web.core.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//@Configuration
//@EnableSwagger2WebMvc
//public class SwaggerConfiguration {
//
////    /*引入Knife4j提供的扩展类*/
////   private final OpenApiExtensionResolver openApiExtensionResolver;openApiExtensionResolver
////
////    @Autowired
////    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
////        this.openApiExtensionResolver = openApiExtensionResolver;
////    }
//
//@Bean(value = "defaultApi2")
//    public Docket defaultApi2() {
//        String groupName="2.X版本";
//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .host("https://www.baidu.com")
//                .apiInfo(apiInfo())
//                .groupName(groupName)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.swagger.bootstrap.ui.demo.new2"))
//                .paths(PathSelectors.any())
//                .build()
//                //赋予插件体系
//                .extensions(openApiExtensionResolver.buildExtensions(groupName));
//        return docket;
//    }