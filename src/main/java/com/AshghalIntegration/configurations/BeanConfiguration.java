package com.AshghalIntegration.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AshghalIntegration.Entity.AshghalSubmissions;

@Configuration
public class BeanConfiguration {

	@Bean
	public AshghalSubmissions newInstance() {
		return new AshghalSubmissions();
	}
}
