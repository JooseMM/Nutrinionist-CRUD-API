package com.example.nutrionist_api.utils;

import java.util.List;

public class Response<T> {
    private int status;
    private String message;
    private T data;
    private List<String> errors;

    public Response(int status, String message, T data, List<String> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }

}