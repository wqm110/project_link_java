package com.ruoyi.web.core.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import springfox.documentation.spi.service.contexts.SecurityContext;
import java.util.ArrayList;
import com.google.common.collect.Lists;
import java.util.List;


@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }
    @Bean
    public Docket productApi() {
        //schema
        List<GrantType> grantTypes=new ArrayList<>();
        TokenRequestEndpoint tokenRequestEndpoint=new TokenRequestEndpoint("http://localhost:30013/oauth/authorize","app1","123");
        TokenEndpoint tokenEndpoint=new TokenEndpoint("http://localhost:30013/oauth/token","access_token");
        AuthorizationCodeGrant authorizationCodeGrant=new AuthorizationCodeGrant(tokenRequestEndpoint,tokenEndpoint);
        grantTypes.add(authorizationCodeGrant);
        OAuth oAuth=new OAuthBuilder().name("oauth2")
                .grantTypes(grantTypes).build();
        //context
        //scope方位
        List<AuthorizationScope> scopes=new ArrayList<>();
        scopes.add(new AuthorizationScope("read","read  resources"));
        scopes.add(new AuthorizationScope("write","write resources"));
        scopes.add(new AuthorizationScope("reads","read all resources"));
        scopes.add(new AuthorizationScope("writes","write all resources"));

        SecurityReference securityReference=new SecurityReference("oauth2",scopes.toArray(new AuthorizationScope[]{}));
        SecurityContext securityContext=new SecurityContext(Lists.newArrayList(securityReference),PathSelectors.any());
        //schemas
        List<SecurityScheme> securitySchemes=Lists.newArrayList(oAuth);
        //securyContext
        List<SecurityContext> securityContexts=Lists.newArrayList(securityContext);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ruoyi"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts)
                .securitySchemes(securitySchemes)
                .apiInfo(apiInfo());


    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("project_link接口文档").description("")
                .termsOfServiceUrl("https://doc.xiaominfo.com")
                .contact(new Contact("Developers", "https://gitee.com/xiaoym/knife4j", ""))
                .license("Open Source")
                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();

    }

//    @Bean(value = "defaultApi2")
//    public Docket defaultApi2() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(true)
//                .apiInfo(new ApiInfoBuilder()
//                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
//                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
//                        .termsOfServiceUrl("http://www.xx.com/")
//                        .contact("xx@qq.com")
//                        .version("1.0")
//                        .build()
//                )
//
//                //分组名称
//                .groupName("2.X版本").groupName("1.x")
//                .select()
//                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.ruoyi"))
//                .paths(PathSelectors.any())
//
//                .build();
//    }
}