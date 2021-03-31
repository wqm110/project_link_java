package com.ruoyi.web.core.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Value("${swagger.host}")
    private String host;

    private static final String SPLITOR = ",";


    @Autowired
    public Knife4jConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket productApi() {


        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ruoyi"))
                .paths(PathSelectors.any())
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes())
                .apiInfo(apiInfo());
    }

    /**
     * 重写basePackage方法，让swagger支持多个包扫描
     *
     * @param basePackage
     * @return
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
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

    /**
     * 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用认证参数的接口），
     * 如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，所有包含"auth"的接口不需要使用securitySchemes。
     * 即不需要使用上文中设置的名为“Authorization”，type为“header”的认证参数。
     * 通俗讲，就是能匹配上的就使用默认认证，就不使用header里面的Authorization认证参数
     */
//————————————————
//    版权声明：本文为CSDN博主「masterJP」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_23035335/article/details/102772428
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return securityContexts;
    }

    /**
     * 设置完成后进入SwaggerUI，右上角出现“Authorization”按钮，点击即可输入我们配置的认证参数。
     * 对于不需要输入参数的接口（上文所述的包含auth的接口），在未输入Authorization参数就可以访问。
     * 其他接口则将返回401错误。点击右上角“Authorization”按钮，输入配置的参数后即可通过认证访问。参数输入后全局有效，无需每个接口单独输入。
     * 通过Swagger2的securitySchemes配置全局参数：如下列代码所示，securitySchemes的ApiKey中增加一个名为“Authorization”，type为“header”的参数。
     *
     * @return
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {authorizationScope};
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

}