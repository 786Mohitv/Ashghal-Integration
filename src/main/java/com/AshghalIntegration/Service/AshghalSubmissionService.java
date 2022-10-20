package com.AshghalIntegration.Service;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import com.AshghalIntegration.Repository.AshghalSubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;

@Service
public class AshghalSubmissionService {

    @Autowired
    private AshghalSubmissionRepo ashghalSubmissionRepo;   
    
    public void saveSubmission(AshghalSubmissions ashghalSubmission) {
    	
    	try {
    		ashghalSubmissionRepo.save(ashghalSubmission);
		} catch (Exception e) {
			throw new RuntimeException("Error Occurred in saving to db " + e.getMessage());
		}
    	
    }


}
