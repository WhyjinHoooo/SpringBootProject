package com.canesblack.spring_project1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	// @Configuration를 사용하고 @Bean을 반드시 입력해야함
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Canesblack API Documentation")
				.version("1.0")
				.description("카네스플랙 스프링 프로젝트 API 명세서")
				);
	}
	
}
