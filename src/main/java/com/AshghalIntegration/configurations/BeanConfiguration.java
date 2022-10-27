package com.AshghalIntegration.configurations;

import com.AshghalIntegration.Response.Utils.AIUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AshghalIntegration.Entity.AshghalSubmissions;

@Configuration
public class BeanConfiguration {

	@Bean
	public AshghalSubmissions newAshghalSubmissionInstance() {
		return new AshghalSubmissions();
	}
	@Bean
	public AIUtils newAIUtilsInstance(){
		return new AIUtils();
	}
}
