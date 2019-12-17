package com.litse.dbservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.litse.dbservice"))
                .paths(PathSelectors.ant("/product/*"))
                .build()
                .apiInfo(apiInfo()).useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET,getArrayList());
    }

    private List<ResponseMessage> getArrayList(){

        return  Arrays.asList(new ResponseMessageBuilder().code(400).message("k roduct").build(), new ResponseMessageBuilder().code(404).message("Error Product").build());

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Shop Product REST API ",
                "Information about Shop Product ",
                "1.0.0",
                "Terms of service",
                new Contact("Murad Mammadli", "www.litse.com", "myeaddress@listse.com"),
                "License of API", "www.litse.com/license", Collections.emptyList());
    }
}
