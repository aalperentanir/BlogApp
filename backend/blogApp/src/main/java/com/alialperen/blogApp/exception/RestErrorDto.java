package com.alialperen.blogApp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data

public class RestErrorDto {
	
	
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy")
    private LocalDateTime timestamp;
    private String message;

    public RestErrorDto() {
        this.timestamp = LocalDateTime.now();
    }

    public RestErrorDto(HttpStatus status) {
        this();
        this.status = status;
    }
    
    public RestErrorDto(HttpStatus status, Throwable throwable) {
        this();
        this.status = status;
        this.message = "Unexpected Error";
    }
    
    public RestErrorDto(HttpStatus status, String message, Throwable throwable) {
        this();
        this.status = status;
        this.message = message;
    }
    
}

