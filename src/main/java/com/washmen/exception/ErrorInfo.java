package com.washmen.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorInfo implements Serializable {
    String code;
    String message;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
