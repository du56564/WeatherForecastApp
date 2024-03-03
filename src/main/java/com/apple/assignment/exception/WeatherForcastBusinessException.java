package com.apple.assignment.exception;

public class WeatherForcastBusinessException extends Exception {

    private String errMessage;
    private String errCode;
    private String className;

    public WeatherForcastBusinessException(String message) {
        this.errMessage = message;
    }

    public WeatherForcastBusinessException(String errMsz, String className) {
        this(errMsz);
        this.className = className;
    }

    public WeatherForcastBusinessException(String errMessage, String errCode, String className) {
        this(errMessage, className);
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
