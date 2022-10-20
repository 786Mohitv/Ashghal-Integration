package com.AshghalIntegration.Repository;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AshghalSubmissionRepo extends CrudRepository<AshghalSubmissions,Long> {

//	void save(String projectId, String contractId, String submissionId, String submissionFolderPath,
//			String manifestFilePath);

}
