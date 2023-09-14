package com.tom.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private int code;
    private String message;
    private T data;


    public static <T> Response<?> SUCCESS( T data) {
        Response<T> response = new Response<>();
        response.setCode(Details.SUCCESS.getCode());
        response.setMessage(Details.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }
    public static <T> Response<?> SUCCESS() {
        Response<T> response = new Response<>();
        response.setCode(Details.SUCCESS.getCode());
        response.setMessage(Details.SUCCESS.getMessage());
        return response;
    }
    public static <T> Response<?> FAIL() {
        Response<T> response = new Response<>();
        response.setCode(Details.FAIL.getCode());
        response.setMessage(Details.FAIL.getMessage());
        return response;
    }
    public static <T> Response<?> FAIL(Details details) {
        Response<T> response = new Response<>();
        response.setCode(details.getCode());
        response.setMessage(details.getMessage());
        return response;
    }
}
