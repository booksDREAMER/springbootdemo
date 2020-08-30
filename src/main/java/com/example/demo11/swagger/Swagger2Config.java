package com.example.demo11.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Tag;
 
 /**
  * @Author:  Eyes of dreams
  * @Date: 2020/8/26 17:15
  * @Description: http://localhost:8080/swagger-ui.html
  **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
 
 
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo11.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springBoot--基础数据API说明文档")
                .description("2020.08.20版本")
                //.termsOfServiceUrl("http://mindao.com.cn")
                .contact("刘鹏飞")
                .version("0.0.1")
                .build();
    }
}