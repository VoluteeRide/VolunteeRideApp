package com.volunteeride.rest.exceptionmapper;

/**
 * This class represents the wrapper for the failure message returned as a JSON response.
 *
 * Created by ayazlakdawala on 9/26/15.
 */
public class ErrorMessage {

    private String errorMessage;
    private String errorCause;
    private String errorResolution;
    private String errorCode;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, String errorCause, String errorResolution, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCause = errorCause;
        this.errorResolution = errorResolution;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(String errorCause) {
        this.errorCause = errorCause;
    }

    public String getErrorResolution() {
        return errorResolution;
    }

    public void setErrorResolution(String errorResolution) {
        this.errorResolution = errorResolution;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
