package com.jsp.ets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//@Configuration
//@OpenAPIDefinition
public class Documentation {

	Info info() {
		return new Info().title("Edu Tracking System - RESTful API").version("v1").description(
				"### The Edu Tracking system is an online solution that helps track overall student performance and suggests job opportunities based on their results."
				+ " The platform allows trainers to rate students for each subject based on their performance."
				+ "The application then uses these ratings to automatically suggest suitable students for the  job requirements, built on REST Architecture Style");
	}

	@Bean
	OpenAPI openApi() {
		return new OpenAPI().info(info());
	}
}
