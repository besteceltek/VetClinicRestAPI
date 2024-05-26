package com.beste.veterinary.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result {
    private int statusCode;
    private String statusMessage;
}
