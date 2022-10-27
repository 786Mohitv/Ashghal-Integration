package com.AshghalIntegration.Service;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import com.AshghalIntegration.Repository.AshghalSubmissionRepo;
import com.AshghalIntegration.Response.Constants.AIConstants;
import com.AshghalIntegration.Response.GenericResponse;
import com.AshghalIntegration.Response.Utils.AIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import java.util.Optional;

@Service
public class AshghalSubmissionService {

    @Autowired
    private AshghalSubmissionRepo ashghalSubmissionRepo;
	@Autowired
	private AIUtils aiUtils;

    
    public GenericResponse saveSubmission(AshghalSubmissions ashghalSubmission) {

		var submissionIdCheckResult =	aiUtils.nullAndEmptyCheck("Submission Id",ashghalSubmission.getSubmissionId());
		if(!submissionIdCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			return submissionIdCheckResult;

		Optional<AshghalSubmissions> submissionBySubmissionId = ashghalSubmissionRepo.findBySubmissionIdIgnoreCase(ashghalSubmission.getSubmissionId());
		if (submissionBySubmissionId.isPresent()) {
			return new GenericResponse(
					aiUtils.sepmResponseFailure(
							"Submission Id: " + ashghalSubmission.getSubmissionId() + " already exists."));
		}

		var projectIdCheckResult =	aiUtils.nullAndEmptyCheck("Project Id",ashghalSubmission.getProjectId());
		if(!projectIdCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			return projectIdCheckResult;

		var contractIdCheckResult =	aiUtils.nullAndEmptyCheck("Contract Id",ashghalSubmission.getContractId());
		if(!contractIdCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			return contractIdCheckResult;

		var submissionFPCheckResult = aiUtils.nullAndEmptyCheck("Submission Folder Path",ashghalSubmission.getSubmissionFolderPath());
		if(!submissionFPCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			return submissionFPCheckResult;

		var manifestFPCheckResult = aiUtils.nullAndEmptyCheck("ManifestFile Path",ashghalSubmission.getManifestFilePath());
		if(!manifestFPCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			return  manifestFPCheckResult;



    	var savedSubmission	= ashghalSubmissionRepo.save(ashghalSubmission);
		return aiUtils.genericResponseSuccess("Submission Saved successfully: "+ashghalSubmission.getSubmissionId());

    }


}
