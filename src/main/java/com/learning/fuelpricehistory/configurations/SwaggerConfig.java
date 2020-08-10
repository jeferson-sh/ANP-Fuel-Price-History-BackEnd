package com.learning.fuelpricehistory.configurations;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import({ BeanValidatorPluginsConfiguration.class, SpringDataRestConfiguration.class })
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.learning.fuelpricehistory.controllers"))
                .paths(PathSelectors.any()).build().apiInfo(getInfo());
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Preço de Combustíveis",
                "API desenvolvida com Spring Boot para consumir e gerenciar dados sobre preços de combustíveis disponibilizados pela Agência Nacional do Petróleo",
                "1.0", "",
                new Contact("Jéferson Jose da Silva", "https://gitlab.com/JefersonSilva", "jeferson.silva@dcx.ufpb.br"),
                "", "", Collections.emptyList());
    }
}
