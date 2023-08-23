package com.tom.mycat.response;

public enum Details {

    SUCCESS(200, "SUCCESS"),
    FAIL(400 , "Fail");

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
