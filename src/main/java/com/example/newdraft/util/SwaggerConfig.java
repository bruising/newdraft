package com.example.newdraft.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: springboot_swagger2
 * @Package com.example.springboot_swagger2.util
 * @Description:
 * @date 2020/1/6 星期一 12:04
 */
//让Spring来加载该类配置
@Configuration
//启用Swagger2
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.newdraft.controller"))// 设置basePackage会将包下的所有类的所有方法作为api
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  只有标记了@ApiOperation的方法才会暴露出给swagger
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("NewDraft项目说明文档")//标题
                .contact(new Contact("", "", ""))//作者 项目链接 邮箱
                .version("1.1")//版本
                .description("系统说明").build();

    }
}
