package com.dwes.api.configuracion;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi publicApiGeneral() {
        return GroupedOpenApi.builder()
            .group("general-public")
            .pathsToMatch("/api/v1/jabones/**", "/api/v1/categorias/**")
            .build();
    }
    @Bean
    public GroupedOpenApi publicApiJabones() {
        return GroupedOpenApi.builder()
            .group("jabones-public")
            .pathsToMatch("/api/v1/jabones/**")
            .build();
    }
    @Bean
    public GroupedOpenApi publicApiCategorias() {
        return GroupedOpenApi.builder()
            .group("categorias-public")
            .pathsToMatch("/api/v1/categorias/**")
            .build();
    }
    
/*
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("springshop-admin")
                .pathsToMatch("/admin/**")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
                .build();
    }
    */

}