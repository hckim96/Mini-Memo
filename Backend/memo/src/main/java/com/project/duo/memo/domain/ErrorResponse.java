package com.project.duo.memo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ErrorResponse {

    private Map<String, Object> error;

    public ErrorResponse(String cause) {
        error = new LinkedHashMap<>();
        error.put("result", "error");
        error.put("cause", cause);
    }
}
