package com.AshghalIntegration.Configurations;

import com.AshghalIntegration.FileManagement.Entity.ApiLogHistory;
import com.AshghalIntegration.Response.Utils.AIUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

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
	@Bean
	public ApiLogHistory newLogHistoryInstance(){
		return new ApiLogHistory();
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(64000);
		return loggingFilter;
	}
}
