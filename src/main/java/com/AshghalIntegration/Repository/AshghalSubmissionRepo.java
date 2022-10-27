package com.AshghalIntegration.Repository;

import com.AshghalIntegration.Entity.AshghalSubmissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AshghalSubmissionRepo extends CrudRepository<AshghalSubmissions,Long> {

    Optional<AshghalSubmissions> findBySubmissionIdIgnoreCase(String name);

}
