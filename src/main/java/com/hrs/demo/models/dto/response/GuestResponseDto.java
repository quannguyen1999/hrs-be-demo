package com.hrs.demo.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GuestResponseDto {

    @Schema(
            description = "Id of guest", example = "9e713850-de02-47e8-8556-2cce75955b33"
    )
    private UUID id;

    @Schema(
            description = "Name of guest", example = "Nguyen Van A"
    )
    private String name;

    @Schema(
            description = "phone of guest", example = "0708821227"
    )
    private String phone;

    @Schema(
            description = "Is this guest check in", example = "True/False"
    )
    private boolean checkedIn;

    @Schema(
            description = "Date check in", example = "1/1/2025"
    )
    private LocalDateTime checkInDate;

    @Schema(
            description = "Date check out", example = "2/1/2025"
    )
    private LocalDateTime checkOutDate;

}
