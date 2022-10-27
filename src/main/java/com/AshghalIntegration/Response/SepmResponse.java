package com.AshghalIntegration.Response;

public class SepmResponse {

    private String responseCode;
    private String responseMessage;
    private String Status;

    public SepmResponse(String responseCode, String responseMessage, String status) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        Status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getStatus() {
        return Status;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "SepmResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
