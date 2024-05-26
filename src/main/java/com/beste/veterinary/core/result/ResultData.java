package com.beste.veterinary.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    T data;

    public ResultData(int statusCode, String statusMessage, T data) {
        super(statusCode, statusMessage);
        this.data = data;
    }
}
