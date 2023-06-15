package com.example.goaltrackingspringboot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private int status;
    private Object data;
    private String message;
}
