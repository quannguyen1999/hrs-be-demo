package com.hrs.demo.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ParcelResponseDto {


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
    private Date receivedAt;


    @Schema(
            description = "Is picked up", example = "True/False"
    )
    private boolean pickedUp;

}
