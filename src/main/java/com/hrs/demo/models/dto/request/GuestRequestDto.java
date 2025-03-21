package com.hrs.demo.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GuestRequestDto {
    @Schema(
            description = "Name of guest", example = "Nguyen Van A"
    )
    private String name;

    @Schema(
            description = "phone of guest", example = "0708821227"
    )
    private String phone;

    @Schema(
            description = "Date check out", example = "1/1/2025"
    )
    private LocalDateTime checkOutDate;
}
