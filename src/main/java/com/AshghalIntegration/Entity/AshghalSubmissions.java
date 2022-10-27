package com.AshghalIntegration.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ashghal_submissions")
public class AshghalSubmissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    private String projectId;
    @Column(nullable = false)
    private String contractId;
    @Column(nullable = false,unique = true)
    private String submissionId;
    @Column(nullable = false)
    private String submissionFolderPath;
    @Column(nullable = false)
    private String manifestFilePath;

    public AshghalSubmissions() {
    	super();
    }

    public AshghalSubmissions(String projectId, String contractId, String submissionId, String submissionFolderPath, String manifestFilePath) {
        this.projectId = projectId;
        this.contractId = contractId;
        this.submissionId = submissionId;
        this.submissionFolderPath = submissionFolderPath;
        this.manifestFilePath = manifestFilePath;
    }

    public Long getId() {
        return Id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getSubmissionFolderPath() {
        return submissionFolderPath;
    }

    public void setSubmissionFolderPath(String submissionFolderPath) {
        this.submissionFolderPath = submissionFolderPath;
    }

    public String getManifestFilePath() {
        return manifestFilePath;
    }

    public void setManifestFilePath(String manifestFilePath) {
        this.manifestFilePath = manifestFilePath;
    }

    @Override
    public String toString() {
        return "AshghalSubmissions{" +
                "projectId='" + projectId + '\'' +
                ", contractId='" + contractId + '\'' +
                ", submissionId='" + submissionId + '\'' +
                ", submissionFolderPath='" + submissionFolderPath + '\'' +
                ", manifestFilePath='" + manifestFilePath + '\'' +
                '}';
    }
}
