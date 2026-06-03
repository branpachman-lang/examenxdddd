package com.lprog.examenlpseg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private Map<String, String> fieldErrors;

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, null);
    }

    public static <T> ApiResponse<T> validationError(String message, Map<String, String> fieldErrors) {
        return new ApiResponse<>(false, message, null, fieldErrors);
    }

    public boolean isSuccess() {
        return success;
    }
}
