package com.AshghalIntegration.Service;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import com.AshghalIntegration.FileManagement.Entity.ApiLogHistory;
import com.AshghalIntegration.FileManagement.Repository.ApiLogHistoryRepo;
import com.AshghalIntegration.FileManagement.Service.LogService;
import com.AshghalIntegration.Repository.AshghalSubmissionRepo;
import com.AshghalIntegration.Response.Constants.AIConstants;
import com.AshghalIntegration.Response.GenericResponse;
import com.AshghalIntegration.Response.Utils.AIUtils;
import com.AshghalIntegration.Uitls.ApiConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class AshghalSubmissionService {

    @Autowired
    private AshghalSubmissionRepo ashghalSubmissionRepo;
	@Autowired
	private AIUtils aiUtils;
	@Autowired
	private LogService logService;
	@Autowired
	private ApiLogHistoryRepo apiLogHistoryRepo;
	@Autowired
	private ApiLogHistory apiLogHistory;

	@Autowired
	private ObjectMapper objectMapper;

    
    public GenericResponse saveSubmission(AshghalSubmissions ashghalSubmission) {

		apiLogHistory.setApiRefId(ApiConstants.SepmNotifyApiRefId);
		apiLogHistory.setRequestTimeStamp(new Date());
		String logFile = logService.getLogFileAbsolutePath(apiLogHistory.getApiRefId());
		try{
			logService.writeToFile(logFile,"<---------- New Submission notification Arrived ---------->",LogLevel.INFO);
			logService.writeToFile(logFile,"Request : "+objectMapper.writeValueAsString(ashghalSubmission),LogLevel.INFO);
		}
		catch (Exception e)
		{
				e.printStackTrace();
		}
		try{
			var submissionIdCheckResult =	aiUtils.nullAndEmptyCheck("Submission Id",ashghalSubmission.getSubmissionId());
			if(!submissionIdCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			{
				logService.writeToFile(logFile,"Empty/null Value For SubmissionID",LogLevel.INFO);
				return submissionIdCheckResult;
			}


			Optional<AshghalSubmissions> submissionBySubmissionId = ashghalSubmissionRepo.findBySubmissionIdIgnoreCase(ashghalSubmission.getSubmissionId());
			if (submissionBySubmissionId.isPresent()) {
				logService.writeToFile(logFile,"Duplicate Value For SubmissionID",LogLevel.INFO);
				return new GenericResponse(
						aiUtils.sepmResponseFailure(
								"Submission Id: " + ashghalSubmission.getSubmissionId() + " already exists."));
			}

			var projectIdCheckResult =	aiUtils.nullAndEmptyCheck("Project Id",ashghalSubmission.getProjectId());
			if(!projectIdCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			{
				logService.writeToFile(logFile,"Empty/null value for ProjectId",LogLevel.INFO);
				return projectIdCheckResult;
			}


			var contractIdCheckResult =	aiUtils.nullAndEmptyCheck("Contract Id",ashghalSubmission.getContractId());
			if(!contractIdCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			{
				logService.writeToFile(logFile,"Empty/null value for ContractId", LogLevel.INFO);
				return contractIdCheckResult;
			}


			var submissionFPCheckResult = aiUtils.nullAndEmptyCheck("Submission Folder Path",ashghalSubmission.getSubmissionFolderPath());
			if(!submissionFPCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			{
				logService.writeToFile(logFile,"Empty/null value for Submission Folder Path", LogLevel.INFO);
				return submissionFPCheckResult;
			}


			var manifestFPCheckResult = aiUtils.nullAndEmptyCheck("ManifestFile Path",ashghalSubmission.getManifestFilePath());
			if(!manifestFPCheckResult.getResponse().getStatus().equals(AIConstants.STATUS_SUCCESS))
			{
				logService.writeToFile(logFile,"Empty/null value for Manifest File Path",LogLevel.INFO);
				return  manifestFPCheckResult;
			}

			var savedSubmission	= ashghalSubmissionRepo.save(ashghalSubmission);
			logService.writeToFile(logFile,"Notification Received Successfully", LogLevel.INFO);

		}
		catch (Exception e)
		{
			logService.writeToFile(logFile,"Error: "+e.getMessage(), LogLevel.INFO);
			e.printStackTrace();
		}
		finally {
			apiLogHistory.setResponseTimeStamp(new Date());
			logService.saveLogFile(logFile, apiLogHistory);
		}

		return aiUtils.genericResponseSuccess("Notification Received successfully: "+ashghalSubmission.getSubmissionId());

    }


}
