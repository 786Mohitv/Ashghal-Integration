package com.AshghalIntegration.Controller;

import com.AshghalIntegration.Response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import com.AshghalIntegration.Response.SepmResponse;
import com.AshghalIntegration.Service.AshghalSubmissionService;

@RestController
public class AshghalSubmissionController {
	
	@Autowired
	private AshghalSubmissionService ashghalSubmissionService;
	
	@PostMapping("/notify")
	public GenericResponse getNotification(@RequestBody AshghalSubmissions ashghalSubmissions)
	{
			return ashghalSubmissionService.saveSubmission(ashghalSubmissions);
	}
}
