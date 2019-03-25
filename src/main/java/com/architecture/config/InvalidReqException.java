package com.architecture.config;

import lombok.Data;

@Data
public class InvalidReqException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

 
    public InvalidReqException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
 
    public InvalidReqException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}