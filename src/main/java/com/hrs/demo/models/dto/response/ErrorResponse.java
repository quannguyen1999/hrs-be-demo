package com.hrs.demo.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @Schema(
            description = "message error", example = "Username invalid"
    )
    public String message;

    @Schema(
            description = "List detail message error", example = "Username must have greater 20 characters"
    )
    public List<String> details;

}
