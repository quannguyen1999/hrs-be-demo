package com.hrs.demo.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class ParcelRequestDto {
    @Schema(
            description = "Id of parcel", example = "9e713850-de02-47e8-8556-2cce75955b33"
    )
    private UUID id;

    @Schema(
            description = "Id of guest", example = "9e713850-de02-47e8-8556-2cce75955b33"
    )
    private UUID guestId;

    @Schema(
            description = "Date received", example = "2/1/2025"
    )
    private LocalDateTime receivedAt;

    @Schema(
            description = "Is picked up", example = "True/False"
    )
    private boolean pickedUp;
}
