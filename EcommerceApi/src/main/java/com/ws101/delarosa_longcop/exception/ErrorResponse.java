package com.ws101.delarosa_longcop.exception;

public class ErrorResponse {
    private int statusCode;
    private String error;
    private String message;
    private String timestamp;

    public ErrorResponse(int statusCode, String error, String message, String timestamp) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}