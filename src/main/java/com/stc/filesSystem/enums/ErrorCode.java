package com.stc.filesSystem.enums;

public enum ErrorCode {


    // DATABASE CAUSE ERRORS
    DUPLICATE_RECORD(30001, "DUPLICATE_RECORD"),
    BAD_REQUEST(40001, "BAD_REQUEST"),
    NOT_FOUND(40002, "NOT_FOUND"),
    UNIQUE(40003, "UNIQUE"),
    REQUIRED(40004, "REQUIRED"),
    DATA_NOT_VALID(410000, "DATA_NOT_VALID"),
    MISSING_DATA(40022, "MISSING_DATA"),
    MAX_UPLOAD_SIZE_EXCEEDED(40151, "Maximum upload size exceeded"),
    INTERNAL_SERVER_ERROR(50000, null),
    UNAUTHORIZED(50001, "UNAUTHORIZED,User does not have enough privileges for this action");

    private final int value;
    private final String message;

    ErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
    }


    public int getValue() {
        return this.value;
    }

    public String getMessage() {
        return message;
    }
}
