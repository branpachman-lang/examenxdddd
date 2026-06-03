package com.lprog.examenlpseg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    public static ApiResponse ok(String message, Object data) {
        return new ApiResponse(true, message, data);
    }
    public static ApiResponse error(String message) {
        return new ApiResponse(false, message, null);
    }
    public boolean isSuccess() {
        return success;
    }
}
