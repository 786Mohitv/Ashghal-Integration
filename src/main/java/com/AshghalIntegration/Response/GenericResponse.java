package com.AshghalIntegration.Response;


public class GenericResponse {
    public GenericResponse(SepmResponse response) {
        this.response = response;
    }

    private SepmResponse response;

    public SepmResponse getResponse() {
        return response;
    }

    public void setResponse(SepmResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "response=" + response +
                '}';
    }
}
