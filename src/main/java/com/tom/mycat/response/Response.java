package com.tom.mycat.response;

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
}
