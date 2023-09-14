package com.tom.restaurant.response;

public enum Details {

    SUCCESS(200, "SUCCESS"),
    FAIL(400 , "Fail"),
    PASSWORD_NO_MATCH(411 , "Current Password Is Not Correct"),
    DATA_NOT_FOUND(410 , "DATA Not Found"),
    ROLE_NOT_FOUND(409 , "Role Not Found"),
    DATA_ALREADY_EXISTS(408 , "Data Already Exists");

    private final int code;
    private final String message;

    public  int getCode() {return code; }
    public String getMessage() {
        return message;
    }
    Details(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
