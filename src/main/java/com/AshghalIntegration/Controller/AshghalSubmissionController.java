package com.AshghalIntegration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import com.AshghalIntegration.Response.ResponseMessage;
import com.AshghalIntegration.Service.AshghalSubmissionService;

@RestController
public class AshghalSubmissionController {
	
	@Autowired
	private AshghalSubmissionService ashghalSubmissionService;
	
	@PostMapping("/notify")
	public ResponseEntity<ResponseMessage> getNotification(@RequestBody AshghalSubmissions ashghalSubmissions)
	{
		String message="";
		try {
			ashghalSubmissionService.saveSubmission(ashghalSubmissions);
			message = "There is a new Submission transfered";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Error Occurred";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
		
	}
}
