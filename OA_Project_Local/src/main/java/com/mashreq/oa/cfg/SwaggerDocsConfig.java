package com.mashreq.oa.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 
public class SwaggerDocsConfig {
	@Bean
	 public   Docket createDocket() {
		   return  new Docket(DocumentationType.SWAGGER_2)  //UI screen type
				              .select()  //to specify  RestControllers
				              .apis(RequestHandlerSelectors.basePackage("com.eidiko.niranjan.controller")) //base pkg for RestContrllers
				              .paths(PathSelectors.regex("/data.*")) // to specify request paths
				              .build(); // builds the Docket obj
				              //.useDefaultResponseMessages(true)
		
	}
}
//http://localhost:5050/swagger-ui/index.html