package com.lzh.conf.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@EnableAutoConfiguration
public class SwaggerConfig {


    @Bean  
    public Docket createRestApi() {
    	List<Parameter> parameterList = Lists.newArrayList();
        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();
        tokenParameterBuilder.name("X-Authorization").description("Token parameter").
                modelRef(new ModelRef("String")).parameterType("header").required(true);
        parameterList.add(tokenParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2) 
                .useDefaultResponseMessages(false)
                .globalOperationParameters(parameterList)
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.basePackage("com.lzh.controller"))  
                .paths(PathSelectors.any())  
                .build();  
    }  
  
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("这是一个测试项目，用于新架构的示范")  
                .termsOfServiceUrl("http://106.14.113.68:8080/index.html")  
                .contact(new Contact("lizehao", "", "641801732@qq.com"))
                .version("1.0")  
                .build();  
    }  
    
    @Bean
    UiConfiguration uiConfig() {
      return new UiConfiguration(
          null,// url
          "list",       // docExpansion          => none | list
          "alpha",      // apiSorter             => alpha
          "schema",     // defaultModelRendering => schema
          UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
          false,        // enableJsonEditor      => true | false
          true,null);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }
}
