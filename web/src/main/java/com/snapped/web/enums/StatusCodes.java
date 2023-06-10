package com.snapped.web.enums;

import lombok.Data;


public enum StatusCodes {

    Success("S100", "Success"),
    AuthError("S401", "Authentication Error"),
    InternalError("S500", "Internal Server Error");

    private final String code;
    private final String message;

    StatusCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
